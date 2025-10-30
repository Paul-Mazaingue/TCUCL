package tcucl.back_tcucl.entity.onglet.batiment;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.AssertTrue;
import tcucl.back_tcucl.entity.onglet.batiment.enums.EnumBatiment_Mobilier;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "mobilier_electromenager")
public class MobilierElectromenager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateAjout;
    private Integer valeurEnumMobilier;
    private Integer quantite;
    private Float poidsDuProduit;
    private Integer dureeAmortissement;


    @AssertTrue(message = "Si le produit fait partie de la liste spécifiée, le poids du produit ne doit pas être rempli.")
    public Boolean assertPoidsProduitValide() {
        // Vérifie si le produit fait partie de la liste interdite
        if (PRODUITS_INTERDITS.contains(valeurEnumMobilier)) {
            // Si le produit fait partie de la liste, poidsDuProduit doit être nul ou égal à zéro
            return poidsDuProduit == 0.0f;
        }
        // Si le produit n'est pas dans la liste, aucune contrainte sur poidsDuProduit
        return true;
    }

    public void setMobilier(EnumBatiment_Mobilier valeur) {
        this.valeurEnumMobilier = valeur.getCode();
    }

    public EnumBatiment_Mobilier getMobilier() {
        return this.valeurEnumMobilier != null ?EnumBatiment_Mobilier.fromCode(this.valeurEnumMobilier) : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateAjout() {
        return dateAjout;
    }

    public void setDateAjout(LocalDate dateAjout) {
        this.dateAjout = dateAjout;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public Float getPoidsDuProduit() {
        return poidsDuProduit;
    }

    public void setPoidsDuProduit(Float poidsDuProduit) {
        this.poidsDuProduit = poidsDuProduit;
    }

    public Integer getDureeAmortissement() {
        return dureeAmortissement;
    }

    public void setDureeAmortissement(Integer dureeAmortissement) {
        this.dureeAmortissement = dureeAmortissement;
    }


    // Définition du Set avec les codes des produits dans EnumBatiment_Mobilier
    private static final Set<Integer> PRODUITS_INTERDITS = Set.of(
            EnumBatiment_Mobilier.ASPIRATEUR_CLASSIQUE.getCode(),
            EnumBatiment_Mobilier.ASPIRATEUR_TRAINEAU_PRO.getCode(),
            EnumBatiment_Mobilier.CLIMATISEUR_MOBILE.getCode(),
            EnumBatiment_Mobilier.CONGELATEUR_ARMOR.getCode(),
            EnumBatiment_Mobilier.CONGELATEUR_COFFRE.getCode(),
            EnumBatiment_Mobilier.LAVELINGE_7KG.getCode(),
            EnumBatiment_Mobilier.SECHE_LINGE_6KG.getCode(),
            EnumBatiment_Mobilier.LAVE_VAISSELLE.getCode(),
            EnumBatiment_Mobilier.MACHINE_A_CAFE_EXPRESSO.getCode(),
            EnumBatiment_Mobilier.MACHINE_A_CAFE_FILTRE.getCode(),
            EnumBatiment_Mobilier.MACHINE_A_CAFE_DOSETTES.getCode(),
            EnumBatiment_Mobilier.MACHINE_A_CAFE_MOYENNE.getCode(),
            EnumBatiment_Mobilier.BOUILLOIRE.getCode(),
            EnumBatiment_Mobilier.MICRO_ONDES.getCode(),
            EnumBatiment_Mobilier.PLAQUES_DE_CUISSON.getCode(),
            EnumBatiment_Mobilier.RADIATEUR_ELECTRIQUE.getCode(),
            EnumBatiment_Mobilier.REFRIGERATEUR.getCode(),
            EnumBatiment_Mobilier.AUTRE_MOBILIER_EN_EUROS.getCode(),
            EnumBatiment_Mobilier.AUTRE_MOBILIER_EN_TONNES.getCode()
    );
}

