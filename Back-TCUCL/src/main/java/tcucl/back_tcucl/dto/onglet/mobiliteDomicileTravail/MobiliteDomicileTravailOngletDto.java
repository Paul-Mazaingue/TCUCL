package tcucl.back_tcucl.dto.onglet.mobiliteDomicileTravail;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import tcucl.back_tcucl.entity.onglet.MobiliteDomicileTravailOnglet;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MobiliteDomicileTravailOngletDto {

    private Long id;
    private Boolean estTermine;
    private String note;

    private Integer voitureThermiqueEtudiantKm;
    private Integer voitureElectriqueEtudiantKm;
    private Integer voitureHybrideEtudiantKm;
    private Integer motoEtudiantKm;
    private Integer trainRegionalEtudiantKm;
    private Integer busEtudiantKm;
    private Integer metroTramwayEtudiantKm;
    private Integer veloEtudiantKm;
    private Integer trottinetteElectriqueEtudiantKm;
    private Integer veloElectriqueEtudiantKm;
    private Integer marcheAPiedEtudiantKm;
    private Integer nbJoursDeplacementEtudiant;

    private Integer voitureThermiqueSalarieKm;
    private Integer voitureElectriqueSalarieKm;
    private Integer voitureHybrideSalarieKm;
    private Integer motoSalarieKm;
    private Integer trainRegionalSalarieKm;
    private Integer busSalarieKm;
    private Integer metroTramwaySalarieKm;
    private Integer veloSalarieKm;
    private Integer trottinetteElectriqueSalarieKm;
    private Integer veloElectriqueSalarieKm;
    private Integer marcheAPiedSalarieKm;
    private Integer nbJoursDeplacementSalarie;


    public MobiliteDomicileTravailOngletDto() {
    }

    public MobiliteDomicileTravailOngletDto(MobiliteDomicileTravailOnglet entity) {
        this.id = entity.getId();
        this.estTermine = entity.getEstTermine();
        this.note = entity.getNote();
        this.voitureThermiqueEtudiantKm = entity.getVoitureThermiqueEtudiantKm();
        this.voitureElectriqueEtudiantKm = entity.getVoitureElectriqueEtudiantKm();
        this.voitureHybrideEtudiantKm = entity.getVoitureHybrideEtudiantKm();
        this.motoEtudiantKm = entity.getMotoEtudiantKm();
        this.trainRegionalEtudiantKm = entity.getTrainRegionalEtudiantKm();
        this.busEtudiantKm = entity.getBusEtudiantKm();
        this.metroTramwayEtudiantKm = entity.getMetroTramwayEtudiantKm();
        this.veloEtudiantKm = entity.getVeloEtudiantKm();
        this.trottinetteElectriqueEtudiantKm = entity.getTrottinetteElectriqueEtudiantKm();
        this.veloElectriqueEtudiantKm = entity.getVeloElectriqueEtudiantKm();
        this.marcheAPiedEtudiantKm = entity.getMarcheAPiedEtudiantKm();
        this.nbJoursDeplacementEtudiant = entity.getNbJoursDeplacementEtudiant();
        this.voitureThermiqueSalarieKm = entity.getVoitureThermiqueSalarieKm();
        this.voitureElectriqueSalarieKm = entity.getVoitureElectriqueSalarieKm();
        this.voitureHybrideSalarieKm = entity.getVoitureHybrideSalarieKm();
        this.motoSalarieKm = entity.getMotoSalarieKm();
        this.trainRegionalSalarieKm = entity.getTrainRegionalSalarieKm();
        this.busSalarieKm = entity.getBusSalarieKm();
        this.metroTramwaySalarieKm = entity.getMetroTramwaySalarieKm();
        this.veloSalarieKm = entity.getVeloSalarieKm();
        this.trottinetteElectriqueSalarieKm = entity.getTrottinetteElectriqueSalarieKm();
        this.veloElectriqueSalarieKm = entity.getVeloElectriqueSalarieKm();
        this.marcheAPiedSalarieKm = entity.getMarcheAPiedSalarieKm();
        this.nbJoursDeplacementSalarie = entity.getNbJoursDeplacementSalarie();

    }





    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getEstTermine() {
        return estTermine;
    }

    public void setEstTermine(Boolean estTermine) {
        this.estTermine = estTermine;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getVoitureThermiqueEtudiantKm() {
        return voitureThermiqueEtudiantKm;
    }

    public void setVoitureThermiqueEtudiantKm(Integer voitureThermiqueEtudiantKm) {
        this.voitureThermiqueEtudiantKm = voitureThermiqueEtudiantKm;
    }

    public Integer getVoitureElectriqueEtudiantKm() {
        return voitureElectriqueEtudiantKm;
    }

    public void setVoitureElectriqueEtudiantKm(Integer voitureElectriqueEtudiantKm) {
        this.voitureElectriqueEtudiantKm = voitureElectriqueEtudiantKm;
    }

    public Integer getVoitureHybrideEtudiantKm() {
        return voitureHybrideEtudiantKm;
    }

    public void setVoitureHybrideEtudiantKm(Integer voitureHybrideEtudiantKm) {
        this.voitureHybrideEtudiantKm = voitureHybrideEtudiantKm;
    }

    public Integer getMotoEtudiantKm() {
        return motoEtudiantKm;
    }

    public void setMotoEtudiantKm(Integer motoEtudiantKm) {
        this.motoEtudiantKm = motoEtudiantKm;
    }

    public Integer getTrainRegionalEtudiantKm() {
        return trainRegionalEtudiantKm;
    }

    public void setTrainRegionalEtudiantKm(Integer trainRegionalEtudiantKm) {
        this.trainRegionalEtudiantKm = trainRegionalEtudiantKm;
    }

    public Integer getBusEtudiantKm() {
        return busEtudiantKm;
    }

    public void setBusEtudiantKm(Integer busEtudiantKm) {
        this.busEtudiantKm = busEtudiantKm;
    }

    public Integer getMetroTramwayEtudiantKm() {
        return metroTramwayEtudiantKm;
    }

    public void setMetroTramwayEtudiantKm(Integer metroTramwayEtudiantKm) {
        this.metroTramwayEtudiantKm = metroTramwayEtudiantKm;
    }

    public Integer getVeloEtudiantKm() {
        return veloEtudiantKm;
    }

    public void setVeloEtudiantKm(Integer veloEtudiantKm) {
        this.veloEtudiantKm = veloEtudiantKm;
    }

    public Integer getTrottinetteElectriqueEtudiantKm() {
        return trottinetteElectriqueEtudiantKm;
    }

    public void setTrottinetteElectriqueEtudiantKm(Integer trottinetteElectriqueEtudiantKm) {
        this.trottinetteElectriqueEtudiantKm = trottinetteElectriqueEtudiantKm;
    }

    public Integer getVeloElectriqueEtudiantKm() {
        return veloElectriqueEtudiantKm;
    }

    public void setVeloElectriqueEtudiantKm(Integer veloElectriqueEtudiantKm) {
        this.veloElectriqueEtudiantKm = veloElectriqueEtudiantKm;
    }

    public Integer getMarcheAPiedEtudiantKm() {
        return marcheAPiedEtudiantKm;
    }

    public void setMarcheAPiedEtudiantKm(Integer marcheAPiedEtudiantKm) {
        this.marcheAPiedEtudiantKm = marcheAPiedEtudiantKm;
    }

    public Integer getNbJoursDeplacementEtudiant() {
        return nbJoursDeplacementEtudiant;
    }

    public void setNbJoursDeplacementEtudiant(Integer nbJoursDeplacementEtudiant) {
        this.nbJoursDeplacementEtudiant = nbJoursDeplacementEtudiant;
    }

    public Integer getVoitureThermiqueSalarieKm() {
        return voitureThermiqueSalarieKm;
    }

    public void setVoitureThermiqueSalarieKm(Integer voitureThermiqueSalarieKm) {
        this.voitureThermiqueSalarieKm = voitureThermiqueSalarieKm;
    }

    public Integer getVoitureElectriqueSalarieKm() {
        return voitureElectriqueSalarieKm;
    }

    public void setVoitureElectriqueSalarieKm(Integer voitureElectriqueSalarieKm) {
        this.voitureElectriqueSalarieKm = voitureElectriqueSalarieKm;
    }

    public Integer getVoitureHybrideSalarieKm() {
        return voitureHybrideSalarieKm;
    }

    public void setVoitureHybrideSalarieKm(Integer voitureHybrideSalarieKm) {
        this.voitureHybrideSalarieKm = voitureHybrideSalarieKm;
    }

    public Integer getMotoSalarieKm() {
        return motoSalarieKm;
    }

    public void setMotoSalarieKm(Integer motoSalarieKm) {
        this.motoSalarieKm = motoSalarieKm;
    }

    public Integer getTrainRegionalSalarieKm() {
        return trainRegionalSalarieKm;
    }

    public void setTrainRegionalSalarieKm(Integer trainRegionalSalarieKm) {
        this.trainRegionalSalarieKm = trainRegionalSalarieKm;
    }

    public Integer getBusSalarieKm() {
        return busSalarieKm;
    }

    public void setBusSalarieKm(Integer busSalarieKm) {
        this.busSalarieKm = busSalarieKm;
    }

    public Integer getMetroTramwaySalarieKm() {
        return metroTramwaySalarieKm;
    }

    public void setMetroTramwaySalarieKm(Integer metroTramwaySalarieKm) {
        this.metroTramwaySalarieKm = metroTramwaySalarieKm;
    }

    public Integer getVeloSalarieKm() {
        return veloSalarieKm;
    }

    public void setVeloSalarieKm(Integer veloSalarieKm) {
        this.veloSalarieKm = veloSalarieKm;
    }

    public Integer getTrottinetteElectriqueSalarieKm() {
        return trottinetteElectriqueSalarieKm;
    }

    public void setTrottinetteElectriqueSalarieKm(Integer trottinetteElectriqueSalarieKm) {
        this.trottinetteElectriqueSalarieKm = trottinetteElectriqueSalarieKm;
    }

    public Integer getVeloElectriqueSalarieKm() {
        return veloElectriqueSalarieKm;
    }

    public void setVeloElectriqueSalarieKm(Integer veloElectriqueSalarieKm) {
        this.veloElectriqueSalarieKm = veloElectriqueSalarieKm;
    }

    public Integer getMarcheAPiedSalarieKm() {
        return marcheAPiedSalarieKm;
    }

    public void setMarcheAPiedSalarieKm(Integer marcheAPiedSalarieKm) {
        this.marcheAPiedSalarieKm = marcheAPiedSalarieKm;
    }

    public Integer getNbJoursDeplacementSalarie() {
        return nbJoursDeplacementSalarie;
    }

    public void setNbJoursDeplacementSalarie(Integer nbJoursDeplacementSalarie) {
        this.nbJoursDeplacementSalarie = nbJoursDeplacementSalarie;
    }
}