package tcucl.back_tcucl.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tcucl.back_tcucl.dto.ListIdDto;
import tcucl.back_tcucl.dto.OutilSuiviDto;
import tcucl.back_tcucl.dto.SyntheseEGESResultatDto;
import tcucl.back_tcucl.dto.onglet.dechet.DechetResultatDto;
import tcucl.back_tcucl.dto.onglet.energie.EnergieResultatDto;
import tcucl.back_tcucl.dto.onglet.mobiliteDomicileTravail.MobiliteDomicileTravailResultatDto;
import tcucl.back_tcucl.dto.onglet.mobInternational.MobInternationalResultatDto;
import tcucl.back_tcucl.entity.Annee;
import tcucl.back_tcucl.entity.Trajectoire;
import tcucl.back_tcucl.entity.onglet.GeneralOnglet;
import tcucl.back_tcucl.entity.onglet.batiment.MobilierElectromenager;
import tcucl.back_tcucl.entity.onglet.numerique.NumeriqueOnglet;
import tcucl.back_tcucl.repository.AnneeRepository;
import tcucl.back_tcucl.repository.TrajectoireRepository;
import tcucl.back_tcucl.service.*;
import tcucl.back_tcucl.exceptionPersonnalisee.AucunEtudiantEnregistre;
import tcucl.back_tcucl.exceptionPersonnalisee.AucunSalarieEnregistre;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class OutilSuiviServiceImpl implements OutilSuiviService {

    private static final Logger log = LoggerFactory.getLogger(OutilSuiviServiceImpl.class);

    private static final List<String> POSTES = List.of(
            "Emissions fugitives",
            "Energie",
            "Déplacements France",
            "Déplacements internationaux",
            "Bâtiments, mobilier et parkings",
            "Numérique",
            "Autres immobilisations",
            "Achats et restauration",
            "Déchets"
    );

    private static final Map<String, String> POSTE_TO_TRAJECTOIRE_KEY = Map.of(
            "Emissions fugitives", "emissions-fugitives",
            "Energie", "energie",
            "Déplacements internationaux", "autres-deplacements",
            "Bâtiments, mobilier et parkings", "batiments",
            "Numérique", "numerique",
            "Autres immobilisations", "autres-immobilisations",
            "Achats et restauration", "achats",
            "Déchets", "dechets"
    );

    private static final List<String> INDICATOR_KEYS = List.of(
            "conso_energie",
            "chauffage",
            "electricite",
            "intensite_carbone_energie",
            "distance_salarie",
            "part_modale_voiture_salarie",
            "part_modale_ve_salarie",
            "part_modale_doux_salarie",
            "distance_etudiants",
            "part_modale_voiture_etudiants",
            "part_modale_ve_etudiants",
            "part_modale_doux_etudiants",
            "intensite_carbone_trajets",
            "distance_internationale",
            "intensite_carbone_international",
            "nb_mobilier",
            "nb_numerique",
            "quantite_dechets"
    );

    private final AnneeRepository anneeRepository;
    private final AnneeService anneeService;
    private final SyntheseEGESService syntheseEGESService;
    private final TrajectoireRepository trajectoireRepository;
    private final GeneralOngletService generalOngletService;
    private final EnergieOngletService energieOngletService;
    private final MobiliteDomicileTravailOngletService mobiliteDomicileTravailOngletService;
    private final MobInternationalOngletService mobInternationalOngletService;
    private final BatimentImmobilisationMobilierOngletService batimentImmobilisationMobilierOngletService;
    private final NumeriqueOngletService numeriqueOngletService;
    private final DechetOngletService dechetOngletService;

    public OutilSuiviServiceImpl(AnneeRepository anneeRepository,
                                 AnneeService anneeService,
                                 SyntheseEGESService syntheseEGESService,
                                 TrajectoireRepository trajectoireRepository,
                                 GeneralOngletService generalOngletService,
                                 EnergieOngletService energieOngletService,
                                 MobiliteDomicileTravailOngletService mobiliteDomicileTravailOngletService,
                                 MobInternationalOngletService mobInternationalOngletService,
                                 BatimentImmobilisationMobilierOngletService batimentImmobilisationMobilierOngletService,
                                 NumeriqueOngletService numeriqueOngletService,
                                 DechetOngletService dechetOngletService) {
        this.anneeRepository = anneeRepository;
        this.anneeService = anneeService;
        this.syntheseEGESService = syntheseEGESService;
        this.trajectoireRepository = trajectoireRepository;
        this.generalOngletService = generalOngletService;
        this.energieOngletService = energieOngletService;
        this.mobiliteDomicileTravailOngletService = mobiliteDomicileTravailOngletService;
        this.mobInternationalOngletService = mobInternationalOngletService;
        this.batimentImmobilisationMobilierOngletService = batimentImmobilisationMobilierOngletService;
        this.numeriqueOngletService = numeriqueOngletService;
        this.dechetOngletService = dechetOngletService;
    }

    @Override
    public OutilSuiviDto loadForEntite(Long entiteId) {
        log.info("[OutilSuivi] Chargement des données pour entiteId={}", entiteId);

        List<Integer> allYears = anneeRepository.findByEntiteId(entiteId).stream()
                .map(Annee::getAnneeValeur)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    log.info("[OutilSuivi] Années disponibles pour entiteId={} : {}", entiteId, allYears);

        if (allYears.isEmpty()) {
            log.info("[OutilSuivi] Aucune année trouvée pour entiteId={}", entiteId);
            return new OutilSuiviDto(entiteId,
                    Collections.emptyList(),
                    Collections.emptyList(),
                    Collections.emptyList(),
                    new ArrayList<>(POSTES),
                    new LinkedHashMap<>(),
                    new LinkedHashMap<>(),
                    new LinkedHashMap<>(),
                    Collections.emptyList());
        }

        Trajectoire trajectoire = trajectoireRepository.findByEntite_Id(entiteId).orElse(null);
        List<Integer> years = filterYearsByTrajectoire(allYears, trajectoire);
    log.info("[OutilSuivi] Années retenues pour entiteId={} (référence={}, cible={}) : {}", entiteId,
                trajectoire != null ? trajectoire.getReferenceYear() : null,
                trajectoire != null ? trajectoire.getTargetYear() : null,
                years);

        if (years.isEmpty()) {
            log.info("[OutilSuivi] Aucune année dans l'intervalle référence/cible pour entiteId={}", entiteId);
            return new OutilSuiviDto(entiteId,
                    Collections.emptyList(),
                    Collections.emptyList(),
                    Collections.emptyList(),
                    new ArrayList<>(POSTES),
                    new LinkedHashMap<>(),
                    new LinkedHashMap<>(),
                    new LinkedHashMap<>(),
                    Collections.emptyList());
        }

        Map<Integer, YearData> dataByYear = new LinkedHashMap<>();
        for (Integer year : years) {
            dataByYear.put(year, buildYearData(entiteId, year).orElse(null));
            YearData snapshot = dataByYear.get(year);
            if (snapshot != null) {
                log.info("[OutilSuivi] Année {} - totalGlobal={} - postes={}", year, snapshot.globalTotal, snapshot.posteValues);
                log.info("[OutilSuivi] Année {} - indicateurs={}", year, snapshot.indicatorValues);
            } else {
                log.info("[OutilSuivi] Année {} - aucune donnée calculée", year);
            }
        }

        List<Float> realise = new ArrayList<>(years.size());
        List<Float> globalTotals = new ArrayList<>(years.size());
        Map<Integer, List<Float>> postesRealiseParAn = new LinkedHashMap<>();
        Map<Integer, Map<String, Float>> indicateursParAn = new LinkedHashMap<>();

        for (Integer year : years) {
            YearData data = dataByYear.get(year);
            List<Float> perPoste = new ArrayList<>(POSTES.size());
            for (String poste : POSTES) {
                perPoste.add(data != null ? data.posteValues.get(poste) : null);
            }
            postesRealiseParAn.put(year, perPoste);

            Float globalValue = data != null ? data.globalTotal : null;
            realise.add(globalValue);
            globalTotals.add(globalValue);
            indicateursParAn.put(year, snapshotIndicators(data));
        }

        Map<String, Map<Integer, Float>> posteObjectives = computePosteObjectives(years, dataByYear, trajectoire);

        Map<Integer, List<Float>> postesObjectifParAn = new LinkedHashMap<>();
        List<Float> objectif = new ArrayList<>(years.size());

        for (Integer year : years) {
            List<Float> objectives = new ArrayList<>(POSTES.size());
            Float sum = null;
            for (String poste : POSTES) {
                Float value = posteObjectives.getOrDefault(poste, Collections.emptyMap()).get(year);
                objectives.add(value);
                if (value != null) {
                    sum = sum == null ? value : sum + value;
                }
            }
            postesObjectifParAn.put(year, objectives);
            objectif.add(sum);
        }

    log.info("[OutilSuivi] Séries globales objectif={} realise={}", objectif, realise);
    log.info("[OutilSuivi] Détails objectifs par poste={}", posteObjectives);


        OutilSuiviDto dto = new OutilSuiviDto(
                entiteId,
                years,
                objectif,
                realise,
                new ArrayList<>(POSTES),
                postesObjectifParAn,
                postesRealiseParAn,
                indicateursParAn,
                globalTotals
        );

    log.info("[OutilSuivi] DTO final renvoyé pour entiteId={} : {}", entiteId, dto);
        log.info("[OutilSuivi] Chargement terminé pour entiteId={} (années={})", entiteId, years.size());
        return dto;
    }

    private List<Integer> filterYearsByTrajectoire(List<Integer> years, Trajectoire trajectoire) {
        if (trajectoire == null) {
            return years;
        }
        Integer reference = trajectoire.getReferenceYear();
        Integer target = trajectoire.getTargetYear();
        if (reference == null || target == null) {
            log.info("[OutilSuivi] Trajectoire sans référence ou cible, aucune filtration appliquée");
            return years;
        }
        if (reference > target) {
            log.warn("[OutilSuivi] Référence ({}) supérieure à la cible ({}) - conservation de toutes les années", reference, target);
            return years;
        }
        return years.stream()
                .filter(year -> year >= reference && year <= target)
                .collect(Collectors.toList());
    }

    private Optional<YearData> buildYearData(Long entiteId, Integer year) {
        try {
            ListIdDto ongletIds = anneeService.getongletIdListForEntiteAndAnnee(entiteId, year);
            if (ongletIds == null) {
                log.info("[OutilSuivi] Aucun onglet enregistré pour entiteId={}, année={}", entiteId, year);
                return Optional.empty();
            }
            log.info("[OutilSuivi] entiteId={}, année={} -> onglets={}", entiteId, year, ongletIds);

            SyntheseEGESResultatDto synthese = syntheseEGESService.getSyntheseEGESResultat(entiteId, year);
            Map<String, Float> postes = createPosteMap();

            Float emissionFugitives = clean(synthese.getEmissionFugitivesGlobal());
            Float energie = clean(synthese.getEnergieGlobal());
            Float mobiliteDomicile = clean(synthese.getMobiliteDomicileTravailGlobal());
            Float autreMobiliteFr = clean(synthese.getAutreMobiliteFrGlobal());
            Float mobiliteInternational = clean(synthese.getMobiliteInternationalGlobal());
            Float batiment = clean(synthese.getBatimentParkingGlobal());
            Float numerique = clean(synthese.getNumeriqueGlobal());
            Float autreImmobilisation = clean(synthese.getAutreImmobilisationGlobal());
            Float achat = clean(synthese.getAchatGlobal());
            Float dechet = clean(synthese.getDechetGlobal());

            postes.put("Emissions fugitives", emissionFugitives);
            postes.put("Energie", energie);
            postes.put("Déplacements France", sumNullable(mobiliteDomicile, autreMobiliteFr));
            postes.put("Déplacements internationaux", mobiliteInternational);
            postes.put("Bâtiments, mobilier et parkings", batiment);
            postes.put("Numérique", numerique);
            postes.put("Autres immobilisations", autreImmobilisation);
            postes.put("Achats et restauration", achat);
            postes.put("Déchets", dechet);

            Float globalTotal = clean(synthese.getBilanCarboneTotalGlobal());
            if (globalTotal == null) {
                globalTotal = sumNullable(postes.values().toArray(new Float[0]));
            }

            Map<String, Float> indicators = createIndicatorMap();
            Float nbSalaries = null;
            Float nbEtudiants = null;

            if (ongletIds.getGeneralOnglet() != null) {
                GeneralOnglet general = generalOngletService.getGeneralOngletById(ongletIds.getGeneralOnglet());
                if (general != null) {
                    nbSalaries = general.getNbSalarie() != null ? general.getNbSalarie().floatValue() : null;
                    nbEtudiants = general.getNbEtudiant() != null ? general.getNbEtudiant().floatValue() : null;
                }
            }

            EnergieResultatDto energieResultat = null;
            if (ongletIds.getEnergieOnglet() != null) {
                energieResultat = energieOngletService.getEnergieResultat(ongletIds.getEnergieOnglet());
            }
            populateEnergyIndicators(indicators, energieResultat);

            MobiliteDomicileTravailResultatDto mobiliteResultat = null;
            if (ongletIds.getMobiliteDomicileTravailOnglet() != null) {
                try {
                    mobiliteResultat = mobiliteDomicileTravailOngletService.getMobiliteDomicileTravailResultat(ongletIds.getMobiliteDomicileTravailOnglet());
                } catch (AucunSalarieEnregistre | AucunEtudiantEnregistre ex) {
                    log.info("[OutilSuivi] Mobilité domicile-travail incomplète pour entiteId={}, année={} : {}", entiteId, year, ex.getMessage());
                }
            }
            populateMobilityIndicators(indicators, mobiliteResultat, nbSalaries, nbEtudiants);

            MobInternationalResultatDto mobInternationalResultat = null;
            if (ongletIds.getMobInternationalOnglet() != null) {
                mobInternationalResultat = mobInternationalOngletService.getMobInternationalResultat(ongletIds.getMobInternationalOnglet());
            }
            populateInternationalIndicators(indicators, mobInternationalResultat);

            if (ongletIds.getBatimentImmobilisationMobilierOnglet() != null) {
                var batimentOnglet = batimentImmobilisationMobilierOngletService.getBatimentImmobilisationMobilierOngletById(ongletIds.getBatimentImmobilisationMobilierOnglet());
                if (batimentOnglet != null) {
                    float nbMobilier = batimentOnglet.getMobilierElectromenagers().stream()
                            .filter(Objects::nonNull)
                            .map(MobilierElectromenager::getQuantite)
                            .filter(Objects::nonNull)
                            .mapToInt(Integer::intValue)
                            .sum();
                    indicators.put("nb_mobilier", nbMobilier > 0 ? nbMobilier : 0f);
                }
            }

            if (ongletIds.getNumeriqueOnglet() != null) {
                NumeriqueOnglet numeriqueOnglet = numeriqueOngletService.getNumeriqueOngletById(ongletIds.getNumeriqueOnglet());
                if (numeriqueOnglet != null) {
                    float nbEquipements = numeriqueOnglet.getEquipementNumeriqueList().stream()
                            .filter(Objects::nonNull)
                            .map(e -> e.getNombre())
                            .filter(Objects::nonNull)
                            .mapToInt(Integer::intValue)
                            .sum();
                    indicators.put("nb_numerique", nbEquipements > 0 ? nbEquipements : 0f);
                }
            }

            if (ongletIds.getDechetOnglet() != null) {
                DechetResultatDto dechetResultat = dechetOngletService.getDechetResultat(ongletIds.getDechetOnglet());
                indicators.put("quantite_dechets", clean(dechetResultat != null ? dechetResultat.getTotalProduit() : null));
            }

            return Optional.of(new YearData(year,
                    globalTotal,
                    postes,
                    indicators,
                    mobiliteDomicile,
                    autreMobiliteFr,
                    mobiliteInternational));
        } catch (EntityNotFoundException ex) {
            log.info("[OutilSuivi] Année non trouvée pour entiteId={} et année={} : {}", entiteId, year, ex.getMessage());
            return Optional.empty();
        } catch (RuntimeException ex) {
            log.warn("[OutilSuivi] Impossible de calculer les données pour entiteId={}, année={} : {}", entiteId, year, ex.getMessage());
            return Optional.empty();
        }
    }

    private Map<String, Float> snapshotIndicators(YearData data) {
        Map<String, Float> snapshot = createIndicatorMap();
        if (data != null) {
            INDICATOR_KEYS.forEach(key -> snapshot.put(key, data.indicatorValues.get(key)));
        }
        return snapshot;
    }

    private Map<String, Map<Integer, Float>> computePosteObjectives(List<Integer> years,
                                                                     Map<Integer, YearData> dataByYear,
                                                                     Trajectoire trajectoire) {
        Map<String, Map<Integer, Float>> result = new LinkedHashMap<>();

        if (trajectoire == null) {
            return copyActualSeries(years, dataByYear);
        }

        YearData baselineData = resolveBaselineData(trajectoire.getReferenceYear(), years, dataByYear);
        if (baselineData == null) {
            return copyActualSeries(years, dataByYear);
        }

        Integer refYear = trajectoire.getReferenceYear() != null ? trajectoire.getReferenceYear() : baselineData.year;
        if (refYear == null && !years.isEmpty()) {
            refYear = years.get(0);
        }

        Integer targetYear = trajectoire.getTargetYear();
        if (targetYear == null && !years.isEmpty()) {
            targetYear = years.get(years.size() - 1);
        }

        Float globalTargetPct = clampPct(trajectoire.getTargetPercentage());
        Map<String, Integer> reductions = trajectoire.getPosteReductions() != null ? trajectoire.getPosteReductions() : Collections.emptyMap();

        for (String poste : POSTES) {
            Map<Integer, Float> series = new LinkedHashMap<>();
            Float baselineValue = baselineData.posteValues.get(poste);
            Float reductionPct = resolveReductionPct(poste, reductions, globalTargetPct);
            Float targetValue = computeTargetValue(baselineValue, reductionPct);

            for (Integer year : years) {
                YearData actualData = dataByYear.get(year);
                Float actualValue = actualData != null ? actualData.posteValues.get(poste) : null;
                Float value = computeObjectiveValue(year, refYear, targetYear, baselineValue, targetValue, actualValue);
                series.put(year, value);
            }

            result.put(poste, series);
            log.info("[OutilSuivi] Objectifs poste='{}' baseline={} réduction={}%% -> {}", poste, baselineValue, reductionPct, series);
        }

        return result;
    }

    private Map<String, Map<Integer, Float>> copyActualSeries(List<Integer> years, Map<Integer, YearData> dataByYear) {
        Map<String, Map<Integer, Float>> result = new LinkedHashMap<>();
        for (String poste : POSTES) {
            Map<Integer, Float> series = new LinkedHashMap<>();
            for (Integer year : years) {
                YearData data = dataByYear.get(year);
                series.put(year, data != null ? data.posteValues.get(poste) : null);
            }
            result.put(poste, series);
        }
        return result;
    }

    private YearData resolveBaselineData(Integer referenceYear, List<Integer> years, Map<Integer, YearData> dataByYear) {
        if (referenceYear != null) {
            YearData data = dataByYear.get(referenceYear);
            if (data != null) {
                return data;
            }
        }
        for (Integer year : years) {
            YearData data = dataByYear.get(year);
            if (data != null) {
                return data;
            }
        }
        return null;
    }

    private Map<String, Float> createPosteMap() {
        Map<String, Float> map = new LinkedHashMap<>(POSTES.size());
        POSTES.forEach(poste -> map.put(poste, null));
        return map;
    }

    private Map<String, Float> createIndicatorMap() {
        Map<String, Float> map = new LinkedHashMap<>(INDICATOR_KEYS.size());
        INDICATOR_KEYS.forEach(key -> map.put(key, null));
        return map;
    }

    private void populateEnergyIndicators(Map<String, Float> indicators, EnergieResultatDto energieResultat) {
        indicators.put("conso_energie", clean(energieResultat != null ? energieResultat.getConsoEnergieFinale() : null));
        indicators.put("chauffage", clean(energieResultat != null ? energieResultat.getConsoEnergieChauffage() : null));
        indicators.put("electricite", clean(energieResultat != null ? energieResultat.getconsoEnergieSpecifique() : null));
        indicators.put("intensite_carbone_energie", clean(energieResultat != null ? energieResultat.getIntensiteCarboneMoyenne() : null));
    }

    private void populateMobilityIndicators(Map<String, Float> indicators,
                                            MobiliteDomicileTravailResultatDto mobilite,
                                            Float nbSalaries,
                                            Float nbEtudiants) {
        Float distanceSalarie = multiply(clean(mobilite != null ? mobilite.getDistanceAnnuelleParUsagerSalaries() : null), nbSalaries);
        Float distanceEtudiants = multiply(clean(mobilite != null ? mobilite.getDistanceAnnuelleParUsagerEtudiants() : null), nbEtudiants);

        indicators.put("distance_salarie", distanceSalarie);
        indicators.put("distance_etudiants", distanceEtudiants);

        Float partVoitureSalarie = sumNullable(clean(mobilite != null ? mobilite.getPartModaleVoitureThermiqueSalaries() : null),
                clean(mobilite != null ? mobilite.getPartModaleVoitureElectriqueSalaries() : null));
        Float partVoitureEtudiant = sumNullable(clean(mobilite != null ? mobilite.getPartModaleVoitureThermiqueEtudiants() : null),
                clean(mobilite != null ? mobilite.getPartModaleVoitureElectriqueEtudiants() : null));

        indicators.put("part_modale_voiture_salarie", partVoitureSalarie);
        indicators.put("part_modale_ve_salarie", clean(mobilite != null ? mobilite.getPartModaleVoitureElectriqueSalaries() : null));
        indicators.put("part_modale_doux_salarie", clean(mobilite != null ? mobilite.getPartModaleModesDouxSalaries() : null));

        indicators.put("part_modale_voiture_etudiants", partVoitureEtudiant);
        indicators.put("part_modale_ve_etudiants", clean(mobilite != null ? mobilite.getPartModaleVoitureElectriqueEtudiants() : null));
        indicators.put("part_modale_doux_etudiants", clean(mobilite != null ? mobilite.getPartModaleModesDouxEtudiants() : null));

        Float intensity = computeMobilityIntensity(mobilite, distanceSalarie, distanceEtudiants);
        indicators.put("intensite_carbone_trajets", intensity);
    }

    private Float computeMobilityIntensity(MobiliteDomicileTravailResultatDto mobilite,
                                           Float distanceSalarie,
                                           Float distanceEtudiant) {
        if (mobilite == null) {
            return null;
        }
        Float emissionSalaries = clean(mobilite.getTotalSalaries());
        Float emissionEtudiants = clean(mobilite.getTotalEtudiants());

        Float totalDistance = sumNullable(distanceSalarie, distanceEtudiant);
        Float totalEmission = sumNullable(emissionSalaries, emissionEtudiants);

        if (totalDistance == null || totalDistance <= 0 || totalEmission == null) {
            return null;
        }
        double intensity = (totalEmission * 1_000_000d) / totalDistance;
        return (float) intensity;
    }

    private void populateInternationalIndicators(Map<String, Float> indicators, MobInternationalResultatDto resultat) {
        InternationalTotals totals = computeInternationalTotals(resultat);
        indicators.put("distance_internationale", totals.distanceKm);
        if (totals.distanceKm != null && totals.distanceKm > 0 && totals.emissionTon != null) {
            indicators.put("intensite_carbone_international", (totals.emissionTon * 1_000_000f) / totals.distanceKm);
        }
    }

    private InternationalTotals computeInternationalTotals(MobInternationalResultatDto resultat) {
        if (resultat == null) {
            return new InternationalTotals(null, null);
        }

        Float proEmission = sumNullable(resultat.getEmissionGesProEurope(), resultat.getEmissionGesProHorsEurope());
        Float stageEmission = sumNullable(resultat.getEmissionGesStageEurope(), resultat.getEmissionGesStagesHorsEurope());
        Float semestreEmission = sumNullable(resultat.getEmissionGesSemestresEtudiantsEurope(), resultat.getEmissionGesSemestresEtudiantsHorsEurope());
        Float formationEmission = sumNullable(resultat.getEmissionGesFormationContinueEurope(), resultat.getEmissionGesFormationContinueHorsEurope());

        Float proDistance = computeDistanceFromEmission(proEmission, clean(resultat.getProsIntensiteCarboneDesDeplacemeents_gCo2eParKm()));
        Float stageDistance = computeDistanceFromEmission(stageEmission, clean(resultat.getStagesIntensiteCarboneDesDeplacemeents_gCo2eParKm()));
        Float semestreDistance = computeDistanceFromEmission(semestreEmission, clean(resultat.getSemestresIntensiteCarboneDesDeplacemeents_gCo2eParKm()));
        Float formationDistance = computeDistanceFromEmission(formationEmission, clean(resultat.getFormationContinueIntensiteCarboneDesDeplacemeents_gCo2eParKm()));

        Float totalEmission = sumNullable(proEmission, stageEmission, semestreEmission, formationEmission);
        Float totalDistance = sumNullable(proDistance, stageDistance, semestreDistance, formationDistance);

        return new InternationalTotals(totalEmission, totalDistance);
    }

    private Float computeDistanceFromEmission(Float emissionTon, Float intensity) {
        if (emissionTon == null || intensity == null || intensity <= 0f) {
            return null;
        }
        double distance = (emissionTon * 1_000_000d) / intensity;
        return (float) distance;
    }

    private Float resolveReductionPct(String poste, Map<String, Integer> reductions, Float defaultPct) {
        Float fallback = defaultPct != null ? defaultPct : 0f;
        if ("Déplacements France".equals(poste)) {
            Integer pct = reductions.get("mobilite-dom-tram");
            return pct != null ? clampPct(pct.floatValue()) : fallback;
        }
        String key = POSTE_TO_TRAJECTOIRE_KEY.get(poste);
        if (key != null) {
            Integer pct = reductions.get(key);
            if (pct != null) {
                return clampPct(pct.floatValue());
            }
        }
        return fallback;
    }

    private Float computeTargetValue(Float baseline, Float reductionPct) {
        if (baseline == null) {
            return null;
        }
        if (reductionPct == null) {
            return baseline;
        }
        return baseline * (1f - reductionPct / 100f);
    }

    private Float computeObjectiveValue(Integer year,
                                        Integer refYear,
                                        Integer targetYear,
                                        Float baseline,
                                        Float target,
                                        Float actual) {
        if (baseline == null || target == null || refYear == null || targetYear == null) {
            return actual;
        }
        if (year <= refYear) {
            return actual != null ? actual : baseline;
        }
        if (year >= targetYear) {
            return target;
        }
        int span = targetYear - refYear;
        if (span <= 0) {
            return target;
        }
        double progress = (double) (year - refYear) / span;
        return baseline + (target - baseline) * (float) progress;
    }

    private Float clean(Float value) {
        if (value == null) {
            return null;
        }
        if (value.isNaN() || value.isInfinite()) {
            return null;
        }
        return value;
    }

    private Float multiply(Float a, Float b) {
        Float ca = clean(a);
        Float cb = clean(b);
        if (ca == null || cb == null) {
            return null;
        }
        return ca * cb;
    }

    private Float sumNullable(Float... values) {
        float sum = 0f;
        boolean hasValue = false;
        if (values != null) {
            for (Float value : values) {
                Float cleanValue = clean(value);
                if (cleanValue != null) {
                    sum += cleanValue;
                    hasValue = true;
                }
            }
        }
        return hasValue ? sum : null;
    }

    private Float clampPct(Float pct) {
        if (pct == null) {
            return null;
        }
        float value = Math.max(0f, Math.min(100f, pct));
        return value;
    }

    private static final class YearData {
        final Integer year;
        final Float globalTotal;
        final Map<String, Float> posteValues;
        final Map<String, Float> indicatorValues;
        final Float mobiliteDomicile;
        final Float autreMobiliteFrance;
        final Float mobiliteInternational;

        YearData(Integer year,
                 Float globalTotal,
                 Map<String, Float> posteValues,
                 Map<String, Float> indicatorValues,
                 Float mobiliteDomicile,
                 Float autreMobiliteFrance,
                 Float mobiliteInternational) {
            this.year = year;
            this.globalTotal = globalTotal;
            this.posteValues = posteValues;
            this.indicatorValues = indicatorValues;
            this.mobiliteDomicile = mobiliteDomicile;
            this.autreMobiliteFrance = autreMobiliteFrance;
            this.mobiliteInternational = mobiliteInternational;
        }
    }

    private static final class InternationalTotals {
        final Float emissionTon;
        final Float distanceKm;

        InternationalTotals(Float emissionTon, Float distanceKm) {
            this.emissionTon = emissionTon;
            this.distanceKm = distanceKm;
        }
    }
}
