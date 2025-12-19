package tcucl.back_tcucl.service.impl.onglet;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tcucl.back_tcucl.dto.onglet.mobInternational.MobInternationalOngletDto;
import tcucl.back_tcucl.dto.onglet.mobInternational.MobInternationalResultatDto;
import tcucl.back_tcucl.dto.onglet.mobInternational.VoyageDto;
import tcucl.back_tcucl.entity.facteurEmission.FacteurEmissionParametre;
import tcucl.back_tcucl.entity.onglet.GeneralOnglet;
import tcucl.back_tcucl.entity.onglet.mobInternationale.MobInternationalOnglet;
import tcucl.back_tcucl.entity.onglet.mobInternationale.Voyage;
import tcucl.back_tcucl.entity.onglet.mobInternationale.enums.EnumMobInternationale_Pays;
import tcucl.back_tcucl.exceptionPersonnalisee.NonTrouveGeneralCustomException;
import tcucl.back_tcucl.exceptionPersonnalisee.ValidationCustomException;
import tcucl.back_tcucl.manager.MobInternationalOngletManager;
import tcucl.back_tcucl.service.FacteurEmissionService;
import tcucl.back_tcucl.service.MobInternationalOngletService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class MobInternationalOngletServiceImpl implements MobInternationalOngletService {

    private static final Logger log = LoggerFactory.getLogger(MobInternationalOngletServiceImpl.class);
    @Autowired
    private Validator validator;

    private final MobInternationalOngletManager mobInternationalOngletManager;
    private final FacteurEmissionService facteurEmissionService;

    public MobInternationalOngletServiceImpl(MobInternationalOngletManager mobInternationalOngletManager, FacteurEmissionService facteurEmissionService) {
        this.mobInternationalOngletManager = mobInternationalOngletManager;
        this.facteurEmissionService = facteurEmissionService;
    }

    @Override
    public MobInternationalOnglet getMobInternationalOngletById(Long ongletId) {
        return mobInternationalOngletManager.getMobInternationalOngletById(ongletId);
    }

    @Override
    public void updateMobInternationalOngletPartiel(Long ongletId, MobInternationalOngletDto mobInternationalOngletDto) {
        mobInternationalOngletManager.updateMobInternationalOngletPartiel(ongletId, mobInternationalOngletDto);
    }

    @Override
    public void ajouterVoyage(Long ongletId, VoyageDto voyageDto) {
        mobInternationalOngletManager.ajouterVoyage(ongletId, voyageDto);
    }

    @Override
    public void supprimerVoyage(Long ongletId, Long voyageId) {
        mobInternationalOngletManager.supprimerVoyage(ongletId, voyageId);
    }

    @Override
    public void updateVoyagePartiel(Long ongletId, Long voyageId, VoyageDto voyageDto) {
        mobInternationalOngletManager.updateVoyagePartiel(ongletId, voyageId, voyageDto);
    }

    @Override
    public MobInternationalResultatDto getMobInternationalResultat(Long OngletId) {
        MobInternationalOnglet onglet = mobInternationalOngletManager.getMobInternationalOngletById(OngletId);
        MobInternationalResultatDto resultat = new MobInternationalResultatDto();

        //Récupération de la liste de voyages
        List<Voyage> voyages = onglet.getVoyages();

        //Récupération du nombre de Salariés et d'étudiants
        GeneralOnglet generalOnglet = onglet.getOngletDeClass(GeneralOnglet.class);
        Float nbSalaries = (float) generalOnglet.getNbSalarie();
        Float nbEtudiants = (float) generalOnglet.getNbEtudiant();

        //initialisation des sommes à faire
        // Les sommes seront utilisées plus tard
        // Les noms des variables sont inspirées de l'excel avec les noms des colonnes histoire de s'y retrouver

        //premier tableau
        AtomicReference<Float> T9T39sumProsEuropeTrain = new AtomicReference<>(0f);
        AtomicReference<Float> S9S39sumProsEuropeAvion = new AtomicReference<>(0f);
        AtomicReference<Float> P9P39sumProsEurope = new AtomicReference<>(0f);
        AtomicReference<Float> P41P124sumProsHorsEurope = new AtomicReference<>(0f);

        AtomicReference<Float> V9V39sumStagesEtudiantsEuropeTrain = new AtomicReference<>(0f);
        AtomicReference<Float> U9U39sumStagesEtudiantsEuropeAvion = new AtomicReference<>(0f);
        AtomicReference<Float> Q9Q39sumStagesEtudiantsEurope = new AtomicReference<>(0f);
        AtomicReference<Float> Q41Q124sumStagesEtudiantsHorsEurope = new AtomicReference<>(0f);

        AtomicReference<Float> X9X39sumSemestresEtudiantsEuropeTrain = new AtomicReference<>(0f);
        AtomicReference<Float> W9W39sumSemestresEtudiantsEuropeAvion = new AtomicReference<>(0f);
        AtomicReference<Float> R9R39sumSemestresEtudiantsEurope = new AtomicReference<>(0f);
        AtomicReference<Float> R41R124sumSemestresEtudiantsHorsEurope = new AtomicReference<>(0f);

        AtomicReference<Float> X9X39sumFormationContinueEuropeTrain = new AtomicReference<>(0f); //A dupliquer
        AtomicReference<Float> W9W39sumFormationContinueEuropeAvion = new AtomicReference<>(0f); //A dupliquer
        AtomicReference<Float> R9R39sumFormationContinueEurope = new AtomicReference<>(0f); //A dupliquer
        AtomicReference<Float> R41R124sumFormationContinueHorsEurope = new AtomicReference<>(0f); //A dupliquer

        //second tableau
        AtomicReference<Float> C9D124sumProsDepart = new AtomicReference<>(0f);
        AtomicReference<Float> E9F124sumStagesEtudiantsDepart = new AtomicReference<>(0f);
        AtomicReference<Float> G9H124sumSemestresEtudiantsDepart = new AtomicReference<>(0f);
        AtomicReference<Float> G9H124sumFormationContinueDepart = new AtomicReference<>(0f); //A dupliquer

        AtomicReference<Float> C9D39sumProsDepartEurope = new AtomicReference<>(0f);
        AtomicReference<Float> E9F39sumStagesEtudiantsDepartEurope = new AtomicReference<>(0f);
        AtomicReference<Float> G9H39sumSemestresEtudiantsDepartEurope = new AtomicReference<>(0f);
        AtomicReference<Float> G9H39sumFormationContinueDepartEurope = new AtomicReference<>(0f); //A dupliquer

        AtomicReference<Float> Z41Z124sumProsDistanceHorsEurope = new AtomicReference<>(0f);
        AtomicReference<Float> AA41AA124sumStagesEtudiantsDistanceHorsEurope = new AtomicReference<>(0f);
        AtomicReference<Float> AB41AB124sumSemestresEtudiantsDistanceHorsEurope = new AtomicReference<>(0f);
        AtomicReference<Float> AB41AB124sumFormationContinueDistanceHorsEurope = new AtomicReference<>(0f); //A dupliquer

        AtomicReference<Float> C41C124sumProsDepartHorsEurope = new AtomicReference<>(0f);
        AtomicReference<Float> E41E124sumStagesEtudiantsDepartHorsEurope = new AtomicReference<>(0f);
        AtomicReference<Float> G41G124sumSemestresEtudiantsDepartHorsEurope = new AtomicReference<>(0f);
        AtomicReference<Float> G41G124sumFormationContinueDepartHorsEurope = new AtomicReference<>(0f); //A dupliquer

        AtomicReference<Float> D9D39sumProsDepartEuropeTrain = new AtomicReference<>(0f);
        AtomicReference<Float> F9F39sumStagesEtudiantsDepartEuropeTrain = new AtomicReference<>(0f);
        AtomicReference<Float> H9H39sumSemestresEtudiantsDepartEuropeTrain = new AtomicReference<>(0f);
        AtomicReference<Float> H9H39sumFormationContinueDepartEuropeTrain = new AtomicReference<>(0f); //A dupliquer

        AtomicReference<Float> AC9AC39sumProsDistanceEuropeAvion = new AtomicReference<>(0f);
        AtomicReference<Float> AE9AE39sumStagesEtudiantsDistanceEuropeAvion = new AtomicReference<>(0f);
        AtomicReference<Float> AG9AG39sumSemestresEtudiantsDistanceEuropeAvion = new AtomicReference<>(0f);
        AtomicReference<Float> AG9AG39sumFormationContinueDistanceEuropeAvion = new AtomicReference<>(0f); //A dupliquer

        AtomicReference<Float> C9C39sumProsDepartEuropeAvion = new AtomicReference<>(0f);
        AtomicReference<Float> E9E39sumStagesEtudiantsDepartEuropeAvion = new AtomicReference<>(0f);
        AtomicReference<Float> G9G39sumSemestresEtudiantsDepartEuropeAvion = new AtomicReference<>(0f);
        AtomicReference<Float> G9G39sumFormationContinueDepartEuropeAvion = new AtomicReference<>(0f); //A dupliquer

        AtomicReference<Float> AD9AD39sumProsDistanceEuropeTrain = new AtomicReference<>(0f);
        AtomicReference<Float> AF9AF39sumStagesEtudiantsDistanceEuropeTrain = new AtomicReference<>(0f);
        AtomicReference<Float> AH9AH39sumSemestresEtudiantsDistanceEuropeTrain = new AtomicReference<>(0f);
        AtomicReference<Float> AH9AH39sumFormationContinueDistanceEuropeTrain = new AtomicReference<>(0f); //A dupliquer

        AtomicReference<Float> P9P124sumEgesPros = new AtomicReference<>(0f);
        AtomicReference<Float> Q9Q124sumEgesStagesEtudiants = new AtomicReference<>(0f);
        AtomicReference<Float> R9R124sumEgesSemestresEtudiants = new AtomicReference<>(0f);
        AtomicReference<Float> R9R124sumEgesFormationContinue = new AtomicReference<>(0f); //A dupliquer

        AtomicReference<Float> Z9Z124sumDistancePros = new AtomicReference<>(0f);
        AtomicReference<Float> AA9AA124sumDistanceStagesEtudiants = new AtomicReference<>(0f);
        AtomicReference<Float> AB9AB124sumDistanceSemestresEtudiants = new AtomicReference<>(0f);
        AtomicReference<Float> AB9AB124sumDistanceFormationContinue = new AtomicReference<>(0f); //A dupliquer


        // On parcourt toutes les voyages pour calculer les sommes et les émissions de GES du voyage actuel
        voyages.forEach(voyage -> {
            // On va créer des variables pour toutes les colonnes afin de s'y retrouver
            Float CprosAvion = voyage.getProsAvion() != null ? voyage.getProsAvion().floatValue() : 0f;
            Float DprosTrain = voyage.getProsTrain() != null ? voyage.getProsTrain().floatValue() : 0f;
            Float EstagesEtudiantsAvion = voyage.getStagesEtudiantsAvion() != null ? voyage.getStagesEtudiantsAvion().floatValue() : 0f;
            Float FstagesEtudiantsTrain = voyage.getStagesEtudiantsTrain() != null ? voyage.getStagesEtudiantsTrain().floatValue() : 0f;
            Float GsemestresEtudiantsAvion = voyage.getSemestresEtudiantsAvion() != null ? voyage.getSemestresEtudiantsAvion().floatValue() : 0f;
            Float HsemestresEtudiantsTrain = voyage.getSemestresEtudiantsTrain() != null ? voyage.getSemestresEtudiantsTrain().floatValue() : 0f;
            Float GformationContinueAvion = voyage.getFormationContinueAvion() != null ? voyage.getFormationContinueAvion().floatValue() : 0f; //a dupliquer
            Float HformationContinueTrain = voyage.getFormationContinueTrain() != null ? voyage.getFormationContinueTrain().floatValue() : 0f; // a dupliquer


            Float IDistanceMoyenneVolDOiseau = (float) voyage.getPays().getDistanceMoyenneVolOiseau();
            Float JegesMoyenAvion = facteurEmissionService.findByCategorieAndType(
                    FacteurEmissionParametre.MOBILITE_LONGUE_DISTANCE_AVION,
                    voyage.getPays().getLibelle()
            ).getFacteurEmission();

            Float KegesMoyenAvionTrainees = JegesMoyenAvion;
            if (voyage.getPays() != EnumMobInternationale_Pays.BELGIQUE
                    && voyage.getPays() != EnumMobInternationale_Pays.LUXEMBOURG
                    && voyage.getPays() != EnumMobInternationale_Pays.MONACO
                    && voyage.getPays() != EnumMobInternationale_Pays.PAYS_BAS) {
                KegesMoyenAvionTrainees = JegesMoyenAvion * 1.7f;
            }

            Float LegesMoyenTrainNombre = 0f;
            if (voyage.getPays().getIsAccessibleEnTrain()) {
                try{
                LegesMoyenTrainNombre = facteurEmissionService.findByCategorieAndType(
                        FacteurEmissionParametre.MOBILITE_LONGUE_DISTANCE_TRAIN_NOMBRE,
                        voyage.getPays().getLibelle()
                ).getFacteurEmission();
                }catch(NonTrouveGeneralCustomException ignored){
                    System.out.println(ignored.getMessage());
                    // Si le facteur d'émission n'est pas trouvé, on laisse la valeur à 0
                    // Cas spécifique au train car il y a une possibilité d'aller en train très compmiqué
                    // Elle sont donc autorisés
                }
            }
            Float MegesMoyenTrainDistance = 0f;
            if (voyage.getPays().getIsAccessibleEnTrain()) {
                try {
                    MegesMoyenTrainDistance = facteurEmissionService.findByCategorieAndType(
                            FacteurEmissionParametre.MOBILITE_LONGUE_DISTANCE_TRAIN_DISTANCE,
                            voyage.getPays().getLibelle()
                    ).getFacteurEmission();
                }catch (NonTrouveGeneralCustomException ignored) {
                    System.out.println(ignored.getMessage());
                    // Si le facteur d'émission n'est pas trouvé, on laisse la valeur à 0
                    // Cas spécifique au train car il y a une possibilité d'aller en train très compmiqué
                    // Elle sont donc autorisés
                }
            }
            Float NegesTotal = (
                    (CprosAvion + EstagesEtudiantsAvion + GsemestresEtudiantsAvion + GformationContinueAvion) * JegesMoyenAvion * 2f + //ajouter valeur de la nouvelle colonne
                            (DprosTrain + FstagesEtudiantsTrain + HsemestresEtudiantsTrain + HformationContinueTrain) * LegesMoyenTrainNombre * 2f) //ajouter valeur de la nouvelle colonne
                    / 1000f;
            Float OegesTotalTrainees = (
                    (CprosAvion + EstagesEtudiantsAvion + GsemestresEtudiantsAvion + GformationContinueAvion) * KegesMoyenAvionTrainees * 2f +
                            (DprosTrain + FstagesEtudiantsTrain + HsemestresEtudiantsTrain + HformationContinueTrain) * LegesMoyenTrainNombre * 2f)
                    / 1000f;

            Float PegesPros = (CprosAvion * KegesMoyenAvionTrainees * 2f + DprosTrain * LegesMoyenTrainNombre * 2f) / 1000f;
            Float QegesStagesEtudiants = (EstagesEtudiantsAvion * KegesMoyenAvionTrainees * 2f + FstagesEtudiantsTrain * LegesMoyenTrainNombre * 2f) / 1000f;
            Float RegesSemestresEtudiants = (GsemestresEtudiantsAvion * KegesMoyenAvionTrainees * 2f + HsemestresEtudiantsTrain * LegesMoyenTrainNombre * 2f) / 1000f;
            Float RegesFormationContinue = (GformationContinueAvion * KegesMoyenAvionTrainees * 2f + HformationContinueTrain * LegesMoyenTrainNombre * 2f) / 1000f; //A dupliquer

            Float SegesProsAvion = (CprosAvion * KegesMoyenAvionTrainees) / 1000f;
            Float TegesProsTrain = (DprosTrain * LegesMoyenTrainNombre) / 1000f;
            Float UegesStagesEtudiantsAvion = (EstagesEtudiantsAvion * KegesMoyenAvionTrainees) / 1000f;
            Float VegesStagesEtudiantsTrain = (FstagesEtudiantsTrain * LegesMoyenTrainNombre) / 1000f;
            Float WegesSemestresEtudiantsAvion = (GsemestresEtudiantsAvion * KegesMoyenAvionTrainees) / 1000f;
            Float XegesSemestresEtudiantsTrain = (HsemestresEtudiantsTrain * LegesMoyenTrainNombre) / 1000f;
            Float WegesFormationContinueAvion = (GformationContinueAvion * KegesMoyenAvionTrainees) / 1000f; //A dupliquer
            Float XegesFormationContinueTrain = (HformationContinueTrain * LegesMoyenTrainNombre) / 1000f; //A dupliquer

            Float YdistanceTotale = ((CprosAvion + EstagesEtudiantsAvion + GsemestresEtudiantsAvion + GformationContinueAvion) * (IDistanceMoyenneVolDOiseau + 100)) * 2 // ajouter valeur de la nouvelle colonne
                    + (DprosTrain + FstagesEtudiantsTrain + HsemestresEtudiantsTrain + HformationContinueTrain) * (IDistanceMoyenneVolDOiseau * 2f * 1.2f); // ajouter valeur de la nouvelle colonne
            Float ZdistancePros = ((CprosAvion) * (IDistanceMoyenneVolDOiseau + 100)) * 2
                    + (DprosTrain) * (IDistanceMoyenneVolDOiseau * 2f * 1.2f);
            Float AAdistanceStagesEtudiants = ((EstagesEtudiantsAvion) * (IDistanceMoyenneVolDOiseau + 100)) * 2
                    + (FstagesEtudiantsTrain) * (IDistanceMoyenneVolDOiseau * 2f * 1.2f);
            Float ABdistanceSemestresEtudiants = ((GsemestresEtudiantsAvion) * (IDistanceMoyenneVolDOiseau + 100)) * 2
                    + (HsemestresEtudiantsTrain) * (IDistanceMoyenneVolDOiseau * 2f * 1.2f);
            Float ABdistanceFormationContinueEtudiants = ((GformationContinueAvion) * (IDistanceMoyenneVolDOiseau + 100)) * 2 //A dupliquer
                    + (HformationContinueTrain) * (IDistanceMoyenneVolDOiseau * 2f * 1.2f);

            Float ACdistanceProsAvion = (CprosAvion * (IDistanceMoyenneVolDOiseau + 100)) * 2;
            Float ADdistanceProsTrain = (DprosTrain * IDistanceMoyenneVolDOiseau * 2f * 1.2f);
            Float AEdistanceStagesEtudiantsAvion = (EstagesEtudiantsAvion * (IDistanceMoyenneVolDOiseau + 100)) * 2;
            Float AFdistanceStagesEtudiantsTrain = (FstagesEtudiantsTrain * IDistanceMoyenneVolDOiseau * 2f * 1.2f);
            Float AGdistanceSemestresEtudiantsAvion = (GsemestresEtudiantsAvion * (IDistanceMoyenneVolDOiseau + 100)) * 2;
            Float AHdistanceSemestresEtudiantsTrain = (HsemestresEtudiantsTrain * IDistanceMoyenneVolDOiseau * 2f * 1.2f);
            Float AGdistanceFormationContinueAvion = (GformationContinueAvion * (IDistanceMoyenneVolDOiseau + 100)) * 2; //A dupliquer
            Float AHdistanceFormationContinueTrain = (HformationContinueTrain * IDistanceMoyenneVolDOiseau * 2f * 1.2f); //A dupliquer

            // On ajoute les EGES du voyage avec son ID dans la map
            resultat.addEmissionGesParPays(voyage.getPays(), OegesTotalTrainees);

            // On va mettre à jour les sommes pour chaque Colonne qui est utilisé pour une somme
            // Syntaxe reprise de l'excel
            // _9_124 -> monde
            // _9_39 -> europe
            // _41_124 -> hors europe

            C9D124sumProsDepart.updateAndGet(v -> v + CprosAvion + DprosTrain);
            E9F124sumStagesEtudiantsDepart.updateAndGet(v -> v + EstagesEtudiantsAvion + FstagesEtudiantsTrain);
            G9H124sumSemestresEtudiantsDepart.updateAndGet(v -> v + GsemestresEtudiantsAvion + HsemestresEtudiantsTrain);
            G9H124sumFormationContinueDepart.updateAndGet(v -> v + GformationContinueAvion + HformationContinueTrain); //A dupliquer

            P9P124sumEgesPros.updateAndGet(v -> v + PegesPros);
            Q9Q124sumEgesStagesEtudiants.updateAndGet(v -> v + QegesStagesEtudiants);
            R9R124sumEgesSemestresEtudiants.updateAndGet(v -> v + RegesSemestresEtudiants);
            R9R124sumEgesFormationContinue.updateAndGet(v -> v + RegesFormationContinue); //A dupliquer

            Z9Z124sumDistancePros.updateAndGet(v -> v + ZdistancePros);
            AA9AA124sumDistanceStagesEtudiants.updateAndGet(v -> v + AAdistanceStagesEtudiants);
            AB9AB124sumDistanceSemestresEtudiants.updateAndGet(v -> v + ABdistanceSemestresEtudiants);
            AB9AB124sumDistanceFormationContinue.updateAndGet(v -> v + ABdistanceFormationContinueEtudiants); //A dupliquer
            if (voyage.getPays().getIsEurope()) {
                C9D39sumProsDepartEurope.updateAndGet(v -> v + CprosAvion + DprosTrain);
                C9C39sumProsDepartEuropeAvion.updateAndGet(v -> v + CprosAvion);
                D9D39sumProsDepartEuropeTrain.updateAndGet(v -> v + DprosTrain);
                E9F39sumStagesEtudiantsDepartEurope.updateAndGet(v -> v + EstagesEtudiantsAvion + FstagesEtudiantsTrain);
                E9E39sumStagesEtudiantsDepartEuropeAvion.updateAndGet(v -> v + EstagesEtudiantsAvion);
                F9F39sumStagesEtudiantsDepartEuropeTrain.updateAndGet(v -> v + FstagesEtudiantsTrain);
                G9H39sumSemestresEtudiantsDepartEurope.updateAndGet(v -> v + GsemestresEtudiantsAvion + HsemestresEtudiantsTrain);
                G9H39sumFormationContinueDepartEurope.updateAndGet(v -> v + GformationContinueAvion + HformationContinueTrain); //A dupliquer
                G9G39sumSemestresEtudiantsDepartEuropeAvion.updateAndGet(v -> v + GsemestresEtudiantsAvion);
                G9G39sumFormationContinueDepartEuropeAvion.updateAndGet(v -> v + GformationContinueAvion); //A dupliquer
                H9H39sumSemestresEtudiantsDepartEuropeTrain.updateAndGet(v -> v + HsemestresEtudiantsTrain);
                H9H39sumFormationContinueDepartEuropeTrain.updateAndGet(v -> v + HformationContinueTrain); //A dupliquer
                P9P39sumProsEurope.updateAndGet(v -> v + PegesPros);
                Q9Q39sumStagesEtudiantsEurope.updateAndGet(v -> v + QegesStagesEtudiants);
                R9R39sumSemestresEtudiantsEurope.updateAndGet(v -> v + RegesSemestresEtudiants);
                R9R39sumFormationContinueEurope.updateAndGet(v -> v + RegesFormationContinue); //A dupliquer

                S9S39sumProsEuropeAvion.updateAndGet(v -> v + SegesProsAvion);
                T9T39sumProsEuropeTrain.updateAndGet(v -> v + TegesProsTrain);
                U9U39sumStagesEtudiantsEuropeAvion.updateAndGet(v -> v + UegesStagesEtudiantsAvion);
                V9V39sumStagesEtudiantsEuropeTrain.updateAndGet(v -> v + VegesStagesEtudiantsTrain);
                W9W39sumSemestresEtudiantsEuropeAvion.updateAndGet(v -> v + WegesSemestresEtudiantsAvion);
                X9X39sumSemestresEtudiantsEuropeTrain.updateAndGet(v -> v + XegesSemestresEtudiantsTrain);
                W9W39sumFormationContinueEuropeAvion.updateAndGet(v -> v + WegesFormationContinueAvion); //A dupliquer
                X9X39sumFormationContinueEuropeTrain.updateAndGet(v -> v + XegesFormationContinueTrain); //A dupliquer
                AC9AC39sumProsDistanceEuropeAvion.updateAndGet(v -> v + ACdistanceProsAvion);
                AD9AD39sumProsDistanceEuropeTrain.updateAndGet(v -> v + ADdistanceProsTrain);
                AE9AE39sumStagesEtudiantsDistanceEuropeAvion.updateAndGet(v -> v + AEdistanceStagesEtudiantsAvion);
                AF9AF39sumStagesEtudiantsDistanceEuropeTrain.updateAndGet(v -> v + AFdistanceStagesEtudiantsTrain);
                AG9AG39sumSemestresEtudiantsDistanceEuropeAvion.updateAndGet(v -> v + AGdistanceSemestresEtudiantsAvion);
                AH9AH39sumSemestresEtudiantsDistanceEuropeTrain.updateAndGet(v -> v + AHdistanceSemestresEtudiantsTrain);
                AG9AG39sumFormationContinueDistanceEuropeAvion.updateAndGet(v -> v + AGdistanceFormationContinueAvion); //A dupliquer
                AH9AH39sumFormationContinueDistanceEuropeTrain.updateAndGet(v -> v + AHdistanceFormationContinueTrain); //A dupliquer
            } else {
                C41C124sumProsDepartHorsEurope.updateAndGet(v -> v + CprosAvion);
                E41E124sumStagesEtudiantsDepartHorsEurope.updateAndGet(v -> v + EstagesEtudiantsAvion);
                G41G124sumSemestresEtudiantsDepartHorsEurope.updateAndGet(v -> v + GsemestresEtudiantsAvion);
                G41G124sumFormationContinueDepartHorsEurope.updateAndGet(v -> v + GformationContinueAvion); //A dupliquer

                P41P124sumProsHorsEurope.updateAndGet(v -> v + PegesPros);
                Q41Q124sumStagesEtudiantsHorsEurope.updateAndGet(v -> v + QegesStagesEtudiants);
                R41R124sumSemestresEtudiantsHorsEurope.updateAndGet(v -> v + RegesSemestresEtudiants);
                R41R124sumFormationContinueHorsEurope.updateAndGet(v -> v + RegesFormationContinue); //A dupliquer

                Z41Z124sumProsDistanceHorsEurope.updateAndGet(v -> v + ZdistancePros);
                AA41AA124sumStagesEtudiantsDistanceHorsEurope.updateAndGet(v -> v + AAdistanceStagesEtudiants);
                AB41AB124sumSemestresEtudiantsDistanceHorsEurope.updateAndGet(v -> v + ABdistanceSemestresEtudiants);
                AB41AB124sumFormationContinueDistanceHorsEurope.updateAndGet(v -> v + ABdistanceFormationContinueEtudiants); //A dupliquer
            }
        });

        // Maintenenant que nous avons fait le tour des tous les voyages, toutes les sommes ont été créées et remplis
        // Nous pouvons donc maintenant les utiliser pour remplir le dto

        // PREMIER TABLEAU
        // Emission de Ges - Europe Train
        resultat.setEmissionGesProEuropeTrain(T9T39sumProsEuropeTrain.get() * 2f);
        resultat.setEmissionGesStagesEuropeTrain(V9V39sumStagesEtudiantsEuropeTrain.get() * 2f);
        resultat.setEmissionGesSemestresEtudiantsEuropeTrain(X9X39sumSemestresEtudiantsEuropeTrain.get() * 2f);
        resultat.setEmissionGesFormationContinueEuropeTrain(X9X39sumFormationContinueEuropeTrain.get() * 2f); //A dupliquer

        // Emission de Ges - Europe Avion
        resultat.setEmissionGesProEuropeAvion(S9S39sumProsEuropeAvion.get() * 2f);
        resultat.setEmissionGesStagesEuropeAvion(U9U39sumStagesEtudiantsEuropeAvion.get() * 2f);
        resultat.setEmissionGesSemestresEtudiantsEuropeAvion(W9W39sumSemestresEtudiantsEuropeAvion.get() * 2f);
        resultat.setEmissionGesFormationContinueEuropeAvion(W9W39sumFormationContinueEuropeAvion.get() * 2f); //A dupliquer

        // Emission de Ges - Europe
        resultat.setEmissionGesProEurope(P9P39sumProsEurope.get());
        resultat.setEmissionGesStageEurope(Q9Q39sumStagesEtudiantsEurope.get());
        resultat.setEmissionGesSemestresEtudiantsEurope(R9R39sumSemestresEtudiantsEurope.get());
        resultat.setEmissionGesFormationContinueEurope(R9R39sumFormationContinueEurope.get()); //A dupliquer

        // Emission de Ges - Hors Europe
        resultat.setEmissionGesProHorsEurope(P41P124sumProsHorsEurope.get());
        resultat.setEmissionGesStagesHorsEurope(Q41Q124sumStagesEtudiantsHorsEurope.get());
        resultat.setEmissionGesSemestresEtudiantsHorsEurope(R41R124sumSemestresEtudiantsHorsEurope.get());
        resultat.setEmissionGesFormationContinueHorsEurope(R41R124sumFormationContinueHorsEurope.get()); //A dupliquer

        // SECOND TABLEAU
        // Proportion de departs
        resultat.setProsProportionDeDeparts(nbSalaries != 0f
            ? (C9D124sumProsDepart.get() / nbSalaries) * 100
            : 0f); // a dupliquer si ajout de colonne (attention : si relatif aux étudiants voir au if en dessous)

        resultat.setStagesProportionDeDeparts(nbEtudiants != 0f
            ? (E9F124sumStagesEtudiantsDepart.get() / nbEtudiants) * 100
            : 0f);
        resultat.setSemestresProportionDeDeparts(nbEtudiants != 0f
            ? (G9H124sumSemestresEtudiantsDepart.get() / nbEtudiants) * 100
            : 0f);
        resultat.setFormationContinueProportionDeDeparts(nbEtudiants != 0f
            ? (G9H124sumFormationContinueDepart.get() / nbEtudiants) * 100
            : 0f); // a dupliquer si ajout de colonne (attention : si relatif aux salariés voir au if au dessus)

        // Part Europe vs Non Europe
        if (C9D124sumProsDepart.get() != 0f) {
            resultat.setProsPartEuropeVsNonEurope((C9D39sumProsDepartEurope.get() / C9D124sumProsDepart.get()) * 100);
        }
        if (E9F124sumStagesEtudiantsDepart.get() != 0f) {
            resultat.setStagesPartEuropeVsNonEurope((E9F39sumStagesEtudiantsDepartEurope.get() / E9F124sumStagesEtudiantsDepart.get()) * 100);
        }
        if (G9H124sumSemestresEtudiantsDepart.get() != 0f) {
            resultat.setSemestresPartEuropeVsNonEurope((G9H39sumSemestresEtudiantsDepartEurope.get() / G9H124sumSemestresEtudiantsDepart.get()) * 100);
        }
        if (G9H124sumFormationContinueDepart.get() != 0f) {
            resultat.setFormationContinuePartEuropeVsNonEurope((G9H39sumFormationContinueDepartEurope.get() / G9H124sumFormationContinueDepart.get()) * 100); //A dupliquer
        }

        // Distance Moyenne Hors Europe
        if (C41C124sumProsDepartHorsEurope.get() != 0f) {
            resultat.setProsDistanceMoyenneHorsEurope(Z41Z124sumProsDistanceHorsEurope.get() / 2 / C41C124sumProsDepartHorsEurope.get());
        }
        if (E41E124sumStagesEtudiantsDepartHorsEurope.get() != 0f) {
            resultat.setStagesDistanceMoyenneHorsEurope(AA41AA124sumStagesEtudiantsDistanceHorsEurope.get() / 2 / E41E124sumStagesEtudiantsDepartHorsEurope.get());
        }
        if (G41G124sumSemestresEtudiantsDepartHorsEurope.get() != 0f) {
            resultat.setSemestresDistanceMoyenneHorsEurope(AB41AB124sumSemestresEtudiantsDistanceHorsEurope.get() / 2 / G41G124sumSemestresEtudiantsDepartHorsEurope.get());
        }
        if (G41G124sumFormationContinueDepartHorsEurope.get() != 0f) {
            resultat.setFormationContinueDistanceMoyenneHorsEurope(AB41AB124sumFormationContinueDistanceHorsEurope.get() / 2 / G41G124sumFormationContinueDepartHorsEurope.get()); //A dupliquer
        }

        // Part Train Europe
        if (C9D39sumProsDepartEurope.get() != 0f) {
            resultat.setProsPartTrainEnEurope((D9D39sumProsDepartEuropeTrain.get() / C9D39sumProsDepartEurope.get()) * 100);
        }
        if (E9F39sumStagesEtudiantsDepartEurope.get() != 0f) {
            resultat.setStagesPartTrainEnEurope((F9F39sumStagesEtudiantsDepartEuropeTrain.get() / E9F39sumStagesEtudiantsDepartEurope.get()) * 100);
        }
        if (G9H39sumSemestresEtudiantsDepartEurope.get() != 0f) {
            resultat.setSemestresPartTrainEnEurope((H9H39sumSemestresEtudiantsDepartEuropeTrain.get() / G9H39sumSemestresEtudiantsDepartEurope.get()) * 100);
        }
        if (G9H39sumFormationContinueDepartEurope.get() != 0f) {
            resultat.setFormationContinuePartTrainEnEurope((H9H39sumFormationContinueDepartEuropeTrain.get() / G9H39sumFormationContinueDepartEurope.get()) * 100); //A dupliquer
        }

        // Distance Moyenne Europe Avion
        if (C9C39sumProsDepartEuropeAvion.get() != 0f) {
            resultat.setProsDistancemoyenneEuropeAvion(AC9AC39sumProsDistanceEuropeAvion.get() / 2 /  C9C39sumProsDepartEuropeAvion.get());
        }
        if (E9E39sumStagesEtudiantsDepartEuropeAvion.get() != 0f) {
            resultat.setStagesDistancemoyenneEuropeAvion(AE9AE39sumStagesEtudiantsDistanceEuropeAvion.get() / 2 / E9E39sumStagesEtudiantsDepartEuropeAvion.get());
        }
        if (G9G39sumSemestresEtudiantsDepartEuropeAvion.get() != 0f) {
            resultat.setSemestresDistancemoyenneEuropeAvion(AG9AG39sumSemestresEtudiantsDistanceEuropeAvion.get() / 2 / G9G39sumSemestresEtudiantsDepartEuropeAvion.get());
        }
        if (G9G39sumFormationContinueDepartEuropeAvion.get() != 0f) {
            resultat.setFormationContinueDistancemoyenneEuropeAvion(AG9AG39sumFormationContinueDistanceEuropeAvion.get() / 2 / G9G39sumFormationContinueDepartEuropeAvion.get()); //A dupliquer
        }

        // Distance Moyenne Europe Train
        if (D9D39sumProsDepartEuropeTrain.get() != 0f) {
            resultat.setProsDistanceMoyenneEuropeTrain(AD9AD39sumProsDistanceEuropeTrain.get() / 2 /D9D39sumProsDepartEuropeTrain.get());
        }
        if (F9F39sumStagesEtudiantsDepartEuropeTrain.get() != 0f) {
            resultat.setStagesDistanceMoyenneEuropeTrain(AF9AF39sumStagesEtudiantsDistanceEuropeTrain.get() / 2/ F9F39sumStagesEtudiantsDepartEuropeTrain.get());
        }
        if (H9H39sumSemestresEtudiantsDepartEuropeTrain.get() != 0f) {
            resultat.setSemestresDistanceMoyenneEuropeTrain(AH9AH39sumSemestresEtudiantsDistanceEuropeTrain.get() / 2 / H9H39sumSemestresEtudiantsDepartEuropeTrain.get());
        }
        if (H9H39sumFormationContinueDepartEuropeTrain.get() != 0f) {
            resultat.setFormationContinueDistanceMoyenneEuropeTrain(AH9AH39sumFormationContinueDistanceEuropeTrain.get() / 2 / H9H39sumFormationContinueDepartEuropeTrain.get()); //A dupliquer
        }

        // Intensite carbone des déplacements gCO2e/km
        if (Z9Z124sumDistancePros.get() != 0f) {
            resultat.setProsIntensiteCarboneDesDeplacemeents_gCo2eParKm(P9P124sumEgesPros.get() * 1000000f / Z9Z124sumDistancePros.get());
        }
        if (AA9AA124sumDistanceStagesEtudiants.get() != 0f) {
            resultat.setStagesIntensiteCarboneDesDeplacemeents_gCo2eParKm(Q9Q124sumEgesStagesEtudiants.get() * 1000000f / AA9AA124sumDistanceStagesEtudiants.get());
        }
        if (AB9AB124sumDistanceSemestresEtudiants.get() != 0f) {
            resultat.setSemestresIntensiteCarboneDesDeplacemeents_gCo2eParKm(R9R124sumEgesSemestresEtudiants.get() * 1000000f / AB9AB124sumDistanceSemestresEtudiants.get());
        }
        if (AB9AB124sumDistanceFormationContinue.get() != 0f) {
            resultat.setFormationContinueIntensiteCarboneDesDeplacemeents_gCo2eParKm(R9R124sumEgesFormationContinue.get() * 1000000f / AB9AB124sumDistanceFormationContinue.get()); //A dupliquer
        }

        // Intensité carbone des déplacements kgCO2e/départ
        if (C9D124sumProsDepart.get() != 0f) {
            resultat.setProsIntensiteCarboneDesDeplacemeents_kgCo2eParDepart(((P9P124sumEgesPros.get() * 1000f) / 2f) / C9D124sumProsDepart.get());
        }
        if (E9F124sumStagesEtudiantsDepart.get() != 0f) {
            resultat.setStagesIntensiteCarboneDesDeplacemeents_kgCo2eParDepart(((Q9Q124sumEgesStagesEtudiants.get() * 1000f) / 2f) / E9F124sumStagesEtudiantsDepart.get());
        }
        if (G9H124sumSemestresEtudiantsDepart.get() != 0f) {
            resultat.setSemestresIntensiteCarboneDesDeplacemeents_kgCo2eParDepart(((R9R124sumEgesSemestresEtudiants.get() * 1000f) / 2f) / G9H124sumSemestresEtudiantsDepart.get());
        }
        if (G9H124sumFormationContinueDepart.get() != 0f) {
            resultat.setFormationContinueIntensiteCarboneDesDeplacemeents_kgCo2eParDepart(((R9R124sumEgesFormationContinue.get() * 1000f) / 2f) / G9H124sumFormationContinueDepart.get()); //A dupliquer
        }

        return resultat;
    }

    @Override
    public void importVoyagesFromExcel(Long ongletId, MultipartFile file, boolean modeAjout) {

        // Import de l'onglet
        MobInternationalOnglet mobInternationalOngletById = mobInternationalOngletManager.getMobInternationalOngletById(ongletId);

        // Récupération de la liste des voyages
        List<Voyage> voyagesById = mobInternationalOngletById.getVoyages();

        // Si on n'est pas en mode modeAjout, on vide la liste des voyages
        if (!modeAjout) {
            voyagesById.clear();
        }

        // Création d'une map, Destination -> Voyage
        // avec les valeurs des voyages existants en base (byId)
        Map<EnumMobInternationale_Pays, Voyage> voyagesParPays = new HashMap<>();
        if (modeAjout) {
            for (Voyage v : voyagesById) {
                voyagesParPays.put(v.getPays(), v);
            }
        }

        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            // Récupération de la première feuille
            Sheet sheet = workbook.getSheetAt(0);

            // itération sur les lignes de la feuille
            for (int i = 3; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);

                // Vérification si la ligne est vide (si ne comporte aucun trajet, cela ne vérifie pas la colonne des noms de pays)
                if (row == null || isRowEmpty(row)) continue;

                //Récupération de la Destination (pays)
                String nomPaysStr = getCellValueAsString(row.getCell(0));
                EnumMobInternationale_Pays pays = EnumMobInternationale_Pays.fromLibelle(nomPaysStr);
                if (pays == null) {
                    System.err.println("Pays non reconnu : " + nomPaysStr);
                    continue;
                }

                //Récupération des valeurs des trajets pour la destination
                Integer prosAvion = getCellValueAsInteger(row.getCell(5));
                Integer stagesEtudiantsAvion = getCellValueAsInteger(row.getCell(3));
                Integer semestresEtudiantsAvion = getCellValueAsInteger(row.getCell(1));
                Integer formationContinueAvion = getCellValueAsInteger(row.getCell(7)); //A dupliquer si ajout de colonne avec le bon n° de colonne

                Integer stagesEtudiantsTrain = 0;
                Integer semestresEtudiantsTrain = 0;
                Integer prosTrain = 0;
                Integer formationContinueTrain = 0; //A dupliquer si ajout de colonne
                // Si le pays est accessible en train, on récupère les valeurs des trajets en train
                if (pays.getIsAccessibleEnTrain()) {
                    prosTrain = getCellValueAsInteger(row.getCell(6));
                    stagesEtudiantsTrain = getCellValueAsInteger(row.getCell(4));
                    semestresEtudiantsTrain = getCellValueAsInteger(row.getCell(2));
                    formationContinueTrain = getCellValueAsInteger(row.getCell(8)); //A dupliquer si ajout de colonne avec le bon n° de colonne
                }

                // Si nous sommes en mode ajout  et que le pays existe déjà,
                // on met à jour les valeurs en sommant l'excel et les valeurs en base
                if (modeAjout && voyagesParPays.containsKey(pays)) {
                    Voyage existing = voyagesParPays.get(pays);
                    existing.setProsAvion(existing.getProsAvion() + prosAvion);
                    existing.setProsTrain(existing.getProsTrain() + prosTrain);
                    existing.setStagesEtudiantsAvion(existing.getStagesEtudiantsAvion() + stagesEtudiantsAvion);
                    existing.setStagesEtudiantsTrain(existing.getStagesEtudiantsTrain() + stagesEtudiantsTrain);
                    existing.setSemestresEtudiantsAvion(existing.getSemestresEtudiantsAvion() + semestresEtudiantsAvion);
                    existing.setSemestresEtudiantsTrain(existing.getSemestresEtudiantsTrain() + semestresEtudiantsTrain);
                    continue;
                }

                // Si nous ne sommes pas en mode ajout, OU que le pays n'existe pas encore,
                // on crée un nouveau voyage
                Voyage voyage = new Voyage(pays, prosAvion, prosTrain, stagesEtudiantsAvion, stagesEtudiantsTrain, semestresEtudiantsAvion, semestresEtudiantsTrain, formationContinueTrain, formationContinueTrain); // ajouter les valeurs de la nouvelle colonne

                voyagesById.add(voyage);
            }

            // Check des contraintes de validation (s'il n'y a pas de valeurs train si la destination n'est pas accessible en train)
            Set<ConstraintViolation<MobInternationalOnglet>> violations = validator.validate(mobInternationalOngletById);
            if (!violations.isEmpty()) {
                throw new ValidationCustomException(violations);
            }

            mobInternationalOngletManager.save(mobInternationalOngletById);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private String getCellValueAsString(Cell cell) {
        if (cell == null || cell.getCellType() == CellType.BLANK) return "";
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue().trim();
            case NUMERIC -> String.valueOf((int) cell.getNumericCellValue());
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            default -> "";
        };
    }

    private Integer getCellValueAsInteger(Cell cell) {
        if (cell == null || cell.getCellType() == CellType.BLANK) return 0;
        return switch (cell.getCellType()) {
            case NUMERIC -> (int) cell.getNumericCellValue();
            case STRING -> {
                try {
                    yield Integer.parseInt(cell.getStringCellValue().trim());
                } catch (NumberFormatException e) {
                    yield 0;
                }
            }
            default -> 0;
        };
    }

    // Méthode pour vérifier si une ligne est entièrement vide
    private boolean isRowEmpty(Row row) {
        for (int i = 1; i < row.getLastCellNum(); i++) {
            Cell cell = row.getCell(i);
            if (cell != null && cell.getCellType() == CellType.NUMERIC && cell.getCellType() != CellType.BLANK) {
                return false;
            }
        }
        return true;
    }

}
