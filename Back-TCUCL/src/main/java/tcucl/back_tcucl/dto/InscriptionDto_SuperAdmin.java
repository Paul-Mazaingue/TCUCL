package tcucl.back_tcucl.dto;

public class InscriptionDto_SuperAdmin {

    private String nom;
    private String prenom;
    private String email;
    private boolean estAdmin;
    private boolean estSuperAdmin;
    private Long entiteId;


    public InscriptionDto_SuperAdmin(String nom, String prenom, String email, boolean estAdmin, boolean estSuperAdmin, Long entiteId) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.estAdmin = estAdmin;
        this.estSuperAdmin = estSuperAdmin;
        this.entiteId = entiteId;
    }

    public InscriptionDto_SuperAdmin() {
        // Constructeur par défaut requis pour la désérialisation JSON
    }

    public InscriptionDto_SuperAdmin(InscriptionDto inscriptionDto, boolean estSuperAdmin, Long entiteId) {
        this.nom = inscriptionDto.getNom();
        this.prenom = inscriptionDto.getPrenom();
        this.email = inscriptionDto.getEmail();
        this.estAdmin = inscriptionDto.getEstAdmin();
        this.estSuperAdmin = estSuperAdmin;
        this.entiteId = entiteId;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEstAdmin() {
        return estAdmin;
    }

    public void setEstAdmin(boolean estAdmin) {
        this.estAdmin = estAdmin;
    }

    public boolean isEstSuperAdmin() {
        return estSuperAdmin;
    }

    public void setEstSuperAdmin(boolean estSuperAdmin) {
        this.estSuperAdmin = estSuperAdmin;
    }

    public Long getEntiteId() {
        return entiteId;
    }

    public void setEntiteId(Long entiteId) {
        this.entiteId = entiteId;
    }
}
