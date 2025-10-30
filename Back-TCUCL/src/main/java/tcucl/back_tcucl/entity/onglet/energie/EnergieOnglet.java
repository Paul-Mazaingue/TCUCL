package tcucl.back_tcucl.entity.onglet.energie;

import jakarta.persistence.*;
import tcucl.back_tcucl.entity.onglet.Onglet;
import tcucl.back_tcucl.entity.onglet.energie.enums.EnumEnergie_NomReseauVille;
import tcucl.back_tcucl.entity.onglet.energie.enums.EnumEnergie_UniteBois;
import tcucl.back_tcucl.entity.onglet.energie.enums.EnumEnergie_UniteFioul;
import tcucl.back_tcucl.entity.onglet.energie.enums.EnumEnergie_UniteGaz;

@Entity
@Table(name = "energie_onglet")
public class EnergieOnglet extends Onglet {

    private Float ConsoGaz = 0f;
    private Float ConsoFioul = 0f;
    private Float ConsoBois = 0f;
    private Float ConsoReseauVille = 0f;
    private Float ConsoElecChauffage = 0f;
    private Float ConsoElecSpecifique = 0f;
    private Float ConsoEau = 0f;
    // valeur stockée en base = code de l'enum,
    // valeur affichée par get = 1 valeur de l'enum
    // valeur à setter = 1 valeur de l'enum
    private Integer valeurEnumEnergieReseauVille = 0;
    private Integer valeurEnumEnergieUniteBois = 0;
    private Integer valeurEnumEnergieUniteFioul = 0;
    private Integer valeurEnumEnergieUniteGaz = 0;

    public EnergieOnglet() {
        super();
    }

    public Float getConsoEau() {
        return ConsoEau;
    }

    public void setConsoEau(Float consoEau) {
        ConsoEau = consoEau;
    }

    public Float getConsoElecSpecifique() {
        return ConsoElecSpecifique;
    }

    public void setConsoElecSpecifique(Float consoElecSpecifique) {
        ConsoElecSpecifique = consoElecSpecifique;
    }

    public Float getConsoElecChauffage() {
        return ConsoElecChauffage;
    }

    public void setConsoElecChauffage(Float consoElecChauffage) {
        ConsoElecChauffage = consoElecChauffage;
    }

    public Float getConsoReseauVille() {
        return ConsoReseauVille;
    }

    public void setConsoReseauVille(Float consoReseauVille) {
        ConsoReseauVille = consoReseauVille;
    }

    public Float getConsoBois() {
        return ConsoBois;
    }

    public void setConsoBois(Float consoBois) {
        ConsoBois = consoBois;
    }

    public Float getConsoFioul() {
        return ConsoFioul;
    }

    public void setConsoFioul(Float consoFioul) {
        ConsoFioul = consoFioul;
    }

    public Float getConsoGaz() {
        return ConsoGaz;
    }

    public void setConsoGaz(Float consoGaz) {
        ConsoGaz = consoGaz;
    }


    public void setNomReseauVille(EnumEnergie_NomReseauVille valeur) {
        this.valeurEnumEnergieReseauVille = valeur.getCode();
    }


    public void setUniteBois(EnumEnergie_UniteBois valeur) {
        this.valeurEnumEnergieUniteBois = valeur.getCode();
    }


    public void setUniteFioul(EnumEnergie_UniteFioul valeur) {
        this.valeurEnumEnergieUniteFioul = valeur.getCode();
    }


    public void setUniteGaz(EnumEnergie_UniteGaz valeur) {
        this.valeurEnumEnergieUniteGaz = valeur.getCode();
    }

    public EnumEnergie_UniteGaz getUniteGaz() {
        return valeurEnumEnergieUniteGaz != null ? EnumEnergie_UniteGaz.fromCode(valeurEnumEnergieUniteGaz) : null;
    }


    public EnumEnergie_UniteFioul getUniteFioul() {
        return valeurEnumEnergieUniteFioul != null ? EnumEnergie_UniteFioul.fromCode(valeurEnumEnergieUniteFioul) : null;
    }

    public EnumEnergie_UniteBois getUniteBois() {
        return valeurEnumEnergieUniteBois != null ? EnumEnergie_UniteBois.fromCode(valeurEnumEnergieUniteBois) : null;
    }

    public EnumEnergie_NomReseauVille getNomReseauVille() {
        return valeurEnumEnergieReseauVille != null ? EnumEnergie_NomReseauVille.fromCode(valeurEnumEnergieReseauVille) : null;
    }


    
}
