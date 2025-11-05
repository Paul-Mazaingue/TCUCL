package tcucl.back_tcucl.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tcucl.back_tcucl.dto.TrajectoireDto;
import tcucl.back_tcucl.dto.TrajectoirePosteDto;
import tcucl.back_tcucl.dto.TrajectoirePosteReglageDto;
import tcucl.back_tcucl.entity.Entite;
import tcucl.back_tcucl.entity.Trajectoire;
import tcucl.back_tcucl.repository.EntiteRepository;
import tcucl.back_tcucl.repository.TrajectoireRepository;
import tcucl.back_tcucl.service.TrajectoireService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class TrajectoireServiceImpl implements TrajectoireService {
    private static final Logger log = LoggerFactory.getLogger(TrajectoireServiceImpl.class);

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
        .map(t -> {
            List<TrajectoirePosteReglageDto> postes = t.getPosteReductions()
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .map(e -> new TrajectoirePosteReglageDto(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
            return new TrajectoireDto(
                t.getEntite().getId(),
                t.getReferenceYear(),
                t.getTargetYear(),
                t.getTargetPercentage(),
                t.getLockGlobal(),
                postes
            );
        })
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
    entity.setLockGlobal(dto.getLockGlobal());

    if (entity.getPosteReductions() == null) {
        entity.setPosteReductions(new HashMap<>());
    }
    entity.getPosteReductions().clear();
    if (dto.getPostesReglages() != null) {
        dto.getPostesReglages().stream()
            .filter(p -> p.getId() != null && !p.getId().isBlank())
            .forEach(p -> entity.getPosteReductions().put(
                p.getId(),
                p.getReductionBasePct() != null ? Math.max(0, Math.min(100, p.getReductionBasePct())) : 0
            ));
    }

        Trajectoire saved = trajectoireRepository.save(entity);
    List<TrajectoirePosteReglageDto> postes = saved.getPosteReductions()
        .entrySet()
        .stream()
        .sorted(Map.Entry.comparingByKey())
        .map(e -> new TrajectoirePosteReglageDto(e.getKey(), e.getValue()))
        .collect(Collectors.toList());
    return new TrajectoireDto(saved.getEntite().getId(), saved.getReferenceYear(), saved.getTargetYear(), saved.getTargetPercentage(), saved.getLockGlobal(), postes);
    }

    @Override
    @Transactional(readOnly = true)
    public java.util.List<TrajectoirePosteDto> getPostesDefaults(Long entiteId) {
        // Mocks déplacés du front vers le back
        java.util.List<TrajectoirePosteDto> list = new java.util.ArrayList<>();
        list.add(new TrajectoirePosteDto("energie", "Énergie", 1300, 35));
        list.add(new TrajectoirePosteDto("mobilite-dom-tram", "Déplacements domicile-travail", 850, 40));
        list.add(new TrajectoirePosteDto("autres-deplacements", "Déplacements professionnels", 650, 35));
        list.add(new TrajectoirePosteDto("batiments", "Bâtiments et mobilier", 500, 25));
        list.add(new TrajectoirePosteDto("numerique", "Numérique", 280, 45));
        list.add(new TrajectoirePosteDto("achats", "Achats et restauration", 950, 35));
        list.add(new TrajectoirePosteDto("dechets", "Déchets", 180, 50));
        list.add(new TrajectoirePosteDto("autres-immobilisations", "Autres immobilisations", 220, 20));
        list.add(new TrajectoirePosteDto("emissions-fugitives", "Émissions fugitives", 45, 20));
        log.info("[Trajectoire] Postes par défaut renvoyés pour entiteId={}", entiteId);
        return list;
    }
}
