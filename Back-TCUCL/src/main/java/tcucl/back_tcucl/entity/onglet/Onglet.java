package tcucl.back_tcucl.entity.onglet;

import jakarta.persistence.*;
import tcucl.back_tcucl.entity.Annee;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Onglet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String note;
    private Boolean estTermine;

    @ManyToOne
    @JoinColumn(name = "annee_id")
    private Annee annee;

    public Onglet() {
        this.note = "";
        this.estTermine = false;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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

    public Annee getAnnee() {
        return annee;
    }

    public void setAnnee(Annee annee) {
        this.annee = annee;
    }

    public <T extends Onglet> T getOngletDeClass(Class<T> clazz){
        return this.getAnnee().getOngletByType(clazz);
    }
}
