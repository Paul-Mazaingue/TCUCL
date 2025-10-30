package tcucl.back_tcucl.dto.onglet.emissionFugitive;

import java.util.Map;

public class EmissionFugitiveResultatDto {
    private Map<Long,Float> emissionGESMachine;

    private Float totalEmissionGES;

    public EmissionFugitiveResultatDto() {

    }

    public Map<Long, Float> getEmissionGESMachine() {
        return emissionGESMachine;
    }

    public void setEmissionGESMachine(Map<Long, Float> emissionGESMachine) {
        this.emissionGESMachine = emissionGESMachine;
    }

    public Float getTotalEmissionGES() {
        return totalEmissionGES;
    }

    public void setTotalEmissionGES(Float totalEmissionGES) {
        this.totalEmissionGES = totalEmissionGES;
    }


}
