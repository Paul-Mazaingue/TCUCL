package tcucl.back_tcucl.dto;

public class ModificationUtilisateurParAdminDto {

    private String nom;
    private String prenom;
    private String email;
    private boolean estAdmin;

    public ModificationUtilisateurParAdminDto(String nom, String prenom, String email, boolean estAdmin) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.estAdmin = estAdmin;
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
}
