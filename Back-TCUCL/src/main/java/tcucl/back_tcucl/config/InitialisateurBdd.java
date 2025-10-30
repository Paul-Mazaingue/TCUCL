package tcucl.back_tcucl.config;

import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tcucl.back_tcucl.dto.CreationEntiteEtAdminDto_SuperAdmin;
import tcucl.back_tcucl.exceptionPersonnalisee.AnneeUniversitaireDejaCreeException;
import tcucl.back_tcucl.service.ApplicationParamService;
import tcucl.back_tcucl.service.EntiteService;
import tcucl.back_tcucl.service.ParametreService;

import static tcucl.back_tcucl.Constante.SUPERADMIN_TRUE;

@Component
public class InitialisateurBdd implements CommandLineRunner {

    Logger logger = org.slf4j.LoggerFactory.getLogger(InitialisateurBdd.class);

    private final ApplicationParamService applicationParamService;
    private final ParametreService parametreService;
    private final EntiteService entiteService;

    public InitialisateurBdd(ApplicationParamService applicationParamService, ParametreService parametreService, EntiteService entiteService) {
        this.applicationParamService = applicationParamService;
        this.parametreService = parametreService;

        this.entiteService = entiteService;
    }

    @Override
    public void run(String... args) {
        if (!applicationParamService.isDerniereAnneeCreee()) {
            int annee = AnneeConfig.getAnneeCourante();
            applicationParamService.setDerniereAnneeCreee(annee);
            logger.info("INIT : Année " + annee + " enregistrée au démarrage en bdd en tant que dernière année créée.");
        } else {
            logger.info("INIT : Aucune action nécessaire : la valeur de la dernière année créée est déjà enregistrée : " + applicationParamService.getDerniereAnneeCreee());
        }

        // Création de l'entité SUPERADMIN + userTechniqueSuperAdmin SuperAdmin
        try {
            if (entiteService.getAllEntites().isEmpty()) {
                parametreService.creerEntiteEtAdmin(
                        new CreationEntiteEtAdminDto_SuperAdmin(
                                "Entité SUPERADMIN",            // NomEntité
                                "SUPERADMIN",                        // Type
                                "Super Admin",                       // NomUtilisateur
                                "Utilisateur Technique 1",           // PrenomUtilisateur
                                "trajectoirecarbone.ucl@gmail.com",  // <-- A REMPLACER PAR L'EMAIL DU SUPERADMIN
                                SUPERADMIN_TRUE                      // EstSuperAdmin
                        )
                );
                logger.info("INIT : Entité SUPERADMIN créée avec succès.");
            } else {
                logger.info("INIT : Entité SUPERADMIN déjà créée.");
            }
        } catch (Exception e) {
            logger.error("INIT : Erreur lors de la création de l'entité SUPERADMIN : " + e.getMessage());
        }

        // vérification que l'ajout d'année a bien été fait cette année au redémarrage de l'app
        // au cas où l'app a planté pendant l'éxecution de la cron
        try {
            parametreService.creerAnneeSuivante();
        } catch (AnneeUniversitaireDejaCreeException e) {
            logger.warn("INIT : L'année universitaire {} est déjà créée pour toutes les entités, aucune action effectuée.", AnneeConfig.getAnneeCourante());
        } catch (Exception e) {
            logger.error("INIT : Erreur lors de la création de l'année universitaire pour toutes les entités: {}", e.getMessage());
        }

        logger.info("----------------  Initialisation de l'application terminée.  ----------------  \n\n");

    }
}
