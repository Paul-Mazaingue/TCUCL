package tcucl.back_tcucl.dto.onglet.numerique;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import tcucl.back_tcucl.entity.onglet.numerique.NumeriqueOnglet;

import java.util.List;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class NumeriqueOngletDto {

    private Long id;
    private Boolean estTermine;
    private String note;

    List<EquipementNumeriqueDto> equipementNumeriqueList;

    private Boolean useMethodSimplifiee;
    private Float TraficCloudUtilisateur;
    private Float TraficTipUtilisateur;
    private Float PartTraficFranceEtranger;

    public NumeriqueOngletDto() {
    }

    public NumeriqueOngletDto(NumeriqueOnglet numeriqueOnglet) {
        this.id = numeriqueOnglet.getId();
        this.estTermine = numeriqueOnglet.getEstTermine();
        this.note = numeriqueOnglet.getNote();

        this.useMethodSimplifiee = numeriqueOnglet.getUseMethodSimplifiee();
        this.TraficCloudUtilisateur = numeriqueOnglet.getTraficCloudUtilisateur();
        this.TraficTipUtilisateur = numeriqueOnglet.getTraficTipUtilisateur();
        this.PartTraficFranceEtranger = numeriqueOnglet.getPartTraficFranceEtranger();

        if (numeriqueOnglet.getEquipementNumeriqueList() != null) {
            this.equipementNumeriqueList = numeriqueOnglet.getEquipementNumeriqueList().stream()
                    .map(EquipementNumeriqueDto::new)
                    .collect(Collectors.toList());
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getEstTermine() {
        return estTermine;
    }

    public void setEstTermine(Boolean estTermine) {
        this.estTermine = estTermine;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<EquipementNumeriqueDto> getEquipementNumeriqueList() {
        return equipementNumeriqueList;
    }

    public void setEquipementNumeriqueList(List<EquipementNumeriqueDto> equipementNumeriqueList) {
        this.equipementNumeriqueList = equipementNumeriqueList;
    }

    public Boolean getUseMethodSimplifiee() {
        return useMethodSimplifiee;
    }

    public void setUseMethodSimplifiee(Boolean useMethodSimplifiee) {
        this.useMethodSimplifiee = useMethodSimplifiee;
    }

    public Float getTraficCloudUtilisateur() {
        return TraficCloudUtilisateur;
    }

    public void setTraficCloudUtilisateur(Float traficCloudUtilisateur) {
        TraficCloudUtilisateur = traficCloudUtilisateur;
    }

    public Float getTraficTipUtilisateur() {
        return TraficTipUtilisateur;
    }

    public void setTraficTipUtilisateur(Float traficTipUtilisateur) {
        TraficTipUtilisateur = traficTipUtilisateur;
    }

    public Float getPartTraficFranceEtranger() {
        return PartTraficFranceEtranger;
    }

    public void setPartTraficFranceEtranger(Float partTraficFranceEtranger) {
        PartTraficFranceEtranger = partTraficFranceEtranger;
    }

}
