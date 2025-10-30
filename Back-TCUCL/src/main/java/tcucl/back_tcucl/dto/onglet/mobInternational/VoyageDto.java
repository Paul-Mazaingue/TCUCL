package tcucl.back_tcucl.dto.onglet.mobInternational;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import tcucl.back_tcucl.entity.onglet.mobInternationale.Voyage;
import tcucl.back_tcucl.entity.onglet.mobInternationale.enums.EnumMobInternationale_Pays;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class VoyageDto {

    private Long id;
    private EnumMobInternationale_Pays nomPays;
    private Integer prosAvion;
    private Integer prosTrain;
    private Integer stagesEtudiantsAvion;
    private Integer stagesEtudiantsTrain;
    private Integer semestresEtudiantsAvion;
    private Integer semestresEtudiantsTrain;
    private Integer formationContinueAvion;  //A dupliquer si ajout de colonne et ajouter getter/setter
    private Integer formationContinueTrain; //A dupliquer si ajout de colonne et ajouter getter/setter

    public VoyageDto() {}

    public VoyageDto(Voyage entity) {
        this.id = entity.getId();
        this.nomPays = entity.getPays();
        this.prosAvion = entity.getProsAvion();
        this.prosTrain = entity.getProsTrain();
        this.stagesEtudiantsAvion = entity.getStagesEtudiantsAvion();
        this.stagesEtudiantsTrain = entity.getStagesEtudiantsTrain();
        this.semestresEtudiantsAvion = entity.getSemestresEtudiantsAvion();
        this.semestresEtudiantsTrain = entity.getSemestresEtudiantsTrain();
        this.formationContinueAvion = entity.getFormationContinueAvion(); //A dupliquer si ajout de colonne
        this.formationContinueTrain = entity.getFormationContinueTrain(); //A dupliquer si ajout de colonne
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EnumMobInternationale_Pays getNomPays() {
        return nomPays;
    }

    public void setNomPays(EnumMobInternationale_Pays nomPays) {
        this.nomPays = nomPays;
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
