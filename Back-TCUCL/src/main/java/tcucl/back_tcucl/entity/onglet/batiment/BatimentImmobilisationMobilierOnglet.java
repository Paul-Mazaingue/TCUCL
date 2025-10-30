package tcucl.back_tcucl.entity.onglet.batiment;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import tcucl.back_tcucl.dto.onglet.batimentImmobilisationMobilier.BatimentExistantOuNeufConstruitDto;
import tcucl.back_tcucl.dto.onglet.batimentImmobilisationMobilier.EntretienCourantDto;
import tcucl.back_tcucl.dto.onglet.batimentImmobilisationMobilier.MobilierElectromenagerDto;
import tcucl.back_tcucl.entity.onglet.Onglet;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "batiment_onglet")
public class BatimentImmobilisationMobilierOnglet extends Onglet {

    //CascadeType.ALL sans remove car remove sur un enfant retirerait également l'onglet parent
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name = "batiment_onglet_associe_batiment",
            joinColumns = @JoinColumn(name = "batiment_onglet_id"),
            inverseJoinColumns = @JoinColumn(name = "batiment_id")
    )
    @Valid
    private List<BatimentExistantOuNeufConstruit> batimentsExistantOuNeufConstruits = new ArrayList<>();

    //CascadeType.ALL sans remove car remove sur un enfant retirerait également l'onglet parent
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name = "batiment_onglet_associe_entretien",
            joinColumns = @JoinColumn(name = "batiment_onglet_id"),
            inverseJoinColumns = @JoinColumn(name = "entretien_id")
    )
    @Valid
    private List<EntretienCourant> entretiensCourants = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "batiment_onglet_id")
    @Valid
    private List<MobilierElectromenager> mobiliersElectromenagers = new ArrayList<>();

    public BatimentImmobilisationMobilierOnglet() {
        super();
    }

    public List<BatimentExistantOuNeufConstruit> getBatimentExistantOuNeufConstruits() {
        return batimentsExistantOuNeufConstruits;
    }

    public void setBatimentExistantOuNeufConstruits(List<BatimentExistantOuNeufConstruit> batimentExistantOuNeufConstruits) {
        this.batimentsExistantOuNeufConstruits = batimentExistantOuNeufConstruits;
    }

    public List<EntretienCourant> getEntretienCourants() {
        return entretiensCourants;
    }

    public void setEntretienCourants(List<EntretienCourant> entretienCourants) {
        this.entretiensCourants = entretienCourants;
    }

    public List<MobilierElectromenager> getMobilierElectromenagers() {
        return mobiliersElectromenagers;
    }

    public void setMobilierElectromenagers(List<MobilierElectromenager> mobilierElectromenagers) {
        this.mobiliersElectromenagers = mobilierElectromenagers;
    }

    public void ajouterBatimentExistantOuNeufConstruit(BatimentExistantOuNeufConstruit batimentExistantOuNeufConstruit) {
        this.batimentsExistantOuNeufConstruits.add(batimentExistantOuNeufConstruit);
    }

    public void ajouterBatimentViaDto(BatimentExistantOuNeufConstruitDto batimentExistantOuNeufConstruitDto) {
        BatimentExistantOuNeufConstruit batimentExistantOuNeufConstruit = new BatimentExistantOuNeufConstruit();

        batimentExistantOuNeufConstruit.setNom_ou_adresse(batimentExistantOuNeufConstruitDto.getNom_ou_adresse());
        batimentExistantOuNeufConstruit.setDateConstruction(batimentExistantOuNeufConstruitDto.getDateConstruction());
        batimentExistantOuNeufConstruit.setDateDerniereGrosseRenovation(batimentExistantOuNeufConstruitDto.getDateDerniereGrosseRenovation());
        batimentExistantOuNeufConstruit.setAcvBatimentRealisee(batimentExistantOuNeufConstruitDto.getAcvBatimentRealisee());
        batimentExistantOuNeufConstruit.setEmissionsGesReellesTCO2(batimentExistantOuNeufConstruitDto.getEmissionsGesReellesTCO2());
        batimentExistantOuNeufConstruit.setTypeBatiment(batimentExistantOuNeufConstruitDto.getTypeBatiment());
        batimentExistantOuNeufConstruit.setSurfaceEnM2(batimentExistantOuNeufConstruitDto.getSurfaceEnM2());
        batimentExistantOuNeufConstruit.setTypeStructure(batimentExistantOuNeufConstruitDto.getTypeStructure());

        this.batimentsExistantOuNeufConstruits.add(batimentExistantOuNeufConstruit);
    }

    public void ajouterEntretienCourant(EntretienCourant entretienCourant) {
        this.entretiensCourants.add(entretienCourant);
    }

    public void ajouterEntretienCourantViaDto(EntretienCourantDto entretienCourantDto) {
        EntretienCourant entretienCourant = new EntretienCourant();

        entretienCourant.setDateAjout(entretienCourantDto.getDateAjout());
        entretienCourant.setNom_adresse(entretienCourantDto.getNom_adresse());
        entretienCourant.setTypeTravaux(entretienCourantDto.getTypeTravaux());
        entretienCourant.setDateTravaux(entretienCourantDto.getDateTravaux());
        entretienCourant.setTypeBatiment(entretienCourantDto.getTypeBatiment());
        entretienCourant.setSurfaceConcernee(entretienCourantDto.getSurfaceConcernee());
        entretienCourant.setDureeAmortissement(entretienCourantDto.getDureeAmortissement());

        this.entretiensCourants.add(entretienCourant);
    }

    public void ajouterMobilierElectromenager(MobilierElectromenager mobilierElectromenager) {
        this.mobiliersElectromenagers.add(mobilierElectromenager);
    }

    public void ajouterMobilierElectromenagerViaDto(MobilierElectromenagerDto mobilierElectromenagerDto) {
        MobilierElectromenager mobilierElectromenager = new MobilierElectromenager();

        mobilierElectromenager.setDateAjout(mobilierElectromenagerDto.getDateAjout());
        mobilierElectromenager.setMobilier(mobilierElectromenagerDto.getMobilier());
        mobilierElectromenager.setQuantite(mobilierElectromenagerDto.getQuantite());
        mobilierElectromenager.setPoidsDuProduit(mobilierElectromenagerDto.getPoidsDuProduit());
        mobilierElectromenager.setDureeAmortissement(mobilierElectromenagerDto.getDureeAmortissement());

        this.mobiliersElectromenagers.add(mobilierElectromenager);
    }


}
