package tcucl.back_tcucl.manager.impl.autre;

import org.springframework.stereotype.Component;
import tcucl.back_tcucl.entity.onglet.batiment.EntretienCourant;
import tcucl.back_tcucl.exceptionPersonnalisee.ElementNontrouveException;
import tcucl.back_tcucl.manager.EntretienCourantManager;
import tcucl.back_tcucl.repository.autre.EntretienCourantRepository;

@Component
public class EntretienCourantManagerImpl implements EntretienCourantManager {

    private final EntretienCourantRepository entretienCourantRepository;

    public EntretienCourantManagerImpl(EntretienCourantRepository entretienCourantRepository) {
        this.entretienCourantRepository = entretienCourantRepository;
    }

    @Override
    public EntretienCourant save(EntretienCourant entretienCourant) {
        return entretienCourantRepository.save(entretienCourant);
    }

    @Override
    public EntretienCourant findEntretienCourantById(Long entretienCourantId) {
        return entretienCourantRepository.findById(entretienCourantId).orElseThrow(() -> new ElementNontrouveException("EntretienCourant", entretienCourantId));
    }
}
