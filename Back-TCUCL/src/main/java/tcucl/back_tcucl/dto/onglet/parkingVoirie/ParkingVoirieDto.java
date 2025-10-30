package tcucl.back_tcucl.dto.onglet.parkingVoirie;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import tcucl.back_tcucl.entity.onglet.parkingVoirie.ParkingVoirie;
import tcucl.back_tcucl.entity.onglet.parkingVoirie.enums.EnumParkingVoirie_Type;
import tcucl.back_tcucl.entity.onglet.parkingVoirie.enums.EnumParkingVoirie_TypeStructure;

import java.time.LocalDate;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParkingVoirieDto {

    private Long id;

    private String nomOuAdresse;
    private LocalDate dateConstruction;
    private Boolean emissionsGesConnues;
    private Float emissionsGesReelles; // en TCO2e/an
    private EnumParkingVoirie_Type type; // Enum correspondant au type de bâtiment
    private Float nombreM2;
    private EnumParkingVoirie_TypeStructure typeStructure;
    private LocalDate dateAjoutEnBase;

    public ParkingVoirieDto() {
    }

    public ParkingVoirieDto(ParkingVoirie entity) {
        this.id = entity.getId();
        this.nomOuAdresse = entity.getNomOuAdresse();
        this.dateConstruction = entity.getDateConstruction();
        this.emissionsGesConnues = entity.getEmissionsGesConnues();
        this.emissionsGesReelles = entity.getEmissionsGesReelles();
        this.type = entity.getType();
        this.nombreM2 = entity.getNombreM2();
        this.typeStructure = entity.getTypeStructure();
        this.dateAjoutEnBase = entity.getDateAjoutEnBase();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomOuAdresse() {
        return nomOuAdresse;
    }

    public void setNomOuAdresse(String nomOuAdresse) {
        this.nomOuAdresse = nomOuAdresse;
    }

    public LocalDate getDateConstruction() {
        return dateConstruction;
    }

    public void setDateConstruction(LocalDate dateConstruction) {
        this.dateConstruction = dateConstruction;
    }

    public Boolean getEmissionsGesConnues() {
        return emissionsGesConnues;
    }

    public void setEmissionsGesConnues(Boolean emissionsGesConnues) {
        this.emissionsGesConnues = emissionsGesConnues;
    }

    public Float getEmissionsGesReelles() {
        return emissionsGesReelles;
    }

    public void setEmissionsGesReelles(Float emissionsGesReelles) {
        this.emissionsGesReelles = emissionsGesReelles;
    }

    public EnumParkingVoirie_Type getType() {
        return type;
    }

    public void setType(EnumParkingVoirie_Type type) {
        this.type = type;
    }

    public Float getNombreM2() {
        return nombreM2;
    }

    public void setNombreM2(Float nombreM2) {
        this.nombreM2 = nombreM2;
    }

    public EnumParkingVoirie_TypeStructure getTypeStructure() {
        return typeStructure;
    }

    public void setTypeStructure(EnumParkingVoirie_TypeStructure typeStructure) {
        this.typeStructure = typeStructure;
    }

    public LocalDate getDateAjoutEnBase() {
        return dateAjoutEnBase;
    }

    public void setDateAjoutEnBase(LocalDate dateAjoutEnBase) {
        this.dateAjoutEnBase = dateAjoutEnBase;
    }
}
