package tcucl.back_tcucl.dto;

public class TrajectoirePosteDto {
    private String id;
    private String nom;
    private int emissionsReference;
    private int reductionBasePct;

    public TrajectoirePosteDto() {}

    public TrajectoirePosteDto(String id, String nom, int emissionsReference, int reductionBasePct) {
        this.id = id;
        this.nom = nom;
        this.emissionsReference = emissionsReference;
        this.reductionBasePct = reductionBasePct;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public int getEmissionsReference() { return emissionsReference; }
    public void setEmissionsReference(int emissionsReference) { this.emissionsReference = emissionsReference; }

    public int getReductionBasePct() { return reductionBasePct; }
    public void setReductionBasePct(int reductionBasePct) { this.reductionBasePct = reductionBasePct; }
}
