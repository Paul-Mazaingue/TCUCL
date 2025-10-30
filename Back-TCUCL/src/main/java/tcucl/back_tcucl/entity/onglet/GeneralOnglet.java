package tcucl.back_tcucl.entity.onglet;

import jakarta.persistence.*;

@Entity
@Table(name = "general_onglet")
public class GeneralOnglet extends Onglet {

    private Integer nbSalarie = 0;
    private Integer nbEtudiant = 0;

    public GeneralOnglet(){
        super();
    }

    public Integer getNbSalarie() {
        return nbSalarie;
    }

    public void setNbSalarie(Integer nbSalarie) {
        this.nbSalarie = nbSalarie;
    }

    public Integer getNbEtudiant() {
        return nbEtudiant;
    }

    public void setNbEtudiant(Integer nbEtudiant) {
        this.nbEtudiant = nbEtudiant;
    }

    
}
