package tcucl.back_tcucl.dto.onglet.dechet;

public class DechetResultatDto {

    private Float ordures_menageres;
    private Float cartons;
    private Float verre;
    private Float metaux;
    private Float textile;

    private Float totalProduit;
    private Float totalEvite;

    public DechetResultatDto() {
    }

    public Float getOrdures_menageres() {
        return ordures_menageres;
    }

    public void setOrdures_menageres(Float ordures_menageres) {
        this.ordures_menageres = ordures_menageres;
    }

    public Float getCartons() {
        return cartons;
    }

    public void setCartons(Float cartons) {
        this.cartons = cartons;
    }

    public Float getVerre() {
        return verre;
    }

    public void setVerre(Float verre) {
        this.verre = verre;
    }

    public Float getMetaux() {
        return metaux;
    }

    public void setMetaux(Float metaux) {
        this.metaux = metaux;
    }

    public Float getTextile() {
        return textile;
    }

    public void setTextile(Float textile) {
        this.textile = textile;
    }

    public Float getTotalProduit() {
        return totalProduit;
    }

    public void setTotalProduit(Float totalProduit) {
        this.totalProduit = totalProduit;
    }

    public Float getTotalEvite() {
        return totalEvite;
    }

    public void setTotalEvite(Float totalEvite) {
        this.totalEvite = totalEvite;
    }
}
