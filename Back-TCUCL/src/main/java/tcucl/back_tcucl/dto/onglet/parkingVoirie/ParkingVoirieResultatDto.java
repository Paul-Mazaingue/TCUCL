package tcucl.back_tcucl.dto.onglet.parkingVoirie;

import java.util.Map;

public class ParkingVoirieResultatDto {

    private Map<Long,Float> emissionGESParkingVoirie;

    private Float totalEmissionGES;

    public ParkingVoirieResultatDto() {
    }

    public Map<Long, Float> getEmissionGESParkingVoirie() {
        return emissionGESParkingVoirie;
    }

    public void setEmissionGESParkingVoirie(Map<Long, Float> emissionGESParkingVoirie) {
        this.emissionGESParkingVoirie = emissionGESParkingVoirie;
    }

    public Float getTotalEmissionGES() {
        return totalEmissionGES;
    }

    public void setTotalEmissionGES(Float totalEmissionGES) {
        this.totalEmissionGES = totalEmissionGES;
    }
}
