package tcucl.back_tcucl.dto;

import tcucl.back_tcucl.entity.Entite;

public class EntiteNomIdDto {

    private String nom;
    private Long id;

    public EntiteNomIdDto() {
    }

    public EntiteNomIdDto(Entite entite) {
        this.nom = entite.getNom();
        this.id = entite.getId();
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
