package tcucl.back_tcucl.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tcucl.back_tcucl.dto.TrajectoireDto;
import tcucl.back_tcucl.entity.Entite;
import tcucl.back_tcucl.entity.Trajectoire;
import tcucl.back_tcucl.repository.EntiteRepository;
import tcucl.back_tcucl.repository.TrajectoireRepository;
import tcucl.back_tcucl.service.TrajectoireService;

@Service
@Transactional
public class TrajectoireServiceImpl implements TrajectoireService {

    private final TrajectoireRepository trajectoireRepository;
    private final EntiteRepository entiteRepository;

    public TrajectoireServiceImpl(TrajectoireRepository trajectoireRepository, EntiteRepository entiteRepository) {
        this.trajectoireRepository = trajectoireRepository;
        this.entiteRepository = entiteRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public TrajectoireDto getByEntiteId(Long entiteId) {
        return trajectoireRepository.findByEntite_Id(entiteId)
                .map(t -> new TrajectoireDto(
                        t.getEntite().getId(),
                        t.getReferenceYear(),
                        t.getTargetYear(),
                        t.getTargetPercentage()
                ))
                .orElse(null);
    }

    @Override
    public TrajectoireDto upsert(Long entiteId, TrajectoireDto dto) {
        Entite entite = entiteRepository.findById(entiteId)
                .orElseThrow(() -> new IllegalArgumentException("Entité introuvable: " + entiteId));

        Trajectoire entity = trajectoireRepository.findByEntite_Id(entiteId)
                .orElseGet(() -> new Trajectoire(entite, dto.getReferenceYear(), dto.getTargetYear(), dto.getTargetPercentage()));

        entity.setEntite(entite);
        entity.setReferenceYear(dto.getReferenceYear());
        entity.setTargetYear(dto.getTargetYear());
        entity.setTargetPercentage(dto.getTargetPercentage());

        Trajectoire saved = trajectoireRepository.save(entity);
        return new TrajectoireDto(saved.getEntite().getId(), saved.getReferenceYear(), saved.getTargetYear(), saved.getTargetPercentage());
    }
}
