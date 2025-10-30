package tcucl.back_tcucl.service.impl.onglet;

import org.springframework.stereotype.Service;
import tcucl.back_tcucl.dto.onglet.numerique.EquipementNumeriqueDto;
import tcucl.back_tcucl.dto.onglet.numerique.NumeriqueOngletDto;
import tcucl.back_tcucl.dto.onglet.numerique.NumeriqueResultatDto;
import tcucl.back_tcucl.entity.facteurEmission.FacteurEmissionParametre;
import tcucl.back_tcucl.entity.onglet.numerique.NumeriqueOnglet;
import tcucl.back_tcucl.entity.onglet.numerique.EquipementNumerique;
import tcucl.back_tcucl.manager.NumeriqueOngletManager;
import tcucl.back_tcucl.service.FacteurEmissionService;
import tcucl.back_tcucl.service.NumeriqueOngletService;

import java.util.concurrent.atomic.AtomicReference;

@Service
public class NumeriqueOngletServiceImpl implements NumeriqueOngletService {

    private final NumeriqueOngletManager numeriqueOngletManager;
    private final FacteurEmissionService facteurEmissionService;

    public NumeriqueOngletServiceImpl(NumeriqueOngletManager numeriqueOngletManager, FacteurEmissionService facteurEmissionService) {
        this.numeriqueOngletManager = numeriqueOngletManager;
        this.facteurEmissionService = facteurEmissionService;
    }

    @Override
    public NumeriqueOnglet getNumeriqueOngletById(Long ongletId) {
        return numeriqueOngletManager.getNumeriqueOngletById(ongletId);
    }

    @Override
    public EquipementNumerique getEquipementNumeriqueById(Long ongletId, Long equipementId) {
        return numeriqueOngletManager.getEquipementNumeriqueById(ongletId, equipementId);
    }

    @Override
    public void updateNumeriqueOngletPartiel(Long ongletId, NumeriqueOngletDto numeriqueOngletDto) {
        numeriqueOngletManager.updateNumeriqueOngletPartiel(ongletId, numeriqueOngletDto);
    }

    @Override
    public void ajouterEquipementNumerique(Long ongletId, EquipementNumeriqueDto equipementNumeriqueDto) {
        numeriqueOngletManager.ajouterEquipementNumerique(ongletId, equipementNumeriqueDto);
    }

    @Override
    public void supprimerEquipementNumerique(Long ongletId, Long equipementId) {
        numeriqueOngletManager.supprimerEquipementNumerique(ongletId, equipementId);
    }

    @Override
    public void updateEquipementNumeriquePartiel(Long ongletId, Long equipementId, EquipementNumeriqueDto equipementNumeriqueDto) {
        numeriqueOngletManager.updateEquipementNumeriquePartiel(ongletId, equipementId, equipementNumeriqueDto);
    }

    @Override
    public NumeriqueResultatDto getNumeriqueResultat(Long ongletId) {
        NumeriqueOnglet onglet = numeriqueOngletManager.getNumeriqueOngletById(ongletId);
        NumeriqueResultatDto resultat = new NumeriqueResultatDto();

        AtomicReference<Float> sumEgesEquipementsNumeriques = new AtomicReference<>(0f);

        onglet.getEquipementNumeriqueList().forEach(equipement -> {
            float egesEquipement;
            if (equipement.getNombre() > 0) {
                Float facteurEmission;
                if (equipement.getEmissionsGesPrecisesConnues()) {
                    facteurEmission = equipement.getEmissionsReellesParProduitKgCO2e();
                } else {
                    facteurEmission = facteurEmissionService.findByCategorieAndType(
                            FacteurEmissionParametre.NUMERIQUE,
                            equipement.getEquipement().getLibelle()
                    ).getFacteurEmission();
                }

                if (equipement.getDureeAmortissement() <= 0) {
                    throw new IllegalArgumentException("La durée d'amortissement doit être supérieure à 0.");
                }
                egesEquipement = facteurEmission * equipement.getNombre() / (equipement.getDureeAmortissement() * 1000);
            } else {
                egesEquipement = 0f;
            }

            Float finalEgesEquipement = egesEquipement;
            sumEgesEquipementsNumeriques.updateAndGet(v -> v + finalEgesEquipement);
            resultat.addResultatEquipementNumerique(
                    equipement.getId(),
                    egesEquipement
            );
        });

        resultat.setMethodeSimplifieeResultat((sumEgesEquipementsNumeriques.get() / 0.81f) * 0.19f);
        float trafficCloudReel = onglet.getTraficCloudUtilisateur() + (onglet.getTraficTipUtilisateur() / 0.15f);
        Float partTraficFranceEtranger = onglet.getPartTraficFranceEtranger();
        Float empreinteCarboneDataCenter = (trafficCloudReel * partTraficFranceEtranger * 0.007f * 493f +
                trafficCloudReel * (1 - partTraficFranceEtranger)
                        * 0.069f * 57.1f) / 1000000f;
        resultat.setEmpreinteCarboneDataCenter(
                empreinteCarboneDataCenter
        );

        if(trafficCloudReel == 0){
            resultat.setEmpreinteCarboneDataCenterEtReseaux(0f);
            resultat.setTotalNumerique(sumEgesEquipementsNumeriques.get() + resultat.getMethodeSimplifieeResultat());
        }else{
            Float reseau = (sumEgesEquipementsNumeriques.get()/0.81f)*0.05f;
            Float phaseAmont = (1-partTraficFranceEtranger)*trafficCloudReel*0.0669f*2.58f*0.15f*213.8f/1000000f;
            resultat.setEmpreinteCarboneDataCenterEtReseaux(empreinteCarboneDataCenter + phaseAmont + reseau);
            resultat.setTotalNumerique(sumEgesEquipementsNumeriques.get() + resultat.getEmpreinteCarboneDataCenterEtReseaux());
        }

        return resultat;
    }
}
