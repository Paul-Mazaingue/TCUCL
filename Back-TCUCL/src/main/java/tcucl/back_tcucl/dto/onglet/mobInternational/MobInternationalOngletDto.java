package tcucl.back_tcucl.dto.onglet.mobInternational;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import tcucl.back_tcucl.entity.onglet.mobInternationale.MobInternationalOnglet;

import java.util.List;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MobInternationalOngletDto {

    private Long id;
    private Boolean estTermine;
    private String note;
    private List<VoyageDto> voyageVersUneDestinationMobInternationale;

    public MobInternationalOngletDto() {}

    public MobInternationalOngletDto(MobInternationalOnglet entity) {
        this.id = entity.getId();
        this.estTermine = entity.getEstTermine();
        this.note = entity.getNote();

        if (entity.getVoyages() != null) {
            this.voyageVersUneDestinationMobInternationale = entity.getVoyages().stream()
                    .map(VoyageDto::new)
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

    public List<VoyageDto> getVoyageVersUneDestinationMobInternationale() {
        return voyageVersUneDestinationMobInternationale;
    }

    public void setVoyageVersUneDestinationMobInternationale(List<VoyageDto> voyageVersUneDestinationMobInternationale) {
        this.voyageVersUneDestinationMobInternationale = voyageVersUneDestinationMobInternationale;
    }

}
