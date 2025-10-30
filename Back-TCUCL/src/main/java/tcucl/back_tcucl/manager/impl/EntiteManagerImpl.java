package tcucl.back_tcucl.manager.impl;

import org.springframework.stereotype.Component;
import tcucl.back_tcucl.entity.Entite;
import tcucl.back_tcucl.exceptionPersonnalisee.EntiteNonTrouveIdException;
import tcucl.back_tcucl.manager.EntiteManager;
import tcucl.back_tcucl.repository.EntiteRepository;

import java.util.List;
import java.util.Optional;

@Component
public class EntiteManagerImpl implements EntiteManager {

    private final EntiteRepository entiteRepository;

    public EntiteManagerImpl(EntiteRepository entiteRepository) {
        this.entiteRepository = entiteRepository;
    }

    @Override
    public Entite getEntitebyId(Long entiteId) {
        Optional<Entite> entite = entiteRepository.findById(entiteId);
        if (entite.isEmpty()) {
            throw new EntiteNonTrouveIdException(entiteId);
        }
        return entite.get();

    }

    @Override
    public Entite save(Entite entite) {
        return entiteRepository.save(entite);
    }

    @Override
    public List<Entite> getAllEntites() {
        return entiteRepository.findAll();
    }

    @Override
    public boolean existsEntiteByNomAndType(String nomEntite, String typeEntite) {
        return entiteRepository.existsByNomAndType(nomEntite, typeEntite);
    }
}
