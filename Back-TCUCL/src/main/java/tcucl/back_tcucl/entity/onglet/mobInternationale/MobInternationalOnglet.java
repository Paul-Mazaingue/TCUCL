package tcucl.back_tcucl.entity.onglet.mobInternationale;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import tcucl.back_tcucl.dto.onglet.mobInternational.VoyageDto;
import tcucl.back_tcucl.entity.onglet.Onglet;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mob_international_onglet")
public class MobInternationalOnglet extends Onglet {

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "voyage_mob_internationale_id")
    @Valid
    private List<Voyage> voyages = new ArrayList<>();

    public MobInternationalOnglet() {
        super();
    }

    public List<Voyage> getVoyages() {
        return voyages;
    }

    public void setVoyages(List<Voyage> voyage) {
        this.voyages = voyage;
    }

    

    public void ajouterVoyageViaDto(VoyageDto voyageDto) {
        // ajouter une colonne rajouter nouvelle colonne train et avion
        Voyage voyage = new Voyage();
        voyage.setPays(voyageDto.getNomPays());
        voyage.setProsAvion(voyageDto.getProsAvion());
        voyage.setProsTrain(voyageDto.getProsTrain());
        voyage.setStagesEtudiantsAvion(voyageDto.getStagesEtudiantsAvion());
        voyage.setStagesEtudiantsTrain(voyageDto.getStagesEtudiantsTrain());
        voyage.setSemestresEtudiantsAvion(voyageDto.getSemestresEtudiantsAvion());
        voyage.setSemestresEtudiantsTrain(voyageDto.getSemestresEtudiantsTrain());
        voyage.setFormationContinueAvion(voyageDto.getFormationContinueAvion());
        voyage.setFormationContinueTrain(voyageDto.getFormationContinueTrain());

        this.voyages.add(voyage);
    }
}
