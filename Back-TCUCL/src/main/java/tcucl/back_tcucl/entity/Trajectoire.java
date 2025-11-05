package tcucl.back_tcucl.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "trajectoire")
public class Trajectoire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entite_id", nullable = false)
    private Entite entite;

    @Column(name = "reference_year", nullable = false)
    private Integer referenceYear;

    @Column(name = "target_year", nullable = false)
    private Integer targetYear;

    // Pourcentage visé global (0-100)
    @Column(name = "target_percentage", nullable = false)
    private Float targetPercentage;

    public Trajectoire() {}

    public Trajectoire(Entite entite, Integer referenceYear, Integer targetYear, Float targetPercentage) {
        this.entite = entite;
        this.referenceYear = referenceYear;
        this.targetYear = targetYear;
        this.targetPercentage = targetPercentage;
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
}