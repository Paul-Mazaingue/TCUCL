package tcucl.back_tcucl.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tcucl.back_tcucl.dto.SyntheseEGESResultatDto;
import tcucl.back_tcucl.dto.TrajectoireDto;
import tcucl.back_tcucl.dto.TrajectoirePosteDto;
import tcucl.back_tcucl.dto.TrajectoirePosteReglageDto;
import tcucl.back_tcucl.dto.TrajectoireResponseDto;
import tcucl.back_tcucl.entity.Annee;
import tcucl.back_tcucl.entity.Entite;
import tcucl.back_tcucl.entity.Trajectoire;
import tcucl.back_tcucl.repository.AnneeRepository;
import tcucl.back_tcucl.repository.EntiteRepository;
import tcucl.back_tcucl.repository.TrajectoireRepository;
import tcucl.back_tcucl.service.SyntheseEGESService;
import tcucl.back_tcucl.service.TrajectoireService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Service
@Transactional
public class TrajectoireServiceImpl implements TrajectoireService {
    private static final Logger log = LoggerFactory.getLogger(TrajectoireServiceImpl.class);

    private final TrajectoireRepository trajectoireRepository;
    private final EntiteRepository entiteRepository;
    private final AnneeRepository anneeRepository;
    private final SyntheseEGESService syntheseEGESService;

    private static final String GLOBAL_ENTITE_NOM = "GLOBAL_DEFAULT";
    private static final String GLOBAL_ENTITE_TYPE = "GLOBAL";

    public TrajectoireServiceImpl(TrajectoireRepository trajectoireRepository,
                                  EntiteRepository entiteRepository,
                                  AnneeRepository anneeRepository,
                                  SyntheseEGESService syntheseEGESService) {
        this.trajectoireRepository = trajectoireRepository;
        this.entiteRepository = entiteRepository;
        this.anneeRepository = anneeRepository;
        this.syntheseEGESService = syntheseEGESService;
    }

    /**
     * Retourne l'entité porteuse des trajectoires globales (toutes entités) en la créant au besoin.
     * Évite de persister des trajectoires avec entite_id NULL (contrainte NOT NULL en base).
     */
    private Entite getOrCreateGlobalEntite() {
        // Cherche d'abord par type, puis par nom, pour réutiliser l'existant
        return entiteRepository.findFirstByType(GLOBAL_ENTITE_TYPE)
                .or(() -> entiteRepository.findByNom(GLOBAL_ENTITE_NOM))
                .orElseGet(() -> {
                    Entite e = new Entite();
                    e.setNom(GLOBAL_ENTITE_NOM);
                    e.setType(GLOBAL_ENTITE_TYPE);
                    return entiteRepository.save(e);
                });
    }

    @Override
    @Transactional(readOnly = true)
    public TrajectoireResponseDto getByEntiteId(Long entiteId) {
        List<String> warnings = new java.util.ArrayList<>();
        Long scopedId = entiteId;
        if (entiteId != null && entiteId == 0L) {
            Entite global = getOrCreateGlobalEntite();
            scopedId = global.getId();
        }

        TrajectoireDto real = trajectoireRepository.findFirstByEntite_IdOrderByIdDesc(scopedId)
            .map(this::toDto)
            .orElse(null);

        if (real == null) {
                real = trajectoireRepository.findFirstByEntiteIsNullOrderByIdDesc()
                    .map(this::toDto)
                    .orElse(null);
            if (real != null) {
                warnings.add("Utilisation de la trajectoire par défaut (aucune trajectoire spécifique pour cette entité).");
            } else {
                warnings.add("Aucune trajectoire définie (ni par entité ni par défaut).");
            }
        }

        TrajectoireDto mock = buildMockDto(entiteId);
        return new TrajectoireResponseDto(real, mock, warnings);
    }

    @Override
    public void propagateGlobalToAll() {
        // Récupère la trajectoire globale
        Entite globalEntite = getOrCreateGlobalEntite();
        Trajectoire source = trajectoireRepository.findFirstByEntite_IdOrderByIdDesc(globalEntite.getId()).orElse(null);
        if (source == null) {
            throw new IllegalStateException("Aucune trajectoire globale définie (entiteId=0 / " + GLOBAL_ENTITE_NOM + ")");
        }

        TrajectoireDto dto = toDto(source);

        // Appliquer à toutes les entités existantes (hors globale)
        List<Entite> entites = entiteRepository.findAll();
        for (Entite e : entites) {
            if (e == null || e.getId() == null) continue;
            if (GLOBAL_ENTITE_TYPE.equals(e.getType()) || GLOBAL_ENTITE_NOM.equals(e.getNom())) continue;
            upsert(e.getId(), dto);
        }
    }

    @Override
    public TrajectoireDto upsert(Long entiteId, TrajectoireDto dto) {
        Entite entite = null;
        if (entiteId != null && entiteId != 0L) {
            entite = entiteRepository.findById(entiteId)
                    .orElseThrow(() -> new IllegalArgumentException("Entité introuvable: " + entiteId));
        }

        Trajectoire entity;
        Entite cible;
        if (entiteId != null && entiteId == 0L) {
            Entite globalEntite = getOrCreateGlobalEntite();
            entity = trajectoireRepository.findFirstByEntite_IdOrderByIdDesc(globalEntite.getId()).orElse(null);
            if (entity == null) {
                entity = new Trajectoire(globalEntite, dto.getReferenceYear(), dto.getTargetYear(), dto.getTargetPercentage());
            }
            cible = globalEntite;
        } else {
            entity = trajectoireRepository.findFirstByEntite_IdOrderByIdDesc(entiteId).orElse(null);
            if (entity == null) {
                entity = new Trajectoire(entite, dto.getReferenceYear(), dto.getTargetYear(), dto.getTargetPercentage());
            }
            cible = entite;
        }
        entity.setEntite(cible);
        entity.setReferenceYear(dto.getReferenceYear());
        entity.setTargetYear(dto.getTargetYear());
        entity.setTargetPercentage(dto.getTargetPercentage());
        entity.setLockGlobal(dto.getLockGlobal());

        if (entity.getPosteReductions() == null) {
            entity.setPosteReductions(new HashMap<>());
        }
        entity.getPosteReductions().clear();
        if (dto.getPostesReglages() != null) {
            for (TrajectoirePosteReglageDto p : dto.getPostesReglages()) {
                if (p == null || p.getId() == null || p.getId().isBlank()) {
                    continue;
                }
                int pct = p.getReductionBasePct() != null ? Math.max(0, Math.min(100, p.getReductionBasePct())) : 0;
                entity.getPosteReductions().put(p.getId(), pct);
            }
        }

        Trajectoire saved = trajectoireRepository.save(entity);
        List<TrajectoirePosteReglageDto> postes = saved.getPosteReductions()
            .entrySet()
            .stream()
            .sorted(Map.Entry.comparingByKey())
            .map(e -> new TrajectoirePosteReglageDto(e.getKey(), e.getValue()))
            .collect(Collectors.toList());
        Long entiteIdDto = saved.getEntite() != null ? saved.getEntite().getId() : 0L;
        return new TrajectoireDto(entiteIdDto, saved.getReferenceYear(), saved.getTargetYear(), saved.getTargetPercentage(), saved.getLockGlobal(), postes);
    }

    @Override
    @Transactional(readOnly = true)
    public java.util.List<TrajectoirePosteDto> getPostesDefaults(Long entiteId) {
        Trajectoire scopeTrajectoire = null;
        if (entiteId != null && entiteId != 0L) {
            scopeTrajectoire = trajectoireRepository.findFirstByEntite_IdOrderByIdDesc(entiteId).orElse(null);
        } else if (entiteId != null && entiteId == 0L) {
            Entite global = getOrCreateGlobalEntite();
            scopeTrajectoire = trajectoireRepository.findFirstByEntite_IdOrderByIdDesc(global.getId()).orElse(null);
        }
        if (scopeTrajectoire == null) {
            scopeTrajectoire = trajectoireRepository.findFirstByEntiteIsNullOrderByIdDesc().orElse(null);
        }

        Integer referenceYear = scopeTrajectoire != null ? scopeTrajectoire.getReferenceYear() : null;
        List<Annee> annees = (entiteId != null && entiteId != 0L) ? anneeRepository.findByEntiteId(entiteId) : Collections.emptyList();
        SortedSet<Integer> years = new TreeSet<>();
        annees.forEach(a -> years.add(a.getAnneeValeur()));
        if (referenceYear == null && !years.isEmpty()) {
            referenceYear = years.first();
        }

        SyntheseEGESResultatDto synth = null;
        if (referenceYear != null && entiteId != null && entiteId != 0L) {
            try {
                synth = syntheseEGESService.getSyntheseEGESResultat(entiteId, referenceYear);
            } catch (Exception ex) {
                log.warn("[Trajectoire] Impossible de charger la synthèse {} pour l'entité {}: {}", referenceYear, entiteId, ex.getMessage());
            }
        }

        Map<String, Integer> reductionOverrides = scopeTrajectoire != null && scopeTrajectoire.getPosteReductions() != null
                ? new HashMap<>(scopeTrajectoire.getPosteReductions())
                : new HashMap<>();
        int defaultReduction = scopeTrajectoire != null && scopeTrajectoire.getTargetPercentage() != null
                ? clampPct(Math.round(scopeTrajectoire.getTargetPercentage()))
                : 0;

        List<TrajectoirePosteDto> skeleton = buildPosteSkeleton();

        if (synth == null) {
            List<TrajectoirePosteDto> fallback = buildStaticFallback();
            applyReductions(fallback, reductionOverrides, defaultReduction);
            log.info("[Trajectoire] Postes par défaut renvoyés (fallback statique) pour entiteId={}", entiteId);
            return fallback;
        }

        Map<String, Float> totals = buildTotalsFromSynthese(synth);
        skeleton.forEach(p -> {
            float val = totals.getOrDefault(p.getId(), 0f);
            p.setEmissionsReference((int) Math.round(Math.max(0f, val)));
            int pct = reductionOverrides.getOrDefault(p.getId(), defaultReduction);
            p.setReductionBasePct(clampPct(pct));
        });

        log.info("[Trajectoire] Postes par défaut calculés depuis les données {} pour entiteId={}", referenceYear, entiteId);
        return skeleton;
    }

    private TrajectoireDto toDto(Trajectoire t) {
        List<TrajectoirePosteReglageDto> postes = t.getPosteReductions()
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .map(e -> new TrajectoirePosteReglageDto(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
        Long entiteId = t.getEntite() != null ? t.getEntite().getId() : 0L;
        return new TrajectoireDto(entiteId, t.getReferenceYear(), t.getTargetYear(), t.getTargetPercentage(), t.getLockGlobal(), postes);
    }

    private TrajectoireDto buildMockDto(Long entiteId) {
        List<TrajectoirePosteReglageDto> postes = java.util.Arrays.asList(
                new TrajectoirePosteReglageDto("energie", 35),
                new TrajectoirePosteReglageDto("mobilite-dom-tram", 40),
                new TrajectoirePosteReglageDto("autres-deplacements", 35),
                new TrajectoirePosteReglageDto("batiments", 25),
                new TrajectoirePosteReglageDto("numerique", 45),
                new TrajectoirePosteReglageDto("achats", 35),
                new TrajectoirePosteReglageDto("dechets", 50),
                new TrajectoirePosteReglageDto("autres-immobilisations", 20),
                new TrajectoirePosteReglageDto("emissions-fugitives", 20)
        );
        return new TrajectoireDto(entiteId, 2019, 2030, 32f, Boolean.FALSE, postes);
    }

    private List<TrajectoirePosteDto> buildPosteSkeleton() {
        List<TrajectoirePosteDto> list = new ArrayList<>();
        list.add(new TrajectoirePosteDto("energie", "Énergie", 0, 0));
        list.add(new TrajectoirePosteDto("mobilite-dom-tram", "Déplacements domicile-travail", 0, 0));
        list.add(new TrajectoirePosteDto("autres-deplacements", "Déplacements professionnels", 0, 0));
        list.add(new TrajectoirePosteDto("batiments", "Bâtiments et mobilier", 0, 0));
        list.add(new TrajectoirePosteDto("numerique", "Numérique", 0, 0));
        list.add(new TrajectoirePosteDto("achats", "Achats et restauration", 0, 0));
        list.add(new TrajectoirePosteDto("dechets", "Déchets", 0, 0));
        list.add(new TrajectoirePosteDto("autres-immobilisations", "Autres immobilisations", 0, 0));
        list.add(new TrajectoirePosteDto("emissions-fugitives", "Émissions fugitives", 0, 0));
        return list;
    }

    private List<TrajectoirePosteDto> buildStaticFallback() {
        List<TrajectoirePosteDto> list = new ArrayList<>();
        list.add(new TrajectoirePosteDto("energie", "Énergie", 1300, 35));
        list.add(new TrajectoirePosteDto("mobilite-dom-tram", "Déplacements domicile-travail", 850, 40));
        list.add(new TrajectoirePosteDto("autres-deplacements", "Déplacements professionnels", 650, 35));
        list.add(new TrajectoirePosteDto("batiments", "Bâtiments et mobilier", 500, 25));
        list.add(new TrajectoirePosteDto("numerique", "Numérique", 280, 45));
        list.add(new TrajectoirePosteDto("achats", "Achats et restauration", 950, 35));
        list.add(new TrajectoirePosteDto("dechets", "Déchets", 180, 50));
        list.add(new TrajectoirePosteDto("autres-immobilisations", "Autres immobilisations", 220, 20));
        list.add(new TrajectoirePosteDto("emissions-fugitives", "Émissions fugitives", 45, 20));
        return list;
    }

    private Map<String, Float> buildTotalsFromSynthese(SyntheseEGESResultatDto synth) {
        Map<String, Float> totals = new HashMap<>();
        if (synth == null) return totals;
        totals.put("energie", safe(synth.getEnergieGlobal()));
        totals.put("mobilite-dom-tram", safe(synth.getMobiliteDomicileTravailGlobal()));
        totals.put("autres-deplacements", safe(synth.getAutreMobiliteFrGlobal()) + safe(synth.getMobiliteInternationalGlobal()));
        totals.put("batiments", safe(synth.getBatimentParkingGlobal()));
        totals.put("numerique", safe(synth.getNumeriqueGlobal()));
        totals.put("achats", safe(synth.getAchatGlobal()));
        totals.put("dechets", safe(synth.getDechetGlobal()));
        totals.put("autres-immobilisations", safe(synth.getAutreImmobilisationGlobal()));
        totals.put("emissions-fugitives", safe(synth.getEmissionFugitivesGlobal()));
        return totals;
    }

    private void applyReductions(List<TrajectoirePosteDto> list, Map<String, Integer> overrides, int defaultReduction) {
        list.forEach(p -> {
            int pct = overrides.getOrDefault(p.getId(), defaultReduction);
            p.setReductionBasePct(clampPct(pct));
        });
    }

    private int clampPct(Integer pct) {
        if (pct == null) return 0;
        return Math.max(0, Math.min(100, pct));
    }

    private float safe(Float v) { return v != null ? v : 0f; }
}
