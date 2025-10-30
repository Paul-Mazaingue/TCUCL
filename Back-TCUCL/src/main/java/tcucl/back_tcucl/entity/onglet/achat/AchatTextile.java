package tcucl.back_tcucl.entity.onglet.achat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "achat_textile")
public class AchatTextile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer Chemise_nb = 0;
    private Integer Polaire_nb = 0;
    private Integer Pull_Acrylique_nb = 0;
    private Integer Pull_Coton_nb = 0;
    private Integer T_shirt_polyester_nb = 0;
    private Integer Jean_nb = 0;
    private Integer Sweat_nb = 0;
    private Integer Veste_Anorak_nb = 0;
    private Integer Manteau_nb = 0;
    private Integer Chaussure_nb = 0;

    public AchatTextile() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Integer getChemise_nb() {
        return Chemise_nb;
    }

    public void setChemise_nb(Integer chemise_nb) {
        Chemise_nb = chemise_nb;
    }

    public Integer getPolaire_nb() {
        return Polaire_nb;
    }

    public void setPolaire_nb(Integer polaire_nb) {
        Polaire_nb = polaire_nb;
    }

    public Integer getPull_Acrylique_nb() {
        return Pull_Acrylique_nb;
    }

    public void setPull_Acrylique_nb(Integer pull_Acrylique_nb) {
        Pull_Acrylique_nb = pull_Acrylique_nb;
    }

    public Integer getPull_Coton_nb() {
        return Pull_Coton_nb;
    }

    public void setPull_Coton_nb(Integer pull_Coton_nb) {
        Pull_Coton_nb = pull_Coton_nb;
    }

    public Integer getT_shirt_polyester_nb() {
        return T_shirt_polyester_nb;
    }

    public void setT_shirt_polyester_nb(Integer t_shirt_polyester_nb) {
        T_shirt_polyester_nb = t_shirt_polyester_nb;
    }

    public Integer getJean_nb() {
        return Jean_nb;
    }

    public void setJean_nb(Integer jean_nb) {
        Jean_nb = jean_nb;
    }

    public Integer getSweat_nb() {
        return Sweat_nb;
    }

    public void setSweat_nb(Integer sweat_nb) {
        Sweat_nb = sweat_nb;
    }

    public Integer getVeste_Anorak_nb() {
        return Veste_Anorak_nb;
    }

    public void setVeste_Anorak_nb(Integer veste_Anorak_nb) {
        Veste_Anorak_nb = veste_Anorak_nb;
    }

    public Integer getManteau_nb() {
        return Manteau_nb;
    }

    public void setManteau_nb(Integer manteau_nb) {
        Manteau_nb = manteau_nb;
    }

    public Integer getChaussure_nb() {
        return Chaussure_nb;
    }

    public void setChaussure_nb(Integer chaussure_nb) {
        Chaussure_nb = chaussure_nb;
    }
}
