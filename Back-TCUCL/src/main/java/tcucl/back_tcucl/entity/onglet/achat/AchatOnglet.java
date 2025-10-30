package tcucl.back_tcucl.entity.onglet.achat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import tcucl.back_tcucl.entity.onglet.Onglet;

@Entity
@Table(name = "achat_onglet")
public class AchatOnglet extends Onglet {

    @OneToOne(cascade = CascadeType.ALL)
    private AchatConsommable achatConsommable = new AchatConsommable();
    @OneToOne(cascade = CascadeType.ALL)
    private AchatRestauration achatRestauration = new AchatRestauration();
    @OneToOne(cascade = CascadeType.ALL)
    private AchatTextile achatTextile = new AchatTextile();

    public AchatOnglet() {
        super();
    }


    public AchatConsommable getAchatConsommable() {
        return achatConsommable;
    }

    public void setAchatConsommable(AchatConsommable achatConsommable) {
        this.achatConsommable = achatConsommable;
    }

    public AchatRestauration getAchatRestauration() {
        return achatRestauration;
    }

    public void setAchatRestauration(AchatRestauration achatRestauration) {
        this.achatRestauration = achatRestauration;
    }

    public AchatTextile getAchatTextile() {
        return achatTextile;
    }

    public void setAchatTextile(AchatTextile achatTextile) {
        this.achatTextile = achatTextile;
    }


}
