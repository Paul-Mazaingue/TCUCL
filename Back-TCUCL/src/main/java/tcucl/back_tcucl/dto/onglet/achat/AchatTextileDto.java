package tcucl.back_tcucl.dto.onglet.achat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import tcucl.back_tcucl.entity.onglet.achat.AchatTextile;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AchatTextileDto {
    private Long id;
    private Integer Chemise_nb;
    private Integer Polaire_nb;
    private Integer Pull_Acrylique_nb;
    private Integer Pull_Coton_nb;
    private Integer T_shirt_polyester_nb;
    private Integer Jean_nb;
    private Integer Sweat_nb;
    private Integer Veste_Anorak_nb;
    private Integer Manteau_nb;
    private Integer Chaussure_nb;

    public AchatTextileDto() {
    }

    public AchatTextileDto(AchatTextile entity) {
        this.id = entity.getId();
        this.Chemise_nb = entity.getChemise_nb();
        this.Polaire_nb = entity.getPolaire_nb();
        this.Pull_Acrylique_nb = entity.getPull_Acrylique_nb();
        this.Pull_Coton_nb = entity.getPull_Coton_nb();
        this.T_shirt_polyester_nb = entity.getT_shirt_polyester_nb();
        this.Jean_nb = entity.getJean_nb();
        this.Sweat_nb = entity.getSweat_nb();
        this.Veste_Anorak_nb = entity.getVeste_Anorak_nb();
        this.Manteau_nb = entity.getManteau_nb();
        this.Chaussure_nb = entity.getChaussure_nb();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
