package tcucl.back_tcucl.dto.onglet.batimentImmobilisationMobilier;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import tcucl.back_tcucl.entity.onglet.batiment.EntretienCourant;
import tcucl.back_tcucl.entity.onglet.batiment.enums.EnumBatiment_TypeBatiment;
import tcucl.back_tcucl.entity.onglet.batiment.enums.EnumBatiment_TypeTravaux;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EntretienCourantDto {

    private Long id;
    private LocalDate dateAjout;
    private String nom_adresse;
    private EnumBatiment_TypeTravaux typeTravaux;
    private LocalDate dateTravaux;
    private EnumBatiment_TypeBatiment typeBatiment;
    private Float surfaceConcernee;
    private Integer dureeAmortissement;

    public EntretienCourantDto() {
    }

    public EntretienCourantDto(EntretienCourant entity) {
        this.id = entity.getId();
        this.dateAjout = entity.getDateAjout();
        this.nom_adresse = entity.getNom_adresse();
        this.typeTravaux = entity.getTypeTravaux();
        this.dateTravaux = entity.getDateTravaux();
        this.typeBatiment = entity.getTypeBatiment();
        this.surfaceConcernee = entity.getSurfaceConcernee();
        this.dureeAmortissement = entity.getDureeAmortissement();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateAjout() {
        return dateAjout;
    }

    public void setDateAjout(LocalDate dateAjout) {
        this.dateAjout = dateAjout;
    }

    public String getNom_adresse() {
        return nom_adresse;
    }

    public void setNom_adresse(String nom_adresse) {
        this.nom_adresse = nom_adresse;
    }

    public EnumBatiment_TypeTravaux getTypeTravaux() {
        return typeTravaux;
    }

    public void setTypeTravaux(EnumBatiment_TypeTravaux typeTravaux) {
        this.typeTravaux = typeTravaux;
    }

    public LocalDate getDateTravaux() {
        return dateTravaux;
    }

    public void setDateTravaux(LocalDate dateTravaux) {
        this.dateTravaux = dateTravaux;
    }

    public EnumBatiment_TypeBatiment getTypeBatiment() {
        return typeBatiment;
    }

    public void setTypeBatiment(EnumBatiment_TypeBatiment typeBatiment) {
        this.typeBatiment = typeBatiment;
    }

    public Float getSurfaceConcernee() {
        return surfaceConcernee;
    }

    public void setSurfaceConcernee(Float surfaceConcernee) {
        this.surfaceConcernee = surfaceConcernee;
    }

    public Integer getDureeAmortissement() {
        return dureeAmortissement;
    }

    public void setDureeAmortissement(Integer dureeAmortissement) {
        this.dureeAmortissement = dureeAmortissement;
    }
}
