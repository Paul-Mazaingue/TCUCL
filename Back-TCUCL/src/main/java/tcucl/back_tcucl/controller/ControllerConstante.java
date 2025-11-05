package tcucl.back_tcucl.controller;

public class ControllerConstante {

    public ControllerConstante() {
    }



    //    GENERAL
    public static final String REST_API = "/api";
    public static final String REST_ONGLET_ID = "/{ongletId}";
    public static final String REST_UTILISATEUR_ID = "/{utilisateurId}";
    public static final String REST_ENTITE_ID = "/{entiteId}";
    public static final String REST_RESULTAT = "/resultat";

    //   TRAJECTOIRE
    public static final String REST_TRAJECTOIRE = "/trajectoire";

    //   OUTIL SUIVI
    public static final String REST_OUTIL_SUIVI = "/outil-suivi";

    //   ------------         AUTH          ------------
    public static final String REST_AUTH = "/auth";
    //Endpoints
    public static final String REST_INSCRIPTION2 = "/inscription2";
    public static final String REST_CONNEXION = "/connexion";
    public static final String REST_CHANGE_MDP_PREMIERE_CONNEXION = "/change-mdp-premiere-connexion";
    //messages
    public static final String REST_MESSAGE_MDP_BIEN_MIS_A_JOUR_PREMIERE_CONNEXION = "Le mot de passe a bien été mis à jour lors de sa première connexion.";

    //   ------------         General          ------------
    public static final String REST_GENERAL = "/general";


    //   ------------       PARAMETRE       ------------
    public static final String REST_PARAMETRE = "/parametre";

    //   ------------       SYNTHESE_EGES       ------------
    public static final String REST_SYNTHESE_EGES = "/synthese-eges";
    //Endpoints
    public static final String REST_MODIFIER_UTILISATEUR_UTILISATEUR = "/modifier-utilisateur-utilisateur";
    public static final String REST_CHANGE_MDP = "/change-mdp";
    public static final String REST_INSCRIRE_UTILISATEUR = "/inscription-utilisateur";
    public static final String REST_SUPPRIMER_UTILISATEUR = "/supprimer-utilisateur";
    public static final String REST_MODIFIER_UTILISATEUR_ADMIN = "/modifier-utilisateur-admin";
    public static final String REST_MODIFIER_EST_ADMIN = "/modifier-est-admin";
    public static final String REST_CREER_ENTITE = "/creer-entite";
    public static final String REST_IMPORT_FACTEURS_EMISSION = "/import-facteurs-emission";
    public static final String REST_CREER_ANNEE_SUIVANTE = "/creer-annee-suivante";
    public static final String REST_UTILISATEUR_ENTITE = "/utilisateur-entite";
    public static final String REST_PEUT_CREER_NOUVELLE_ANNEE = "/peut-creer-nouvelle-annee";
    public static final String REST_ALL_ENTITE_NOM_ID = "/all-entite-nom-id";

    //messages
    public static final String REST_MESSAGE_UTILISATEUR_BIEN_INSCRIT = "";
    public static final String REST_MESSAGE_UTILISATEUR_MODIFIE = "";
    public static final String REST_MESSAGE_UTILISATEUR_SUPPRIME = "";
    public static final String REST_MESSAGE_MDP_BIEN_MIS_A_JOUR = "";
    public static final String REST_MESSAGE_CHANGE_STATUT_ADMIN = "";
    public static final String REST_MESSAGE_ENTITE_CREEE = "";
    public static final String REST_MESSAGE_ANNEE_ENTITE_AJOUTEE = "L'année a bien été ajoutée à l'entité.";
    public static final String REST_MESSAGE_IMPORT_FACTEURS_EMISSION = "";
    public static final String REST_MESSAGE_ANNEE_SUIVANTE_CREEE = "Nouvelle année créée pour toutes les organisations inscrites.";

    //   *******************************************
    //   ------------      ONGLET       ------------
    //   *******************************************

    //   ------------       Energie       ------------
    public static final String REST_ENERGIE_ONGLET = "/energieOnglet";

    //   ---------    Emission Fugitive   ----------
    public static final String REST_EMISSION_FUGITIVE_ONGLET = "/emissionFugitiveOnglet";
    public static final String REST_MACHINE = "/machine";
    public static final String REST_MACHINE_ID = "/{machineId}";

    //   ------------       Mobilite domicile travail       ------------
    public static final String REST_MOBILITE_DOMICILE_TRAVAIL_ONGLET = "/mobiliteDomicileTravailOnglet";

    //   ---------    Autre Mobilité FR   ----------
    public static final String REST_AUTRE_MOB_FR = "/autreMobFrOnglet";

    //   ---------    Mob Internationale   ----------
    public static final String REST_MOB_INTERNATIONALE_ONGLET = "/mobInternationaleOnglet";
    public static final String REST_VOYAGE = "/voyage";
    public static final String REST_VOYAGE_ID = "/{voyageId}";
    public static final String REST_IMPORT_VOYAGES = "/import-voyage";
    public static final String REST_MESSAGE_IMPORT_VOYAGE = "";


    //   ---------    Parking   ----------
    public static final String REST_PARKING_VOIRIE_ONGLET = "parkingVoirieOnglet";
    public static final String REST_PARKING_VOIRIE = "/parkingVoirie";
    public static final String REST_PARKING_VOIRIE_ID = "/{parkingVoirieId}";

    //   ---------    Numerique   ----------
    public static final String REST_NUMERIQUE_ONGLET = "/numeriqueOnglet";
    public static final String REST_EQUIPEMENT_NUMERIQUE = "/equipementNumerique";
    public static final String REST_EQUIPEMENT_NUMERIQUE_ID = "/{equipementNumeriqueId}";

    //   ---------    Batiment Immobilisation Mobilier   ----------
    public static final String REST_BATIMENT_IMMOBILISATION_MOBILIER_ONGLET = "/batimentImmobilisationMobilierOnglet";
    public static final String REST_BATIMENT_EXISTANT_OU_NEUF_CONSTRUIT = "/batimentExistantOuNeufConstruit";
    public static final String REST_BATIMENT_EXISTANT_OU_NEUF_CONSTRUIT_ID = "/{batimentExistantOuNeufConstruitId}";
    public static final String REST_ENTRETIEN_COURANT = "/entretienCourant";
    public static final String REST_ENTRETIEN_COURANT_ID = "/{entretienCourantId}";
    public static final String REST_MOBILIER_ELECTROMENAGER = "/mobilierElectromenager";
    public static final String REST_MOBILIER_ELECTROMENAGER_ID = "/{mobilierElectromenagerId}";

    //   ---------    Achat   ----------
    public static final String REST_ACHAT_ONGLET = "/achatOnglet";

    //   ---------    Autre Immobilisation   ----------
    public static final String REST_AUTRE_IMMOBILISATION = "/autreImmobilisationOnglet";

    //   ---------    Dechet   ----------
    public static final String REST_DECHET_ONGLET = "/dechetOnglet";

    // ---------    General   ----------
    public static final String REST_GENERAL_ONGLET = "/generalOnglet";

    //   ---------    Vehicule    ------------
    public static final String REST_VEHICULE_ONGLET = "/vehiculeOnglet";
    public static final String REST_VEHICULE = "/vehicule";
    public static final String REST_VEHICULE_ID = "/{vehiculeId}";
}
