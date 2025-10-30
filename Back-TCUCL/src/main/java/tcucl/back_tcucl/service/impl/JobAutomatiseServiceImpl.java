package tcucl.back_tcucl.service.impl;

import org.slf4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tcucl.back_tcucl.config.AnneeConfig;
import tcucl.back_tcucl.exceptionPersonnalisee.AnneeUniversitaireDejaCreeException;
import tcucl.back_tcucl.service.JobAutomatiseService;
import tcucl.back_tcucl.service.ParametreService;

@Service
public class JobAutomatiseServiceImpl implements JobAutomatiseService {

    Logger logger = org.slf4j.LoggerFactory.getLogger(JobAutomatiseServiceImpl.class);

    private final ParametreService parametreService;

    public JobAutomatiseServiceImpl(ParametreService parametreService) {
        this.parametreService = parametreService;
    }

    @Scheduled(cron = "0 1 0 1 9 *") // tous les 1er septembre à 00:01 pour la création de l'année universitaire
//    @Scheduled(cron = "*/10 * * * * *") // toutes les 10 sec pour test
    @Override
    public void JobCreerNouvelleAnnee() {
        try {
            parametreService.creerAnneeSuivante();
        } catch (AnneeUniversitaireDejaCreeException e) {
            logger.warn("JOB : L'année universitaire {} est déjà créée pour toutes les entités, aucune action effectuée.", AnneeConfig.getAnneeCourante());
        } catch (Exception e) {
            logger.error("JOB : Erreur lors de la création de l'année universitaire pour toutes les entités: {}", e.getMessage());
        }
    }


}
