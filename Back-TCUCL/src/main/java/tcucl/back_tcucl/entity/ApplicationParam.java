package tcucl.back_tcucl.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.lang.annotation.Documented;

@Entity
@Table(name = "application_param")
public class ApplicationParam {

    @Id
    private Long id = 1L;

    private Integer derniereAnneeCreee;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Integer getDerniereAnneeCreee() {
        return derniereAnneeCreee;
    }

    public void setDerniereAnneeCreee(Integer derniereAnneeCreee) {
        this.derniereAnneeCreee = derniereAnneeCreee;
    }
}
