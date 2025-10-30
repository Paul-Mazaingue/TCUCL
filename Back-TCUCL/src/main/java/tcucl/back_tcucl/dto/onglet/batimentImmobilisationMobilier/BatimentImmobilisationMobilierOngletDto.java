package tcucl.back_tcucl.dto.onglet.batimentImmobilisationMobilier;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import tcucl.back_tcucl.entity.onglet.batiment.BatimentImmobilisationMobilierOnglet;

import java.util.List;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BatimentImmobilisationMobilierOngletDto {

    private Long id;
    private Boolean estTermine;
    private String note;

    List<BatimentExistantOuNeufConstruitDto> batimentsExistantOuNeufConstruits;
    List<EntretienCourantDto> entretiensCourants;
    List<MobilierElectromenagerDto> mobiliersElectromenagers;

    public BatimentImmobilisationMobilierOngletDto() {
    }

    public BatimentImmobilisationMobilierOngletDto(BatimentImmobilisationMobilierOnglet entity) {
        this.id = entity.getId();
        this.estTermine = entity.getEstTermine();
        this.note = entity.getNote();

        if (entity.getBatimentExistantOuNeufConstruits() != null) {
            this.batimentsExistantOuNeufConstruits = entity.getBatimentExistantOuNeufConstruits().stream()
                    .map(BatimentExistantOuNeufConstruitDto::new)
                    .collect(Collectors.toList());
        }

        if (entity.getEntretienCourants() != null) {
            this.entretiensCourants = entity.getEntretienCourants().stream()
                    .map(EntretienCourantDto::new)
                    .collect(Collectors.toList());
        }

        if (entity.getMobilierElectromenagers() != null) {
            this.mobiliersElectromenagers = entity.getMobilierElectromenagers().stream()
                    .map(MobilierElectromenagerDto::new)
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

    public List<BatimentExistantOuNeufConstruitDto> getBatimentsExistantOuNeufConstruits() {
        return batimentsExistantOuNeufConstruits;
    }
    public void setBatimentExistantOuNeufConstruit(List<BatimentExistantOuNeufConstruitDto> batimentsExistantOuNeufConstruits) {
        this.batimentsExistantOuNeufConstruits = batimentsExistantOuNeufConstruits;
    }

    public List<EntretienCourantDto> getEntretiensCourants() {
        return entretiensCourants;
    }
    public void setEntretiensCourants(List<EntretienCourantDto> entretiensCourants) {
        this.entretiensCourants = entretiensCourants;
    }

    public List<MobilierElectromenagerDto> getMobiliersElectromenagers() {
        return mobiliersElectromenagers;
    }
    public void setMobiliersElectromenagers(List<MobilierElectromenagerDto> mobiliersElectromenagers) {
        this.mobiliersElectromenagers = mobiliersElectromenagers;
    }

}
