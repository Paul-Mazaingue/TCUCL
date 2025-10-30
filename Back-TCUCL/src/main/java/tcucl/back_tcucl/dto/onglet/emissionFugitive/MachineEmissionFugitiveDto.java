package tcucl.back_tcucl.dto.onglet.emissionFugitive;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import tcucl.back_tcucl.entity.onglet.emissionFugitive.MachineEmissionFugitive;
import tcucl.back_tcucl.entity.onglet.emissionFugitive.enums.EnumEmissionFugitive_TypeFluide;
import tcucl.back_tcucl.entity.onglet.emissionFugitive.enums.EnumEmissionFugitive_TypeMachine;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MachineEmissionFugitiveDto {

    private Long id;

    private String descriptionMachine;
    private EnumEmissionFugitive_TypeFluide typeFluide;
    private Float quantiteFluideKg;
    private Boolean tauxDeFuiteConnu;
    private Float tauxDeFuite;
    private EnumEmissionFugitive_TypeMachine typeMachine;


    public MachineEmissionFugitiveDto() {
    }

    public MachineEmissionFugitiveDto(MachineEmissionFugitive entity) {
        this.id = entity.getId();
        this.descriptionMachine = entity.getDescriptionMachine();
        this.typeFluide = entity.getTypeFluide();
        this.quantiteFluideKg = entity.getQuantiteFluideKg();
        this.tauxDeFuiteConnu = entity.getTauxDeFuiteConnu();
        this.tauxDeFuite = entity.getTauxDeFuite();
        this.typeMachine = entity.getTypeMachine();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getDescriptionMachine() {
        return descriptionMachine;
    }

    public void setDescriptionMachine(String descriptionMachine) {
        this.descriptionMachine = descriptionMachine;
    }



    public Float getQuantiteFluideKg() {
        return quantiteFluideKg;
    }

    public void setQuantiteFluideKg(Float quantiteFluideKg) {
        this.quantiteFluideKg = quantiteFluideKg;
    }

    public Boolean getTauxDeFuiteConnu() {
        return tauxDeFuiteConnu;
    }

    public void setTauxDeFuiteConnu(Boolean tauxDeFuiteConnu) {
        this.tauxDeFuiteConnu = tauxDeFuiteConnu;
    }

    public Float getTauxDeFuite() {
        return tauxDeFuite;
    }

    public void setTauxDeFuite(Float tauxDeFuite) {
        this.tauxDeFuite = tauxDeFuite;
    }

    public EnumEmissionFugitive_TypeFluide getTypeFluide() {
        return typeFluide;
    }

    public void setTypeFluide(EnumEmissionFugitive_TypeFluide typeFluide) {
        this.typeFluide = typeFluide;
    }

    public EnumEmissionFugitive_TypeMachine getTypeMachine() {
        return typeMachine;
    }

    public void setTypeMachine(EnumEmissionFugitive_TypeMachine typeMachine) {
        this.typeMachine = typeMachine;
    }
}
