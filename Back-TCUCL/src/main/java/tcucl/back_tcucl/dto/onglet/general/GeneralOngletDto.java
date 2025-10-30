package tcucl.back_tcucl.dto.onglet.general;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import tcucl.back_tcucl.entity.onglet.GeneralOnglet;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeneralOngletDto {

    private Long id;
    private String note;
    private Boolean estTermine;

    private Integer nbSalarie;
    private Integer nbEtudiant;

    public GeneralOngletDto() {
    }
    public GeneralOngletDto(GeneralOnglet entity) {
        this.id = entity.getId();
        this.note = entity.getNote();
        this.estTermine = entity.getEstTermine();
        this.nbSalarie = entity.getNbSalarie();
        this.nbEtudiant = entity.getNbEtudiant();
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

    public Integer getNbSalarie() {
        return nbSalarie;
    }

    public void setNbSalarie(Integer nbSalarie) {
        this.nbSalarie = nbSalarie;
    }

    public Integer getNbEtudiant() {
        return nbEtudiant;
    }

    public void setNbEtudiant(Integer nbEtudiant) {
        this.nbEtudiant = nbEtudiant;
    }
}
