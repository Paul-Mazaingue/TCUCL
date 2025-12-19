package tcucl.back_tcucl.entity;

import jakarta.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "trajectoire")
public class Trajectoire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "entite_id", nullable = true)
    private Entite entite;

    @Column(name = "reference_year", nullable = false)
    private Integer referenceYear;

    @Column(name = "target_year", nullable = false)
    private Integer targetYear;

    // Pourcentage visé global (0-100)
    @Column(name = "target_percentage", nullable = false)
    private Float targetPercentage;

    @Column(name = "lock_global")
    private Boolean lockGlobal = Boolean.FALSE;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "trajectoire_poste_reglage", joinColumns = @JoinColumn(name = "trajectoire_id"))
    @MapKeyColumn(name = "poste_id")
    @Column(name = "reduction_pct")
    private Map<String, Integer> posteReductions = new HashMap<>();

    public Trajectoire() {}

    public Trajectoire(Entite entite, Integer referenceYear, Integer targetYear, Float targetPercentage) {
        this.entite = entite;
        this.referenceYear = referenceYear;
        this.targetYear = targetYear;
        this.targetPercentage = targetPercentage;
        this.lockGlobal = Boolean.FALSE;
        this.posteReductions = new HashMap<>();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Entite getEntite() { return entite; }
    public void setEntite(Entite entite) { this.entite = entite; }

    public Integer getReferenceYear() { return referenceYear; }
    public void setReferenceYear(Integer referenceYear) { this.referenceYear = referenceYear; }

    public Integer getTargetYear() { return targetYear; }
    public void setTargetYear(Integer targetYear) { this.targetYear = targetYear; }

    public Float getTargetPercentage() { return targetPercentage; }
    public void setTargetPercentage(Float targetPercentage) { this.targetPercentage = targetPercentage; }

    public Boolean getLockGlobal() { return lockGlobal; }
    public void setLockGlobal(Boolean lockGlobal) { this.lockGlobal = (lockGlobal != null) ? lockGlobal : Boolean.FALSE; }

    public Map<String, Integer> getPosteReductions() { return posteReductions; }
    public void setPosteReductions(Map<String, Integer> posteReductions) {
        this.posteReductions = (posteReductions != null) ? posteReductions : new HashMap<>();
    }
}