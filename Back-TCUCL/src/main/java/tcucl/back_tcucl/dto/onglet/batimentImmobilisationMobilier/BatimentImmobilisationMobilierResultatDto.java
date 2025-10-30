package tcucl.back_tcucl.dto.onglet.batimentImmobilisationMobilier;

import java.util.Map;

public class BatimentImmobilisationMobilierResultatDto {
    private Map<Long,Float> emissionGESBatimentExistantOuNeufConstruit;
    private Map<Long,Float> emissionGESEntretienCourant;
    private Map<Long,Float> emissionGESMobilierElectromenager;


    private Float totalPosteBatiment;
    private Float totalPosteEntretien;
    private Float totalPosteMobilier;


    public BatimentImmobilisationMobilierResultatDto() {
    }

    public Map<Long, Float> getEmissionGESBatimentExistantOuNeufConstruit() {
        return emissionGESBatimentExistantOuNeufConstruit;
    }

    public void setEmissionGESBatimentExistantOuNeufConstruit(Map<Long, Float> emissionGESBatimentExistantOuNeufConstruit) {
        this.emissionGESBatimentExistantOuNeufConstruit = emissionGESBatimentExistantOuNeufConstruit;
    }

    public Map<Long, Float> getEmissionGESEntretienCourant() {
        return emissionGESEntretienCourant;
    }

    public void setEmissionGESEntretienCourant(Map<Long, Float> emissionGESEntretienCourant) {
        this.emissionGESEntretienCourant = emissionGESEntretienCourant;
    }

    public Map<Long, Float> getEmissionGESMobilierElectromenager() {
        return emissionGESMobilierElectromenager;
    }

    public void setEmissionGESMobilierElectromenager(Map<Long, Float> emissionGESMobilierElectromenager) {
        this.emissionGESMobilierElectromenager = emissionGESMobilierElectromenager;
    }

    public Float getTotalPosteBatiment() {
        return totalPosteBatiment;
    }

    public void setTotalPosteBatiment(Float totalPosteBatiment) {
        this.totalPosteBatiment = totalPosteBatiment;
    }

    public Float getTotalPosteEntretien() {
        return totalPosteEntretien;
    }

    public void setTotalPosteEntretien(Float totalPosteEntretien) {
        this.totalPosteEntretien = totalPosteEntretien;
    }

    public Float getTotalPosteMobilier() {
        return totalPosteMobilier;
    }

    public void setTotalPosteMobilier(Float totalPosteMobilier) {
        this.totalPosteMobilier = totalPosteMobilier;
    }
}
