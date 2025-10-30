package tcucl.back_tcucl.entity.onglet.batiment;

import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import tcucl.back_tcucl.dto.onglet.batimentImmobilisationMobilier.BatimentExistantOuNeufConstruitDto;
import tcucl.back_tcucl.entity.onglet.batiment.enums.EnumBatiment_TypeBatiment;
import tcucl.back_tcucl.entity.onglet.batiment.enums.EnumBatiment_TypeStructure;

import java.time.LocalDate;

@Entity
@Table(name = "batiment_existant_ou_neuf_construit")
public class BatimentExistantOuNeufConstruit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom_ou_adresse;
    private LocalDate dateConstruction;
    private LocalDate dateDerniereGrosseRenovation;
    private Boolean acvBatimentRealisee;
    private Float emissionsGesReellesTCO2;
    private Integer valeurEnumTypeBatiment;
    private Float surfaceEnM2;
    private Integer valeurEnumTypeStructure;

    public BatimentExistantOuNeufConstruit() {
    }

    public BatimentExistantOuNeufConstruit(String nom_ou_adresse, LocalDate dateConstruction, LocalDate dateDerniereGrosseRenovation, Boolean acvBatimentRealisee, Float emissionsGesReellesTCO2, Integer valeurEnumTypeBatiment, Float surfaceEnM2, Integer valeurEnumTypeStructure) {
        this.nom_ou_adresse = nom_ou_adresse;
        this.dateConstruction = dateConstruction;
        this.dateDerniereGrosseRenovation = dateDerniereGrosseRenovation;
        this.acvBatimentRealisee = acvBatimentRealisee;
        this.emissionsGesReellesTCO2 = emissionsGesReellesTCO2;
        this.valeurEnumTypeBatiment = valeurEnumTypeBatiment;
        this.surfaceEnM2 = surfaceEnM2;
        this.valeurEnumTypeStructure = valeurEnumTypeStructure;
    }

    public BatimentExistantOuNeufConstruit(BatimentExistantOuNeufConstruitDto batimentExistantOuNeufConstruitDto) {
        this.setId(batimentExistantOuNeufConstruitDto.getId());
        this.setNom_ou_adresse(batimentExistantOuNeufConstruitDto.getNom_ou_adresse());
        this.setDateConstruction(batimentExistantOuNeufConstruitDto.getDateConstruction());
        this.setDateDerniereGrosseRenovation(batimentExistantOuNeufConstruitDto.getDateDerniereGrosseRenovation());
        this.setAcvBatimentRealisee(batimentExistantOuNeufConstruitDto.getAcvBatimentRealisee());
        this.setEmissionsGesReellesTCO2(batimentExistantOuNeufConstruitDto.getEmissionsGesReellesTCO2());
        this.setTypeBatiment(batimentExistantOuNeufConstruitDto.getTypeBatiment());
        this.setSurfaceEnM2(batimentExistantOuNeufConstruitDto.getSurfaceEnM2());
        this.setTypeStructure(batimentExistantOuNeufConstruitDto.getTypeStructure());
    }

    @AssertTrue(message = "Le type de bâtiment doit être vide ou 'NA' si une ACV bâtiment est réalisée.")
    public Boolean assertTypeBatimentVideSiAcv() {
        if (Boolean.TRUE.equals(acvBatimentRealisee)) {
            return valeurEnumTypeBatiment == null
                    || valeurEnumTypeBatiment == 0
                    || valeurEnumTypeBatiment.equals(EnumBatiment_TypeBatiment.NA.getCode());
        }
        return true;
    }
    @AssertTrue(message = "Le type de structure doit être vide ou 'NA' si une ACV bâtiment est réalisée.")
    public Boolean assertTypeStructureVideSiAcv() {
        if (Boolean.TRUE.equals(acvBatimentRealisee)) {
            return valeurEnumTypeStructure == null
                    || valeurEnumTypeStructure == 0
                    || valeurEnumTypeStructure.equals(EnumBatiment_TypeStructure.NA.getCode());
        }
        return true;
    }
    @AssertTrue(message = "Les émissions GES réelles doivent être nulles ou 0 si aucune ACV bâtiment n'est réalisée.")
    public Boolean assertEmissionVideSiPasAcv() {
        if (Boolean.FALSE.equals(acvBatimentRealisee)) {
            return emissionsGesReellesTCO2 == null || emissionsGesReellesTCO2 == 0.0f;
        }
        return true;
    }
    @AssertTrue(message = "L'information sur la réalisation d'une ACV bâtiment doit être renseignée.")
    public Boolean assertAcvRenseignee() {
        return acvBatimentRealisee != null;
    }



    // Getters et Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom_ou_adresse() {
        return nom_ou_adresse;
    }

    public void setNom_ou_adresse(String nom) {
        this.nom_ou_adresse = nom;
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

    public void setEmissionsGesReellesTCO2(Float emissionsGesReelles) {
        this.emissionsGesReellesTCO2 = emissionsGesReelles;
    }


    public void setTypeBatiment(EnumBatiment_TypeBatiment valeur) {
        this.valeurEnumTypeBatiment = valeur.getCode();
    }

    public EnumBatiment_TypeBatiment getTypeBatiment() {
        return this.valeurEnumTypeBatiment != null ? EnumBatiment_TypeBatiment.fromCode(this.valeurEnumTypeBatiment) : null;
    }

    public Float getSurfaceEnM2() {
        return surfaceEnM2;
    }

    public void setSurfaceEnM2(Float surfaceEnM2) {
        this.surfaceEnM2 = surfaceEnM2;
    }

    public void setTypeStructure(EnumBatiment_TypeStructure valeur) {
        this.valeurEnumTypeStructure = valeur.getCode();
    }

    public EnumBatiment_TypeStructure getTypeStructure() {
        return this.valeurEnumTypeStructure != null ? EnumBatiment_TypeStructure.fromCode(this.valeurEnumTypeStructure) : null;
    }

}
