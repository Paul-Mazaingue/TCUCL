package tcucl.back_tcucl.service.impl;

import org.springframework.stereotype.Service;
import tcucl.back_tcucl.manager.ApplicationParamManager;
import tcucl.back_tcucl.service.ApplicationParamService;

@Service
public class ApplicationParamServiceImpl implements ApplicationParamService {

    private final ApplicationParamManager applicationParamManager;

    public ApplicationParamServiceImpl(ApplicationParamManager applicationParamManager) {
        this.applicationParamManager = applicationParamManager;
    }

    @Override
    public Boolean isDerniereAnneeCreee() {
        return applicationParamManager.isDerniereAnneeCreee();
    }

    @Override
    public Integer getDerniereAnneeCreee() {
        return applicationParamManager.getDerniereAnneeCreee();
    }

    @Override
    public void setDerniereAnneeCreee(Integer annee) {
        applicationParamManager.setDerniereAnneeCreee(annee);
    }

}
