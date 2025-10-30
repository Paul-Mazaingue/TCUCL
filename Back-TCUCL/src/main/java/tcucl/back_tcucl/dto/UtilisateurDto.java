package tcucl.back_tcucl.dto;

import tcucl.back_tcucl.entity.Utilisateur;

public class UtilisateurDto {

    private long id;
    private String nom;
    private String prenom;
    private String email;
    private Boolean estAdmin;
    private Boolean isSuperAdmin;
    private String entiteNom;
    private Long entiteId;

    public UtilisateurDto() {
    }

    public UtilisateurDto (Utilisateur utilisateur){
        this.id = utilisateur.getId();
        this.nom = utilisateur.getNom();
        this.prenom = utilisateur.getPrenom();
        this.email = utilisateur.getEmail();
        this.estAdmin = utilisateur.getEstAdmin();
        this.entiteNom = utilisateur.getEntite().getNom();
        this.entiteId = utilisateur.getEntite().getId();
        this.isSuperAdmin = utilisateur.getEstSuperAdmin();

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Boolean getEstAdmin() {
        return estAdmin;
    }

    public void setEstAdmin(Boolean estAdmin) {
        this.estAdmin = estAdmin;
    }

    public Boolean getSuperAdmin() {
        return isSuperAdmin;
    }

    public void setSuperAdmin(Boolean superAdmin) {
        isSuperAdmin = superAdmin;
    }

    public String getEntiteNom() {
        return entiteNom;
    }

    public void setEntiteNom(String entiteNom) {
        this.entiteNom = entiteNom;
    }

    public Long getEntiteId() {
        return entiteId;
    }

    public void setEntiteId(Long entiteId) {
        this.entiteId = entiteId;
    }

}
