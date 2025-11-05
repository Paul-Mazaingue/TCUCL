package tcucl.back_tcucl.dto;

public class TrajectoireDto {
    private Long entiteId;
    private Integer referenceYear;
    private Integer targetYear;
    private Float targetPercentage;

    public TrajectoireDto() {}

    public TrajectoireDto(Long entiteId, Integer referenceYear, Integer targetYear, Float targetPercentage) {
        this.entiteId = entiteId;
        this.referenceYear = referenceYear;
        this.targetYear = targetYear;
        this.targetPercentage = targetPercentage;
    }

    public Long getEntiteId() { return entiteId; }
    public void setEntiteId(Long entiteId) { this.entiteId = entiteId; }
    public Integer getReferenceYear() { return referenceYear; }
    public void setReferenceYear(Integer referenceYear) { this.referenceYear = referenceYear; }
    public Integer getTargetYear() { return targetYear; }
    public void setTargetYear(Integer targetYear) { this.targetYear = targetYear; }
    public Float getTargetPercentage() { return targetPercentage; }
    public void setTargetPercentage(Float targetPercentage) { this.targetPercentage = targetPercentage; }
}
