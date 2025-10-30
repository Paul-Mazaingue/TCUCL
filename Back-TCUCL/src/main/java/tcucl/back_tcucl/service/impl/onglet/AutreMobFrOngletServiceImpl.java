package tcucl.back_tcucl.service.impl.onglet;

import org.springframework.stereotype.Service;
import tcucl.back_tcucl.dto.onglet.autreMobFr.AutreMobFrOngletDto;
import tcucl.back_tcucl.dto.onglet.autreMobFr.AutreMobFrResultatDto;
import tcucl.back_tcucl.entity.facteurEmission.FacteurEmissionParametre;
import tcucl.back_tcucl.entity.onglet.AutreMobFrOnglet;
import tcucl.back_tcucl.manager.AutreMobFrOngletManager;
import tcucl.back_tcucl.service.AutreMobFrOngletService;
import tcucl.back_tcucl.service.FacteurEmissionService;

@Service
public class AutreMobFrOngletServiceImpl implements AutreMobFrOngletService {

    private final AutreMobFrOngletManager autreMobFrOngletManager;
    private final FacteurEmissionService facteurEmissionService;

    public AutreMobFrOngletServiceImpl(AutreMobFrOngletManager autreMobFrOngletManager, FacteurEmissionService facteurEmissionService) {
        this.autreMobFrOngletManager = autreMobFrOngletManager;
        this.facteurEmissionService = facteurEmissionService;
    }

    @Override
    public AutreMobFrOnglet getAutreMobFrOngletById(Long ongletId) {
        return autreMobFrOngletManager.getAutreMobFrOngletById(ongletId);
    }

    @Override
    public void updateAutreMobFrOngletPartiel(Long ongletId, AutreMobFrOngletDto autreMobFrOngletDto) {
        autreMobFrOngletManager.updateAutreMobFrOngletPartiel(ongletId, autreMobFrOngletDto);
    }

    @Override
    public AutreMobFrResultatDto getAutreMobFrResultat(Long ongletId) {
        AutreMobFrOnglet autreMobFrOnglet = autreMobFrOngletManager.getAutreMobFrOngletById(ongletId);
        AutreMobFrResultatDto autreMobFrResultatDto = new AutreMobFrResultatDto();

        Float facteurEmissionVoitureThermique = facteurEmissionService.findByCategorieAndType(
                FacteurEmissionParametre.MOBILITE_LONGUE_DISTANCE,
                FacteurEmissionParametre.MOBILITE_LONGUE_DISTANCE_.VOITURE_THERMIQUE).getFacteurEmission();
        Float egesVoitureThermiqueSalarie = autreMobFrOnglet.getSalarieDistanceTotale_VoitureThermique() * facteurEmissionVoitureThermique / 1000f;
        Float egesVoitureThermiqueEtudiant = autreMobFrOnglet.getEtudiantDistanceTotale_VoitureThermique() * facteurEmissionVoitureThermique / 1000f;
        Float egesVoitureThermique = egesVoitureThermiqueSalarie + egesVoitureThermiqueEtudiant;
        autreMobFrResultatDto.setEmissionGesVoitureThermique(egesVoitureThermique);

        Float facteurEmissionVoitureElectrique = facteurEmissionService.findByCategorieAndType(
                FacteurEmissionParametre.MOBILITE_LONGUE_DISTANCE,
                FacteurEmissionParametre.MOBILITE_LONGUE_DISTANCE_.VOITURE_ELECTRIQUE).getFacteurEmission();
        Float egesVoitureElectriqueSalarie = autreMobFrOnglet.getSalarieDistanceTotale_VoitureElectrique() * facteurEmissionVoitureElectrique / 1000f;
        Float egesVoitureElectriqueEtudiant = autreMobFrOnglet.getEtudiantDistanceTotale_VoitureElectrique() * facteurEmissionVoitureElectrique / 1000f;
        Float egesVoitureElectrique = egesVoitureElectriqueSalarie + egesVoitureElectriqueEtudiant;
        autreMobFrResultatDto.setEmissionGesVoitureElectrique(egesVoitureElectrique);

        Float facteurEmissionAvion = facteurEmissionService.findByCategorieAndType(
                FacteurEmissionParametre.MOBILITE_LONGUE_DISTANCE_TRAIN_DISTANCE,
                FacteurEmissionParametre.MOBILITE_LONGUE_DISTANCE_TRAIN_DISTANCE_.AVION_FRANCE).getFacteurEmission();
        Float egesAvionSalarie = autreMobFrOnglet.getSalarieDistanceTotale_Avion() * facteurEmissionAvion / 1000f;
        Float egesAvionEtudiant = autreMobFrOnglet.getEtudiantDistanceTotale_Avion() * facteurEmissionAvion / 1000f;
        Float egesAvion = egesAvionSalarie + egesAvionEtudiant;
        autreMobFrResultatDto.setEmissionGesAvion(egesAvion);

        Float facteurEmissionFranceTrainRegional = facteurEmissionService.findByCategorieAndType(
                FacteurEmissionParametre.MOBILITE_LONGUE_DISTANCE_TRAIN_DISTANCE,
                FacteurEmissionParametre.MOBILITE_LONGUE_DISTANCE_TRAIN_DISTANCE_.TRAIN_FRANCE_TER).getFacteurEmission();
        Float egesFranceTrainRegionalSalarie = autreMobFrOnglet.getSalarieDistanceTotale_France_TrainRegional() * facteurEmissionFranceTrainRegional / 1000f;
        Float egesFranceTrainRegionalEtudiant = autreMobFrOnglet.getEtudiantDistanceTotale_France_TrainRegional() * facteurEmissionFranceTrainRegional / 1000f;
        Float egesFranceTrainRegional = egesFranceTrainRegionalSalarie + egesFranceTrainRegionalEtudiant;
        autreMobFrResultatDto.setEmissionGesFranceTrainRegional(egesFranceTrainRegional);

        Float facteurEmissionFranceTrainGrandesLignes = facteurEmissionService.findByCategorieAndType(
                FacteurEmissionParametre.MOBILITE_LONGUE_DISTANCE_TRAIN_DISTANCE,
                FacteurEmissionParametre.MOBILITE_LONGUE_DISTANCE_TRAIN_DISTANCE_.TRAIN_FRANCE_TGV).getFacteurEmission();
        Float egesFranceTrainGrandesLignesSalarie = autreMobFrOnglet.getSalarieDistanceTotale_France_TrainGrandesLignes() * facteurEmissionFranceTrainGrandesLignes / 1000f;
        Float egesFranceTrainGrandesLignesEtudiant = autreMobFrOnglet.getEtudiantDistanceTotale_France_TrainGrandesLignes() * facteurEmissionFranceTrainGrandesLignes / 1000f;
        Float egesFranceTrainGrandesLignes = egesFranceTrainGrandesLignesSalarie + egesFranceTrainGrandesLignesEtudiant;
        autreMobFrResultatDto.setEmissionGesFranceTrainGrandesLignes(egesFranceTrainGrandesLignes);

        Float facteurEmissionAutocar = facteurEmissionService.findByCategorieAndType(
                FacteurEmissionParametre.MOBILITE_LONGUE_DISTANCE_TRAIN_DISTANCE,
                FacteurEmissionParametre.MOBILITE_LONGUE_DISTANCE_TRAIN_DISTANCE_.AUTOCAR).getFacteurEmission();
        Float egesAutocarSalarie = autreMobFrOnglet.getSalarieDistanceTotale_Autocar() * facteurEmissionAutocar / 1000f;
        Float egesAutocarEtudiant = autreMobFrOnglet.getEtudiantDistanceTotale_Autocar() * facteurEmissionAutocar / 1000f;
        Float egesAutocar = egesAutocarSalarie + egesAutocarEtudiant;
        autreMobFrResultatDto.setEmissionGesAutocar(egesAutocar);

        autreMobFrResultatDto.setTotalPosteMobiliteFrance(egesVoitureThermique
                + egesVoitureElectrique
                + egesAvion
                + egesFranceTrainRegional
                + egesFranceTrainGrandesLignes
                + egesAutocar);

        autreMobFrResultatDto.setTotalEtudiants(egesVoitureThermiqueEtudiant
                + egesVoitureElectriqueEtudiant
                + egesAvionEtudiant
                + egesFranceTrainRegionalEtudiant
                + egesFranceTrainGrandesLignesEtudiant
                + egesAutocarEtudiant);

        autreMobFrResultatDto.setTotalSalaries(egesVoitureThermiqueSalarie
                + egesVoitureElectriqueSalarie
                + egesAvionSalarie
                + egesFranceTrainRegionalSalarie
                + egesFranceTrainGrandesLignesSalarie
                + egesAutocarSalarie);

        //calcul intermédiaire
        Float nbAllerSimpleTotalSalaries = autreMobFrOnglet.getSalarieNbAllerSimple_VoitureThermique()
                + autreMobFrOnglet.getSalarieNbAllerSimple_VoitureElectrique()
                + autreMobFrOnglet.getSalarieNbAllerSimple_Avion()
                + autreMobFrOnglet.getSalarieNbAllerSimple_France_TrainRegional()
                + autreMobFrOnglet.getSalarieNbAllerSimple_France_TrainGrandesLignes()
                + autreMobFrOnglet.getSalarieNbAllerSimple_Autocar();

        Float distanceTotaleSalaries = autreMobFrOnglet.getSalarieDistanceTotale_VoitureThermique()
                + autreMobFrOnglet.getSalarieDistanceTotale_VoitureElectrique()
                + autreMobFrOnglet.getSalarieDistanceTotale_Avion()
                + autreMobFrOnglet.getSalarieDistanceTotale_France_TrainRegional()
                + autreMobFrOnglet.getSalarieDistanceTotale_France_TrainGrandesLignes()
                + autreMobFrOnglet.getSalarieDistanceTotale_Autocar();

        Float nbAllerSimpleTotalEtudiants = autreMobFrOnglet.getEtudiantNbAllerSimple_VoitureThermique()
                + autreMobFrOnglet.getEtudiantNbAllerSimple_VoitureElectrique()
                + autreMobFrOnglet.getEtudiantNbAllerSimple_Avion()
                + autreMobFrOnglet.getEtudiantNbAllerSimple_France_TrainRegional()
                + autreMobFrOnglet.getEtudiantNbAllerSimple_France_TrainGrandesLignes()
                + autreMobFrOnglet.getEtudiantNbAllerSimple_Autocar();

        Float distanceTotaleEtudiants = autreMobFrOnglet.getEtudiantDistanceTotale_VoitureThermique()
                + autreMobFrOnglet.getEtudiantDistanceTotale_VoitureElectrique()
                + autreMobFrOnglet.getEtudiantDistanceTotale_Avion()
                + autreMobFrOnglet.getEtudiantDistanceTotale_France_TrainRegional()
                + autreMobFrOnglet.getEtudiantDistanceTotale_France_TrainGrandesLignes()
                + autreMobFrOnglet.getEtudiantDistanceTotale_Autocar();


        //Second Tableau
        autreMobFrResultatDto.setDistanceAnnuelleMoyenneParUsagerSalarie(distanceTotaleSalaries / 2 / nbAllerSimpleTotalSalaries);
        autreMobFrResultatDto.setDistanceAnnuelleMoyenneParUsagerEtudiant(distanceTotaleEtudiants / 2 / nbAllerSimpleTotalEtudiants);


        autreMobFrResultatDto.setPartModaleVoitureThermiqueSalarie(
                (autreMobFrOnglet.getSalarieDistanceTotale_VoitureThermique() / distanceTotaleSalaries) * 100);
        autreMobFrResultatDto.setPartModaleVoitureElectriqueSalarie(
                (autreMobFrOnglet.getSalarieDistanceTotale_VoitureElectrique() / distanceTotaleSalaries) * 100);
        autreMobFrResultatDto.setPartModaleAvionSalarie(
                (autreMobFrOnglet.getSalarieDistanceTotale_Avion() / distanceTotaleSalaries) * 100);
        autreMobFrResultatDto.setPartModaleModesDouxSalarie(
                ((autreMobFrOnglet.getSalarieDistanceTotale_France_TrainRegional()
                        + autreMobFrOnglet.getSalarieDistanceTotale_France_TrainGrandesLignes()
                        + autreMobFrOnglet.getSalarieDistanceTotale_Autocar()
                ) / distanceTotaleSalaries) * 100);

        autreMobFrResultatDto.setPartModaleVoitureThermiqueEtudiant(
                (autreMobFrOnglet.getEtudiantDistanceTotale_VoitureThermique() / distanceTotaleEtudiants) * 100);
        autreMobFrResultatDto.setPartModaleVoitureElectriqueEtudiant(
                (autreMobFrOnglet.getEtudiantDistanceTotale_VoitureElectrique() / distanceTotaleEtudiants) * 100);
        autreMobFrResultatDto.setPartModaleAvionEtudiant(
                (autreMobFrOnglet.getEtudiantDistanceTotale_Avion() / distanceTotaleEtudiants) * 100);
        autreMobFrResultatDto.setPartModaleModesDouxEtudiant(
                ((autreMobFrOnglet.getEtudiantDistanceTotale_France_TrainRegional()
                        + autreMobFrOnglet.getEtudiantDistanceTotale_France_TrainGrandesLignes()
                        + autreMobFrOnglet.getEtudiantDistanceTotale_Autocar()
                ) / distanceTotaleEtudiants) * 100);


        autreMobFrResultatDto.setIntensiteCarboneMoyenSalarie(autreMobFrResultatDto.getTotalSalaries()*1000000f/distanceTotaleSalaries);
        autreMobFrResultatDto.setIntensiteCarboneMoyenEtudiant(autreMobFrResultatDto.getTotalEtudiants()*1000000f/distanceTotaleEtudiants);

        return autreMobFrResultatDto;
    }
}
