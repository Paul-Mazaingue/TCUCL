package tcucl.back_tcucl.dto;

public class TrajectoirePosteReglageDto {
    private String id;
    private Integer reductionBasePct;

    public TrajectoirePosteReglageDto() {}

    public TrajectoirePosteReglageDto(String id, Integer reductionBasePct) {
        this.id = id;
        this.reductionBasePct = reductionBasePct;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getReductionBasePct() {
        return reductionBasePct;
    }

    public void setReductionBasePct(Integer reductionBasePct) {
        this.reductionBasePct = reductionBasePct;
    }
}
