package tcucl.back_tcucl.dto.onglet.batimentImmobilisationMobilier;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import tcucl.back_tcucl.entity.onglet.batiment.BatimentExistantOuNeufConstruit;
import tcucl.back_tcucl.entity.onglet.batiment.enums.EnumBatiment_TypeBatiment;
import tcucl.back_tcucl.entity.onglet.batiment.enums.EnumBatiment_TypeStructure;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BatimentExistantOuNeufConstruitDto {

    private Long id;
    private String nom_ou_adresse;
    private LocalDate dateConstruction;
    private LocalDate dateDerniereGrosseRenovation;
    private Boolean acvBatimentRealisee;
    private Float emissionsGesReellesTCO2;
    private Float surfaceEnM2;
    private EnumBatiment_TypeBatiment typeBatiment;
    private EnumBatiment_TypeStructure typeStructure;

    public BatimentExistantOuNeufConstruitDto() {
    }

    public BatimentExistantOuNeufConstruitDto(BatimentExistantOuNeufConstruit entity) {
        this.id = entity.getId();
        this.nom_ou_adresse = entity.getNom_ou_adresse();
        this.dateConstruction = entity.getDateConstruction();
        this.dateDerniereGrosseRenovation = entity.getDateDerniereGrosseRenovation();
        this.acvBatimentRealisee = entity.getAcvBatimentRealisee();
        this.emissionsGesReellesTCO2 = entity.getEmissionsGesReellesTCO2();
        this.surfaceEnM2 = entity.getSurfaceEnM2();
        this.typeBatiment = entity.getTypeBatiment();
        this.typeStructure = entity.getTypeStructure();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom_ou_adresse() {
        return nom_ou_adresse;
    }

    public void setNom_ou_adresse(String nom_ou_adresse) {
        this.nom_ou_adresse = nom_ou_adresse;
    }

    public LocalDate getDateConstruction() {
        return dateConstruction;
    }

    public void setDateConstruction(LocalDate dateConstruction) {
        this.dateConstruction = dateConstruction;
    }

    public LocalDate getDateDerniereGrosseRenovation() {
        return dateDerniereGrosseRenovation;
    }

    public void setDateDerniereGrosseRenovation(LocalDate dateDerniereGrosseRenovation) {
        this.dateDerniereGrosseRenovation = dateDerniereGrosseRenovation;
    }


    public Boolean getAcvBatimentRealisee() {
        return acvBatimentRealisee;
    }

    public void setAcvBatimentRealisee(Boolean acvBatimentRealisee) {
        this.acvBatimentRealisee = acvBatimentRealisee;
    }

    public Float getEmissionsGesReellesTCO2() {
        return emissionsGesReellesTCO2;
    }

    public void setEmissionsGesReellesTCO2(Float emissionsGesReellesTCO2) {
        this.emissionsGesReellesTCO2 = emissionsGesReellesTCO2;
    }

    public Float getSurfaceEnM2() {
        return surfaceEnM2;
    }

    public void setSurfaceEnM2(Float surfaceEnM2) {
        this.surfaceEnM2 = surfaceEnM2;
    }

    public EnumBatiment_TypeBatiment getTypeBatiment() {
        return typeBatiment;
    }

    public void setTypeBatiment(EnumBatiment_TypeBatiment typeBatiment) {
        this.typeBatiment = typeBatiment;
    }

    public EnumBatiment_TypeStructure getTypeStructure() {
        return typeStructure;
    }

    public void setTypeStructure(EnumBatiment_TypeStructure typeStructure) {
        this.typeStructure = typeStructure;
    }
}
