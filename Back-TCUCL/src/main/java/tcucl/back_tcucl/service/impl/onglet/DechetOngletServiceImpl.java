package tcucl.back_tcucl.service.impl.onglet;

import org.springframework.stereotype.Service;
import tcucl.back_tcucl.dto.onglet.dechet.DechetOngletDto;
import tcucl.back_tcucl.dto.onglet.dechet.DechetResultatDto;
import tcucl.back_tcucl.entity.facteurEmission.FacteurEmissionParametre;
import tcucl.back_tcucl.entity.onglet.dechet.Dechet;
import tcucl.back_tcucl.entity.onglet.dechet.DechetOnglet;
import tcucl.back_tcucl.manager.DechetOngletManager;
import tcucl.back_tcucl.service.DechetOngletService;
import tcucl.back_tcucl.service.FacteurEmissionService;

@Service
public class DechetOngletServiceImpl implements DechetOngletService {

    private final DechetOngletManager dechetOngletManager;
    private final FacteurEmissionService facteurEmissionService;

    public DechetOngletServiceImpl(DechetOngletManager dechetOngletManager, FacteurEmissionService facteurEmissionService) {
        this.dechetOngletManager = dechetOngletManager;
        this.facteurEmissionService = facteurEmissionService;
    }

    @Override
    public DechetOnglet getDechetOngletById(Long ongletId) {
        return dechetOngletManager.getDechetOngletById(ongletId);
    }

    @Override
    public void updateDechetOngletPartiel(Long ongletId, DechetOngletDto dechetOngletDto) {
        dechetOngletManager.updateDechetOngletPartiel(ongletId, dechetOngletDto);
    }

    @Override
    public DechetResultatDto getDechetResultat(Long ongletId) {
        DechetOnglet onglet = dechetOngletManager.getDechetOngletById(ongletId);
        DechetResultatDto resultatDto = new DechetResultatDto();

        Dechet orduresMenageres = onglet.getOrdures_menageres();
        Float facteurEmissionProduit = facteurEmissionService.findByCategorieAndTypeAndUnite(
                FacteurEmissionParametre.ORDURES_MENAGERES,
                FacteurEmissionParametre.ORDURES_MENAGERES_.DEFAUT,
                FacteurEmissionParametre.ORDURES_MENAGERES_.DEFAUT_.PRODUIT
        ).getFacteurEmission();
        Float facteurEmissionEvite = facteurEmissionService.findByCategorieAndTypeAndUnite(
                FacteurEmissionParametre.ORDURES_MENAGERES,
                FacteurEmissionParametre.ORDURES_MENAGERES_.DEFAUT,
                FacteurEmissionParametre.ORDURES_MENAGERES_.DEFAUT_.EVITE
        ).getFacteurEmission();
        Float orduresMenageresProduit = orduresMenageres.getQuantiteTonne() * facteurEmissionProduit / 1000f;
        Float orduresMenageresEvite = orduresMenageres.getQuantiteTonne() * facteurEmissionEvite / 1000f;

        Dechet cartons = onglet.getCartons();
        facteurEmissionProduit = facteurEmissionService.findByCategorieAndTypeAndUnite(
                FacteurEmissionParametre.CARTONS,
                cartons.getTraitement().getLibelle(),
                FacteurEmissionParametre.ORDURES_MENAGERES_.DEFAUT_.PRODUIT
        ).getFacteurEmission();
        facteurEmissionEvite = facteurEmissionService.findByCategorieAndTypeAndUnite(
                FacteurEmissionParametre.CARTONS,
                cartons.getTraitement().getLibelle(),
                FacteurEmissionParametre.ORDURES_MENAGERES_.DEFAUT_.EVITE
        ).getFacteurEmission();
        Float cartonsProduit = cartons.getQuantiteTonne() * facteurEmissionProduit / 1000f;
        Float cartonsEvite = cartons.getQuantiteTonne() * facteurEmissionEvite / 1000f;

        Dechet verre = onglet.getVerre();
        facteurEmissionProduit = facteurEmissionService.findByCategorieAndTypeAndUnite(
                FacteurEmissionParametre.VERRE,
                verre.getTraitement().getLibelle(),
                FacteurEmissionParametre.ORDURES_MENAGERES_.DEFAUT_.PRODUIT
        ).getFacteurEmission();
        facteurEmissionEvite = facteurEmissionService.findByCategorieAndTypeAndUnite(
                FacteurEmissionParametre.VERRE,
                verre.getTraitement().getLibelle(),
                FacteurEmissionParametre.ORDURES_MENAGERES_.DEFAUT_.EVITE
        ).getFacteurEmission();
        Float verreProduit = verre.getQuantiteTonne() * facteurEmissionProduit / 1000f;
        Float verreEvite = verre.getQuantiteTonne() * facteurEmissionEvite / 1000f;

        Dechet metaux = onglet.getMetaux();
        facteurEmissionProduit = facteurEmissionService.findByCategorieAndTypeAndUnite(
                FacteurEmissionParametre.METAUX,
                metaux.getTraitement().getLibelle(),
                FacteurEmissionParametre.ORDURES_MENAGERES_.DEFAUT_.PRODUIT
        ).getFacteurEmission();
        facteurEmissionEvite = facteurEmissionService.findByCategorieAndTypeAndUnite(
                FacteurEmissionParametre.METAUX,
                metaux.getTraitement().getLibelle(),
                FacteurEmissionParametre.ORDURES_MENAGERES_.DEFAUT_.EVITE
        ).getFacteurEmission();
        Float metauxProduit = metaux.getQuantiteTonne() * facteurEmissionProduit / 1000f;
        Float metauxEvite = metaux.getQuantiteTonne() * facteurEmissionEvite / 1000f;

        Dechet textile = onglet.getTextile();
        facteurEmissionProduit = facteurEmissionService.findByCategorieAndTypeAndUnite(
                FacteurEmissionParametre.DECHET_TEXTILE,
                FacteurEmissionParametre.DECHET_TEXTILE_.DEFAUT,
                FacteurEmissionParametre.ORDURES_MENAGERES_.DEFAUT_.PRODUIT
        ).getFacteurEmission();
        facteurEmissionEvite = facteurEmissionService.findByCategorieAndTypeAndUnite(
                FacteurEmissionParametre.DECHET_TEXTILE,
                FacteurEmissionParametre.DECHET_TEXTILE_.DEFAUT,
                FacteurEmissionParametre.ORDURES_MENAGERES_.DEFAUT_.EVITE
        ).getFacteurEmission();
        Float textileProduit = textile.getQuantiteTonne() * facteurEmissionProduit / 1000f;
        Float textileEvite = textile.getQuantiteTonne() * facteurEmissionEvite / 1000f;

        resultatDto.setOrdures_menageres(orduresMenageresProduit);
        resultatDto.setCartons(cartonsProduit);
        resultatDto.setVerre(verreProduit);
        resultatDto.setMetaux(metauxProduit);
        resultatDto.setTextile(textileProduit);

        resultatDto.setTotalProduit(
                orduresMenageresProduit + cartonsProduit + verreProduit + metauxProduit + textileProduit
        );
        resultatDto.setTotalEvite(
                orduresMenageresEvite + cartonsEvite + verreEvite + metauxEvite + textileEvite
        );

        return resultatDto;
    }

}
