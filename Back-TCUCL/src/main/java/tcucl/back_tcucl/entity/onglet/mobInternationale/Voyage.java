package tcucl.back_tcucl.entity.onglet.mobInternationale;

import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import tcucl.back_tcucl.entity.onglet.mobInternationale.enums.EnumMobInternationale_Pays;

@Entity
@Table(name = "voyage_mob_internationale")
public class Voyage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer valeurEnumMobInternationale_Pays;
    private Integer prosAvion;
    private Integer prosTrain;
    private Integer stagesEtudiantsAvion;
    private Integer stagesEtudiantsTrain;
    private Integer semestresEtudiantsAvion;
    private Integer semestresEtudiantsTrain;
    private Integer formationContinueAvion; //A dupliquer si ajout de colonne et créer les getter/setter
    private Integer formationContinueTrain; //A dupliquer si ajout de colonne et créer les getter/setter

    public Voyage(EnumMobInternationale_Pays nomPays,
                  Integer prosAvion,
                  Integer prosTrain,
                  Integer stagesEtudiantsAvion,
                  Integer stagesEtudiantsTrain,
                  Integer semestresEtudiantsAvion,
                  Integer semestresEtudiantsTrain,
                  Integer formationContinueAvion,  //A dupliquer si ajout de colonne
                  Integer formationContinueTrain //A dupliquer si ajout de colonne
    ) {
        this.valeurEnumMobInternationale_Pays = nomPays.getCode();
        this.prosAvion = prosAvion;
        this.prosTrain = prosTrain;
        this.stagesEtudiantsAvion = stagesEtudiantsAvion;
        this.stagesEtudiantsTrain = stagesEtudiantsTrain;
        this.semestresEtudiantsAvion = semestresEtudiantsAvion;
        this.semestresEtudiantsTrain = semestresEtudiantsTrain;
        this.formationContinueAvion = formationContinueAvion; //A dupliquer si ajout de colonne
        this.formationContinueTrain = formationContinueTrain; //A dupliquer si ajout de colonne
    }

    public Voyage() {
    }

    @AssertTrue(message = "Les champs train ne doivent pas être remplis pour ce pays.")
    public Boolean assertTrainExistForThisDestination() {
        if (!EnumMobInternationale_Pays.fromCode(this.valeurEnumMobInternationale_Pays).getIsAccessibleEnTrain()) {
            return prosTrain == 0 && stagesEtudiantsTrain == 0 && semestresEtudiantsTrain == 0 && formationContinueTrain == 0; // ajouter la valeur de la nouvelle colonne pour le train
        }
        return true;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public EnumMobInternationale_Pays getPays() {
        return this.valeurEnumMobInternationale_Pays != null ? EnumMobInternationale_Pays.fromCode(this.valeurEnumMobInternationale_Pays) : null;
    }

    public void setPays(EnumMobInternationale_Pays valeur) {
        this.valeurEnumMobInternationale_Pays = valeur.getCode();
    }

    public Integer getProsAvion() {
        return prosAvion;
    }

    public void setProsAvion(Integer prosAvion) {
        this.prosAvion = prosAvion;
    }

    public Integer getProsTrain() {
        return prosTrain;
    }

    public void setProsTrain(Integer prosTrain) {
        this.prosTrain = prosTrain;
    }

    public Integer getStagesEtudiantsAvion() {
        return stagesEtudiantsAvion;
    }

    public void setStagesEtudiantsAvion(Integer stagesEtudiantsAvion) {
        this.stagesEtudiantsAvion = stagesEtudiantsAvion;
    }

    public Integer getStagesEtudiantsTrain() {
        return stagesEtudiantsTrain;
    }

    public void setStagesEtudiantsTrain(Integer stagesEtudiantsTrain) {
        this.stagesEtudiantsTrain = stagesEtudiantsTrain;
    }

    public Integer getSemestresEtudiantsAvion() {
        return semestresEtudiantsAvion;
    }

    public void setSemestresEtudiantsAvion(Integer semestresEtudiantsAvion) {
        this.semestresEtudiantsAvion = semestresEtudiantsAvion;
    }

    public Integer getSemestresEtudiantsTrain() {
        return semestresEtudiantsTrain;
    }

    public void setSemestresEtudiantsTrain(Integer semestresEtudiantsTrain) {
        this.semestresEtudiantsTrain = semestresEtudiantsTrain;
    }

    public Integer getFormationContinueAvion() {
        return formationContinueAvion;
    }

    public void setFormationContinueAvion(Integer formationContinueAvion) {
        this.formationContinueAvion = formationContinueAvion;
    }

    public Integer getFormationContinueTrain() {
        return formationContinueTrain;
    }

    public void setFormationContinueTrain(Integer formationContinueTrain) {
        this.formationContinueTrain = formationContinueTrain;
    }


}
