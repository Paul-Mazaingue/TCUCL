import {
  Component,
  OnInit,
  inject,
  ViewChild,
  ElementRef
} from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../../services/auth.service';
import { ApiEndpoints } from '../../../services/api-endpoints';
import { Pays } from '../../../models/enums/pays.enum';
import { SaveFooterComponent } from '../../save-footer/save-footer.component';
import { OngletStatusService } from '../../../services/onglet-status.service';
import { ONGLET_KEYS } from '../../../constants/onglet-keys';
import { MobInterOngletMapperService } from './mob-inter-onglet-mapper.service';
import {
  MobInternationalOngletModel,
  Voyage
} from '../../../models/mob-international.model';

@Component({
  selector: 'app-destination-page',
  standalone: true,
  templateUrl: './mob-inter-saisie-donnees-page.component.html',
  styleUrls: ['./mob-inter-saisie-donnees-page.component.scss'],
  imports: [FormsModule, HttpClientModule, CommonModule, SaveFooterComponent]
})
export class MobiliteInternationaleSaisieDonneesPageComponent implements OnInit {

  private http = inject(HttpClient);
  private route = inject(ActivatedRoute);
  private authService = inject(AuthService);
  private statusService = inject(OngletStatusService);
  private mapper = inject(MobInterOngletMapperService);

  @ViewChild('fileInput') fileInput!: ElementRef<HTMLInputElement>;

  onglet: MobInternationalOngletModel = { voyages: [] };
  resultats: any = null;

  selectedFile: File | null = null;
  uploadMode: 'APPEND' | 'REPLACE' = 'APPEND';

  nouveauVoyage: Voyage = {
    nomPays: '' as any,
    prosAvion: 0,
    prosTrain: 0,
    stagesEtudiantsAvion: 0,
    stagesEtudiantsTrain: 0,
    semestresEtudiantsAvion: 0,
    semestresEtudiantsTrain: 0
  };

  ONGLET_KEYS = ONGLET_KEYS;
  listePays = Object.values(Pays);

  ngOnInit(): void {
    this.onglet.estTermine = this.statusService.getStatus(ONGLET_KEYS.MobInternationale);

    this.statusService.statuses$.subscribe(s => {
      this.onglet.estTermine = s[ONGLET_KEYS.MobInternationale] ?? false;
    });

    this.route.paramMap.subscribe(params => {
      const id = params.get('id');
      if (id) {
        this.loadData(id);
        this.loadResultats(id);
      }
    });
  }

  loadData(id: string): void {
    const token = this.authService.getToken();
    if (!token) return;

    this.http.get<any>(
      ApiEndpoints.mobInternationaleOnglet.getById(id),
      { headers: { Authorization: `Bearer ${token}` } }
    ).subscribe({
      next: data => {
        const model = this.mapper.fromDto(data);
        this.onglet = model;
        this.statusService.setStatus(
          ONGLET_KEYS.MobInternationale,
          model.estTermine ?? false
        );
      },
      error: err => console.error('Erreur chargement données', err)
    });
  }

  ajouterVoyage(): void {
    this.onglet.voyages.push({ ...this.nouveauVoyage });
    this.resetVoyage();
    this.updateData();
  }

  supprimerVoyage(index: number): void {
    this.onglet.voyages.splice(index, 1);
    this.updateData();
  }

  onEstTermineChange(value: boolean): void {
    this.onglet.estTermine = value;
    this.updateData();
  }

  updateData(): void {
    const id = this.route.snapshot.paramMap.get('id');
    const token = this.authService.getToken();
    if (!id || !token) return;

    this.http.patch(
      ApiEndpoints.mobInternationaleOnglet.update(id),
      this.mapper.toDto(this.onglet),
      { headers: { Authorization: `Bearer ${token}` } }
    ).subscribe({
      next: () => this.loadResultats(id),
      error: err => console.error('PATCH échoué', err)
    });
  }

  loadResultats(id: string): void {
    const token = this.authService.getToken();
    if (!token) return;

    this.http.get(
      ApiEndpoints.mobInternationaleOnglet.resultats(id),
      { headers: { Authorization: `Bearer ${token}` } }
    ).subscribe({
      next: data => this.resultats = data,
      error: err => console.error('Erreur résultats', err)
    });
  }

  rafraichirDonnees(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (!id) return;

    this.loadData(id);
    this.loadResultats(id);
  }


  telechargerExcel(): void {
    const id = this.route.snapshot.paramMap.get('id');
    const token = this.authService.getToken();
    if (!id || !token) return;
  
    this.http.get(
      ApiEndpoints.mobInternationaleOnglet.export(id),
      {
        headers: {
          Authorization: `Bearer ${token}`
        },
        responseType: 'blob'
      }
    ).subscribe({
      next: blob => {
        const url = window.URL.createObjectURL(blob);
        const a = document.createElement('a');
  
        a.href = url;
        a.download = 'mobilite-internationale.xlsx';
        a.click();
  
        window.URL.revokeObjectURL(url);
      },
      error: err => {
        console.error('Erreur téléchargement Excel', err);
        alert('Échec du téléchargement');
      }
    });
  }
  
  /* ======================
     FILE IMPORT
     ====================== */

  triggerFileInput(mode: 'APPEND' | 'REPLACE'): void {
    this.uploadMode = mode;
    this.fileInput.nativeElement.value = '';
    this.fileInput.nativeElement.click();
  }

  onFileSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (!input.files?.length) return;

    this.selectedFile = input.files[0];
    this.importerFichier();
  }

  importerFichier(): void {
    if (!this.selectedFile) return;

    const id = this.route.snapshot.paramMap.get('id');
    const token = this.authService.getToken();
    if (!id || !token) return;

    const formData = new FormData();
    formData.append('file', this.selectedFile);
    formData.append('mode', this.uploadMode);

    this.http.post(
      ApiEndpoints.mobInternationaleOnglet.import(id),
      formData,
      { headers: { Authorization: `Bearer ${token}` } }
    ).subscribe({
      next: () => {
        alert('Import réussi');
        this.loadData(id);
        this.loadResultats(id);
      },
      error: () => alert('Échec de l’import')
    });
  }

  private resetVoyage(): void {
    this.nouveauVoyage = {
      nomPays: '' as any,
      prosAvion: 0,
      prosTrain: 0,
      stagesEtudiantsAvion: 0,
      stagesEtudiantsTrain: 0,
      semestresEtudiantsAvion: 0,
      semestresEtudiantsTrain: 0
    };
  }
}
