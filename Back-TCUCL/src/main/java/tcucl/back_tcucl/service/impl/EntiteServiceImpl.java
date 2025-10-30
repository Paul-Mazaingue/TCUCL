package tcucl.back_tcucl.service.impl;

import org.springframework.stereotype.Service;
import tcucl.back_tcucl.entity.Annee;
import tcucl.back_tcucl.entity.Entite;
import tcucl.back_tcucl.exceptionPersonnalisee.EntiteDejaExistantAvecNomTypeException;
import tcucl.back_tcucl.manager.EntiteManager;
import tcucl.back_tcucl.service.ApplicationParamService;
import tcucl.back_tcucl.service.EntiteService;

import java.util.List;

@Service
public class EntiteServiceImpl implements EntiteService {

    private final EntiteManager entiteManager;
    private final ApplicationParamService applicationParamService;

    public EntiteServiceImpl(EntiteManager entiteManager, ApplicationParamService applicationParamService) {
        this.entiteManager = entiteManager;
        this.applicationParamService = applicationParamService;
    }

    @Override
    public Entite getEntiteById(Long entiteId) {
        return entiteManager.getEntitebyId(entiteId);
    }

    @Override
    public Entite saveEntite(Entite entite) {
        return entiteManager.save(entite);
    }

    @Override
    public Entite creerEntite(String entiteNom, String entiteType, Boolean isSuperAdmin) {
        if (!entiteManager.existsEntiteByNomAndType(entiteNom, entiteType)) {
            Entite entite = new Entite(entiteNom, entiteType);
            Integer derniereAnneeCreee = applicationParamService.getDerniereAnneeCreee();
            if (!isSuperAdmin) {
                for (int i = 2019; i <= derniereAnneeCreee; i++) {
                    Annee annee = new Annee(i);
                    entite.addAnnee(annee); // gère la liaison bidirectionnelle
                }
            }
            else{
                Annee annee = new Annee(2000); // 2000 afin que cette année ne soit jamais appellée dans les graph/année mais la présence d'une année est obligatoire
                entite.addAnnee(annee); // gère la liaison bidirectionnelle
            }
            return entiteManager.save(entite);
        } else {
            throw new EntiteDejaExistantAvecNomTypeException(entiteNom, entiteType);
        }
    }

    @Override
    public List<Entite> getAllEntites() {
        return entiteManager.getAllEntites();
    }

    @Override
    public Boolean existEntiteByNomEtType(String nomEntite, String typeEntite) {
        return entiteManager.existsEntiteByNomAndType(nomEntite, typeEntite);
    }
}
