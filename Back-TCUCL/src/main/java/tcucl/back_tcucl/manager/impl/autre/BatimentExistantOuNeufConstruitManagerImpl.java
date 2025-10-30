package tcucl.back_tcucl.manager.impl.autre;

import org.springframework.stereotype.Component;
import tcucl.back_tcucl.entity.onglet.batiment.BatimentExistantOuNeufConstruit;
import tcucl.back_tcucl.exceptionPersonnalisee.ElementNontrouveException;
import tcucl.back_tcucl.manager.BatimentExistantOuNeufConstruitManager;
import tcucl.back_tcucl.repository.autre.BatimentExistantOuNeufConstruitRepository;

@Component
public class BatimentExistantOuNeufConstruitManagerImpl implements BatimentExistantOuNeufConstruitManager {

    private final BatimentExistantOuNeufConstruitRepository batimentExistantOuNeufConstruitRepository;

    public BatimentExistantOuNeufConstruitManagerImpl(BatimentExistantOuNeufConstruitRepository batimentExistantOuNeufConstruitRepository) {
        this.batimentExistantOuNeufConstruitRepository = batimentExistantOuNeufConstruitRepository;
    }

    @Override
    public BatimentExistantOuNeufConstruit save(BatimentExistantOuNeufConstruit batimentExistantOuNeufConstruit) {
        return batimentExistantOuNeufConstruitRepository.save(batimentExistantOuNeufConstruit);
    }

    @Override
    public BatimentExistantOuNeufConstruit findBatimentExistantOuNeufConstruitById(Long batimentExistantOuNeufConstruitId) {
        return batimentExistantOuNeufConstruitRepository.findById(batimentExistantOuNeufConstruitId).orElseThrow(() -> new ElementNontrouveException("EntretienCourant", batimentExistantOuNeufConstruitId));
    }}
