package tcucl.back_tcucl.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "notes_permanentes")
public class NotesPermanentes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String NoteEnergie = "";
    private String NoteEnergieFugitive = "";
    private String NoteMobiliteDomicileTravail = "";
    private String NoteAutreMobiliteFr = "";
    private String NoteMobiliteInternationale = "";
    private String NoteBatiment = "";
    private String NoteParkings = "";
    private String NoteAutomobile = "";
    private String NoteNumerique = "";
    private String NoteImmobilisation = "";
    private String NoteAchat = "";
    private String NoteDechet = "";


    public NotesPermanentes() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNoteEnergie() {
        return NoteEnergie;
    }

    public void setNoteEnergie(String noteEnergie) {
        NoteEnergie = noteEnergie;
    }

    public String getNoteEnergieFugitive() {
        return NoteEnergieFugitive;
    }

    public void setNoteEnergieFugitive(String noteEnergieFugitive) {
        NoteEnergieFugitive = noteEnergieFugitive;
    }

    public String getNoteMobiliteDomicileTravail() {
        return NoteMobiliteDomicileTravail;
    }

    public void setNoteMobiliteDomicileTravail(String noteMobiliteDomicileTravail) {
        NoteMobiliteDomicileTravail = noteMobiliteDomicileTravail;
    }

    public String getNoteAutreMobiliteFr() {
        return NoteAutreMobiliteFr;
    }

    public void setNoteAutreMobiliteFr(String noteAutreMobiliteFr) {
        NoteAutreMobiliteFr = noteAutreMobiliteFr;
    }

    public String getNoteMobiliteInternationale() {
        return NoteMobiliteInternationale;
    }

    public void setNoteMobiliteInternationale(String noteMobiliteInternationale) {
        NoteMobiliteInternationale = noteMobiliteInternationale;
    }

    public String getNoteBatiment() {
        return NoteBatiment;
    }

    public void setNoteBatiment(String noteBatiment) {
        NoteBatiment = noteBatiment;
    }

    public String getNoteParkings() {
        return NoteParkings;
    }

    public void setNoteParkings(String noteParkings) {
        NoteParkings = noteParkings;
    }

    public String getNoteAutomobile() {
        return NoteAutomobile;
    }

    public void setNoteAutomobile(String noteAutomobile) {
        NoteAutomobile = noteAutomobile;
    }

    public String getNoteNumerique() {
        return NoteNumerique;
    }

    public void setNoteNumerique(String noteNumerique) {
        NoteNumerique = noteNumerique;
    }

    public String getNoteImmobilisation() {
        return NoteImmobilisation;
    }

    public void setNoteImmobilisation(String noteImmobilisation) {
        NoteImmobilisation = noteImmobilisation;
    }

    public String getNoteAchat() {
        return NoteAchat;
    }

    public void setNoteAchat(String noteAchat) {
        NoteAchat = noteAchat;
    }

    public String getNoteDechet() {
        return NoteDechet;
    }

    public void setNoteDechet(String noteDechet) {
        NoteDechet = noteDechet;
    }
}
