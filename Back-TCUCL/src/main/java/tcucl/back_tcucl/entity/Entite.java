package tcucl.back_tcucl.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "entite")
public class Entite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String type;

    // todo créer ENUM pour les types d'enti
    // 1. EDUC_SUP
    // 2. LYCEE
    // 3. SANTE
    // 4. LOGEMENT
    // 5. RESTAURATION

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "notes_permanentes_id")
    private NotesPermanentes notesPermanentes = new NotesPermanentes();

    @OneToMany(mappedBy = "entite", cascade = CascadeType.ALL)
    private List<Annee> annees = new ArrayList<>();


    public Entite(String nom, String type) {
        this.nom = nom;
        this.type = type;

    }

    public Entite() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Annee> getAnnees() {
        return annees;
    }

    public void addAnnee(Annee annee) {
        annees.add(annee);
        annee.setEntite(this);
    }

    public void removeAnnee(Annee annee) {
        annees.remove(annee);
        annee.setEntite(null);
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public NotesPermanentes getNotesPermanentes() {
        return notesPermanentes;
    }

    public void setNotesPermanentes(NotesPermanentes notesPermanentes) {
        this.notesPermanentes = notesPermanentes;
    }
}
