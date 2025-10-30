package tcucl.back_tcucl.dto.onglet.autreMobFr;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import tcucl.back_tcucl.entity.onglet.AutreMobFrOnglet;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AutreMobFrOngletDto {

    private Long id;
    private Boolean estTermine;
    private String note;


    private Float salarieNbAllerSimple_VoitureThermique;
    private Float salarieNbAllerSimple_VoitureElectrique;
    private Float salarieNbAllerSimple_Avion;
    private Float salarieNbAllerSimple_France_TrainRegional;
    private Float salarieNbAllerSimple_France_TrainGrandesLignes;
    private Float salarieNbAllerSimple_Autocar;

    private Float salarieDistanceTotale_VoitureThermique;
    private Float salarieDistanceTotale_VoitureElectrique;
    private Float salarieDistanceTotale_Avion;
    private Float salarieDistanceTotale_France_TrainRegional;
    private Float salarieDistanceTotale_France_TrainGrandesLignes;
    private Float salarieDistanceTotale_Autocar;

    private Float etudiantNbAllerSimple_VoitureThermique;
    private Float etudiantNbAllerSimple_VoitureElectrique;
    private Float etudiantNbAllerSimple_Avion;
    private Float etudiantNbAllerSimple_France_TrainRegional;
    private Float etudiantNbAllerSimple_France_TrainGrandesLignes;
    private Float etudiantNbAllerSimple_Autocar;

    private Float etudiantDistanceTotale_VoitureThermique;
    private Float etudiantDistanceTotale_VoitureElectrique;
    private Float etudiantDistanceTotale_Avion;
    private Float etudiantDistanceTotale_France_TrainRegional;
    private Float etudiantDistanceTotale_France_TrainGrandesLignes;
    private Float etudiantDistanceTotale_Autocar;

    public AutreMobFrOngletDto() {
    }

    public AutreMobFrOngletDto(AutreMobFrOnglet autreMobFrOnglet) {
        this.id = autreMobFrOnglet.getId();
        this.estTermine = autreMobFrOnglet.getEstTermine();
        this.note = autreMobFrOnglet.getNote();

        this.salarieNbAllerSimple_VoitureThermique = autreMobFrOnglet.getSalarieNbAllerSimple_VoitureThermique();
        this.salarieNbAllerSimple_VoitureElectrique = autreMobFrOnglet.getSalarieNbAllerSimple_VoitureElectrique();
        this.salarieNbAllerSimple_Avion = autreMobFrOnglet.getSalarieNbAllerSimple_Avion();
        this.salarieNbAllerSimple_France_TrainRegional = autreMobFrOnglet.getSalarieNbAllerSimple_France_TrainRegional();
        this.salarieNbAllerSimple_France_TrainGrandesLignes = autreMobFrOnglet.getSalarieNbAllerSimple_France_TrainGrandesLignes();
        this.salarieNbAllerSimple_Autocar = autreMobFrOnglet.getSalarieNbAllerSimple_Autocar();

        this.salarieDistanceTotale_VoitureThermique = autreMobFrOnglet.getSalarieDistanceTotale_VoitureThermique();
        this.salarieDistanceTotale_VoitureElectrique = autreMobFrOnglet.getSalarieDistanceTotale_VoitureElectrique();
        this.salarieDistanceTotale_Avion = autreMobFrOnglet.getSalarieDistanceTotale_Avion();
        this.salarieDistanceTotale_France_TrainRegional = autreMobFrOnglet.getSalarieDistanceTotale_France_TrainRegional();
        this.salarieDistanceTotale_France_TrainGrandesLignes = autreMobFrOnglet.getSalarieDistanceTotale_France_TrainGrandesLignes();
        this.salarieDistanceTotale_Autocar = autreMobFrOnglet.getSalarieDistanceTotale_Autocar();

        this.etudiantNbAllerSimple_VoitureThermique = autreMobFrOnglet.getEtudiantNbAllerSimple_VoitureThermique();
        this.etudiantNbAllerSimple_VoitureElectrique = autreMobFrOnglet.getEtudiantNbAllerSimple_VoitureElectrique();
        this.etudiantNbAllerSimple_Avion = autreMobFrOnglet.getEtudiantNbAllerSimple_Avion();
        this.etudiantNbAllerSimple_France_TrainRegional = autreMobFrOnglet.getEtudiantNbAllerSimple_France_TrainRegional();
        this.etudiantNbAllerSimple_France_TrainGrandesLignes = autreMobFrOnglet.getEtudiantNbAllerSimple_France_TrainGrandesLignes();
        this.etudiantNbAllerSimple_Autocar = autreMobFrOnglet.getEtudiantNbAllerSimple_Autocar();

        this.etudiantDistanceTotale_VoitureThermique = autreMobFrOnglet.getEtudiantDistanceTotale_VoitureThermique();
        this.etudiantDistanceTotale_VoitureElectrique = autreMobFrOnglet.getEtudiantDistanceTotale_VoitureElectrique();
        this.etudiantDistanceTotale_Avion = autreMobFrOnglet.getEtudiantDistanceTotale_Avion();
        this.etudiantDistanceTotale_France_TrainRegional = autreMobFrOnglet.getEtudiantDistanceTotale_France_TrainRegional();
        this.etudiantDistanceTotale_France_TrainGrandesLignes = autreMobFrOnglet.getEtudiantDistanceTotale_France_TrainGrandesLignes();
        this.etudiantDistanceTotale_Autocar = autreMobFrOnglet.getEtudiantDistanceTotale_Autocar();

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

    public Float getSalarieNbAllerSimple_VoitureThermique() {
        return salarieNbAllerSimple_VoitureThermique;
    }

    public void setSalarieNbAllerSimple_VoitureThermique(Float salarieNbAllerSimple_VoitureThermique) {
        this.salarieNbAllerSimple_VoitureThermique = salarieNbAllerSimple_VoitureThermique;
    }

    public Float getSalarieNbAllerSimple_VoitureElectrique() {
        return salarieNbAllerSimple_VoitureElectrique;
    }

    public void setSalarieNbAllerSimple_VoitureElectrique(Float salarieNbAllerSimple_VoitureElectrique) {
        this.salarieNbAllerSimple_VoitureElectrique = salarieNbAllerSimple_VoitureElectrique;
    }

    public Float getSalarieNbAllerSimple_Avion() {
        return salarieNbAllerSimple_Avion;
    }

    public void setSalarieNbAllerSimple_Avion(Float salarieNbAllerSimple_Avion) {
        this.salarieNbAllerSimple_Avion = salarieNbAllerSimple_Avion;
    }

    public Float getSalarieNbAllerSimple_France_TrainRegional() {
        return salarieNbAllerSimple_France_TrainRegional;
    }

    public void setSalarieNbAllerSimple_France_TrainRegional(Float salarieNbAllerSimple_France_TrainRegional) {
        this.salarieNbAllerSimple_France_TrainRegional = salarieNbAllerSimple_France_TrainRegional;
    }

    public Float getSalarieNbAllerSimple_France_TrainGrandesLignes() {
        return salarieNbAllerSimple_France_TrainGrandesLignes;
    }

    public void setSalarieNbAllerSimple_France_TrainGrandesLignes(Float salarieNbAllerSimple_France_TrainGrandesLignes) {
        this.salarieNbAllerSimple_France_TrainGrandesLignes = salarieNbAllerSimple_France_TrainGrandesLignes;
    }

    public Float getSalarieNbAllerSimple_Autocar() {
        return salarieNbAllerSimple_Autocar;
    }

    public void setSalarieNbAllerSimple_Autocar(Float salarieNbAllerSimple_Autocar) {
        this.salarieNbAllerSimple_Autocar = salarieNbAllerSimple_Autocar;
    }

    public Float getSalarieDistanceTotale_VoitureThermique() {
        return salarieDistanceTotale_VoitureThermique;
    }

    public void setSalarieDistanceTotale_VoitureThermique(Float salarieDistanceTotale_VoitureThermique) {
        this.salarieDistanceTotale_VoitureThermique = salarieDistanceTotale_VoitureThermique;
    }

    public Float getSalarieDistanceTotale_VoitureElectrique() {
        return salarieDistanceTotale_VoitureElectrique;
    }

    public void setSalarieDistanceTotale_VoitureElectrique(Float salarieDistanceTotale_VoitureElectrique) {
        this.salarieDistanceTotale_VoitureElectrique = salarieDistanceTotale_VoitureElectrique;
    }

    public Float getSalarieDistanceTotale_Avion() {
        return salarieDistanceTotale_Avion;
    }

    public void setSalarieDistanceTotale_Avion(Float salarieDistanceTotale_Avion) {
        this.salarieDistanceTotale_Avion = salarieDistanceTotale_Avion;
    }

    public Float getSalarieDistanceTotale_France_TrainRegional() {
        return salarieDistanceTotale_France_TrainRegional;
    }

    public void setSalarieDistanceTotale_France_TrainRegional(Float salarieDistanceTotale_France_TrainRegional) {
        this.salarieDistanceTotale_France_TrainRegional = salarieDistanceTotale_France_TrainRegional;
    }

    public Float getSalarieDistanceTotale_France_TrainGrandesLignes() {
        return salarieDistanceTotale_France_TrainGrandesLignes;
    }

    public void setSalarieDistanceTotale_France_TrainGrandesLignes(Float salarieDistanceTotale_France_TrainGrandesLignes) {
        this.salarieDistanceTotale_France_TrainGrandesLignes = salarieDistanceTotale_France_TrainGrandesLignes;
    }

    public Float getSalarieDistanceTotale_Autocar() {
        return salarieDistanceTotale_Autocar;
    }

    public void setSalarieDistanceTotale_Autocar(Float salarieDistanceTotale_Autocar) {
        this.salarieDistanceTotale_Autocar = salarieDistanceTotale_Autocar;
    }

    public Float getEtudiantNbAllerSimple_VoitureThermique() {
        return etudiantNbAllerSimple_VoitureThermique;
    }

    public void setEtudiantNbAllerSimple_VoitureThermique(Float etudiantNbAllerSimple_VoitureThermique) {
        this.etudiantNbAllerSimple_VoitureThermique = etudiantNbAllerSimple_VoitureThermique;
    }

    public Float getEtudiantNbAllerSimple_VoitureElectrique() {
        return etudiantNbAllerSimple_VoitureElectrique;
    }

    public void setEtudiantNbAllerSimple_VoitureElectrique(Float etudiantNbAllerSimple_VoitureElectrique) {
        this.etudiantNbAllerSimple_VoitureElectrique = etudiantNbAllerSimple_VoitureElectrique;
    }

    public Float getEtudiantNbAllerSimple_Avion() {
        return etudiantNbAllerSimple_Avion;
    }

    public void setEtudiantNbAllerSimple_Avion(Float etudiantNbAllerSimple_Avion) {
        this.etudiantNbAllerSimple_Avion = etudiantNbAllerSimple_Avion;
    }

    public Float getEtudiantNbAllerSimple_France_TrainRegional() {
        return etudiantNbAllerSimple_France_TrainRegional;
    }

    public void setEtudiantNbAllerSimple_France_TrainRegional(Float etudiantNbAllerSimple_France_TrainRegional) {
        this.etudiantNbAllerSimple_France_TrainRegional = etudiantNbAllerSimple_France_TrainRegional;
    }

    public Float getEtudiantNbAllerSimple_France_TrainGrandesLignes() {
        return etudiantNbAllerSimple_France_TrainGrandesLignes;
    }

    public void setEtudiantNbAllerSimple_France_TrainGrandesLignes(Float etudiantNbAllerSimple_France_TrainGrandesLignes) {
        this.etudiantNbAllerSimple_France_TrainGrandesLignes = etudiantNbAllerSimple_France_TrainGrandesLignes;
    }

    public Float getEtudiantNbAllerSimple_Autocar() {
        return etudiantNbAllerSimple_Autocar;
    }

    public void setEtudiantNbAllerSimple_Autocar(Float etudiantNbAllerSimple_Autocar) {
        this.etudiantNbAllerSimple_Autocar = etudiantNbAllerSimple_Autocar;
    }

    public Float getEtudiantDistanceTotale_VoitureThermique() {
        return etudiantDistanceTotale_VoitureThermique;
    }

    public void setEtudiantDistanceTotale_VoitureThermique(Float etudiantDistanceTotale_VoitureThermique) {
        this.etudiantDistanceTotale_VoitureThermique = etudiantDistanceTotale_VoitureThermique;
    }

    public Float getEtudiantDistanceTotale_VoitureElectrique() {
        return etudiantDistanceTotale_VoitureElectrique;
    }

    public void setEtudiantDistanceTotale_VoitureElectrique(Float etudiantDistanceTotale_VoitureElectrique) {
        this.etudiantDistanceTotale_VoitureElectrique = etudiantDistanceTotale_VoitureElectrique;
    }

    public Float getEtudiantDistanceTotale_Avion() {
        return etudiantDistanceTotale_Avion;
    }

    public void setEtudiantDistanceTotale_Avion(Float etudiantDistanceTotale_Avion) {
        this.etudiantDistanceTotale_Avion = etudiantDistanceTotale_Avion;
    }

    public Float getEtudiantDistanceTotale_France_TrainRegional() {
        return etudiantDistanceTotale_France_TrainRegional;
    }

    public void setEtudiantDistanceTotale_France_TrainRegional(Float etudiantDistanceTotale_France_TrainRegional) {
        this.etudiantDistanceTotale_France_TrainRegional = etudiantDistanceTotale_France_TrainRegional;
    }

    public Float getEtudiantDistanceTotale_France_TrainGrandesLignes() {
        return etudiantDistanceTotale_France_TrainGrandesLignes;
    }

    public void setEtudiantDistanceTotale_France_TrainGrandesLignes(Float etudiantDistanceTotale_France_TrainGrandesLignes) {
        this.etudiantDistanceTotale_France_TrainGrandesLignes = etudiantDistanceTotale_France_TrainGrandesLignes;
    }

    public Float getEtudiantDistanceTotale_Autocar() {
        return etudiantDistanceTotale_Autocar;
    }

    public void setEtudiantDistanceTotale_Autocar(Float etudiantDistanceTotale_Autocar) {
        this.etudiantDistanceTotale_Autocar = etudiantDistanceTotale_Autocar;
    }
}
