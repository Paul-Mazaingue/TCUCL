package tcucl.back_tcucl.dto.onglet.energie;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import tcucl.back_tcucl.entity.onglet.energie.EnergieOnglet;
import tcucl.back_tcucl.entity.onglet.energie.enums.EnumEnergie_NomReseauVille;
import tcucl.back_tcucl.entity.onglet.energie.enums.EnumEnergie_UniteBois;
import tcucl.back_tcucl.entity.onglet.energie.enums.EnumEnergie_UniteFioul;
import tcucl.back_tcucl.entity.onglet.energie.enums.EnumEnergie_UniteGaz;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EnergieOngletDto {
    private Long id;
    private Boolean estTermine;
    private String note;

    private Float consoGaz;
    private Float consoFioul;
    private Float consoBois;
    private Float consoReseauVille;
    private Float consoElecChauffage;
    private Float consoElecSpecifique;
    private Float consoEau;
    private EnumEnergie_NomReseauVille nomReseauVille;
    private EnumEnergie_UniteBois uniteBois;
    private EnumEnergie_UniteFioul uniteFioul;
    private EnumEnergie_UniteGaz uniteGaz;

    public EnergieOngletDto() {
    }

    public EnergieOngletDto(EnergieOnglet entity) {
        this.id = entity.getId();
        this.estTermine = entity.getEstTermine();
        this.note = entity.getNote();

        this.consoGaz = entity.getConsoGaz();
        this.consoFioul = entity.getConsoFioul();
        this.consoBois = entity.getConsoBois();
        this.consoReseauVille = entity.getConsoReseauVille();
        this.consoElecChauffage = entity.getConsoElecChauffage();
        this.consoElecSpecifique = entity.getConsoElecSpecifique();
        this.consoEau = entity.getConsoEau();

        this.nomReseauVille = entity.getNomReseauVille();
        this.uniteBois = entity.getUniteBois();
        this.uniteFioul = entity.getUniteFioul();
        this.uniteGaz = entity.getUniteGaz();
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

    public Float getConsoGaz() {
        return consoGaz;
    }

    public void setConsoGaz(Float consoGaz) {
        this.consoGaz = consoGaz;
    }

    public Float getConsoFioul() {
        return consoFioul;
    }

    public void setConsoFioul(Float consoFioul) {
        this.consoFioul = consoFioul;
    }

    public Float getConsoBois() {
        return consoBois;
    }

    public void setConsoBois(Float consoBois) {
        this.consoBois = consoBois;
    }

    public Float getConsoReseauVille() {
        return consoReseauVille;
    }

    public void setConsoReseauVille(Float consoReseauVille) {
        this.consoReseauVille = consoReseauVille;
    }

    public Float getConsoElecChauffage() {
        return consoElecChauffage;
    }

    public void setConsoElecChauffage(Float consoElecChauffage) {
        this.consoElecChauffage = consoElecChauffage;
    }

    public Float getConsoElecSpecifique() {
        return consoElecSpecifique;
    }

    public void setConsoElecSpecifique(Float consoElecSpecifique) {
        this.consoElecSpecifique = consoElecSpecifique;
    }

    public Float getConsoEau() {
        return consoEau;
    }

    public void setConsoEau(Float consoEau) {
        this.consoEau = consoEau;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public EnumEnergie_NomReseauVille getNomReseauVille() {
        return nomReseauVille;
    }

    public void setNomReseauVille(EnumEnergie_NomReseauVille nomReseauVille) {
        this.nomReseauVille = nomReseauVille;
    }

    public EnumEnergie_UniteBois getUniteBois() {
        return uniteBois;
    }

    public void setUniteBois(EnumEnergie_UniteBois uniteBois) {
        this.uniteBois = uniteBois;
    }

    public EnumEnergie_UniteFioul getUniteFioul() {
        return uniteFioul;
    }

    public void setUniteFioul(EnumEnergie_UniteFioul uniteFioul) {
        this.uniteFioul = uniteFioul;
    }

    public EnumEnergie_UniteGaz getUniteGaz() {
        return uniteGaz;
    }

    public void setUniteGaz(EnumEnergie_UniteGaz uniteGaz) {
        this.uniteGaz = uniteGaz;
    }

}