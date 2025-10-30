package tcucl.back_tcucl.dto.onglet.mobInternational;

import tcucl.back_tcucl.entity.onglet.mobInternationale.enums.EnumMobInternationale_Pays;

import java.util.Map;

public class MobInternationalResultatDto {

    // En cas d'ajout de colonne dupliquer tout les bloc indiquer comme "à dupliquer" puis générer les getter/setter

    private Map<EnumMobInternationale_Pays, Float> emissionGesParPays;

    private Float emissionGesProEuropeTrain=0f;
    private Float emissionGesProEuropeAvion=0f;
    private Float emissionGesProEurope=0f;
    private Float emissionGesProHorsEurope=0f;

    private Float emissionGesStagesEuropeTrain=0f;
    private Float emissionGesStagesEuropeAvion=0f;
    private Float emissionGesStageEurope=0f;
    private Float emissionGesStagesHorsEurope=0f;

    private Float emissionGesSemestresEtudiantsEuropeTrain =0f;
    private Float emissionGesSemestresEtudiantsEuropeAvion =0f;
    private Float emissionGesSemestresEtudiantsEurope =0f;
    private Float emissionGesSemestresEtudiantsHorsEurope =0f;

    // Dupliquer tout le bloc
    private Float emissionGesFormationContinueEuropeTrain=0f;
    private Float emissionGesFormationContinueEuropeAvion=0f;
    private Float emissionGesFormationContinueEurope=0f;
    private Float emissionGesFormationContinueHorsEurope=0f;


    private Float prosProportionDeDeparts=0f;
    private Float prosPartEuropeVsNonEurope=0f;
    private Float prosDistanceMoyenneHorsEurope=0f;
    private Float prosPartTrainEnEurope=0f;
    private Float prosDistancemoyenneEuropeAvion=0f;
    private Float prosDistanceMoyenneEuropeTrain=0f;
    private Float prosIntensiteCarboneDesDeplacemeents_gCo2eParKm=0f;
    private Float prosIntensiteCarboneDesDeplacemeents_kgCo2eParDepart=0f;

    private Float stagesProportionDeDeparts=0f;
    private Float stagesPartEuropeVsNonEurope=0f;
    private Float stagesDistanceMoyenneHorsEurope=0f;
    private Float stagesPartTrainEnEurope=0f;
    private Float stagesDistancemoyenneEuropeAvion=0f;
    private Float stagesDistanceMoyenneEuropeTrain=0f;
    private Float stagesIntensiteCarboneDesDeplacemeents_gCo2eParKm=0f;
    private Float stagesIntensiteCarboneDesDeplacemeents_kgCo2eParDepart=0f;

    private Float semestresProportionDeDeparts=0f;
    private Float semestresPartEuropeVsNonEurope=0f;
    private Float semestresDistanceMoyenneHorsEurope=0f;
    private Float semestresPartTrainEnEurope=0f;
    private Float semestresDistancemoyenneEuropeAvion=0f;
    private Float semestresDistanceMoyenneEuropeTrain=0f;
    private Float semestresIntensiteCarboneDesDeplacemeents_gCo2eParKm=0f;
    private Float semestresIntensiteCarboneDesDeplacemeents_kgCo2eParDepart=0f;

    // Dupliquer tout le bloc
    private Float FormationContinueProportionDeDeparts=0f;
    private Float FormationContinuePartEuropeVsNonEurope=0f;
    private Float FormationContinueDistanceMoyenneHorsEurope=0f;
    private Float FormationContinuePartTrainEnEurope=0f;
    private Float FormationContinueDistancemoyenneEuropeAvion=0f;
    private Float FormationContinueDistanceMoyenneEuropeTrain=0f;
    private Float FormationContinueIntensiteCarboneDesDeplacemeents_gCo2eParKm=0f;
    private Float FormationContinueIntensiteCarboneDesDeplacemeents_kgCo2eParDepart=0f;


    public MobInternationalResultatDto() {
    }

    public Map<EnumMobInternationale_Pays, Float> getEmissionGesParPays() {
        return emissionGesParPays;
    }

    public void setEmissionGesParPays(Map<EnumMobInternationale_Pays, Float> emissionGesParPays) {
        this.emissionGesParPays = emissionGesParPays;
    }

    public void addEmissionGesParPays(EnumMobInternationale_Pays paysId, Float emission) {
        if (this.emissionGesParPays == null) {
            this.emissionGesParPays = new java.util.HashMap<>();
        }
        this.emissionGesParPays.put(paysId, emission);
    }

    public Float getEmissionGesProEuropeTrain() {
        return emissionGesProEuropeTrain;
    }

    public void setEmissionGesProEuropeTrain(Float emissionGesProEuropeTrain) {
        this.emissionGesProEuropeTrain = emissionGesProEuropeTrain;
    }

    public Float getEmissionGesProEuropeAvion() {
        return emissionGesProEuropeAvion;
    }

    public void setEmissionGesProEuropeAvion(Float emissionGesProEuropeAvion) {
        this.emissionGesProEuropeAvion = emissionGesProEuropeAvion;
    }

    public Float getEmissionGesProEurope() {
        return emissionGesProEurope;
    }

    public void setEmissionGesProEurope(Float emissionGesProEurope) {
        this.emissionGesProEurope = emissionGesProEurope;
    }

    public Float getEmissionGesProHorsEurope() {
        return emissionGesProHorsEurope;
    }

    public void setEmissionGesProHorsEurope(Float emissionGesProHorsEurope) {
        this.emissionGesProHorsEurope = emissionGesProHorsEurope;
    }

    public Float getEmissionGesStagesEuropeTrain() {
        return emissionGesStagesEuropeTrain;
    }

    public void setEmissionGesStagesEuropeTrain(Float emissionGesStagesEuropeTrain) {
        this.emissionGesStagesEuropeTrain = emissionGesStagesEuropeTrain;
    }

    public Float getEmissionGesStagesEuropeAvion() {
        return emissionGesStagesEuropeAvion;
    }

    public void setEmissionGesStagesEuropeAvion(Float emissionGesStagesEuropeAvion) {
        this.emissionGesStagesEuropeAvion = emissionGesStagesEuropeAvion;
    }

    public Float getEmissionGesStageEurope() {
        return emissionGesStageEurope;
    }

    public void setEmissionGesStageEurope(Float emissionGesStageEurope) {
        this.emissionGesStageEurope = emissionGesStageEurope;
    }

    public Float getEmissionGesStagesHorsEurope() {
        return emissionGesStagesHorsEurope;
    }

    public void setEmissionGesStagesHorsEurope(Float emissionGesStagesHorsEurope) {
        this.emissionGesStagesHorsEurope = emissionGesStagesHorsEurope;
    }

    public Float getEmissionGesSemestresEtudiantsEuropeTrain() {
        return emissionGesSemestresEtudiantsEuropeTrain;
    }

    public void setEmissionGesSemestresEtudiantsEuropeTrain(Float emissionGesSemestresEtudiantsEuropeTrain) {
        this.emissionGesSemestresEtudiantsEuropeTrain = emissionGesSemestresEtudiantsEuropeTrain;
    }

    public Float getEmissionGesSemestresEtudiantsEuropeAvion() {
        return emissionGesSemestresEtudiantsEuropeAvion;
    }

    public void setEmissionGesSemestresEtudiantsEuropeAvion(Float emissionGesSemestresEtudiantsEuropeAvion) {
        this.emissionGesSemestresEtudiantsEuropeAvion = emissionGesSemestresEtudiantsEuropeAvion;
    }

    public Float getEmissionGesSemestresEtudiantsEurope() {
        return emissionGesSemestresEtudiantsEurope;
    }

    public void setEmissionGesSemestresEtudiantsEurope(Float emissionGesSemestresEtudiantsEurope) {
        this.emissionGesSemestresEtudiantsEurope = emissionGesSemestresEtudiantsEurope;
    }

    public Float getEmissionGesSemestresEtudiantsHorsEurope() {
        return emissionGesSemestresEtudiantsHorsEurope;
    }

    public void setEmissionGesSemestresEtudiantsHorsEurope(Float emissionGesSemestresEtudiantsHorsEurope) {
        this.emissionGesSemestresEtudiantsHorsEurope = emissionGesSemestresEtudiantsHorsEurope;
    }

    public Float getProsProportionDeDeparts() {
        return prosProportionDeDeparts;
    }

    public void setProsProportionDeDeparts(Float prosProportionDeDeparts) {
        this.prosProportionDeDeparts = prosProportionDeDeparts;
    }

    public Float getProsPartEuropeVsNonEurope() {
        return prosPartEuropeVsNonEurope;
    }

    public void setProsPartEuropeVsNonEurope(Float prosPartEuropeVsNonEurope) {
        this.prosPartEuropeVsNonEurope = prosPartEuropeVsNonEurope;
    }

    public Float getProsDistanceMoyenneHorsEurope() {
        return prosDistanceMoyenneHorsEurope;
    }

    public void setProsDistanceMoyenneHorsEurope(Float prosDistanceMoyenneHorsEurope) {
        this.prosDistanceMoyenneHorsEurope = prosDistanceMoyenneHorsEurope;
    }

    public Float getProsPartTrainEnEurope() {
        return prosPartTrainEnEurope;
    }

    public void setProsPartTrainEnEurope(Float prosPartTrainEnEurope) {
        this.prosPartTrainEnEurope = prosPartTrainEnEurope;
    }

    public Float getProsDistancemoyenneEuropeAvion() {
        return prosDistancemoyenneEuropeAvion;
    }

    public void setProsDistancemoyenneEuropeAvion(Float prosDistancemoyenneEuropeAvion) {
        this.prosDistancemoyenneEuropeAvion = prosDistancemoyenneEuropeAvion;
    }

    public Float getProsDistanceMoyenneEuropeTrain() {
        return prosDistanceMoyenneEuropeTrain;
    }

    public void setProsDistanceMoyenneEuropeTrain(Float prosDistanceMoyenneEuropeTrain) {
        this.prosDistanceMoyenneEuropeTrain = prosDistanceMoyenneEuropeTrain;
    }

    public Float getProsIntensiteCarboneDesDeplacemeents_gCo2eParKm() {
        return prosIntensiteCarboneDesDeplacemeents_gCo2eParKm;
    }

    public void setProsIntensiteCarboneDesDeplacemeents_gCo2eParKm(Float prosIntensiteCarboneDesDeplacemeents_gCo2eParKm) {
        this.prosIntensiteCarboneDesDeplacemeents_gCo2eParKm = prosIntensiteCarboneDesDeplacemeents_gCo2eParKm;
    }

    public Float getProsIntensiteCarboneDesDeplacemeents_kgCo2eParDepart() {
        return prosIntensiteCarboneDesDeplacemeents_kgCo2eParDepart;
    }

    public void setProsIntensiteCarboneDesDeplacemeents_kgCo2eParDepart(Float prosIntensiteCarboneDesDeplacemeents_kgCo2eParDepart) {
        this.prosIntensiteCarboneDesDeplacemeents_kgCo2eParDepart = prosIntensiteCarboneDesDeplacemeents_kgCo2eParDepart;
    }

    public Float getStagesProportionDeDeparts() {
        return stagesProportionDeDeparts;
    }

    public void setStagesProportionDeDeparts(Float stagesProportionDeDeparts) {
        this.stagesProportionDeDeparts = stagesProportionDeDeparts;
    }

    public Float getStagesPartEuropeVsNonEurope() {
        return stagesPartEuropeVsNonEurope;
    }

    public void setStagesPartEuropeVsNonEurope(Float stagesPartEuropeVsNonEurope) {
        this.stagesPartEuropeVsNonEurope = stagesPartEuropeVsNonEurope;
    }

    public Float getStagesDistanceMoyenneHorsEurope() {
        return stagesDistanceMoyenneHorsEurope;
    }

    public void setStagesDistanceMoyenneHorsEurope(Float stagesDistanceMoyenneHorsEurope) {
        this.stagesDistanceMoyenneHorsEurope = stagesDistanceMoyenneHorsEurope;
    }

    public Float getStagesPartTrainEnEurope() {
        return stagesPartTrainEnEurope;
    }

    public void setStagesPartTrainEnEurope(Float stagesPartTrainEnEurope) {
        this.stagesPartTrainEnEurope = stagesPartTrainEnEurope;
    }

    public Float getStagesDistancemoyenneEuropeAvion() {
        return stagesDistancemoyenneEuropeAvion;
    }

    public void setStagesDistancemoyenneEuropeAvion(Float stagesDistancemoyenneEuropeAvion) {
        this.stagesDistancemoyenneEuropeAvion = stagesDistancemoyenneEuropeAvion;
    }

    public Float getStagesDistanceMoyenneEuropeTrain() {
        return stagesDistanceMoyenneEuropeTrain;
    }

    public void setStagesDistanceMoyenneEuropeTrain(Float stagesDistanceMoyenneEuropeTrain) {
        this.stagesDistanceMoyenneEuropeTrain = stagesDistanceMoyenneEuropeTrain;
    }

    public Float getStagesIntensiteCarboneDesDeplacemeents_gCo2eParKm() {
        return stagesIntensiteCarboneDesDeplacemeents_gCo2eParKm;
    }

    public void setStagesIntensiteCarboneDesDeplacemeents_gCo2eParKm(Float stagesIntensiteCarboneDesDeplacemeents_gCo2eParKm) {
        this.stagesIntensiteCarboneDesDeplacemeents_gCo2eParKm = stagesIntensiteCarboneDesDeplacemeents_gCo2eParKm;
    }

    public Float getStagesIntensiteCarboneDesDeplacemeents_kgCo2eParDepart() {
        return stagesIntensiteCarboneDesDeplacemeents_kgCo2eParDepart;
    }

    public void setStagesIntensiteCarboneDesDeplacemeents_kgCo2eParDepart(Float stagesIntensiteCarboneDesDeplacemeents_kgCo2eParDepart) {
        this.stagesIntensiteCarboneDesDeplacemeents_kgCo2eParDepart = stagesIntensiteCarboneDesDeplacemeents_kgCo2eParDepart;
    }

    public Float getSemestresProportionDeDeparts() {
        return semestresProportionDeDeparts;
    }

    public void setSemestresProportionDeDeparts(Float semestresProportionDeDeparts) {
        this.semestresProportionDeDeparts = semestresProportionDeDeparts;
    }

    public Float getSemestresPartEuropeVsNonEurope() {
        return semestresPartEuropeVsNonEurope;
    }

    public void setSemestresPartEuropeVsNonEurope(Float semestresPartEuropeVsNonEurope) {
        this.semestresPartEuropeVsNonEurope = semestresPartEuropeVsNonEurope;
    }

    public Float getSemestresDistanceMoyenneHorsEurope() {
        return semestresDistanceMoyenneHorsEurope;
    }

    public void setSemestresDistanceMoyenneHorsEurope(Float semestresDistanceMoyenneHorsEurope) {
        this.semestresDistanceMoyenneHorsEurope = semestresDistanceMoyenneHorsEurope;
    }

    public Float getSemestresPartTrainEnEurope() {
        return semestresPartTrainEnEurope;
    }

    public void setSemestresPartTrainEnEurope(Float semestresPartTrainEnEurope) {
        this.semestresPartTrainEnEurope = semestresPartTrainEnEurope;
    }

    public Float getSemestresDistancemoyenneEuropeAvion() {
        return semestresDistancemoyenneEuropeAvion;
    }

    public void setSemestresDistancemoyenneEuropeAvion(Float semestresDistancemoyenneEuropeAvion) {
        this.semestresDistancemoyenneEuropeAvion = semestresDistancemoyenneEuropeAvion;
    }

    public Float getSemestresDistanceMoyenneEuropeTrain() {
        return semestresDistanceMoyenneEuropeTrain;
    }

    public void setSemestresDistanceMoyenneEuropeTrain(Float semestresDistanceMoyenneEuropeTrain) {
        this.semestresDistanceMoyenneEuropeTrain = semestresDistanceMoyenneEuropeTrain;
    }

    public Float getSemestresIntensiteCarboneDesDeplacemeents_gCo2eParKm() {
        return semestresIntensiteCarboneDesDeplacemeents_gCo2eParKm;
    }

    public void setSemestresIntensiteCarboneDesDeplacemeents_gCo2eParKm(Float semestresIntensiteCarboneDesDeplacemeents_gCo2eParKm) {
        this.semestresIntensiteCarboneDesDeplacemeents_gCo2eParKm = semestresIntensiteCarboneDesDeplacemeents_gCo2eParKm;
    }

    public Float getSemestresIntensiteCarboneDesDeplacemeents_kgCo2eParDepart() {
        return semestresIntensiteCarboneDesDeplacemeents_kgCo2eParDepart;
    }

    public void setSemestresIntensiteCarboneDesDeplacemeents_kgCo2eParDepart(Float semestresIntensiteCarboneDesDeplacemeents_kgCo2eParDepart) {
        this.semestresIntensiteCarboneDesDeplacemeents_kgCo2eParDepart = semestresIntensiteCarboneDesDeplacemeents_kgCo2eParDepart;
    }


    public Float getEmissionGesFormationContinueEuropeTrain() {
        return emissionGesFormationContinueEuropeTrain;
    }

    public void setEmissionGesFormationContinueEuropeTrain(Float emissionGesFormationContinueEuropeTrain) {
        this.emissionGesFormationContinueEuropeTrain = emissionGesFormationContinueEuropeTrain;
    }

    public Float getEmissionGesFormationContinueEuropeAvion() {
        return emissionGesFormationContinueEuropeAvion;
    }

    public void setEmissionGesFormationContinueEuropeAvion(Float emissionGesFormationContinueEuropeAvion) {
        this.emissionGesFormationContinueEuropeAvion = emissionGesFormationContinueEuropeAvion;
    }

    public Float getEmissionGesFormationContinueEurope() {
        return emissionGesFormationContinueEurope;
    }

    public void setEmissionGesFormationContinueEurope(Float emissionGesFormationContinueEurope) {
        this.emissionGesFormationContinueEurope = emissionGesFormationContinueEurope;
    }

    public Float getEmissionGesFormationContinueHorsEurope() {
        return emissionGesFormationContinueHorsEurope;
    }

    public void setEmissionGesFormationContinueHorsEurope(Float emissionGesFormationContinueHorsEurope) {
        this.emissionGesFormationContinueHorsEurope = emissionGesFormationContinueHorsEurope;
    }

    public Float getFormationContinueProportionDeDeparts() {
        return FormationContinueProportionDeDeparts;
    }

    public void setFormationContinueProportionDeDeparts(Float formationContinueProportionDeDeparts) {
        FormationContinueProportionDeDeparts = formationContinueProportionDeDeparts;
    }

    public Float getFormationContinuePartEuropeVsNonEurope() {
        return FormationContinuePartEuropeVsNonEurope;
    }

    public void setFormationContinuePartEuropeVsNonEurope(Float formationContinuePartEuropeVsNonEurope) {
        FormationContinuePartEuropeVsNonEurope = formationContinuePartEuropeVsNonEurope;
    }

    public Float getFormationContinueDistanceMoyenneHorsEurope() {
        return FormationContinueDistanceMoyenneHorsEurope;
    }

    public void setFormationContinueDistanceMoyenneHorsEurope(Float formationContinueDistanceMoyenneHorsEurope) {
        FormationContinueDistanceMoyenneHorsEurope = formationContinueDistanceMoyenneHorsEurope;
    }

    public Float getFormationContinuePartTrainEnEurope() {
        return FormationContinuePartTrainEnEurope;
    }

    public void setFormationContinuePartTrainEnEurope(Float formationContinuePartTrainEnEurope) {
        FormationContinuePartTrainEnEurope = formationContinuePartTrainEnEurope;
    }

    public Float getFormationContinueDistancemoyenneEuropeAvion() {
        return FormationContinueDistancemoyenneEuropeAvion;
    }

    public void setFormationContinueDistancemoyenneEuropeAvion(Float formationContinueDistancemoyenneEuropeAvion) {
        FormationContinueDistancemoyenneEuropeAvion = formationContinueDistancemoyenneEuropeAvion;
    }

    public Float getFormationContinueDistanceMoyenneEuropeTrain() {
        return FormationContinueDistanceMoyenneEuropeTrain;
    }

    public void setFormationContinueDistanceMoyenneEuropeTrain(Float formationContinueDistanceMoyenneEuropeTrain) {
        FormationContinueDistanceMoyenneEuropeTrain = formationContinueDistanceMoyenneEuropeTrain;
    }

    public Float getFormationContinueIntensiteCarboneDesDeplacemeents_gCo2eParKm() {
        return FormationContinueIntensiteCarboneDesDeplacemeents_gCo2eParKm;
    }

    public void setFormationContinueIntensiteCarboneDesDeplacemeents_gCo2eParKm(Float formationContinueIntensiteCarboneDesDeplacemeents_gCo2eParKm) {
        FormationContinueIntensiteCarboneDesDeplacemeents_gCo2eParKm = formationContinueIntensiteCarboneDesDeplacemeents_gCo2eParKm;
    }

    public Float getFormationContinueIntensiteCarboneDesDeplacemeents_kgCo2eParDepart() {
        return FormationContinueIntensiteCarboneDesDeplacemeents_kgCo2eParDepart;
    }

    public void setFormationContinueIntensiteCarboneDesDeplacemeents_kgCo2eParDepart(Float formationContinueIntensiteCarboneDesDeplacemeents_kgCo2eParDepart) {
        FormationContinueIntensiteCarboneDesDeplacemeents_kgCo2eParDepart = formationContinueIntensiteCarboneDesDeplacemeents_kgCo2eParDepart;
    }
}