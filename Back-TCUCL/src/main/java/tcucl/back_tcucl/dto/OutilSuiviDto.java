package tcucl.back_tcucl.dto;

import java.util.List;
import java.util.Map;

public class OutilSuiviDto {
    private Long entiteId;
    private List<Integer> years;
    private List<Float> objectif; // valeurs possibles null (trous)
    private List<Float> realise;  // valeurs possibles null (trous)

    private List<String> postes;
    private Map<Integer, List<Float>> postesObjectifParAn;
    private Map<Integer, List<Float>> postesRealiseParAn;

    private Map<Integer, Map<String, Float>> indicateursParAn;
    private List<Float> globalTotals;

    public OutilSuiviDto() {}

    public OutilSuiviDto(Long entiteId, List<Integer> years, List<Float> objectif, List<Float> realise,
                         List<String> postes, Map<Integer, List<Float>> postesObjectifParAn, Map<Integer, List<Float>> postesRealiseParAn,
                         Map<Integer, Map<String, Float>> indicateursParAn, List<Float> globalTotals) {
        this.entiteId = entiteId;
        this.years = years;
        this.objectif = objectif;
        this.realise = realise;
        this.postes = postes;
        this.postesObjectifParAn = postesObjectifParAn;
        this.postesRealiseParAn = postesRealiseParAn;
        this.indicateursParAn = indicateursParAn;
        this.globalTotals = globalTotals;
    }

    public Long getEntiteId() { return entiteId; }
    public void setEntiteId(Long entiteId) { this.entiteId = entiteId; }
    public List<Integer> getYears() { return years; }
    public void setYears(List<Integer> years) { this.years = years; }
    public List<Float> getObjectif() { return objectif; }
    public void setObjectif(List<Float> objectif) { this.objectif = objectif; }
    public List<Float> getRealise() { return realise; }
    public void setRealise(List<Float> realise) { this.realise = realise; }
    public List<String> getPostes() { return postes; }
    public void setPostes(List<String> postes) { this.postes = postes; }
    public Map<Integer, List<Float>> getPostesObjectifParAn() { return postesObjectifParAn; }
    public void setPostesObjectifParAn(Map<Integer, List<Float>> postesObjectifParAn) { this.postesObjectifParAn = postesObjectifParAn; }
    public Map<Integer, List<Float>> getPostesRealiseParAn() { return postesRealiseParAn; }
    public void setPostesRealiseParAn(Map<Integer, List<Float>> postesRealiseParAn) { this.postesRealiseParAn = postesRealiseParAn; }
    public Map<Integer, Map<String, Float>> getIndicateursParAn() { return indicateursParAn; }
    public void setIndicateursParAn(Map<Integer, Map<String, Float>> indicateursParAn) { this.indicateursParAn = indicateursParAn; }
    public List<Float> getGlobalTotals() { return globalTotals; }
    public void setGlobalTotals(List<Float> globalTotals) { this.globalTotals = globalTotals; }
}
