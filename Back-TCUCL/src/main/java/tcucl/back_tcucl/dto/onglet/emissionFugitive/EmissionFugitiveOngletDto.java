package tcucl.back_tcucl.dto.onglet.emissionFugitive;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import tcucl.back_tcucl.entity.onglet.emissionFugitive.EmissionFugitiveOnglet;

import java.util.List;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmissionFugitiveOngletDto {
    private Long id;
    private Boolean estTermine;
    private String note;

    List<MachineEmissionFugitiveDto> machinesEmissionFugitive;

    public EmissionFugitiveOngletDto() {
    }

    public EmissionFugitiveOngletDto(EmissionFugitiveOnglet entity) {
        this.id = entity.getId();
        this.estTermine = entity.getEstTermine();
        this.note = entity.getNote();

        if (entity.getMachinesEmissionFugitive() != null) {
            this.machinesEmissionFugitive = entity.getMachinesEmissionFugitive().stream()
                    .map(MachineEmissionFugitiveDto::new)
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

    public List<MachineEmissionFugitiveDto> getMachinesEmissionFugitive() {
        return machinesEmissionFugitive;
    }

    public void setMachinesEmissionFugitive(List<MachineEmissionFugitiveDto> machinesEmissionFugitive) {
        this.machinesEmissionFugitive = machinesEmissionFugitive;
    }
}
