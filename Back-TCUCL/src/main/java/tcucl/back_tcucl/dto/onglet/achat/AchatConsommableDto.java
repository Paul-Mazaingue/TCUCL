package tcucl.back_tcucl.dto.onglet.achat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import tcucl.back_tcucl.entity.onglet.achat.AchatConsommable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AchatConsommableDto {

    private Long id;

    private Float papier_T;
    private Float papier_nb;
    private Float livres_T;
    private Float livres_nb;
    private Float cartonNeuf_T;
    private Float cartonRecycle_T;
    private Float petitesFournitures_Eur;
    private Float nbFeuillesImprimeesJetEncre_Nb;
    private Float nbFeuillesImprimeesToner_Nb;
    private Float produitsPharmaceutiques_Eur;
    private Float services_imprimerie_publicite_architecture_ingenierie_maintenance_multi_technique_des_batiments = 0f;
    private Float service_Enseignement = 0f;
    private Float service_Produits_informatiques_electroniques_et_optiques = 0f;
    private Float service_Reparation_et_installation_de_machines_et_d_equipements = 0f;
    private Float service_Transport_terrestre = 0f;
    private Float service_hebergement_et_restauration = 0f;
    private Float service_de_telecommunications = 0f;

    public AchatConsommableDto() {
    }

    public AchatConsommableDto(AchatConsommable entity) {
        this.id = entity.getId();
        this.papier_T = entity.getPapier_T();
        this.papier_nb = entity.getPapier_nb();
        this.livres_T = entity.getLivres_T();
        this.livres_nb = entity.getLivres_nb();
        this.cartonNeuf_T = entity.getCartonNeuf_T();
        this.cartonRecycle_T = entity.getCartonRecycle_T();
        this.petitesFournitures_Eur = entity.getPetitesFournitures_Eur();
        this.nbFeuillesImprimeesJetEncre_Nb = entity.getNbFeuillesImprimeesJetEncre_Nb();
        this.nbFeuillesImprimeesToner_Nb = entity.getNbFeuillesImprimeesToner_Nb();
        this.produitsPharmaceutiques_Eur = entity.getProduitsPharmaceutiques_Eur();
        this.services_imprimerie_publicite_architecture_ingenierie_maintenance_multi_technique_des_batiments = entity.getServices_impPubArchiIngeMaintTechBat();
        this.service_Enseignement = entity.getService_Enseignement();
        this.service_Produits_informatiques_electroniques_et_optiques = entity.getService_Produits_informatiques_electroniques_et_optiques();
        this.service_Reparation_et_installation_de_machines_et_d_equipements = entity.getService_Reparation_et_installation_de_machines_et_d_equipements();
        this.service_Transport_terrestre = entity.getService_Transport_terrestre();
        this.service_hebergement_et_restauration = entity.getService_hebergement_et_restauration();
        this.service_de_telecommunications = entity.getService_de_telecommunications();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getPapier_T() {
        return papier_T;
    }

    public void setPapier_T(Float papier_T) {
        this.papier_T = papier_T;
    }

    public Float getPapier_nb() {
        return papier_nb;
    }

    public void setPapier_nb(Float papier_nb) {
        this.papier_nb = papier_nb;
    }

    public Float getLivres_T() {
        return livres_T;
    }

    public void setLivres_T(Float livres_T) {
        this.livres_T = livres_T;
    }

    public Float getLivres_nb() {
        return livres_nb;
    }

    public void setLivres_nb(Float livres_nb) {
        this.livres_nb = livres_nb;
    }

    public Float getCartonNeuf_T() {
        return cartonNeuf_T;
    }

    public void setCartonNeuf_T(Float cartonNeuf_T) {
        this.cartonNeuf_T = cartonNeuf_T;
    }

    public Float getCartonRecycle_T() {
        return cartonRecycle_T;
    }

    public void setCartonRecycle_T(Float cartonRecycle_T) {
        this.cartonRecycle_T = cartonRecycle_T;
    }

    public Float getPetitesFournitures_Eur() {
        return petitesFournitures_Eur;
    }

    public void setPetitesFournitures_Eur(Float petitesFournitures_Eur) {
        this.petitesFournitures_Eur = petitesFournitures_Eur;
    }

    public Float getNbFeuillesImprimeesJetEncre_Nb() {
        return nbFeuillesImprimeesJetEncre_Nb;
    }

    public void setNbFeuillesImprimeesJetEncre_Nb(Float nbFeuillesImprimeesJetEncre_Nb) {
        this.nbFeuillesImprimeesJetEncre_Nb = nbFeuillesImprimeesJetEncre_Nb;
    }

    public Float getNbFeuillesImprimeesToner_Nb() {
        return nbFeuillesImprimeesToner_Nb;
    }

    public void setNbFeuillesImprimeesToner_Nb(Float nbFeuillesImprimeesToner_Nb) {
        this.nbFeuillesImprimeesToner_Nb = nbFeuillesImprimeesToner_Nb;
    }

    public Float getProduitsPharmaceutiques_Eur() {
        return produitsPharmaceutiques_Eur;
    }

    public void setProduitsPharmaceutiques_Eur(Float produitsPharmaceutiques_Eur) {
        this.produitsPharmaceutiques_Eur = produitsPharmaceutiques_Eur;
    }

    public Float getServices_imprimerie_publicite_architecture_ingenierie_maintenance_multi_technique_des_batiments() {
        return services_imprimerie_publicite_architecture_ingenierie_maintenance_multi_technique_des_batiments;
    }

    public void setServices_imprimerie_publicite_architecture_ingenierie_maintenance_multi_technique_des_batiments(Float services_imprimerie_publicite_architecture_ingenierie_maintenance_multi_technique_des_batiments) {
        this.services_imprimerie_publicite_architecture_ingenierie_maintenance_multi_technique_des_batiments = services_imprimerie_publicite_architecture_ingenierie_maintenance_multi_technique_des_batiments;
    }

    public Float getService_Enseignement() {
        return service_Enseignement;
    }

    public void setService_Enseignement(Float service_Enseignement) {
        this.service_Enseignement = service_Enseignement;
    }

    public Float getService_Produits_informatiques_electroniques_et_optiques() {
        return service_Produits_informatiques_electroniques_et_optiques;
    }

    public void setService_Produits_informatiques_electroniques_et_optiques(Float service_Produits_informatiques_electroniques_et_optiques) {
        this.service_Produits_informatiques_electroniques_et_optiques = service_Produits_informatiques_electroniques_et_optiques;
    }

    public Float getService_Reparation_et_installation_de_machines_et_d_equipements() {
        return service_Reparation_et_installation_de_machines_et_d_equipements;
    }

    public void setService_Reparation_et_installation_de_machines_et_d_equipements(Float service_Reparation_et_installation_de_machines_et_d_equipements) {
        this.service_Reparation_et_installation_de_machines_et_d_equipements = service_Reparation_et_installation_de_machines_et_d_equipements;
    }

    public Float getService_Transport_terrestre() {
        return service_Transport_terrestre;
    }

    public void setService_Transport_terrestre(Float service_Transport_terrestre) {
        this.service_Transport_terrestre = service_Transport_terrestre;
    }

    public Float getService_hebergement_et_restauration() {
        return service_hebergement_et_restauration;
    }

    public void setService_hebergement_et_restauration(Float service_hebergement_et_restauration) {
        this.service_hebergement_et_restauration = service_hebergement_et_restauration;
    }

    public Float getService_de_telecommunications() {
        return service_de_telecommunications;
    }

    public void setService_de_telecommunications(Float service_de_telecommunications) {
        this.service_de_telecommunications = service_de_telecommunications;
    }
}
