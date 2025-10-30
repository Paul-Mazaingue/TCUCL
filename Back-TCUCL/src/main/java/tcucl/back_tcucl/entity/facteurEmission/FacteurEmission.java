package tcucl.back_tcucl.entity.facteurEmission;

import jakarta.persistence.*;

@Entity
@Table(name = "facteurs_emission")
public class FacteurEmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String categorie;

    private String type;

    private String unite;

    private Float facteurEmission;


    // --- Getters and Setters ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }

    public Float getFacteurEmission() {
        return facteurEmission;
    }

    public void setFacteurEmission(Float facteurEmission) {
        this.facteurEmission = facteurEmission;
    }

}
