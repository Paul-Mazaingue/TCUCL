package tcucl.back_tcucl.service.impl.onglet;

import org.springframework.stereotype.Service;
import tcucl.back_tcucl.dto.onglet.energie.EnergieOngletDto;
import tcucl.back_tcucl.dto.onglet.energie.EnergieResultatDto;
import tcucl.back_tcucl.entity.facteurEmission.FacteurEmission;
import tcucl.back_tcucl.entity.facteurEmission.FacteurEmissionParametre;
import tcucl.back_tcucl.entity.onglet.batiment.BatimentExistantOuNeufConstruit;
import tcucl.back_tcucl.entity.onglet.batiment.BatimentImmobilisationMobilierOnglet;
import tcucl.back_tcucl.entity.onglet.energie.EnergieOnglet;
import tcucl.back_tcucl.entity.onglet.energie.enums.EnumEnergie_NomReseauVille;
import tcucl.back_tcucl.entity.onglet.energie.enums.EnumEnergie_UniteBois;
import tcucl.back_tcucl.entity.onglet.energie.enums.EnumEnergie_UniteFioul;
import tcucl.back_tcucl.entity.onglet.energie.enums.EnumEnergie_UniteGaz;
import tcucl.back_tcucl.manager.EnergieOngletManager;
import tcucl.back_tcucl.service.EnergieOngletService;
import tcucl.back_tcucl.service.FacteurEmissionService;

@Service
public class EnergieOngletServiceImpl implements EnergieOngletService {

    private final EnergieOngletManager energieOngletManager;
    private final FacteurEmissionService facteurEmissionService;

    public EnergieOngletServiceImpl(EnergieOngletManager energieOngletManager, FacteurEmissionService facteurEmissionService) {
        this.energieOngletManager = energieOngletManager;
        this.facteurEmissionService = facteurEmissionService;
    }

    @Override
    public EnergieOnglet getEnergieOngletById(Long ongletId) {
        return energieOngletManager.getEnergieOngletById(ongletId);
    }

    @Override
    public void updateEnergieOngletPartiel(Long ongletId, EnergieOngletDto energieOngletDto) {
        energieOngletManager.updateEnergieOngletPartiel(ongletId, energieOngletDto);
    }

    @Override
    public EnergieResultatDto getEnergieResultat(Long ongletId) {
        EnergieOnglet energieOnglet = energieOngletManager.getEnergieOngletById(ongletId);
        EnergieResultatDto energieResultatDto = new EnergieResultatDto();

        FacteurEmission facteurEmission = facteurEmissionService.findByCategorieAndTypeAndUnite(FacteurEmissionParametre.ENERGIE, FacteurEmissionParametre.ENERGIE_.GAZ, energieOnglet.getUniteGaz().getLibelle());
        energieResultatDto.setConsoGaz((facteurEmission.getFacteurEmission() * energieOnglet.getConsoGaz()) / 1000);

        facteurEmission = facteurEmissionService.findByCategorieAndTypeAndUnite(FacteurEmissionParametre.ENERGIE, FacteurEmissionParametre.ENERGIE_.FIOUL, energieOnglet.getUniteFioul().getLibelle());
        energieResultatDto.setConsoFioul((facteurEmission.getFacteurEmission() * energieOnglet.getConsoFioul()) / 1000);

        facteurEmission = facteurEmissionService.findByCategorieAndTypeAndUnite(FacteurEmissionParametre.ENERGIE, FacteurEmissionParametre.ENERGIE_.BOIS_GRANULE_FRANCAIS, energieOnglet.getUniteBois().getLibelle());
        energieResultatDto.setConsoBois((facteurEmission.getFacteurEmission() * energieOnglet.getConsoBois()) / 1000);

        if (energieOnglet.getNomReseauVille() == EnumEnergie_NomReseauVille.LILLE) {
            facteurEmission = facteurEmissionService.findByCategorieAndTypeAndUnite(FacteurEmissionParametre.ENERGIE, FacteurEmissionParametre.ENERGIE_.RESEAU_DE_CHALEUR_LILLE, FacteurEmissionParametre.ENERGIE_.RESEAU_DE_CHALEUR_LILLE_.MWh_PCS);
            energieResultatDto.setConsoReseauVille((facteurEmission.getFacteurEmission() * energieOnglet.getConsoReseauVille()) / 1000);
        } else if (energieOnglet.getNomReseauVille() == EnumEnergie_NomReseauVille.LAMBERSAART) {
            facteurEmission = facteurEmissionService.findByCategorieAndTypeAndUnite(FacteurEmissionParametre.ENERGIE, FacteurEmissionParametre.ENERGIE_.RESEAU_DE_CHALEUR_LAMBERSART, FacteurEmissionParametre.ENERGIE_.RESEAU_DE_CHALEUR_LAMBERSART_.MWh_PCS);
            energieResultatDto.setConsoReseauVille((facteurEmission.getFacteurEmission() * energieOnglet.getConsoReseauVille()) / 1000);
        } else if (energieOnglet.getNomReseauVille() == EnumEnergie_NomReseauVille.LOMME) {
            facteurEmission = facteurEmissionService.findByCategorieAndTypeAndUnite(FacteurEmissionParametre.ENERGIE, FacteurEmissionParametre.ENERGIE_.RESEAU_DE_CHALEUR_LOMME_CAPINGHEM, FacteurEmissionParametre.ENERGIE_.RESEAU_DE_CHALEUR_LOMME_CAPINGHEM_.MWh_PCS);
            energieResultatDto.setConsoReseauVille((facteurEmission.getFacteurEmission() * energieOnglet.getConsoReseauVille()) / 1000);
        } else if (energieOnglet.getNomReseauVille() == EnumEnergie_NomReseauVille.ROUBAIX) {
            facteurEmission = facteurEmissionService.findByCategorieAndTypeAndUnite(FacteurEmissionParametre.ENERGIE, FacteurEmissionParametre.ENERGIE_.RESEAU_DE_CHALEUR_ROUBAIX, FacteurEmissionParametre.ENERGIE_.RESEAU_DE_CHALEUR_ROUBAIX_.MWh_PCS);
            energieResultatDto.setConsoReseauVille((facteurEmission.getFacteurEmission() * energieOnglet.getConsoReseauVille()) / 1000);
        }

        facteurEmission = facteurEmissionService.findByCategorieAndTypeAndUnite(FacteurEmissionParametre.ENERGIE, FacteurEmissionParametre.ENERGIE_.ELECTRICITE_USAGE_CHAUFFAGE, FacteurEmissionParametre.ENERGIE_.ELECTRICITE_USAGE_CHAUFFAGE_.MWh_PCS);
        energieResultatDto.setConsoElecChauffage((facteurEmission.getFacteurEmission() * energieOnglet.getConsoElecChauffage()) / 1000);

        facteurEmission = facteurEmissionService.findByCategorieAndTypeAndUnite(FacteurEmissionParametre.ENERGIE, FacteurEmissionParametre.ENERGIE_.ELECTRICITE_TOUS_USAGE, FacteurEmissionParametre.ENERGIE_.ELECTRICITE_TOUS_USAGE_.MWh_PCS);
        energieResultatDto.setConsoElecSpecifique((facteurEmission.getFacteurEmission() * energieOnglet.getConsoElecSpecifique()) / 1000);

        facteurEmission = facteurEmissionService.findByCategorieAndTypeAndUnite(FacteurEmissionParametre.ENERGIE, FacteurEmissionParametre.ENERGIE_.EAU, FacteurEmissionParametre.ENERGIE_.EAU_.M3);
        energieResultatDto.setConsoEau((facteurEmission.getFacteurEmission() * energieOnglet.getConsoEau()) / 1000);

        energieResultatDto.setTotalPosteFluides(
                energieResultatDto.getConsoGaz() +
                        energieResultatDto.getConsoFioul() +
                        energieResultatDto.getConsoBois() +
                        energieResultatDto.getConsoReseauVille() +
                        energieResultatDto.getConsoElecChauffage() +
                        energieResultatDto.getConsoElecSpecifique() +
                        energieResultatDto.getConsoEau());

        Float consoEnergieGaz = 0F;
        if (energieOnglet.getUniteGaz() == EnumEnergie_UniteGaz.M3) {
            consoEnergieGaz = energieOnglet.getConsoGaz() / 100;
        } else if (energieOnglet.getUniteGaz() == EnumEnergie_UniteGaz.MWH_PCS) {
            consoEnergieGaz = energieOnglet.getConsoGaz();
        }


        Float consoEnergieFioul = 0F;
        if ((energieOnglet.getUniteFioul() == EnumEnergie_UniteFioul.TONNE) || (energieOnglet.getUniteFioul() == EnumEnergie_UniteFioul.M3)) {
            consoEnergieFioul = energieOnglet.getConsoFioul() * 10;
        } else if (energieOnglet.getUniteFioul() == EnumEnergie_UniteFioul.MWH_PCS) {
            consoEnergieFioul = energieOnglet.getConsoFioul();
        }

        Float consoEnergieBois = 0F;
        if (energieOnglet.getUniteBois() == EnumEnergie_UniteBois.TONNE) {
            consoEnergieBois = energieOnglet.getConsoBois() * 4.5F;
        } else if (energieOnglet.getUniteBois() == EnumEnergie_UniteBois.MWH_PCS) {
            consoEnergieBois = energieOnglet.getConsoBois();
        }

        Float consoEnergieReseauVille = energieOnglet.getConsoReseauVille();
        Float consoElecChauffage = energieOnglet.getConsoElecChauffage();
        Float consoElecSpecifique = energieOnglet.getConsoElecSpecifique();

        energieResultatDto.setConsoEnergieFinale(consoEnergieGaz +
                consoEnergieFioul +
                consoEnergieBois +
                consoEnergieReseauVille +
                consoElecChauffage +
                consoElecSpecifique);

        energieResultatDto.setconsoEnergieSpecifique(energieOnglet.getConsoElecSpecifique() );
        energieResultatDto.setConsoEnergieChauffage(energieResultatDto.getConsoEnergieFinale() - energieResultatDto.getconsoEnergieSpecifique());

        float surfaceBatiment = energieOnglet.getOngletDeClass(BatimentImmobilisationMobilierOnglet.class)
                .getBatimentExistantOuNeufConstruits()
                .stream()
                .map(BatimentExistantOuNeufConstruit::getSurfaceEnM2) // pour éviter les NullPointerException
                .filter(surfaceEnM2 -> surfaceEnM2 != null)
                .reduce(0f, Float::sum);
        if (surfaceBatiment != 0) {
            energieResultatDto.setConsoEnergieFinaleParM2(energieResultatDto.getConsoEnergieFinale() * 1000 / surfaceBatiment);
            energieResultatDto.setConsoEnergiePrimaireParM2(((energieOnglet.getConsoElecChauffage() + energieOnglet.getConsoElecSpecifique()) * 2.3f + (energieResultatDto.getConsoEnergieChauffage() - energieOnglet.getConsoElecChauffage())) * 1000f / surfaceBatiment);
            energieResultatDto.setIntensiteCarboneParM2((energieResultatDto.getConsoGaz()
                    + energieResultatDto.getConsoFioul()
                    + energieResultatDto.getConsoBois()
                    + energieResultatDto.getConsoReseauVille()
                    + energieResultatDto.getConsoElecChauffage()
                    + energieResultatDto.getConsoElecSpecifique()) * 1000f / surfaceBatiment);
        } else {
            energieResultatDto.setConsoEnergieFinaleParM2(0F);
            energieResultatDto.setConsoEnergiePrimaireParM2(0F);
            energieResultatDto.setIntensiteCarboneParM2(0F);
        }
        energieResultatDto.setSurfaceTotaleBatiments(surfaceBatiment);

        energieResultatDto.setIntensiteCarboneMoyenne((energieResultatDto.getConsoGaz()
                + energieResultatDto.getConsoFioul()
                + energieResultatDto.getConsoBois()
                + energieResultatDto.getConsoReseauVille()
                + energieResultatDto.getConsoElecChauffage()
                + energieResultatDto.getConsoElecSpecifique()) * 1000000f / (energieResultatDto.getConsoEnergieFinale() * 1000f));

        return energieResultatDto;
    }
}
