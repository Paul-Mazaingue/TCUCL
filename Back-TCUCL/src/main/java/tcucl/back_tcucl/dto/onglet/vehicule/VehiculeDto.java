package tcucl.back_tcucl.dto.onglet.vehicule;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import tcucl.back_tcucl.entity.onglet.vehicule.Vehicule;
import tcucl.back_tcucl.entity.onglet.vehicule.enums.EnumVehicule_Type;

import java.time.LocalDate;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class VehiculeDto {

    private Long id;
    private String modeleOuImmatriculation;
    private EnumVehicule_Type typeVehicule;
    private Integer nombreVehiculesIdentiques;
    private Float nombreKilometresParVoitureMoyen;
    private LocalDate dateAjoutEnBase;

    public VehiculeDto() {
    }

    public VehiculeDto(Vehicule entity) {
        this.id = entity.getId();
        this.modeleOuImmatriculation = entity.getModeleOuImmatriculation();
        this.typeVehicule = entity.getTypeVehicule();
        this.nombreVehiculesIdentiques = entity.getNombreVehiculesIdentiques();
        this.nombreKilometresParVoitureMoyen = entity.getNombreKilometresParVoitureMoyen();
        this.dateAjoutEnBase = entity.getDateAjoutEnBase();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModeleOuImmatriculation() {
        return modeleOuImmatriculation;
    }

    public void setModeleOuImmatriculation(String modeleOuImmatriculation) {
        this.modeleOuImmatriculation = modeleOuImmatriculation;
    }

    public EnumVehicule_Type getTypeVehicule() {
        return typeVehicule;
    }

    public void setTypeVehicule(EnumVehicule_Type typeVehicule) {
        this.typeVehicule = typeVehicule;
    }

    public Integer getNombreVehiculesIdentiques() {
        return nombreVehiculesIdentiques;
    }

    public void setNombreVehiculesIdentiques(Integer nombreVehiculesIdentiques) {
        this.nombreVehiculesIdentiques = nombreVehiculesIdentiques;
    }

    public Float getNombreKilometresParVoitureMoyen() {
        return nombreKilometresParVoitureMoyen;
    }

    public void setNombreKilometresParVoitureMoyen(Float nombreKilometresParVoitureMoyen) {
        this.nombreKilometresParVoitureMoyen = nombreKilometresParVoitureMoyen;
    }

    public LocalDate getDateAjoutEnBase() {
        return dateAjoutEnBase;
    }

    public void setDateAjoutEnBase(LocalDate dateAjoutEnBase) {
        this.dateAjoutEnBase = dateAjoutEnBase;
    }
}
