package tcucl.back_tcucl.service.impl.onglet;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tcucl.back_tcucl.dto.onglet.batimentImmobilisationMobilier.*;
import tcucl.back_tcucl.dto.onglet.batimentImmobilisationMobilier.BatimentImmobilisationMobilierResultatDto;
import tcucl.back_tcucl.entity.Annee;
import tcucl.back_tcucl.entity.Entite;
import tcucl.back_tcucl.entity.facteurEmission.FacteurEmission;
import tcucl.back_tcucl.entity.facteurEmission.FacteurEmissionParametre;
import tcucl.back_tcucl.entity.onglet.batiment.BatimentExistantOuNeufConstruit;
import tcucl.back_tcucl.entity.onglet.batiment.BatimentImmobilisationMobilierOnglet;
import tcucl.back_tcucl.entity.onglet.batiment.EntretienCourant;
import tcucl.back_tcucl.entity.onglet.batiment.MobilierElectromenager;
import tcucl.back_tcucl.entity.onglet.batiment.enums.EnumBatiment_Mobilier;
import tcucl.back_tcucl.entity.onglet.batiment.enums.EnumBatiment_TypeBatiment;
import tcucl.back_tcucl.manager.BatimentImmobilisationMobilierOngletManager;
import tcucl.back_tcucl.service.BatimentImmobilisationMobilierOngletService;
import tcucl.back_tcucl.service.FacteurEmissionService;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BatimentImmobilisationMobilierOngletServiceImpl implements BatimentImmobilisationMobilierOngletService {

    private final BatimentImmobilisationMobilierOngletManager batimentImmobilisationMobilierOngletManager;

    private final FacteurEmissionService facteurEmissionService;

    public BatimentImmobilisationMobilierOngletServiceImpl(BatimentImmobilisationMobilierOngletManager batimentImmobilisationMobilierOngletManager, FacteurEmissionService facteurEmissionService) {
        this.batimentImmobilisationMobilierOngletManager = batimentImmobilisationMobilierOngletManager;
        this.facteurEmissionService = facteurEmissionService;
    }

    @Override
    public BatimentImmobilisationMobilierOnglet getBatimentImmobilisationMobilierOngletById(Long ongletId) {
        return batimentImmobilisationMobilierOngletManager.getBatimentImmobilisationMobilierOngletById(ongletId);

    }

    @Override
    public void updateBatimentImmobilisationMobilierOnglet(Long ongletId, BatimentImmobilisationMobilierOngletDto batimentImmobilisationMobilierOngletDto) {
        batimentImmobilisationMobilierOngletManager.updateBatimentImmobilisationMobilierOnglet(ongletId, batimentImmobilisationMobilierOngletDto);

    }

    @Transactional
    @Override
    public void ajouterBatiment(Long ongletId, BatimentExistantOuNeufConstruitDto batimentExistantOuNeufConstruitDto) {
        // On récupère l'année actuel
        BatimentImmobilisationMobilierOnglet currentOnglet = batimentImmobilisationMobilierOngletManager.getBatimentImmobilisationMobilierOngletById(ongletId);

        BatimentExistantOuNeufConstruit batimentExistantOuNeufConstruit = new BatimentExistantOuNeufConstruit(batimentExistantOuNeufConstruitDto);

        // On récupère l'année de l'onglet
        Optional.ofNullable(currentOnglet.getAnnee())
                // On récupère l'entité associée à l'année
                .map(Annee::getEntite)
                // On récupère la liste des années de l'entité
                .map(Entite::getAnnees)
                // Si l'entité ou la liste des années est vide, on utilise une liste vide
                .orElse(Collections.emptyList())
                // On crée un flux à partir de la liste des années
                .stream()
                // On filtre pour récupérer les années
                // >= annéeActuelle  &&   <= anneeMaxAjout
                .filter(annee -> annee.getAnneeValeur() >= currentOnglet.getAnnee().getAnneeValeur())
                //Sur chacune des années on récupère l'Onglet de batiment immobilisation mobilier
                .map(Annee::getBatimentImmobilisationMobilierOnglet)
                // On ajoute sur chacun des onglets le batiment à ajouter
                .forEach(batOnglet -> batimentImmobilisationMobilierOngletManager.ajouterBatiment(batOnglet, batimentExistantOuNeufConstruit));

    }

    @Override
    public void supprimerBatiment(Long ongletId, Long batimentId) {
        batimentImmobilisationMobilierOngletManager.supprimerBatimentFromOnglet(ongletId, batimentId);
    }

    @Override
    public void updateBatimentPartiel(Long ongletId, Long batimentId, BatimentExistantOuNeufConstruitDto batimentExistantOuNeufConstruitDto) {
        batimentImmobilisationMobilierOngletManager.updateBatimentPartiel(ongletId, batimentId, batimentExistantOuNeufConstruitDto);

    }

    @Transactional
    @Override
    public void ajouterEntretienCourant(Long ongletId, EntretienCourantDto entretienCourantDto) {
        // On récupère l'année actuel
        BatimentImmobilisationMobilierOnglet currentOnglet = batimentImmobilisationMobilierOngletManager.getBatimentImmobilisationMobilierOngletById(ongletId);

        EntretienCourant entretienCourant = new EntretienCourant(entretienCourantDto);

        // On récupère l'année de l'onglet
        Optional.ofNullable(currentOnglet.getAnnee())
                // On récupère l'entité associée à l'année
                .map(Annee::getEntite)
                // On récupère la liste des années de l'entité
                .map(Entite::getAnnees)
                // Si l'entité ou la liste des années est vide, on utilise une liste vide
                .orElse(Collections.emptyList())
                // On crée un flux à partir de la liste des années
                .stream()
                // On filtre pour récupérer les années
                // >= annéeActuelle  &&   <= anneeMaxAjout
                .filter(annee -> annee.getAnneeValeur() >= currentOnglet.getAnnee().getAnneeValeur())
                //Sur chacune des années on récupère l'Onglet de batiment immobilisation mobilier
                .map(Annee::getBatimentImmobilisationMobilierOnglet)
                // On ajoute sur chacun des onglets le batiment à ajouter
                .forEach(batOnglet -> batimentImmobilisationMobilierOngletManager.ajouterEntretienCourant(batOnglet, entretienCourant));


    }

    @Override
    public void supprimerEntretienCourant(Long ongletId, Long entretienCourantId) {
        batimentImmobilisationMobilierOngletManager.supprimerEntretienCourantFromOnglet(ongletId, entretienCourantId);

    }

    @Override
    public void updateEntretienCourantPartiel(Long ongletId, Long entretienCourantId, EntretienCourantDto entretienCourantDto) {
        batimentImmobilisationMobilierOngletManager.updateEntretienCourantPartiel(ongletId, entretienCourantId, entretienCourantDto);

    }

    @Override
    public void ajouterMobilierElectromenager(Long ongletId, MobilierElectromenagerDto mobilierElectromenagerDto) {
        batimentImmobilisationMobilierOngletManager.ajouterMobilierElectromenager(ongletId, mobilierElectromenagerDto);

    }

    @Override
    public void supprimerMobilierElectromenager(Long ongletId, Long mobilierElectromenagerId) {
        batimentImmobilisationMobilierOngletManager.supprimerMobilierElectromenagerFromOnglet(ongletId, mobilierElectromenagerId);

    }

    @Override
    public void updateMobilierElectromenagerPartiel(Long ongletId, Long mobilierElectromenagerId, MobilierElectromenagerDto mobilierElectromenagerDto) {
        batimentImmobilisationMobilierOngletManager.updateMobilierElectromenagerPartiel(ongletId, mobilierElectromenagerId, mobilierElectromenagerDto);

    }

    @Override
    public BatimentImmobilisationMobilierResultatDto getBatimentImmobilisationMobilierResult(Long ongletId) {
        BatimentImmobilisationMobilierOnglet batimentImmobilisationMobilierOnglet = batimentImmobilisationMobilierOngletManager.getBatimentImmobilisationMobilierOngletById(ongletId);
        List<BatimentExistantOuNeufConstruit> batiments = batimentImmobilisationMobilierOnglet.getBatimentExistantOuNeufConstruits();
        List<EntretienCourant> entretienCourants = batimentImmobilisationMobilierOnglet.getEntretienCourants();
        List<MobilierElectromenager> mobilierElectromenagers = batimentImmobilisationMobilierOnglet.getMobilierElectromenagers();

        float[] emissionsReellesDivisees = {0f};
        float[] emissionsCalculees = {0f};

        Map<Long, Float> emissionsParBatiment = batiments.stream()
                .collect(Collectors.toMap(
                        BatimentExistantOuNeufConstruit::getId,
                        batiment -> {

                            FacteurEmission facteurEmission = null;
                            if (batiment.getTypeBatiment() == EnumBatiment_TypeBatiment.BUREAUX) {
                                facteurEmission = facteurEmissionService.findByCategorieAndType(
                                        FacteurEmissionParametre.BATIMENTS_BUREAUX,
                                        batiment.getTypeStructure().toString());
                            } else if (batiment.getTypeBatiment() == EnumBatiment_TypeBatiment.ENSEIGNEMENT) {
                                facteurEmission = facteurEmissionService.findByCategorieAndType(
                                        FacteurEmissionParametre.BATIMENTS_ENSEIGNEMENT,
                                        batiment.getTypeStructure().toString());
                            } else if (batiment.getTypeBatiment() == EnumBatiment_TypeBatiment.EQUIPEMENT_SPORTIF) {
                                facteurEmission = facteurEmissionService.findByCategorieAndType(
                                        FacteurEmissionParametre.BATIMENTS_EQUIP_SPORTIF,
                                        batiment.getTypeStructure().toString());
                            } else if (batiment.getTypeBatiment() == EnumBatiment_TypeBatiment.HOPITAL) {
                                facteurEmission = facteurEmissionService.findByCategorieAndType(
                                        FacteurEmissionParametre.BATIMENTS_HOPITAL,
                                        batiment.getTypeStructure().toString());
                            } else if (batiment.getTypeBatiment() == EnumBatiment_TypeBatiment.LOGEMENT_COLLECTIF) {
                                facteurEmission = facteurEmissionService.findByCategorieAndType(
                                        FacteurEmissionParametre.BATIMENTS_LOGEMENT_COLLECTIF,
                                        batiment.getTypeStructure().toString());
                            } else if (batiment.getTypeBatiment() == EnumBatiment_TypeBatiment.RESTAURATION) {
                                facteurEmission = facteurEmissionService.findByCategorieAndType(
                                        FacteurEmissionParametre.BATIMENTS_RESTAURATION,
                                        batiment.getTypeStructure().toString());
                            } else if (batiment.getTypeBatiment() == EnumBatiment_TypeBatiment.AUTRE) {
                                facteurEmission = facteurEmissionService.findByCategorieAndType(
                                        FacteurEmissionParametre.BATIMENTS_AUTRES,
                                        batiment.getTypeStructure().toString());
                            }

                            if (Boolean.TRUE.equals(batiment.getAcvBatimentRealisee())) {
                                emissionsReellesDivisees[0] += batiment.getEmissionsGesReellesTCO2() / 50f;
                                return batiment.getEmissionsGesReellesTCO2();
                            } else {
                                if (batiment.getDateConstruction().getYear() > (batimentImmobilisationMobilierOnglet.getAnnee().getAnneeValeur() - 50) || batiment.getDateDerniereGrosseRenovation().getYear() > (batimentImmobilisationMobilierOnglet.getAnnee().getAnneeValeur() - 50)) {
                                    emissionsCalculees[0] += facteurEmission.getFacteurEmission() * batiment.getSurfaceEnM2() / (50 * 1000);
                                    return facteurEmission.getFacteurEmission() * batiment.getSurfaceEnM2() / (50 * 1000);
                                } else {
                                    return 0f;
                                }
                            }
                        }
                ));

        BatimentImmobilisationMobilierResultatDto resultatDto = new BatimentImmobilisationMobilierResultatDto();
        resultatDto.setEmissionGESBatimentExistantOuNeufConstruit(emissionsParBatiment);

        float totalPosteBatiment = emissionsReellesDivisees[0] + emissionsCalculees[0];
        resultatDto.setTotalPosteBatiment(totalPosteBatiment);


        Map<Long, Float> emissionsParEntretienCourants = entretienCourants.stream()
                .collect(Collectors.toMap(
                        EntretienCourant::getId,
                        entretienCourant -> {
                            FacteurEmission facteurEmission = null;
                            if (entretienCourant.getTypeBatiment() == EnumBatiment_TypeBatiment.BUREAUX) {
                                facteurEmission = facteurEmissionService.findByCategorieAndType(
                                        FacteurEmissionParametre.ENTRETIEN_BUREAUX,
                                        entretienCourant.getTypeTravaux().toString());
                            } else if (entretienCourant.getTypeBatiment() == EnumBatiment_TypeBatiment.ENSEIGNEMENT) {
                                facteurEmission = facteurEmissionService.findByCategorieAndType(
                                        FacteurEmissionParametre.ENTRETIEN_ENSEIGNEMENT,
                                        entretienCourant.getTypeTravaux().toString());
                            } else if (entretienCourant.getTypeBatiment() == EnumBatiment_TypeBatiment.EQUIPEMENT_SPORTIF) {
                                facteurEmission = facteurEmissionService.findByCategorieAndType(
                                        FacteurEmissionParametre.ENTRETIEN_EQUIP_SPORTIF,
                                        entretienCourant.getTypeTravaux().toString());
                            } else if (entretienCourant.getTypeBatiment() == EnumBatiment_TypeBatiment.HOPITAL) {
                                facteurEmission = facteurEmissionService.findByCategorieAndType(
                                        FacteurEmissionParametre.ENTRETIEN_HOPITAL,
                                        entretienCourant.getTypeTravaux().toString());
                            } else if (entretienCourant.getTypeBatiment() == EnumBatiment_TypeBatiment.LOGEMENT_COLLECTIF) {
                                facteurEmission = facteurEmissionService.findByCategorieAndType(
                                        FacteurEmissionParametre.ENTRETIEN_LOGEMENT_COLLECTIF,
                                        entretienCourant.getTypeTravaux().toString());
                            } else if (entretienCourant.getTypeBatiment() == EnumBatiment_TypeBatiment.RESTAURATION) {
                                facteurEmission = facteurEmissionService.findByCategorieAndType(
                                        FacteurEmissionParametre.ENTRETIEN_RESTAURATION,
                                        entretienCourant.getTypeTravaux().toString());
                            } else if (entretienCourant.getTypeBatiment() == EnumBatiment_TypeBatiment.AUTRE) {
                                facteurEmission = facteurEmissionService.findByCategorieAndType(
                                        FacteurEmissionParametre.ENTRETIEN_AUTRES,
                                        entretienCourant.getTypeTravaux().toString());
                            }

                            if (entretienCourant.getDateTravaux().getYear() > (batimentImmobilisationMobilierOnglet.getAnnee().getAnneeValeur() - entretienCourant.getDureeAmortissement())) {
                                return facteurEmission.getFacteurEmission() * entretienCourant.getSurfaceConcernee() / (entretienCourant.getDureeAmortissement() * 1000);
                            } else {
                                return 0f;
                            }

                        }
                ));

        resultatDto.setEmissionGESEntretienCourant(emissionsParEntretienCourants);
        float totalPosteEntretien = emissionsParEntretienCourants.values().stream()
                .reduce(0f, Float::sum);
        resultatDto.setTotalPosteEntretien(totalPosteEntretien);

        Map<Long, Float> emissionsParMobilierElectromenager = mobilierElectromenagers.stream()
                .collect(Collectors.toMap(
                        MobilierElectromenager::getId,
                        mobilierElectromenager -> {

                            if (mobilierElectromenager.getDateAjout().getYear() > (batimentImmobilisationMobilierOnglet.getAnnee().getAnneeValeur() - mobilierElectromenager.getDureeAmortissement())) {
                                if (mobilierElectromenager.getPoidsDuProduit() != 0.0f) {
                                    FacteurEmission facteurEmissionKgProduit = facteurEmissionService.findByCategorieAndTypeAndUnite(
                                            FacteurEmissionParametre.MOBILIER,
                                            mobilierElectromenager.getMobilier().getLibelle(),
                                            FacteurEmissionParametre.MOBILIER_.KG_CO2E_PAR_KG_PRODUIT
                                    );
                                    return (mobilierElectromenager.getQuantite() * mobilierElectromenager.getPoidsDuProduit() * facteurEmissionKgProduit.getFacteurEmission() / 1000) / mobilierElectromenager.getDureeAmortissement();
                                } else {
                                    if (mobilierElectromenager.getMobilier() == EnumBatiment_Mobilier.AUTRE_MOBILIER_EN_EUROS || (mobilierElectromenager.getMobilier() == EnumBatiment_Mobilier.AUTRE_MOBILIER_EN_TONNES)) {
                                        FacteurEmission facteurEmissionAutre = facteurEmissionService.findByCategorieAndType(
                                                FacteurEmissionParametre.MOBILIER,
                                                mobilierElectromenager.getMobilier().getLibelle()
                                        );
                                        return (facteurEmissionAutre.getFacteurEmission() * mobilierElectromenager.getQuantite() / 1000) / mobilierElectromenager.getDureeAmortissement();
                                    } else {
                                        FacteurEmission facteurEmissionProduit = facteurEmissionService.findByCategorieAndTypeAndUnite(
                                                FacteurEmissionParametre.MOBILIER,
                                                mobilierElectromenager.getMobilier().getLibelle(),
                                                FacteurEmissionParametre.MOBILIER_.KG_CO2E_PAR_PRODUIT
                                        );
                                        return (facteurEmissionProduit.getFacteurEmission() * mobilierElectromenager.getQuantite() / 1000) / mobilierElectromenager.getDureeAmortissement();
                                    }
                                }
                            } else {
                                return 0f;
                            }

                        }
                ));

        resultatDto.setEmissionGESMobilierElectromenager(emissionsParMobilierElectromenager);

        float totalPosteMobilier = emissionsParMobilierElectromenager.values().stream()
                .reduce(0f, Float::sum);
        resultatDto.setTotalPosteMobilier(totalPosteMobilier);

        return resultatDto;
    }
}
