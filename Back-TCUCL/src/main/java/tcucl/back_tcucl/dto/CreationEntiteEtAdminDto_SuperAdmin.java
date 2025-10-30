package tcucl.back_tcucl.dto;

public class CreationEntiteEtAdminDto_SuperAdmin {

    private String nom;
    private String type;
    private String nomUtilisateur;
    private String prenomUtilisateur;
    private String emailUtilisateur;
    private Boolean estSuperAdmin;

    public CreationEntiteEtAdminDto_SuperAdmin() {
        // Constructeur par défaut requis pour la désérialisation JSON
    }

    public CreationEntiteEtAdminDto_SuperAdmin(String nom, String type, String nomUtilisateur, String prenomUtilisateur, String emailUtilisateur, Boolean estSuperAdmin) {
        this.nom = nom;
        this.type = type;
        this.nomUtilisateur = nomUtilisateur;
        this.prenomUtilisateur = prenomUtilisateur;
        this.emailUtilisateur = emailUtilisateur;
        this.estSuperAdmin = estSuperAdmin;
    }

    public CreationEntiteEtAdminDto_SuperAdmin(CreationEntiteEtAdminDto creationEntiteEtAdminDto, Boolean estSuperAdmin) {
        this.nom = creationEntiteEtAdminDto.getNom();
        this.type = creationEntiteEtAdminDto.getType();
        this.nomUtilisateur = creationEntiteEtAdminDto.getNomUtilisateur();
        this.prenomUtilisateur = creationEntiteEtAdminDto.getPrenomUtilisateur();
        this.emailUtilisateur = creationEntiteEtAdminDto.getEmailUtilisateur();
        this.estSuperAdmin = estSuperAdmin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public String getPrenomUtilisateur() {
        return prenomUtilisateur;
    }

    public void setPrenomUtilisateur(String prenomUtilisateur) {
        this.prenomUtilisateur = prenomUtilisateur;
    }

    public String getEmailUtilisateur() {
        return emailUtilisateur;
    }

    public void setEmailUtilisateur(String emailUtilisateur) {
        this.emailUtilisateur = emailUtilisateur;
    }

    public Boolean getEstSuperAdmin() {
        return estSuperAdmin;
    }

    public void setEstSuperAdmin(Boolean estSuperAdmin) {
        this.estSuperAdmin = estSuperAdmin;
    }
}
