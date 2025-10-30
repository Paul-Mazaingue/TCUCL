package tcucl.back_tcucl.manager.impl;

import org.springframework.stereotype.Component;
import tcucl.back_tcucl.manager.ApplicationParamManager;
import tcucl.back_tcucl.repository.ApplicationParamRepository;
import tcucl.back_tcucl.entity.ApplicationParam;

@Component
public class ApplicationParamManagerImpl implements ApplicationParamManager {

    private final ApplicationParamRepository applicationParamRepository;

    public ApplicationParamManagerImpl(ApplicationParamRepository applicationParamRepository) {
        this.applicationParamRepository = applicationParamRepository;
    }

    @Override
    public Boolean isDerniereAnneeCreee() {
        return !applicationParamRepository.findAll().isEmpty();
    }

    @Override
    public Integer getDerniereAnneeCreee() {
        return applicationParamRepository.findById(1L)
                .map(ApplicationParam::getDerniereAnneeCreee)
                .orElse(null);
    }

    @Override
    public void setDerniereAnneeCreee(Integer annee) {
        ApplicationParam param = applicationParamRepository.findById(1L).orElseGet(() -> {
            ApplicationParam nouveau = new ApplicationParam();
            nouveau.setId(1L);
            return nouveau;
        });
        param.setDerniereAnneeCreee(annee);
        applicationParamRepository.save(param);
    }

}
