package tcucl.back_tcucl.dto.onglet.dechet;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import tcucl.back_tcucl.entity.onglet.dechet.DechetOnglet;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DechetOngletDto {

    private Long id;
    private Boolean estTermine;
    private String note;

    private DechetDto ordures_menageres;
    private DechetDto cartons;
    private DechetDto verre;
    private DechetDto metaux;
    private DechetDto textile;

    public DechetOngletDto() {
    }

    public DechetOngletDto(DechetOnglet entity) {
        this.id = entity.getId();
        this.estTermine = entity.getEstTermine();
        this.note = entity.getNote();
        this.ordures_menageres = new DechetDto(entity.getOrdures_menageres());
        this.cartons = new DechetDto(entity.getCartons());
        this.verre = new DechetDto(entity.getVerre());
        this.metaux = new DechetDto(entity.getMetaux());
        this.textile = new DechetDto(entity.getTextile());
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

    public DechetDto getOrdures_menageres() {
        return ordures_menageres;
    }

    public void setOrdures_menageres(DechetDto ordures_menageres) {
        this.ordures_menageres = ordures_menageres;
    }

    public DechetDto getCartons() {
        return cartons;
    }

    public void setCartons(DechetDto cartons) {
        this.cartons = cartons;
    }

    public DechetDto getVerre() {
        return verre;
    }

    public void setVerre(DechetDto verre) {
        this.verre = verre;
    }

    public DechetDto getMetaux() {
        return metaux;
    }

    public void setMetaux(DechetDto metaux) {
        this.metaux = metaux;
    }

    public DechetDto getTextile() {
        return textile;
    }

    public void setTextile(DechetDto textile) {
        this.textile = textile;
    }
}
