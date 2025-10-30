package tcucl.back_tcucl.dto.onglet.vehicule;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import tcucl.back_tcucl.entity.onglet.vehicule.VehiculeOnglet;

import java.util.List;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class VehiculeOngletDto {
    private Long id;
    private String note;
    private Boolean estTermine;
    private List<VehiculeDto> vehiculeList;

    public VehiculeOngletDto() {
    }

    public VehiculeOngletDto(VehiculeOnglet entity) {
        this.id = entity.getId();
        this.note = entity.getNote();
        this.estTermine = entity.getEstTermine();
        if (entity.getVehiculeList() != null) {
            this.vehiculeList = entity.getVehiculeList().stream()
                    .map(VehiculeDto::new)
                    .collect(Collectors.toList());
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Boolean getEstTermine() {
        return estTermine;
    }

    public void setEstTermine(Boolean estTermine) {
        this.estTermine = estTermine;
    }

    public List<VehiculeDto> getVehiculeList() {
        return vehiculeList;
    }

    public void setVehiculeList(List<VehiculeDto> vehiculeList) {
        this.vehiculeList = vehiculeList;
    }
}
