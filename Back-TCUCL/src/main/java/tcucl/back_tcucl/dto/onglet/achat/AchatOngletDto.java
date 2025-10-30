package tcucl.back_tcucl.dto.onglet.achat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import tcucl.back_tcucl.entity.onglet.achat.AchatOnglet;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AchatOngletDto {

    private Long id;
    private Boolean estTermine;
    private String note;

    private AchatConsommableDto achatConsommable;
    private AchatRestaurationDto achatRestauration;
    private AchatTextileDto achatTextile;

    public AchatOngletDto() {
    }
    public AchatOngletDto(AchatOnglet entite) {
        this.id = entite.getId();
        this.estTermine = entite.getEstTermine();
        this.note = entite.getNote();
        this.achatConsommable = new AchatConsommableDto(entite.getAchatConsommable());
        this.achatRestauration = new AchatRestaurationDto(entite.getAchatRestauration());
        this.achatTextile = new AchatTextileDto(entite.getAchatTextile());
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

    public AchatConsommableDto getAchatConsommable() {
        return achatConsommable;
    }

    public void setAchatConsommable(AchatConsommableDto achatConsommable) {
        this.achatConsommable = achatConsommable;
    }

    public AchatRestaurationDto getAchatRestauration() {
        return achatRestauration;
    }

    public void setAchatRestauration(AchatRestaurationDto achatRestauration) {
        this.achatRestauration = achatRestauration;
    }

    public AchatTextileDto getAchatTextile() {
        return achatTextile;
    }

    public void setAchatTextile(AchatTextileDto achatTextile) {
        this.achatTextile = achatTextile;
    }
}
