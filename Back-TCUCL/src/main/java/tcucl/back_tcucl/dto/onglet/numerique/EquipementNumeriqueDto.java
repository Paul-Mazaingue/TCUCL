package tcucl.back_tcucl.dto.onglet.numerique;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import tcucl.back_tcucl.entity.onglet.numerique.EquipementNumerique;
import tcucl.back_tcucl.entity.onglet.numerique.enums.EnumNumerique_Equipement;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EquipementNumeriqueDto {

    private Long id;

    private EnumNumerique_Equipement equipement;
    private Integer nombre;
    private Integer dureeAmortissement;
    private Boolean emissionsGesPrecisesConnues;
    private Float emissionsReellesParProduitKgCO2e;

    public EquipementNumeriqueDto() {
    }

    public EquipementNumeriqueDto(EquipementNumerique equipementNumerique) {
        this.id = equipementNumerique.getId();
        this.equipement = equipementNumerique.getEquipement();
        this.nombre = equipementNumerique.getNombre();
        this.dureeAmortissement = equipementNumerique.getDureeAmortissement();
        this.emissionsGesPrecisesConnues = equipementNumerique.getEmissionsGesPrecisesConnues();
        this.emissionsReellesParProduitKgCO2e = equipementNumerique.getEmissionsReellesParProduitKgCO2e();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EnumNumerique_Equipement getEquipement() {
        return equipement;
    }

    public void setEquipement(EnumNumerique_Equipement equipement) {
        this.equipement = equipement;
    }

    public Integer getNombre() {
        return nombre;
    }

    public void setNombre(Integer nombre) {
        this.nombre = nombre;
    }

    public Integer getDureeAmortissement() {
        return dureeAmortissement;
    }

    public void setDureeAmortissement(Integer dureeAmortissement) {
        this.dureeAmortissement = dureeAmortissement;
    }

    public Boolean getEmissionsGesPrecisesConnues() {
        return emissionsGesPrecisesConnues;
    }

    public void setEmissionsGesPrecisesConnues(Boolean emissionsGesPrecisesConnues) {
        this.emissionsGesPrecisesConnues = emissionsGesPrecisesConnues;
    }

    public Float getEmissionsReellesParProduitKgCO2e() {
        return emissionsReellesParProduitKgCO2e;
    }

    public void setEmissionsReellesParProduitKgCO2e(Float emissionsReellesParProduitKgCO2e) {
        this.emissionsReellesParProduitKgCO2e = emissionsReellesParProduitKgCO2e;
    }
}
