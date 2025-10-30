package tcucl.back_tcucl.dto.onglet.achat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import tcucl.back_tcucl.entity.onglet.achat.AchatRestauration;
import tcucl.back_tcucl.entity.onglet.achat.enums.EnumAchatRestauration_Methode;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AchatRestaurationDto {
    private Long id;

    private EnumAchatRestauration_Methode methode;
    private Integer methodeRapideNombrePersonnesServiesRegimeClassique;
    private Integer methodeRapideNombrePersonnesServiesRegimeFlexitarien;

    private Integer nombreRepasServisDominanteAnimaleBoeuf;
    private Integer nombreRepasServisDominanteAnimalePoulet;
    private Integer nombreRepasServisDominanteVegetaleBoeuf;
    private Integer nombreRepasServisDominanteVegetalePoulet;
    private Integer nombreRepasServisDominanteClassiqueBoeuf;
    private Integer nombreRepasServisDominanteClassiquePoulet;
    private Integer nombreRepasServisRepasMoyen;
    private Integer nombreRepasServisRepasVegetarien;

    private Float boeufAgneauMouton_Tonnes;
    private Float poulet_Tonnes;
    private Float cafe_Tonnes;
    private Float chocolat_Tonnes;
    private Float beurre_Tonnes;
    private Float viandesMoyenne_Tonnes;
    private Float produitsSucresMoyenne_Tonnes;
    private Float poissonsMoyenne_Tonnes;
    private Float fromagesMoyenne_Tonnes;
    private Float oleagineuxMoyenne_Tonnes;
    private Float matieresGrassesMoyenne_Tonnes;
    private Float boissonsMoyenne_Tonnes;
    private Float oeufs_Tonnes;
    private Float cerealesMoyenne_Tonnes;
    private Float legumesMoyenne_Tonnes;
    private Float fruitsMoyenne_Tonnes;
    private Float legumineuseMoyenne_Tonnes;

    public AchatRestaurationDto() {
    }

    public AchatRestaurationDto(AchatRestauration entity) {
        this.id = entity.getId();
        this.methode = entity.getMethodeCalcul();
        this.methodeRapideNombrePersonnesServiesRegimeClassique = entity.getMethodeRapideNombrePersonnesServiesRegimeClassique();
        this.methodeRapideNombrePersonnesServiesRegimeFlexitarien = entity.getMethodeRapideNombrePersonnesServiesRegimeFlexitarien();

        this.nombreRepasServisDominanteAnimaleBoeuf = entity.getNombreRepasServisDominanteAnimaleBoeuf();
        this.nombreRepasServisDominanteAnimalePoulet = entity.getNombreRepasServisDominanteAnimalePoulet();
        this.nombreRepasServisDominanteVegetaleBoeuf = entity.getNombreRepasServisDominanteVegetaleBoeuf();
        this.nombreRepasServisDominanteVegetalePoulet = entity.getNombreRepasServisDominanteVegetalePoulet();
        this.nombreRepasServisDominanteClassiqueBoeuf = entity.getNombreRepasServisDominanteClassiqueBoeuf();
        this.nombreRepasServisDominanteClassiquePoulet = entity.getNombreRepasServisDominanteClassiquePoulet();
        this.nombreRepasServisRepasMoyen = entity.getNombreRepasServisRepasMoyen();
        this.nombreRepasServisRepasVegetarien = entity.getNombreRepasServisRepasVegetarien();

        this.boeufAgneauMouton_Tonnes = entity.getBoeufAgneauMouton_Tonnes();
        this.poulet_Tonnes = entity.getPoulet_Tonnes();
        this.cafe_Tonnes = entity.getCafe_Tonnes();
        this.chocolat_Tonnes = entity.getChocolat_Tonnes();
        this.beurre_Tonnes = entity.getBeurre_Tonnes();
        this.viandesMoyenne_Tonnes = entity.getViandesMoyenne_Tonnes();
        this.produitsSucresMoyenne_Tonnes = entity.getProduitsSucresMoyenne_Tonnes();
        this.poissonsMoyenne_Tonnes = entity.getPoissonsMoyenne_Tonnes();
        this.fromagesMoyenne_Tonnes = entity.getFromagesMoyenne_Tonnes();
        this.oleagineuxMoyenne_Tonnes = entity.getOleagineuxMoyenne_Tonnes();
        this.matieresGrassesMoyenne_Tonnes = entity.getMatieresGrassesMoyenne_Tonnes();
        this.boissonsMoyenne_Tonnes = entity.getBoissonsMoyenne_Tonnes();
        this.oeufs_Tonnes = entity.getOeufs_Tonnes();
        this.cerealesMoyenne_Tonnes = entity.getCerealesMoyenne_Tonnes();
        this.legumesMoyenne_Tonnes = entity.getLegumesMoyenne_Tonnes();
        this.fruitsMoyenne_Tonnes = entity.getFruitsMoyenne_Tonnes();
        this.legumineuseMoyenne_Tonnes = entity.getLegumineuseMoyenne_Tonnes();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EnumAchatRestauration_Methode getMethode() {
        return methode;
    }

    public void setMethode(EnumAchatRestauration_Methode methode) {
        this.methode = methode;
    }

    public Integer getMethodeRapideNombrePersonnesServiesRegimeClassique() {
        return methodeRapideNombrePersonnesServiesRegimeClassique;
    }

    public void setMethodeRapideNombrePersonnesServiesRegimeClassique(Integer methodeRapideNombrePersonnesServiesRegimeClassique) {
        this.methodeRapideNombrePersonnesServiesRegimeClassique = methodeRapideNombrePersonnesServiesRegimeClassique;
    }

    public Integer getMethodeRapideNombrePersonnesServiesRegimeFlexitarien() {
        return methodeRapideNombrePersonnesServiesRegimeFlexitarien;
    }

    public void setMethodeRapideNombrePersonnesServiesRegimeFlexitarien(Integer methodeRapideNombrePersonnesServiesRegimeFlexitarien) {
        this.methodeRapideNombrePersonnesServiesRegimeFlexitarien = methodeRapideNombrePersonnesServiesRegimeFlexitarien;
    }

    public Integer getNombreRepasServisDominanteAnimaleBoeuf() {
        return nombreRepasServisDominanteAnimaleBoeuf;
    }

    public void setNombreRepasServisDominanteAnimaleBoeuf(Integer nombreRepasServisDominanteAnimaleBoeuf) {
        this.nombreRepasServisDominanteAnimaleBoeuf = nombreRepasServisDominanteAnimaleBoeuf;
    }

    public Integer getNombreRepasServisDominanteAnimalePoulet() {
        return nombreRepasServisDominanteAnimalePoulet;
    }

    public void setNombreRepasServisDominanteAnimalePoulet(Integer nombreRepasServisDominanteAnimalePoulet) {
        this.nombreRepasServisDominanteAnimalePoulet = nombreRepasServisDominanteAnimalePoulet;
    }

    public Integer getNombreRepasServisDominanteVegetaleBoeuf() {
        return nombreRepasServisDominanteVegetaleBoeuf;
    }

    public void setNombreRepasServisDominanteVegetaleBoeuf(Integer nombreRepasServisDominanteVegetaleBoeuf) {
        this.nombreRepasServisDominanteVegetaleBoeuf = nombreRepasServisDominanteVegetaleBoeuf;
    }

    public Integer getNombreRepasServisDominanteVegetalePoulet() {
        return nombreRepasServisDominanteVegetalePoulet;
    }

    public void setNombreRepasServisDominanteVegetalePoulet(Integer nombreRepasServisDominanteVegetalePoulet) {
        this.nombreRepasServisDominanteVegetalePoulet = nombreRepasServisDominanteVegetalePoulet;
    }

    public Integer getNombreRepasServisDominanteClassiqueBoeuf() {
        return nombreRepasServisDominanteClassiqueBoeuf;
    }

    public void setNombreRepasServisDominanteClassiqueBoeuf(Integer nombreRepasServisDominanteClassiqueBoeuf) {
        this.nombreRepasServisDominanteClassiqueBoeuf = nombreRepasServisDominanteClassiqueBoeuf;
    }

    public Integer getNombreRepasServisDominanteClassiquePoulet() {
        return nombreRepasServisDominanteClassiquePoulet;
    }

    public void setNombreRepasServisDominanteClassiquePoulet(Integer nombreRepasServisDominanteClassiquePoulet) {
        this.nombreRepasServisDominanteClassiquePoulet = nombreRepasServisDominanteClassiquePoulet;
    }

    public Integer getNombreRepasServisRepasMoyen() {
        return nombreRepasServisRepasMoyen;
    }

    public void setNombreRepasServisRepasMoyen(Integer nombreRepasServisRepasMoyen) {
        this.nombreRepasServisRepasMoyen = nombreRepasServisRepasMoyen;
    }

    public Integer getNombreRepasServisRepasVegetarien() {
        return nombreRepasServisRepasVegetarien;
    }

    public void setNombreRepasServisRepasVegetarien(Integer nombreRepasServisRepasVegetarien) {
        this.nombreRepasServisRepasVegetarien = nombreRepasServisRepasVegetarien;
    }

    public Float getBoeufAgneauMouton_Tonnes() {
        return boeufAgneauMouton_Tonnes;
    }

    public void setBoeufAgneauMouton_Tonnes(Float boeufAgneauMouton_Tonnes) {
        this.boeufAgneauMouton_Tonnes = boeufAgneauMouton_Tonnes;
    }

    public Float getPoulet_Tonnes() {
        return poulet_Tonnes;
    }

    public void setPoulet_Tonnes(Float poulet_Tonnes) {
        this.poulet_Tonnes = poulet_Tonnes;
    }

    public Float getCafe_Tonnes() {
        return cafe_Tonnes;
    }

    public void setCafe_Tonnes(Float cafe_Tonnes) {
        this.cafe_Tonnes = cafe_Tonnes;
    }

    public Float getChocolat_Tonnes() {
        return chocolat_Tonnes;
    }

    public void setChocolat_Tonnes(Float chocolat_Tonnes) {
        this.chocolat_Tonnes = chocolat_Tonnes;
    }

    public Float getBeurre_Tonnes() {
        return beurre_Tonnes;
    }

    public void setBeurre_Tonnes(Float beurre_Tonnes) {
        this.beurre_Tonnes = beurre_Tonnes;
    }

    public Float getViandesMoyenne_Tonnes() {
        return viandesMoyenne_Tonnes;
    }

    public void setViandesMoyenne_Tonnes(Float viandesMoyenne_Tonnes) {
        this.viandesMoyenne_Tonnes = viandesMoyenne_Tonnes;
    }

    public Float getProduitsSucresMoyenne_Tonnes() {
        return produitsSucresMoyenne_Tonnes;
    }

    public void setProduitsSucresMoyenne_Tonnes(Float produitsSucresMoyenne_Tonnes) {
        this.produitsSucresMoyenne_Tonnes = produitsSucresMoyenne_Tonnes;
    }

    public Float getPoissonsMoyenne_Tonnes() {
        return poissonsMoyenne_Tonnes;
    }

    public void setPoissonsMoyenne_Tonnes(Float poissonsMoyenne_Tonnes) {
        this.poissonsMoyenne_Tonnes = poissonsMoyenne_Tonnes;
    }

    public Float getFromagesMoyenne_Tonnes() {
        return fromagesMoyenne_Tonnes;
    }

    public void setFromagesMoyenne_Tonnes(Float fromagesMoyenne_Tonnes) {
        this.fromagesMoyenne_Tonnes = fromagesMoyenne_Tonnes;
    }

    public Float getOleagineuxMoyenne_Tonnes() {
        return oleagineuxMoyenne_Tonnes;
    }

    public void setOleagineuxMoyenne_Tonnes(Float oleagineuxMoyenne_Tonnes) {
        this.oleagineuxMoyenne_Tonnes = oleagineuxMoyenne_Tonnes;
    }

    public Float getMatieresGrassesMoyenne_Tonnes() {
        return matieresGrassesMoyenne_Tonnes;
    }

    public void setMatieresGrassesMoyenne_Tonnes(Float matieresGrassesMoyenne_Tonnes) {
        this.matieresGrassesMoyenne_Tonnes = matieresGrassesMoyenne_Tonnes;
    }

    public Float getBoissonsMoyenne_Tonnes() {
        return boissonsMoyenne_Tonnes;
    }

    public void setBoissonsMoyenne_Tonnes(Float boissonsMoyenne_Tonnes) {
        this.boissonsMoyenne_Tonnes = boissonsMoyenne_Tonnes;
    }

    public Float getOeufs_Tonnes() {
        return oeufs_Tonnes;
    }

    public void setOeufs_Tonnes(Float oeufs_Tonnes) {
        this.oeufs_Tonnes = oeufs_Tonnes;
    }

    public Float getCerealesMoyenne_Tonnes() {
        return cerealesMoyenne_Tonnes;
    }

    public void setCerealesMoyenne_Tonnes(Float cerealesMoyenne_Tonnes) {
        this.cerealesMoyenne_Tonnes = cerealesMoyenne_Tonnes;
    }

    public Float getLegumesMoyenne_Tonnes() {
        return legumesMoyenne_Tonnes;
    }

    public void setLegumesMoyenne_Tonnes(Float legumesMoyenne_Tonnes) {
        this.legumesMoyenne_Tonnes = legumesMoyenne_Tonnes;
    }

    public Float getFruitsMoyenne_Tonnes() {
        return fruitsMoyenne_Tonnes;
    }

    public void setFruitsMoyenne_Tonnes(Float fruitsMoyenne_Tonnes) {
        this.fruitsMoyenne_Tonnes = fruitsMoyenne_Tonnes;
    }

    public Float getLegumineuseMoyenne_Tonnes() {
        return legumineuseMoyenne_Tonnes;
    }

    public void setLegumineuseMoyenne_Tonnes(Float legumineuseMoyenne_Tonnes) {
        this.legumineuseMoyenne_Tonnes = legumineuseMoyenne_Tonnes;
    }
}
