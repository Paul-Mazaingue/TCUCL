package tcucl.back_tcucl.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "utilisateur")
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nom;
    private String prenom;
    private String mdp;
    private String email;
    private Boolean estPremiereConnexion;
    private String role;
    private Boolean estAdmin;
    private Boolean estSuperAdmin;

    @Column(name = "password_changed_at")
    private Instant passwordChangedAt;

    @ManyToOne
    @JoinColumn(name = "entite_id")
    private Entite entite;

    public Utilisateur(String nom, String prenom, String mdp, String email, Boolean estPremiereConnexion, String role, Boolean estAdmin, Boolean estSuperAdmin, Entite entite) {
        this.nom = nom;
        this.prenom = prenom;
        this.mdp = mdp;
        this.email = email;
        this.estPremiereConnexion = estPremiereConnexion;
        this.role = role;
        this.estAdmin = estAdmin;
        this.estSuperAdmin = estSuperAdmin;
        this.entite = entite;
        this.passwordChangedAt = Instant.now();
    }

    public Utilisateur() {

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

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEstPremiereConnexion() {
        return estPremiereConnexion;
    }

    public void setEstPremiereConnexion(Boolean estPremiereConnexion) {
        this.estPremiereConnexion = estPremiereConnexion;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getEstAdmin() {
        return estAdmin;
    }

    public void setEstAdmin(Boolean estAdmin) {
        this.estAdmin = estAdmin;
    }

    public Boolean getEstSuperAdmin() {
        return estSuperAdmin;
    }

    public void setEstSuperAdmin(Boolean estSuperAdmin) {
        this.estSuperAdmin = estSuperAdmin;
    }

    public Entite getEntite() {
        return entite;
    }

    public void setEntite(Entite entite) {
        this.entite = entite;
    }

    public Instant getPasswordChangedAt() {
        return passwordChangedAt;
    }

    public void setPasswordChangedAt(Instant passwordChangedAt) {
        this.passwordChangedAt = passwordChangedAt;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", mdp='" + mdp + '\'' +
                ", email='" + email + '\'' +
                ", estPremiereConnexion=" + estPremiereConnexion +
                ", role='" + role + '\'' +
                ", estAdmin=" + estAdmin +
                ", estSuperAdmin=" + estSuperAdmin +
                ", entite=" + entite +
                '}';
    }
}