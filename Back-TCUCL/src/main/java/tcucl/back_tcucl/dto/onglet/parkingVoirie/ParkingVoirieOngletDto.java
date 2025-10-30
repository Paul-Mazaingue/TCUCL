package tcucl.back_tcucl.dto.onglet.parkingVoirie;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import tcucl.back_tcucl.entity.onglet.parkingVoirie.ParkingVoirieOnglet;

import java.util.List;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParkingVoirieOngletDto {
    private Long id;
    private Boolean estTermine;
    private String note;
    private List<ParkingVoirieDto> parkingVoirieList;


    public ParkingVoirieOngletDto() {}

    public ParkingVoirieOngletDto(ParkingVoirieOnglet entity) {
        this.id = entity.getId();
        this.estTermine = entity.getEstTermine();
        this.note = entity.getNote();

        if (entity.getParkingVoirieList() != null) {
            this.parkingVoirieList = entity.getParkingVoirieList().stream()
                    .map(ParkingVoirieDto::new)
                    .collect(Collectors.toList());
        }
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

    public List<ParkingVoirieDto> getParkingVoirieList() {
        return parkingVoirieList;
    }

    public void setParkingVoirieList(List<ParkingVoirieDto> parkingVoirieList) {
        this.parkingVoirieList = parkingVoirieList;
    }
}
