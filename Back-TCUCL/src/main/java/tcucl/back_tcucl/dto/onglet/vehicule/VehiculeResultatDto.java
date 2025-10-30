package tcucl.back_tcucl.dto.onglet.vehicule;

import java.util.Map;

public class VehiculeResultatDto {
    private Map<Long,Float> emissionGESVehicule;
    private Float totalEmissionGESFabrication;
    private Float totalEmissionGESPetrole;
    private Float totalEmissionGESElectrique;
    private Float totalEmissionGES;

    public VehiculeResultatDto() {
    }

    public Map<Long, Float> getEmissionGESVehicule() {
        return emissionGESVehicule;
    }

    public void setEmissionGESVehicule(Map<Long, Float> emissionGESVehicule) {
        this.emissionGESVehicule = emissionGESVehicule;
    }

    public Float getTotalEmissionGESFabrication() {
        return totalEmissionGESFabrication;
    }

    public void setTotalEmissionGESFabrication(Float totalEmissionGESFabrication) {
        this.totalEmissionGESFabrication = totalEmissionGESFabrication;
    }

    public Float getTotalEmissionGESPetrole() {
        return totalEmissionGESPetrole;
    }

    public void setTotalEmissionGESPetrole(Float totalEmissionGESPetrole) {
        this.totalEmissionGESPetrole = totalEmissionGESPetrole;
    }

    public Float getTotalEmissionGESElectrique() {
        return totalEmissionGESElectrique;
    }

    public void setTotalEmissionGESElectrique(Float totalEmissionGESElectrique) {
        this.totalEmissionGESElectrique = totalEmissionGESElectrique;
    }

    public Float getTotalEmissionGES() {
        return totalEmissionGES;
    }

    public void setTotalEmissionGES(Float totalEmissionGES) {
        this.totalEmissionGES = totalEmissionGES;
    }
}
