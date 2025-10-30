package tcucl.back_tcucl.service.impl.onglet;

import org.springframework.stereotype.Service;
import tcucl.back_tcucl.dto.onglet.autreImmobilisation.AutreImmobilisationOngletDto;
import tcucl.back_tcucl.dto.onglet.autreImmobilisation.AutreImmobilisationResultatDto;
import tcucl.back_tcucl.entity.facteurEmission.FacteurEmission;
import tcucl.back_tcucl.entity.facteurEmission.FacteurEmissionParametre;
import tcucl.back_tcucl.entity.onglet.AutreImmobilisationOnglet;
import tcucl.back_tcucl.manager.AutreImmobilisationOngletManager;
import tcucl.back_tcucl.service.AutreImmobilisationOngletService;
import tcucl.back_tcucl.service.FacteurEmissionService;

@Service
public class AutreImmobilisationOngletServiceImpl implements AutreImmobilisationOngletService {

    private final AutreImmobilisationOngletManager autreImmobilisationOngletManager;
    private final FacteurEmissionService facteurEmissionService;


    public AutreImmobilisationOngletServiceImpl(AutreImmobilisationOngletManager autreImmobilisationOngletManager, FacteurEmissionService facteurEmissionService) {
        this.autreImmobilisationOngletManager = autreImmobilisationOngletManager;
        this.facteurEmissionService = facteurEmissionService;
    }

    @Override
    public AutreImmobilisationOnglet getAutreImmobilisationOngletById(Long ongletId) {
        return autreImmobilisationOngletManager.getAutreImmobilisationOngletById(ongletId);
    }

    @Override
    public void updateAutreImmobilisationOngletPartiel(Long ongletId, AutreImmobilisationOngletDto autreImmobilisationOngletDto) {
        autreImmobilisationOngletManager.updateAutreImmobilisationOngletPartiel(ongletId, autreImmobilisationOngletDto);
    }

    @Override
    public AutreImmobilisationResultatDto getAutreImmobilisationResultat(Long ongletId) {
        AutreImmobilisationOnglet autreImmobilisationOnglet = autreImmobilisationOngletManager.getAutreImmobilisationOngletById(ongletId);
        AutreImmobilisationResultatDto autreImmobilisationResultatDto = new AutreImmobilisationResultatDto();

        if (Boolean.TRUE.equals(autreImmobilisationOnglet.getInstallationComplete_IsEmissionGESConnues())){
            autreImmobilisationResultatDto.setInstallationComplete(autreImmobilisationOnglet.getInstallationComplete_EmissionDeGes());
        } else {
            autreImmobilisationResultatDto.setInstallationComplete(0f);
        }

        if (Boolean.TRUE.equals(autreImmobilisationOnglet.getPanneaux_IsEmissionGESConnues())){
            autreImmobilisationResultatDto.setPanneaux(autreImmobilisationOnglet.getPanneaux_EmissionDeGes());
        } else {
            FacteurEmission facteurEmission = facteurEmissionService.findByCategorieAndType(FacteurEmissionParametre.PHOTOVOLTAIQUE, "Panneaux");
            autreImmobilisationResultatDto.setPanneaux((facteurEmission.getFacteurEmission() * autreImmobilisationOnglet.getPanneaux_PuissanceTotale() * 25 / (autreImmobilisationOnglet.getPanneaux_DureeDeVie() * 1000)));
        }

        if (Boolean.TRUE.equals(autreImmobilisationOnglet.getOnduleur_IsEmissionGESConnues())){
            autreImmobilisationResultatDto.setOnduleurs(autreImmobilisationOnglet.getOnduleur_EmissionDeGes());
        } else {
            FacteurEmission facteurEmission = facteurEmissionService.findByCategorieAndType(FacteurEmissionParametre.PHOTOVOLTAIQUE, "Onduleurs");
            autreImmobilisationResultatDto.setOnduleurs((facteurEmission.getFacteurEmission() * autreImmobilisationOnglet.getOnduleur_PuissanceTotale() * 25 / (autreImmobilisationOnglet.getOnduleur_DureeDeVie() * 1000)));
        }

        autreImmobilisationResultatDto.setTotalPostePhotovoltaique(
                autreImmobilisationResultatDto.getInstallationComplete()
                        + autreImmobilisationResultatDto.getPanneaux()
                        + autreImmobilisationResultatDto.getOnduleurs());

        if (Boolean.TRUE.equals(autreImmobilisationOnglet.getGroupesElectrogenes_IsEmissionConnue())){
            autreImmobilisationResultatDto.setGroupesElectrogenes(autreImmobilisationOnglet.getGroupesElectrogenes_EmissionReelle() * autreImmobilisationOnglet.getGroupesElectrogenes_Nombre() / (autreImmobilisationOnglet.getGroupesElectrogenes_DureeAmortissement() * 1000));
        } else {
            FacteurEmission facteurEmission = facteurEmissionService.findByCategorieAndType(FacteurEmissionParametre.AUTRES_IMMOBILISATIONS, "Groupes électrogènes");
            autreImmobilisationResultatDto.setGroupesElectrogenes((facteurEmission.getFacteurEmission() * autreImmobilisationOnglet.getGroupesElectrogenes_Nombre() * autreImmobilisationOnglet.getGroupesElectrogenes_PoidsDuProduit() / (autreImmobilisationOnglet.getGroupesElectrogenes_DureeAmortissement() * 1000)));
        }

        if (Boolean.TRUE.equals(autreImmobilisationOnglet.getMoteurElectrique_IsEmissionConnue())){
            autreImmobilisationResultatDto.setMoteurElectrique(autreImmobilisationOnglet.getMoteurElectrique_EmissionReelle() * autreImmobilisationOnglet.getMoteurElectrique_Nombre() / (autreImmobilisationOnglet.getMoteurElectrique_DureeAmortissement() * 1000));
        } else {
            FacteurEmission facteurEmission = facteurEmissionService.findByCategorieAndType(FacteurEmissionParametre.AUTRES_IMMOBILISATIONS, "Moteur électrique");
            autreImmobilisationResultatDto.setMoteurElectrique((facteurEmission.getFacteurEmission() * autreImmobilisationOnglet.getMoteurElectrique_Nombre() * autreImmobilisationOnglet.getMoteurElectrique_PoidsDuProduit() / (autreImmobilisationOnglet.getMoteurElectrique_DureeAmortissement() * 1000)));
        }

        if (Boolean.TRUE.equals(autreImmobilisationOnglet.getAutresMachinesKg_IsEmissionConnue())){
            autreImmobilisationResultatDto.setAutresMachinesEnKgDeMachine(autreImmobilisationOnglet.getAutresMachinesKg_EmissionReelle() * autreImmobilisationOnglet.getAutresMachinesKg_Nombre() / (autreImmobilisationOnglet.getAutresMachinesKg_DureeAmortissement() * 1000));
        } else {
            FacteurEmission facteurEmission = facteurEmissionService.findByCategorieAndType(FacteurEmissionParametre.AUTRES_IMMOBILISATIONS, "Autres machines en kg de machines");
            autreImmobilisationResultatDto.setAutresMachinesEnKgDeMachine((facteurEmission.getFacteurEmission() * autreImmobilisationOnglet.getAutresMachinesKg_Nombre() * autreImmobilisationOnglet.getAutresMachinesKg_PoidsDuProduit() / (autreImmobilisationOnglet.getAutresMachinesKg_DureeAmortissement() * 1000)));
        }

        if (Boolean.TRUE.equals(autreImmobilisationOnglet.getAutresMachinesEur_IsEmissionConnue())){
            autreImmobilisationResultatDto.setAutresMachineEnEurosDachats(autreImmobilisationOnglet.getAutresMachinesEur_EmissionReelle() * autreImmobilisationOnglet.getAutresMachinesEur_Nombre() / (autreImmobilisationOnglet.getAutresMachinesEur_DureeAmortissement() * 1000));
        } else {
            FacteurEmission facteurEmission = facteurEmissionService.findByCategorieAndType(FacteurEmissionParametre.AUTRES_IMMOBILISATIONS, "Autres machines en € d'achats");
            autreImmobilisationResultatDto.setAutresMachineEnEurosDachats((facteurEmission.getFacteurEmission() * autreImmobilisationOnglet.getAutresMachinesEur_Nombre() / (autreImmobilisationOnglet.getAutresMachinesEur_DureeAmortissement() * 1000)));
        }

        autreImmobilisationResultatDto.setTotalPosteBatiment(
                autreImmobilisationResultatDto.getGroupesElectrogenes()
                        + autreImmobilisationResultatDto.getMoteurElectrique()
                        + autreImmobilisationResultatDto.getAutresMachinesEnKgDeMachine()
                        + autreImmobilisationResultatDto.getAutresMachineEnEurosDachats());


        return autreImmobilisationResultatDto;
    }
}
