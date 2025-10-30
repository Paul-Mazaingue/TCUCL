package tcucl.back_tcucl.entity.onglet;

import jakarta.persistence.*;

@Entity
@Table(name = "autre_mob_fr_onglet")
public class AutreMobFrOnglet extends Onglet {

    private Float salarieNbAllerSimple_VoitureThermique = 0f;
    private Float salarieNbAllerSimple_VoitureElectrique = 0f;
    private Float salarieNbAllerSimple_Avion = 0f;
    private Float salarieNbAllerSimple_France_TrainRegional = 0f;
    private Float salarieNbAllerSimple_France_TrainGrandesLignes = 0f;
    private Float salarieNbAllerSimple_Autocar = 0f;

    private Float salarieDistanceTotale_VoitureThermique = 0f;
    private Float salarieDistanceTotale_VoitureElectrique = 0f;
    private Float salarieDistanceTotale_Avion = 0f;
    private Float salarieDistanceTotale_France_TrainRegional = 0f;
    private Float salarieDistanceTotale_France_TrainGrandesLignes = 0f;
    private Float salarieDistanceTotale_Autocar = 0f;

    private Float etudiantNbAllerSimple_VoitureThermique = 0f;
    private Float etudiantNbAllerSimple_VoitureElectrique = 0f;
    private Float etudiantNbAllerSimple_Avion = 0f;
    private Float etudiantNbAllerSimple_France_TrainRegional = 0f;
    private Float etudiantNbAllerSimple_France_TrainGrandesLignes = 0f;
    private Float etudiantNbAllerSimple_Autocar = 0f;

    private Float etudiantDistanceTotale_VoitureThermique = 0f;
    private Float etudiantDistanceTotale_VoitureElectrique = 0f;
    private Float etudiantDistanceTotale_Avion = 0f;
    private Float etudiantDistanceTotale_France_TrainRegional = 0f;
    private Float etudiantDistanceTotale_France_TrainGrandesLignes = 0f;
    private Float etudiantDistanceTotale_Autocar = 0f;

    public AutreMobFrOnglet() {
        super();
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
