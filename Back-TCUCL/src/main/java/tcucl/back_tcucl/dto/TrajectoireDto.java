package tcucl.back_tcucl.dto;

import java.util.ArrayList;
import java.util.List;

public class TrajectoireDto {
    private Long entiteId;
    private Integer referenceYear;
    private Integer targetYear;
    private Float targetPercentage;
    private Boolean lockGlobal; 
    private List<TrajectoirePosteReglageDto> postesReglages; 

    public TrajectoireDto() {
        this.postesReglages = new ArrayList<>(); 
        this.lockGlobal = Boolean.FALSE;
    }

    public TrajectoireDto(Long entiteId, Integer referenceYear, Integer targetYear, Float targetPercentage) {
        this(entiteId, referenceYear, targetYear, targetPercentage, Boolean.FALSE, new ArrayList<>()); 
    }

    public TrajectoireDto(Long entiteId, Integer referenceYear, Integer targetYear, Float targetPercentage,
                          Boolean lockGlobal, List<TrajectoirePosteReglageDto> postesReglages) {
        this.entiteId = entiteId;
        this.referenceYear = referenceYear;
        this.targetYear = targetYear;
        this.targetPercentage = targetPercentage;
        this.lockGlobal = lockGlobal != null ? lockGlobal : Boolean.FALSE;
        this.postesReglages = (postesReglages != null) ? new ArrayList<>(postesReglages) : new ArrayList<>(); 
    }

    public Long getEntiteId() { return entiteId; }
    public void setEntiteId(Long entiteId) { this.entiteId = entiteId; }
    public Integer getReferenceYear() { return referenceYear; }
    public void setReferenceYear(Integer referenceYear) { this.referenceYear = referenceYear; }
    public Integer getTargetYear() { return targetYear; }
    public void setTargetYear(Integer targetYear) { this.targetYear = targetYear; }
    public Float getTargetPercentage() { return targetPercentage; }
    public void setTargetPercentage(Float targetPercentage) { this.targetPercentage = targetPercentage; }
    public Boolean getLockGlobal() { return lockGlobal; }
    public void setLockGlobal(Boolean lockGlobal) { this.lockGlobal = lockGlobal != null ? lockGlobal : Boolean.FALSE; }
    public List<TrajectoirePosteReglageDto> getPostesReglages() { return postesReglages; }
    public void setPostesReglages(List<TrajectoirePosteReglageDto> postesReglages) { 
        this.postesReglages = (postesReglages != null) ? new ArrayList<>(postesReglages) : new ArrayList<>(); 
    }
}
