package tcucl.back_tcucl.service.impl;

import org.springframework.stereotype.Service;
import tcucl.back_tcucl.dto.ListIdDto;
import tcucl.back_tcucl.dto.SyntheseEGESResultatDto;
import tcucl.back_tcucl.entity.onglet.energie.enums.EnumEnergie_UniteBois;
import tcucl.back_tcucl.entity.onglet.energie.enums.EnumEnergie_UniteFioul;
import tcucl.back_tcucl.service.*;


@Service
public class SyntheseEGESServiceImpl implements SyntheseEGESService {

    private final AnneeService anneeService;

    private final GeneralOngletService generalOngletService;

    private final EnergieOngletService energieOngletService;

    private final AchatOngletService achatOngletService;

    private final AutreImmobilisationOngletService autreImmobilisationOngletService;

    private final AutreMobFrOngletService autreMobFrOngletService;

    private final BatimentImmobilisationMobilierOngletService batimentImmobilisationMobilierOngletService;

    private final DechetOngletService dechetOngletService;

    private final EmissionFugitiveOngletService emissionFugitiveOngletService;

    private final MobiliteDomicileTravailOngletService mobiliteDomicileTravailOngletService;

    private final MobInternationalOngletService mobInternationalOngletService;

    private final NumeriqueOngletService numeriqueOngletService;

    private final ParkingVoirieOngletService parkingVoirieOngletService;

    private final VehiculeOngletService vehiculeOngletService;

    public SyntheseEGESServiceImpl(AnneeService anneeService, GeneralOngletService generalOngletService, EnergieOngletService energieOngletService, AchatOngletService achatOngletService, AutreImmobilisationOngletService autreImmobilisationOngletService, AutreMobFrOngletService autreMobFrOngletService, BatimentImmobilisationMobilierOngletService batimentImmobilisationMobilierOngletService, DechetOngletService dechetOngletService, EmissionFugitiveOngletService emissionFugitiveOngletService, MobiliteDomicileTravailOngletService mobiliteDomicileTravailOngletService, MobInternationalOngletService mobInternationalOngletService, NumeriqueOngletService numeriqueOngletService, ParkingVoirieOngletService parkingVoirieOngletService, VehiculeOngletService vehiculeOngletService) {
        this.anneeService = anneeService;
        this.generalOngletService = generalOngletService;
        this.energieOngletService = energieOngletService;
        this.achatOngletService = achatOngletService;
        this.autreImmobilisationOngletService = autreImmobilisationOngletService;
        this.autreMobFrOngletService = autreMobFrOngletService;
        this.batimentImmobilisationMobilierOngletService = batimentImmobilisationMobilierOngletService;
        this.dechetOngletService = dechetOngletService;
        this.emissionFugitiveOngletService = emissionFugitiveOngletService;
        this.mobiliteDomicileTravailOngletService = mobiliteDomicileTravailOngletService;
        this.mobInternationalOngletService = mobInternationalOngletService;
        this.numeriqueOngletService = numeriqueOngletService;
        this.parkingVoirieOngletService = parkingVoirieOngletService;
        this.vehiculeOngletService = vehiculeOngletService;
    }


    @Override
    public SyntheseEGESResultatDto getSyntheseEGESResultat(Long entiteId, Integer annee) {
        ListIdDto onglets = anneeService.getongletIdListForEntiteAndAnnee(entiteId, annee);
        SyntheseEGESResultatDto syntheseEGESResultatDto = new SyntheseEGESResultatDto();
        Long generalOngletId = onglets.getGeneralOnglet();
        Integer nbEtudiant = generalOngletService.getGeneralOngletById(generalOngletId).getNbEtudiant();
        Integer nbSalarie = generalOngletService.getGeneralOngletById(generalOngletId).getNbSalarie();

        // Emission fugitives
        Long emissionFugitiveOngletId = onglets.getEmissionFugitiveOnglet();
        Float emissionFugitivesGlobal = emissionFugitiveOngletService
                .getEmissionFugitiveResult(emissionFugitiveOngletId)
                .getTotalEmissionGES();
        emissionFugitivesGlobal = (emissionFugitivesGlobal != null) ? emissionFugitivesGlobal : 0.0f;
        syntheseEGESResultatDto.setEmissionFugitivesGlobal(emissionFugitivesGlobal);
        syntheseEGESResultatDto.setEmissionFugitivesParUsager(syntheseEGESResultatDto.getEmissionFugitivesGlobal() * 1000 / (nbEtudiant + nbSalarie));

        // Energie
        Long energieOngletId = onglets.getEnergieOnglet();
        Float energieGlobal = energieOngletService
                .getEnergieResultat(energieOngletId)
                .getTotalPosteFluides();
        energieGlobal = (energieGlobal != null) ? energieGlobal : 0.0f;
        syntheseEGESResultatDto.setEnergieGlobal(energieGlobal);
        syntheseEGESResultatDto.setEnergieParUsager(syntheseEGESResultatDto.getEnergieGlobal() * 1000 / (nbEtudiant + nbSalarie));

        // Mobilité domicile travail
        Long mobiliteDomicileTravailOngletId = onglets.getMobiliteDomicileTravailOnglet();
        Float mobiliteDomicileTravailGlobal = mobiliteDomicileTravailOngletService
                .getMobiliteDomicileTravailResultat(mobiliteDomicileTravailOngletId)
                .getTotalPosteMobiliteFrance();
        mobiliteDomicileTravailGlobal = (mobiliteDomicileTravailGlobal != null) ? mobiliteDomicileTravailGlobal : 0.0f;
        syntheseEGESResultatDto.setMobiliteDomicileTravailGlobal(mobiliteDomicileTravailGlobal);
        syntheseEGESResultatDto.setMobiliteDomicileTravailParUsager(syntheseEGESResultatDto.getMobiliteDomicileTravailGlobal() * 1000 / (nbEtudiant + nbSalarie));

        // Autre mobilité France
        Long autreMobFrOngletId = onglets.getAutreMobFrOnglet();
        Long vehiculeOngletId = onglets.getVehiculeOnglet();

        Float autreMobFrTotal = autreMobFrOngletService
                .getAutreMobFrResultat(autreMobFrOngletId)
                .getTotalPosteMobiliteFrance();
        autreMobFrTotal = (autreMobFrTotal != null) ? autreMobFrTotal : 0.0f;

        Float vehiculeTotal = vehiculeOngletService
                .getVehiculeResult(vehiculeOngletId)
                .getTotalEmissionGES();
        vehiculeTotal = (vehiculeTotal != null) ? vehiculeTotal : 0.0f;

        Float autreMobiliteFrGlobal = autreMobFrTotal + vehiculeTotal;
        syntheseEGESResultatDto.setAutreMobiliteFrGlobal(autreMobiliteFrGlobal);
        syntheseEGESResultatDto.setAutreMobiliteFrParUsager(syntheseEGESResultatDto.getAutreMobiliteFrGlobal() * 1000 / (nbEtudiant + nbSalarie));

        // Mobilité internationale
        Long mobInternationalOngletId = onglets.getMobInternationalOnglet();
        Float emissionGesProEurope = mobInternationalOngletService
                .getMobInternationalResultat(mobInternationalOngletId)
                .getEmissionGesProEurope();
        emissionGesProEurope = (emissionGesProEurope != null) ? emissionGesProEurope : 0.0f;

        Float emissionGesStageEurope = mobInternationalOngletService
                .getMobInternationalResultat(mobInternationalOngletId)
                .getEmissionGesStageEurope();
        emissionGesStageEurope = (emissionGesStageEurope != null) ? emissionGesStageEurope : 0.0f;

        Float emissionGesSemestreEurope = mobInternationalOngletService
                .getMobInternationalResultat(mobInternationalOngletId)
                .getEmissionGesSemestresEtudiantsEurope();
        emissionGesSemestreEurope = (emissionGesSemestreEurope != null) ? emissionGesSemestreEurope : 0.0f;

        Float emissionGesProHorsEurope = mobInternationalOngletService
                .getMobInternationalResultat(mobInternationalOngletId)
                .getEmissionGesProHorsEurope();
        emissionGesProHorsEurope = (emissionGesProHorsEurope != null) ? emissionGesProHorsEurope : 0.0f;

        Float emissionGesStagesHorsEurope = mobInternationalOngletService
                .getMobInternationalResultat(mobInternationalOngletId)
                .getEmissionGesStagesHorsEurope();
        emissionGesStagesHorsEurope = (emissionGesStagesHorsEurope != null) ? emissionGesStagesHorsEurope : 0.0f;

        Float emissionGesSemestresHorsEurope = mobInternationalOngletService
                .getMobInternationalResultat(mobInternationalOngletId)
                .getEmissionGesSemestresEtudiantsHorsEurope();
        emissionGesSemestresHorsEurope = (emissionGesSemestresHorsEurope != null) ? emissionGesSemestresHorsEurope : 0.0f;

        Float mobiliteInternationalGlobal = emissionGesProEurope + emissionGesStageEurope + emissionGesSemestreEurope +
                emissionGesProHorsEurope + emissionGesStagesHorsEurope + emissionGesSemestresHorsEurope;
        syntheseEGESResultatDto.setMobiliteInternationalGlobal(mobiliteInternationalGlobal);
        syntheseEGESResultatDto.setMobiliteInternationalParUsager(syntheseEGESResultatDto.getMobiliteInternationalGlobal() * 1000 / (nbEtudiant + nbSalarie));

        // Immobilisation bâtiment mobilier (incluant parking)
        Long batimentImmobilisationMobilierOngletId = onglets.getBatimentImmobilisationMobilierOnglet();
        Long parkingVoirieOngletId = onglets.getParkingVoirieOnglet();

        Float totalPosteBatiment = batimentImmobilisationMobilierOngletService
                .getBatimentImmobilisationMobilierResult(batimentImmobilisationMobilierOngletId)
                .getTotalPosteBatiment();
        totalPosteBatiment = (totalPosteBatiment != null) ? totalPosteBatiment : 0.0f;

        Float totalPosteEntretien = batimentImmobilisationMobilierOngletService
                .getBatimentImmobilisationMobilierResult(batimentImmobilisationMobilierOngletId)
                .getTotalPosteEntretien();
        totalPosteEntretien = (totalPosteEntretien != null) ? totalPosteEntretien : 0.0f;

        Float totalPosteMobilier = batimentImmobilisationMobilierOngletService
                .getBatimentImmobilisationMobilierResult(batimentImmobilisationMobilierOngletId)
                .getTotalPosteMobilier();
        totalPosteMobilier = (totalPosteMobilier != null) ? totalPosteMobilier : 0.0f;

        Float totalEmissionGESParking = parkingVoirieOngletService
                .getParkingVoirieResult(parkingVoirieOngletId)
                .getTotalEmissionGES();
        totalEmissionGESParking = (totalEmissionGESParking != null) ? totalEmissionGESParking : 0.0f;

        Float batimentParkingGlobal = totalPosteBatiment + totalPosteEntretien + totalPosteMobilier + totalEmissionGESParking;
        syntheseEGESResultatDto.setBatimentParkingGlobal(batimentParkingGlobal);
        syntheseEGESResultatDto.setBatimentParkingParUsager(syntheseEGESResultatDto.getBatimentParkingGlobal() * 1000 / (nbEtudiant + nbSalarie));

        // Numérique
        Long numeriqueOngletId = onglets.getNumeriqueOnglet();
        Float numeriqueGlobal = numeriqueOngletService
                .getNumeriqueResultat(numeriqueOngletId)
                .getTotalNumerique();
        numeriqueGlobal = (numeriqueGlobal != null) ? numeriqueGlobal : 0.0f;
        syntheseEGESResultatDto.setNumeriqueGlobal(numeriqueGlobal);
        syntheseEGESResultatDto.setNumeriqueParUsager(syntheseEGESResultatDto.getNumeriqueGlobal() * 1000 / (nbEtudiant + nbSalarie));

        // Autres immobilisations
        Long autreImmobilisationOngletId = onglets.getAutreImmobilisationOnglet();
        Float totalPostePhotovoltaique = autreImmobilisationOngletService
                .getAutreImmobilisationResultat(autreImmobilisationOngletId)
                .getTotalPostePhotovoltaique();
        totalPostePhotovoltaique = (totalPostePhotovoltaique != null) ? totalPostePhotovoltaique : 0.0f;

        Float totalPosteBatimentAutre = autreImmobilisationOngletService
                .getAutreImmobilisationResultat(autreImmobilisationOngletId)
                .getTotalPosteBatiment();
        totalPosteBatimentAutre = (totalPosteBatimentAutre != null) ? totalPosteBatimentAutre : 0.0f;

        Float autreImmobilisationGlobal = totalPostePhotovoltaique + totalPosteBatimentAutre;
        syntheseEGESResultatDto.setAutreImmobilisationGlobal(autreImmobilisationGlobal);
        syntheseEGESResultatDto.setAutreImmobilisationParUsager(syntheseEGESResultatDto.getAutreImmobilisationGlobal() * 1000 / (nbEtudiant + nbSalarie));

        // Achats
        Long achatOngletId = onglets.getAchatOnglet();
        Float totalPosteAchat = achatOngletService
                .getAchatResultat(achatOngletId)
                .getTotalPosteAchat();
        totalPosteAchat = (totalPosteAchat != null) ? totalPosteAchat : 0.0f;

        Float totalPosteTextile = achatOngletService
                .getAchatResultat(achatOngletId)
                .getTotalPosteTextile();
        totalPosteTextile = (totalPosteTextile != null) ? totalPosteTextile : 0.0f;

        Float totalPosteRestauration = achatOngletService
                .getAchatResultat(achatOngletId)
                .getTotalPosteRestauration();
        totalPosteRestauration = (totalPosteRestauration != null) ? totalPosteRestauration : 0.0f;

        Float achatGlobal = totalPosteAchat + totalPosteTextile + totalPosteRestauration;
        syntheseEGESResultatDto.setAchatGlobal(achatGlobal);
        syntheseEGESResultatDto.setAchatParUsager(syntheseEGESResultatDto.getAchatGlobal() * 1000 / (nbEtudiant + nbSalarie));

        // Déchets
        Long dechetOngletId = onglets.getDechetOnglet();
        Float dechetGlobal = dechetOngletService
                .getDechetResultat(dechetOngletId)
                .getTotalProduit();
        dechetGlobal = (dechetGlobal != null) ? dechetGlobal : 0.0f;
        syntheseEGESResultatDto.setDechetGlobal(dechetGlobal);
        syntheseEGESResultatDto.setDechetParUsager(syntheseEGESResultatDto.getDechetGlobal() * 1000 / (nbEtudiant + nbSalarie));

        syntheseEGESResultatDto.setBilanCarboneTotalGlobal((!syntheseEGESResultatDto.getEmissionFugitivesGlobal().isNaN() ? syntheseEGESResultatDto.getEmissionFugitivesGlobal() : 0.0f)
                + (!syntheseEGESResultatDto.getEnergieGlobal().isNaN() ? syntheseEGESResultatDto.getEnergieGlobal() : 0.0f)
                + (!syntheseEGESResultatDto.getMobiliteDomicileTravailGlobal().isNaN() ? syntheseEGESResultatDto.getMobiliteDomicileTravailGlobal() : 0.0f)
                + (!syntheseEGESResultatDto.getAutreMobiliteFrGlobal().isNaN() ? syntheseEGESResultatDto.getAutreMobiliteFrGlobal() : 0.0f)
                + (!syntheseEGESResultatDto.getMobiliteInternationalGlobal().isNaN() ? syntheseEGESResultatDto.getMobiliteInternationalGlobal() : 0.0f)
                + (!syntheseEGESResultatDto.getBatimentParkingGlobal().isNaN() ? syntheseEGESResultatDto.getBatimentParkingGlobal() : 0.0f)
                + (!syntheseEGESResultatDto.getNumeriqueGlobal().isNaN() ? syntheseEGESResultatDto.getNumeriqueGlobal() : 0.0f)
                + (!syntheseEGESResultatDto.getAutreImmobilisationGlobal().isNaN() ? syntheseEGESResultatDto.getAutreImmobilisationGlobal() : 0.0f)
                + (!syntheseEGESResultatDto.getAchatGlobal().isNaN() ? syntheseEGESResultatDto.getAchatGlobal() : 0.0f)
                + (!syntheseEGESResultatDto.getDechetGlobal().isNaN() ? syntheseEGESResultatDto.getDechetGlobal() : 0.0f));

        syntheseEGESResultatDto.setBilanCarboneTotalParUsager((!syntheseEGESResultatDto.getEmissionFugitivesParUsager().isNaN() ? syntheseEGESResultatDto.getEmissionFugitivesParUsager() : 0.0f)
                + (!syntheseEGESResultatDto.getEnergieParUsager().isNaN() ? syntheseEGESResultatDto.getEnergieParUsager() : 0.0f)
                + (!syntheseEGESResultatDto.getMobiliteDomicileTravailParUsager().isNaN() ? syntheseEGESResultatDto.getMobiliteDomicileTravailParUsager() : 0.0f)
                + (!syntheseEGESResultatDto.getAutreMobiliteFrParUsager().isNaN() ? syntheseEGESResultatDto.getAutreMobiliteFrParUsager() : 0.0f)
                + (!syntheseEGESResultatDto.getMobiliteInternationalParUsager().isNaN() ? syntheseEGESResultatDto.getMobiliteInternationalParUsager() : 0.0f)
                + (!syntheseEGESResultatDto.getBatimentParkingParUsager().isNaN() ? syntheseEGESResultatDto.getBatimentParkingParUsager() : 0.0f)
                + (!syntheseEGESResultatDto.getNumeriqueParUsager().isNaN() ? syntheseEGESResultatDto.getNumeriqueParUsager() : 0.0f)
                + (!syntheseEGESResultatDto.getAutreImmobilisationParUsager().isNaN() ? syntheseEGESResultatDto.getAutreImmobilisationParUsager() : 0.0f)
                + (!syntheseEGESResultatDto.getAchatParUsager().isNaN() ? syntheseEGESResultatDto.getAchatParUsager() : 0.0f)
                + (!syntheseEGESResultatDto.getDechetParUsager().isNaN() ? syntheseEGESResultatDto.getDechetParUsager() : 0.0f));

        float amontFioul = 0;
        if ((energieOngletService.getEnergieOngletById(energieOngletId).getUniteFioul() == EnumEnergie_UniteFioul.TONNE)) {
            amontFioul = 676;
        } else if ((energieOngletService.getEnergieOngletById(energieOngletId).getUniteFioul() == EnumEnergie_UniteFioul.MWH_PCS)) {
            amontFioul = 37;
        } else {
            amontFioul = 571;
        }

        float amontBois = 0;
        if ((energieOngletService.getEnergieOngletById(energieOngletId).getUniteBois() == EnumEnergie_UniteBois.TONNE)) {
            amontBois = 51.1F;
        } else if ((energieOngletService.getEnergieOngletById(energieOngletId).getUniteBois() == EnumEnergie_UniteBois.MWH_PCS)) {
            amontBois = 16.4F;
        }

        // Récupération des valeurs avec gestion des null pour les calculs d'émissions
        Float consoGaz = energieOngletService.getEnergieResultat(energieOngletId).getConsoGaz();
        consoGaz = (consoGaz != null) ? consoGaz : 0.0f;

        Float consoFioul = energieOngletService.getEnergieResultat(energieOngletId).getConsoFioul();
        consoFioul = (consoFioul != null) ? consoFioul : 0.0f;

        Float consoBois = energieOngletService.getEnergieResultat(energieOngletId).getConsoBois();
        consoBois = (consoBois != null) ? consoBois : 0.0f;

        Float consoGazOnglet = energieOngletService.getEnergieOngletById(energieOngletId).getConsoGaz();
        consoGazOnglet = (consoGazOnglet != null) ? consoGazOnglet : 0.0f;

        Float consoFioulOnglet = energieOngletService.getEnergieOngletById(energieOngletId).getConsoFioul();
        consoFioulOnglet = (consoFioulOnglet != null) ? consoFioulOnglet : 0.0f;

        Float consoBoisOnglet = energieOngletService.getEnergieOngletById(energieOngletId).getConsoBois();
        consoBoisOnglet = (consoBoisOnglet != null) ? consoBoisOnglet : 0.0f;

        Float consoElecChauffage = energieOngletService.getEnergieResultat(energieOngletId).getConsoElecChauffage();
        consoElecChauffage = (consoElecChauffage != null) ? consoElecChauffage : 0.0f;

        Float consoElecSpecifique = energieOngletService.getEnergieResultat(energieOngletId).getConsoElecSpecifique();
        consoElecSpecifique = (consoElecSpecifique != null) ? consoElecSpecifique : 0.0f;

        Float consoElecChauffageOnglet = energieOngletService.getEnergieOngletById(energieOngletId).getConsoElecChauffage();
        consoElecChauffageOnglet = (consoElecChauffageOnglet != null) ? consoElecChauffageOnglet : 0.0f;

        Float consoElecSpecifiqueOnglet = energieOngletService.getEnergieOngletById(energieOngletId).getConsoElecSpecifique();
        consoElecSpecifiqueOnglet = (consoElecSpecifiqueOnglet != null) ? consoElecSpecifiqueOnglet : 0.0f;

        Float consoReseauVille = energieOngletService.getEnergieResultat(energieOngletId).getConsoReseauVille();
        consoReseauVille = (consoReseauVille != null) ? consoReseauVille : 0.0f;

        Float consoReseauVilleOnglet = energieOngletService.getEnergieOngletById(energieOngletId).getConsoReseauVille();
        consoReseauVilleOnglet = (consoReseauVilleOnglet != null) ? consoReseauVilleOnglet : 0.0f;

        Float consoEau = energieOngletService.getEnergieResultat(energieOngletId).getConsoEau();
        consoEau = (consoEau != null) ? consoEau : 0.0f;

        Float totalEmissionGESPetrole = vehiculeOngletService.getVehiculeResult(vehiculeOngletId).getTotalEmissionGESPetrole();
        totalEmissionGESPetrole = (totalEmissionGESPetrole != null) ? totalEmissionGESPetrole : 0.0f;

        Float totalEmissionGESElectrique = vehiculeOngletService.getVehiculeResult(vehiculeOngletId).getTotalEmissionGESElectrique();
        totalEmissionGESElectrique = (totalEmissionGESElectrique != null) ? totalEmissionGESElectrique : 0.0f;

        Float totalEmissionGESFabrication = vehiculeOngletService.getVehiculeResult(vehiculeOngletId).getTotalEmissionGESFabrication();
        totalEmissionGESFabrication = (totalEmissionGESFabrication != null) ? totalEmissionGESFabrication : 0.0f;

        Float totalEvite = dechetOngletService.getDechetResultat(dechetOngletId).getTotalEvite();
        totalEvite = (totalEvite != null) ? totalEvite : 0.0f;

        syntheseEGESResultatDto.setEmissionDirecteCombustion((float) (consoGaz + consoFioul + consoBois
                - (consoGazOnglet * 0.385 / 1000)
                - (consoFioulOnglet * amontFioul / 1000)
                - (consoBoisOnglet * amontBois / 1000)));

        syntheseEGESResultatDto.setEmissionDirecteMoteurThermique(totalEmissionGESPetrole);
        syntheseEGESResultatDto.setEmissionDirecteFugitives(emissionFugitivesGlobal);

        syntheseEGESResultatDto.setEmissionIndirecteConsoElec((float) (consoElecChauffage + consoElecSpecifique
                - (consoElecChauffageOnglet * 23.8 / 1000)
                - (consoElecSpecifiqueOnglet * 13.8 / 1000)
                + totalEmissionGESElectrique));

        syntheseEGESResultatDto.setEmissionIndirecteConsoVapeurChaleurFroid(consoReseauVille);

        syntheseEGESResultatDto.setEmissionNonIncluseDansDirectOuIndirecte((float) ((consoGazOnglet * 0.385 / 1000)
                + (consoFioulOnglet * amontFioul / 1000)
                + (consoBoisOnglet * amontBois / 1000)
                + (consoReseauVilleOnglet * 0 / 1000)
                + (consoElecChauffageOnglet * 23.8 / 1000)
                + (consoElecSpecifiqueOnglet * 13.8 / 1000)
                + (consoReseauVilleOnglet * 0 / 1000)));

        syntheseEGESResultatDto.setAchatProduitOuService(totalPosteAchat + totalPosteTextile + totalPosteRestauration + consoEau);

        syntheseEGESResultatDto.setImmobilisationBien(totalPosteBatiment + totalPosteMobilier + totalPosteEntretien
                + totalEmissionGESParking + numeriqueGlobal + totalPostePhotovoltaique + totalPosteBatimentAutre + totalEmissionGESFabrication);

        syntheseEGESResultatDto.setDechet(dechetGlobal);
        syntheseEGESResultatDto.setDeplacementProfessionnel(autreMobFrTotal + mobiliteInternationalGlobal);
        syntheseEGESResultatDto.setDeplacementDomicileTravail(mobiliteDomicileTravailGlobal);

        syntheseEGESResultatDto.setBilanCarboneTotalScope((!syntheseEGESResultatDto.getEmissionDirecteCombustion().isNaN() ? syntheseEGESResultatDto.getEmissionDirecteCombustion() : 0.0f)
                + (!syntheseEGESResultatDto.getEmissionDirecteMoteurThermique().isNaN() ? syntheseEGESResultatDto.getEmissionDirecteMoteurThermique() : 0.0f)
                + (!syntheseEGESResultatDto.getEmissionDirecteFugitives().isNaN() ? syntheseEGESResultatDto.getEmissionDirecteFugitives() : 0.0f)
                + (!syntheseEGESResultatDto.getEmissionIndirecteConsoElec().isNaN() ? syntheseEGESResultatDto.getEmissionIndirecteConsoElec() : 0.0f)
                + (!syntheseEGESResultatDto.getEmissionIndirecteConsoVapeurChaleurFroid().isNaN() ? syntheseEGESResultatDto.getEmissionIndirecteConsoVapeurChaleurFroid() : 0.0f)
                + (!syntheseEGESResultatDto.getEmissionNonIncluseDansDirectOuIndirecte().isNaN() ? syntheseEGESResultatDto.getEmissionNonIncluseDansDirectOuIndirecte() : 0.0f)
                + (!syntheseEGESResultatDto.getAchatProduitOuService().isNaN() ? syntheseEGESResultatDto.getAchatProduitOuService() : 0.0f)
                + (!syntheseEGESResultatDto.getImmobilisationBien().isNaN() ? syntheseEGESResultatDto.getImmobilisationBien() : 0.0f)
                + (!syntheseEGESResultatDto.getDechet().isNaN() ? syntheseEGESResultatDto.getDechet() : 0.0f)
                + (!syntheseEGESResultatDto.getDeplacementProfessionnel().isNaN() ? syntheseEGESResultatDto.getDeplacementProfessionnel() : 0.0f)
                + (!syntheseEGESResultatDto.getDeplacementDomicileTravail().isNaN() ? syntheseEGESResultatDto.getDeplacementDomicileTravail() : 0.0f));

        syntheseEGESResultatDto.setEmissionEvitee(totalEvite);

        return syntheseEGESResultatDto;
    }
}
