package tcucl.back_tcucl.service.impl.onglet;

import org.springframework.stereotype.Service;
import tcucl.back_tcucl.dto.onglet.achat.AchatOngletDto;
import tcucl.back_tcucl.dto.onglet.achat.AchatResultatDto;
import tcucl.back_tcucl.entity.facteurEmission.FacteurEmissionParametre;
import tcucl.back_tcucl.entity.onglet.achat.AchatOnglet;
import tcucl.back_tcucl.manager.AchatOngletManager;
import tcucl.back_tcucl.service.AchatOngletService;
import tcucl.back_tcucl.service.FacteurEmissionService;

@Service
public class AchatOngletServiceImpl implements AchatOngletService {

    private final AchatOngletManager achatOngletManager;
    private final FacteurEmissionService facteurEmissionService;

    public AchatOngletServiceImpl(AchatOngletManager achatOngletManager, FacteurEmissionService facteurEmissionService) {
        this.achatOngletManager = achatOngletManager;
        this.facteurEmissionService = facteurEmissionService;
    }

    @Override
    public AchatOnglet getAchatOngletById(Long ongletId) {
        return achatOngletManager.getAchatOngletById(ongletId);
    }

    @Override
    public void updateAchatOngletPartiel(Long ongletId, AchatOngletDto achatOngletDto) {
        achatOngletManager.updateAchatOngletPartiel(ongletId, achatOngletDto);
    }

    @Override
    public AchatResultatDto getAchatResultat(Long ongletId) {
        AchatOnglet onglet = achatOngletManager.getAchatOngletById(ongletId);
        AchatResultatDto resultat = new AchatResultatDto();
        Float facteurEmissionParametre;

        // CONSOMMABLES
        facteurEmissionParametre = facteurEmissionService.findByCategorieAndTypeAndUnite(
                FacteurEmissionParametre.ACHATS_CONSOMMABLES,
                FacteurEmissionParametre.ACHATS_CONSOMMABLES_.PAPIER,
                FacteurEmissionParametre.ACHATS_CONSOMMABLES_.PAPIER_.kgCO2e_nombre_ramette).getFacteurEmission();
        resultat.setPapier_nb(
                onglet.getAchatConsommable().getPapier_nb() * facteurEmissionParametre/1000f);

        facteurEmissionParametre = facteurEmissionService.findByCategorieAndTypeAndUnite(
                FacteurEmissionParametre.ACHATS_CONSOMMABLES,
                FacteurEmissionParametre.ACHATS_CONSOMMABLES_.PAPIER,
                FacteurEmissionParametre.ACHATS_CONSOMMABLES_.PAPIER_.kgCO2e_tonnes).getFacteurEmission();
        resultat.setPapier_T(
                onglet.getAchatConsommable().getPapier_T() * facteurEmissionParametre);

        facteurEmissionParametre = facteurEmissionService.findByCategorieAndTypeAndUnite(
                FacteurEmissionParametre.ACHATS_CONSOMMABLES,
                FacteurEmissionParametre.ACHATS_CONSOMMABLES_.LIVRES,
                FacteurEmissionParametre.ACHATS_CONSOMMABLES_.LIVRES_.kgCO2e_livre).getFacteurEmission();
        resultat.setLivres_nb(
                onglet.getAchatConsommable().getLivres_nb() * facteurEmissionParametre/1000f);

        facteurEmissionParametre = facteurEmissionService.findByCategorieAndTypeAndUnite(
                FacteurEmissionParametre.ACHATS_CONSOMMABLES,
                FacteurEmissionParametre.ACHATS_CONSOMMABLES_.LIVRES,
                FacteurEmissionParametre.ACHATS_CONSOMMABLES_.LIVRES_.kgCO2e_kg).getFacteurEmission();
        resultat.setLivres_T(
                onglet.getAchatConsommable().getLivres_T() * facteurEmissionParametre);

        facteurEmissionParametre = facteurEmissionService.findByCategorieAndType(
                FacteurEmissionParametre.ACHATS_CONSOMMABLES,
                FacteurEmissionParametre.ACHATS_CONSOMMABLES_.CARTON_NEUF).getFacteurEmission();
        resultat.setCartonNeuf_T(
                onglet.getAchatConsommable().getCartonNeuf_T() * facteurEmissionParametre);

        facteurEmissionParametre = facteurEmissionService.findByCategorieAndType(
                FacteurEmissionParametre.ACHATS_CONSOMMABLES,
                FacteurEmissionParametre.ACHATS_CONSOMMABLES_.CARTON_RECYCLE).getFacteurEmission();
        resultat.setCartonRecycle_T(
                onglet.getAchatConsommable().getCartonRecycle_T() * facteurEmissionParametre);

        facteurEmissionParametre = facteurEmissionService.findByCategorieAndType(
                FacteurEmissionParametre.ACHATS_CONSOMMABLES,
                FacteurEmissionParametre.ACHATS_CONSOMMABLES_.PETITES_FOURNITURES).getFacteurEmission();
        resultat.setPetitesFournitures_Eur(
                onglet.getAchatConsommable().getPetitesFournitures_Eur() * facteurEmissionParametre/1000f);

        facteurEmissionParametre = facteurEmissionService.findByCategorieAndType(
                FacteurEmissionParametre.ACHATS_CONSOMMABLES,
                FacteurEmissionParametre.ACHATS_CONSOMMABLES_.NB_FEUILLES_IMPRIMEES_EN_JET_D_ENCRE).getFacteurEmission();
        resultat.setNbFeuillesImprimeesJetEncre_Nb(
                onglet.getAchatConsommable().getNbFeuillesImprimeesJetEncre_Nb() * facteurEmissionParametre/1000f);

        facteurEmissionParametre = facteurEmissionService.findByCategorieAndType(
                FacteurEmissionParametre.ACHATS_CONSOMMABLES,
                FacteurEmissionParametre.ACHATS_CONSOMMABLES_.NB_FEUILLES_IMPRIMEES_EN_TONER).getFacteurEmission();
        resultat.setNbFeuillesImprimeesToner_Nb(
                onglet.getAchatConsommable().getNbFeuillesImprimeesToner_Nb() * facteurEmissionParametre/1000f);

        facteurEmissionParametre = facteurEmissionService.findByCategorieAndType(
                FacteurEmissionParametre.ACHATS_CONSOMMABLES,
                FacteurEmissionParametre.ACHATS_CONSOMMABLES_.SERVICE_PRODUITS_PHARMACEUTIQUE).getFacteurEmission();
        resultat.setProduitsPharmaceutiques_Eur(
                onglet.getAchatConsommable().getProduitsPharmaceutiques_Eur() * facteurEmissionParametre/1000f);

        facteurEmissionParametre = facteurEmissionService.findByCategorieAndType(
                FacteurEmissionParametre.ACHATS_CONSOMMABLES,
                FacteurEmissionParametre.ACHATS_CONSOMMABLES_.SERVICE_PRODUITS_PHARMACEUTIQUE).getFacteurEmission();
        resultat.setProduitsPharmaceutiques_Eur(
                onglet.getAchatConsommable().getProduitsPharmaceutiques_Eur() * facteurEmissionParametre/1000f);

        facteurEmissionParametre = facteurEmissionService.findByCategorieAndType(
                FacteurEmissionParametre.ACHATS_CONSOMMABLES,
                FacteurEmissionParametre.ACHATS_CONSOMMABLES_.SERVICE_AUTRE).getFacteurEmission();
        resultat.setServices_imprimerie_publicite_architecture_ingenierie_maintenance_multi_technique_des_batiments(
                ((onglet.getAchatConsommable().getServices_impPubArchiIngeMaintTechBat() != null) ? onglet.getAchatConsommable().getServices_impPubArchiIngeMaintTechBat() : 0.0f) * facteurEmissionParametre/1000f);

        facteurEmissionParametre = facteurEmissionService.findByCategorieAndType(
                FacteurEmissionParametre.ACHATS_CONSOMMABLES,
                FacteurEmissionParametre.ACHATS_CONSOMMABLES_.SERVICE_ENSEIGNEMENT).getFacteurEmission();
        resultat.setService_Enseignement(
                onglet.getAchatConsommable().getService_Enseignement() * facteurEmissionParametre/1000f);

        facteurEmissionParametre = facteurEmissionService.findByCategorieAndType(
                FacteurEmissionParametre.ACHATS_CONSOMMABLES,
                FacteurEmissionParametre.ACHATS_CONSOMMABLES_.SERVICE_INFORMATIQUE_ELECTRONIQUE_OPTIQUE).getFacteurEmission();
        resultat.setService_Produits_informatiques_electroniques_et_optiques(
                onglet.getAchatConsommable().getService_Produits_informatiques_electroniques_et_optiques() * facteurEmissionParametre/1000f);

        facteurEmissionParametre = facteurEmissionService.findByCategorieAndType(
                FacteurEmissionParametre.ACHATS_CONSOMMABLES,
                FacteurEmissionParametre.ACHATS_CONSOMMABLES_.SERVICE_REPARATION_INSTALLATION).getFacteurEmission();
        resultat.setService_Reparation_et_installation_de_machines_et_d_equipements(
                onglet.getAchatConsommable().getService_Reparation_et_installation_de_machines_et_d_equipements() * facteurEmissionParametre/1000f);

        facteurEmissionParametre = facteurEmissionService.findByCategorieAndType(
                FacteurEmissionParametre.ACHATS_CONSOMMABLES,
                FacteurEmissionParametre.ACHATS_CONSOMMABLES_.SERVICE_TRANSPORT_TERRESTRES).getFacteurEmission();
        resultat.setService_Transport_terrestre(
                onglet.getAchatConsommable().getService_Transport_terrestre() * facteurEmissionParametre/1000f);

        facteurEmissionParametre = facteurEmissionService.findByCategorieAndType(
                FacteurEmissionParametre.ACHATS_CONSOMMABLES,
                FacteurEmissionParametre.ACHATS_CONSOMMABLES_.SERVICE_HEBERGEMENT_RESTAURATION).getFacteurEmission();
        resultat.setService_hebergement_et_restauration(
                onglet.getAchatConsommable().getService_hebergement_et_restauration() * facteurEmissionParametre/1000f);

        facteurEmissionParametre = facteurEmissionService.findByCategorieAndType(
                FacteurEmissionParametre.ACHATS_CONSOMMABLES,
                FacteurEmissionParametre.ACHATS_CONSOMMABLES_.SERVICE_TELECOMMUNICATIONS).getFacteurEmission();
        resultat.setService_de_telecommunications(
                onglet.getAchatConsommable().getService_de_telecommunications() * facteurEmissionParametre/1000f);

        resultat.setTotalPosteAchat(
                resultat.getPapier_nb()
                        + resultat.getPapier_T()
                        + resultat.getLivres_nb()
                        + resultat.getLivres_T()
                        + resultat.getCartonNeuf_T()
                        + resultat.getCartonRecycle_T()
                        + resultat.getPetitesFournitures_Eur()
                        + resultat.getNbFeuillesImprimeesJetEncre_Nb()
                        + resultat.getNbFeuillesImprimeesToner_Nb()
                        + resultat.getProduitsPharmaceutiques_Eur()
                        + resultat.getServices_imprimerie_publicite_architecture_ingenierie_maintenance_multi_technique_des_batiments()
                        + resultat.getService_Enseignement()
                        + resultat.getService_Produits_informatiques_electroniques_et_optiques()
                        + resultat.getService_Reparation_et_installation_de_machines_et_d_equipements()
                        + resultat.getService_Transport_terrestre()
                        + resultat.getService_hebergement_et_restauration()
                        + resultat.getService_de_telecommunications()
        );

        // TEXTILES
        facteurEmissionParametre = facteurEmissionService.findByCategorieAndType(
                FacteurEmissionParametre.TEXTILES,
                FacteurEmissionParametre.TEXTILES_.CHEMISE).getFacteurEmission();
        resultat.setChemise_nb(
                onglet.getAchatTextile().getChemise_nb() * facteurEmissionParametre /1000f);

        facteurEmissionParametre = facteurEmissionService.findByCategorieAndType(
                FacteurEmissionParametre.TEXTILES,
                FacteurEmissionParametre.TEXTILES_.POLAIRE).getFacteurEmission();
        resultat.setPolaire_nb(
                onglet.getAchatTextile().getPolaire_nb() * facteurEmissionParametre /1000f);

        facteurEmissionParametre = facteurEmissionService.findByCategorieAndType(
                FacteurEmissionParametre.TEXTILES,
                FacteurEmissionParametre.TEXTILES_.PULL_ACRYLIQUE).getFacteurEmission();
        resultat.setPull_Acrylique_nb(
                onglet.getAchatTextile().getPull_Acrylique_nb() * facteurEmissionParametre /1000f);

        facteurEmissionParametre = facteurEmissionService.findByCategorieAndType(
                FacteurEmissionParametre.TEXTILES,
                FacteurEmissionParametre.TEXTILES_.PULL_COTON).getFacteurEmission();
        resultat.setPull_Coton_nb(
                onglet.getAchatTextile().getPull_Coton_nb() * facteurEmissionParametre /1000f);

        facteurEmissionParametre = facteurEmissionService.findByCategorieAndType(
                FacteurEmissionParametre.TEXTILES,
                FacteurEmissionParametre.TEXTILES_.T_SHIRT_POLYESTER).getFacteurEmission();
        resultat.setT_shirt_polyester_nb(
                onglet.getAchatTextile().getT_shirt_polyester_nb() * facteurEmissionParametre /1000f);

        facteurEmissionParametre = facteurEmissionService.findByCategorieAndType(
                FacteurEmissionParametre.TEXTILES,
                FacteurEmissionParametre.TEXTILES_.JEAN).getFacteurEmission();
        resultat.setJean_nb(
                onglet.getAchatTextile().getJean_nb() * facteurEmissionParametre /1000f);

        facteurEmissionParametre = facteurEmissionService.findByCategorieAndType(
                FacteurEmissionParametre.TEXTILES,
                FacteurEmissionParametre.TEXTILES_.SWEAT).getFacteurEmission();
        resultat.setSweat_nb(
                onglet.getAchatTextile().getSweat_nb() * facteurEmissionParametre /1000f);

        facteurEmissionParametre = facteurEmissionService.findByCategorieAndType(
                FacteurEmissionParametre.TEXTILES,
                FacteurEmissionParametre.TEXTILES_.VESTE_ANORAK).getFacteurEmission();
        resultat.setVeste_Anorak_nb(
                onglet.getAchatTextile().getVeste_Anorak_nb() * facteurEmissionParametre /1000f);

        facteurEmissionParametre = facteurEmissionService.findByCategorieAndType(
                FacteurEmissionParametre.TEXTILES,
                FacteurEmissionParametre.TEXTILES_.MANTEAU).getFacteurEmission();
        resultat.setManteau_nb(
                onglet.getAchatTextile().getManteau_nb() * facteurEmissionParametre /1000f);

        facteurEmissionParametre = facteurEmissionService.findByCategorieAndType(
                FacteurEmissionParametre.TEXTILES,
                FacteurEmissionParametre.TEXTILES_.CHAUSSURE).getFacteurEmission();
        resultat.setChaussure_nb(
                onglet.getAchatTextile().getChaussure_nb() * facteurEmissionParametre /1000f);

        resultat.setTotalPosteTextile(
                resultat.getChemise_nb()
                        + resultat.getPolaire_nb()
                        + resultat.getPull_Acrylique_nb()
                        + resultat.getPull_Coton_nb()
                        + resultat.getT_shirt_polyester_nb()
                        + resultat.getJean_nb()
                        + resultat.getSweat_nb()
                        + resultat.getVeste_Anorak_nb()
                        + resultat.getManteau_nb()
                        + resultat.getChaussure_nb()
        );

        // RESTAURATION
        switch (onglet.getAchatRestauration().getMethodeCalcul()){
            case METHODE_RAPIDE -> {
                facteurEmissionParametre = facteurEmissionService.findByCategorieAndType(
                        FacteurEmissionParametre.RESTAURATION_METHODE_RAPIDE,
                        FacteurEmissionParametre.RESTAURATION_METHODE_RAPIDE_.ALIMENTATION_REGIME_CLASSIQUE).getFacteurEmission();
                resultat.setMethodeRapideNombrePersonnesServiesRegimeClassique(
                        onglet.getAchatRestauration().getMethodeRapideNombrePersonnesServiesRegimeClassique() * facteurEmissionParametre /1000f);

                facteurEmissionParametre = facteurEmissionService.findByCategorieAndType(
                        FacteurEmissionParametre.RESTAURATION_METHODE_RAPIDE,
                        FacteurEmissionParametre.RESTAURATION_METHODE_RAPIDE_.ALIMENTATION_REGIME_FLEXITARIEN).getFacteurEmission();
                resultat.setMethodeRapideNombrePersonnesServiesRegimeFlexitarien(
                        onglet.getAchatRestauration().getMethodeRapideNombrePersonnesServiesRegimeFlexitarien() * facteurEmissionParametre /1000f);

                resultat.setTotalPosteRestauration(
                        resultat.getMethodeRapideNombrePersonnesServiesRegimeClassique()
                                + resultat.getMethodeRapideNombrePersonnesServiesRegimeFlexitarien()
                );
            }
            case METHODE_INTERMEDIAIRE -> {
                facteurEmissionParametre = facteurEmissionService.findByCategorieAndType(
                        FacteurEmissionParametre.RESTAURATION_METHODE_INTERMEDIAIRE,
                        FacteurEmissionParametre.RESTAURATION_METHODE_INTERMEDIAIRE_.NOMBRE_DE_REPAS_SERVIS_DOMINANTE_ANIMALE_BOEUF).getFacteurEmission();
                resultat.setNombreRepasServisDominanteAnimaleBoeuf(
                        onglet.getAchatRestauration().getNombreRepasServisDominanteAnimaleBoeuf() * facteurEmissionParametre /1000f);

                facteurEmissionParametre = facteurEmissionService.findByCategorieAndType(
                        FacteurEmissionParametre.RESTAURATION_METHODE_INTERMEDIAIRE,
                        FacteurEmissionParametre.RESTAURATION_METHODE_INTERMEDIAIRE_.NOMBRE_DE_REPAS_SERVIS_DOMINANTE_ANIMALE_POULET).getFacteurEmission();
                resultat.setNombreRepasServisDominanteAnimalePoulet(
                        onglet.getAchatRestauration().getNombreRepasServisDominanteAnimalePoulet() * facteurEmissionParametre /1000f);

                facteurEmissionParametre = facteurEmissionService.findByCategorieAndType(
                        FacteurEmissionParametre.RESTAURATION_METHODE_INTERMEDIAIRE,
                        FacteurEmissionParametre.RESTAURATION_METHODE_INTERMEDIAIRE_.NOMBRE_DE_REPAS_SERVIS_DOMINANTE_VEGETALE_BOEUF).getFacteurEmission();
                resultat.setNombreRepasServisDominanteVegetaleBoeuf(
                        onglet.getAchatRestauration().getNombreRepasServisDominanteVegetaleBoeuf() * facteurEmissionParametre /1000f);

                facteurEmissionParametre = facteurEmissionService.findByCategorieAndType(
                        FacteurEmissionParametre.RESTAURATION_METHODE_INTERMEDIAIRE,
                        FacteurEmissionParametre.RESTAURATION_METHODE_INTERMEDIAIRE_.NOMBRE_DE_REPAS_SERVIS_DOMINANTE_VEGETALE_POULET).getFacteurEmission();
                resultat.setNombreRepasServisDominanteVegetalePoulet(
                        onglet.getAchatRestauration().getNombreRepasServisDominanteVegetalePoulet() * facteurEmissionParametre /1000f);

                facteurEmissionParametre = facteurEmissionService.findByCategorieAndType(
                        FacteurEmissionParametre.RESTAURATION_METHODE_INTERMEDIAIRE,
                        FacteurEmissionParametre.RESTAURATION_METHODE_INTERMEDIAIRE_.NOMBRE_DE_REPAS_SERVIS_DOMINANTE_CLASSIQUE_BOEUF).getFacteurEmission();
                resultat.setNombreRepasServisDominanteClassiqueBoeuf(
                        onglet.getAchatRestauration().getNombreRepasServisDominanteClassiqueBoeuf() * facteurEmissionParametre /1000f);

                facteurEmissionParametre = facteurEmissionService.findByCategorieAndType(
                        FacteurEmissionParametre.RESTAURATION_METHODE_INTERMEDIAIRE,
                        FacteurEmissionParametre.RESTAURATION_METHODE_INTERMEDIAIRE_.NOMBRE_DE_REPAS_SERVIS_DOMINANTE_CLASSIQUE_POULET).getFacteurEmission();
                resultat.setNombreRepasServisDominanteClassiquePoulet(
                        onglet.getAchatRestauration().getNombreRepasServisDominanteClassiquePoulet() * facteurEmissionParametre /1000f);

                facteurEmissionParametre = facteurEmissionService.findByCategorieAndType(
                        FacteurEmissionParametre.RESTAURATION_METHODE_INTERMEDIAIRE,
                        FacteurEmissionParametre.RESTAURATION_METHODE_INTERMEDIAIRE_.NOMBRE_DE_REPAS_SERVIS_REPAS_MOYEN).getFacteurEmission();
                resultat.setNombreRepasServisRepasMoyen(
                        onglet.getAchatRestauration().getNombreRepasServisRepasMoyen() * facteurEmissionParametre /1000f);

                facteurEmissionParametre = facteurEmissionService.findByCategorieAndType(
                        FacteurEmissionParametre.RESTAURATION_METHODE_INTERMEDIAIRE,
                        FacteurEmissionParametre.RESTAURATION_METHODE_INTERMEDIAIRE_.NOMBRE_DE_REPAS_SERVIS_REPAS_VEGETARIEN).getFacteurEmission();
                resultat.setNombreRepasServisRepasVegetarien(
                        onglet.getAchatRestauration().getNombreRepasServisRepasVegetarien() * facteurEmissionParametre /1000f);

                resultat.setTotalPosteRestauration(
                        resultat.getNombreRepasServisDominanteAnimaleBoeuf()
                                + resultat.getNombreRepasServisDominanteAnimalePoulet()
                                + resultat.getNombreRepasServisDominanteVegetaleBoeuf()
                                + resultat.getNombreRepasServisDominanteVegetalePoulet()
                                + resultat.getNombreRepasServisDominanteClassiqueBoeuf()
                                + resultat.getNombreRepasServisDominanteClassiquePoulet()
                                + resultat.getNombreRepasServisRepasMoyen()
                                + resultat.getNombreRepasServisRepasVegetarien()
                );
            }
            case METHODE_DETAILLE -> {
                facteurEmissionParametre = facteurEmissionService.findByCategorieAndType(
                        FacteurEmissionParametre.RESTAURATION_METHODE_DETAILLEE,
                        FacteurEmissionParametre.RESTAURATION_METHODE_DETAILLEE_.BOEUF_AGNEAU_POULET).getFacteurEmission();
                resultat.setBoeufAgneauMouton_Tonnes(
                        onglet.getAchatRestauration().getBoeufAgneauMouton_Tonnes() * facteurEmissionParametre);

                facteurEmissionParametre = facteurEmissionService.findByCategorieAndType(
                        FacteurEmissionParametre.RESTAURATION_METHODE_DETAILLEE,
                        FacteurEmissionParametre.RESTAURATION_METHODE_DETAILLEE_.POULET).getFacteurEmission();
                resultat.setPoulet_Tonnes(
                        onglet.getAchatRestauration().getPoulet_Tonnes() * facteurEmissionParametre);

                facteurEmissionParametre = facteurEmissionService.findByCategorieAndType(
                        FacteurEmissionParametre.RESTAURATION_METHODE_DETAILLEE,
                        FacteurEmissionParametre.RESTAURATION_METHODE_DETAILLEE_.CAFE).getFacteurEmission();
                resultat.setCafe_Tonnes(
                        onglet.getAchatRestauration().getCafe_Tonnes() * facteurEmissionParametre);

                facteurEmissionParametre = facteurEmissionService.findByCategorieAndType(
                        FacteurEmissionParametre.RESTAURATION_METHODE_DETAILLEE,
                        FacteurEmissionParametre.RESTAURATION_METHODE_DETAILLEE_.CHOCOLAT).getFacteurEmission();
                resultat.setChocolat_Tonnes(
                        onglet.getAchatRestauration().getChocolat_Tonnes() * facteurEmissionParametre);

                facteurEmissionParametre = facteurEmissionService.findByCategorieAndType(
                        FacteurEmissionParametre.RESTAURATION_METHODE_DETAILLEE,
                        FacteurEmissionParametre.RESTAURATION_METHODE_DETAILLEE_.BEURRE).getFacteurEmission();
                resultat.setBeurre_Tonnes(
                        onglet.getAchatRestauration().getBeurre_Tonnes() * facteurEmissionParametre);

                facteurEmissionParametre = facteurEmissionService.findByCategorieAndType(
                        FacteurEmissionParametre.RESTAURATION_METHODE_DETAILLEE,
                        FacteurEmissionParametre.RESTAURATION_METHODE_DETAILLEE_.VIANDE_MOYENNE).getFacteurEmission();
                resultat.setViandesMoyenne_Tonnes(
                        onglet.getAchatRestauration().getViandesMoyenne_Tonnes() * facteurEmissionParametre);

                facteurEmissionParametre = facteurEmissionService.findByCategorieAndType(
                        FacteurEmissionParametre.RESTAURATION_METHODE_DETAILLEE,
                        FacteurEmissionParametre.RESTAURATION_METHODE_DETAILLEE_.PRODUITS_SUCRES_MOYENNE).getFacteurEmission();
                resultat.setProduitsSucresMoyenne_Tonnes(
                        onglet.getAchatRestauration().getProduitsSucresMoyenne_Tonnes() * facteurEmissionParametre);

                facteurEmissionParametre = facteurEmissionService.findByCategorieAndType(
                        FacteurEmissionParametre.RESTAURATION_METHODE_DETAILLEE,
                        FacteurEmissionParametre.RESTAURATION_METHODE_DETAILLEE_.POISSONS_MOYENNE).getFacteurEmission();
                resultat.setPoissonsMoyenne_Tonnes(
                        onglet.getAchatRestauration().getPoissonsMoyenne_Tonnes() * facteurEmissionParametre);

                facteurEmissionParametre = facteurEmissionService.findByCategorieAndType(
                        FacteurEmissionParametre.RESTAURATION_METHODE_DETAILLEE,
                        FacteurEmissionParametre.RESTAURATION_METHODE_DETAILLEE_.FROMAGES_MOYENNE).getFacteurEmission();
                resultat.setFromagesMoyenne_Tonnes(
                        onglet.getAchatRestauration().getFromagesMoyenne_Tonnes() * facteurEmissionParametre);

                facteurEmissionParametre = facteurEmissionService.findByCategorieAndType(
                        FacteurEmissionParametre.RESTAURATION_METHODE_DETAILLEE,
                        FacteurEmissionParametre.RESTAURATION_METHODE_DETAILLEE_.OLEAGINEUX_MOYENNE).getFacteurEmission();
                resultat.setOleagineuxMoyenne_Tonnes(
                        onglet.getAchatRestauration().getOleagineuxMoyenne_Tonnes() * facteurEmissionParametre);

                facteurEmissionParametre = facteurEmissionService.findByCategorieAndType(
                        FacteurEmissionParametre.RESTAURATION_METHODE_DETAILLEE,
                        FacteurEmissionParametre.RESTAURATION_METHODE_DETAILLEE_.MATIERES_GRASSES_MOYENNE).getFacteurEmission();
                resultat.setMatieresGrassesMoyenne_Tonnes(
                        onglet.getAchatRestauration().getMatieresGrassesMoyenne_Tonnes() * facteurEmissionParametre);

                facteurEmissionParametre = facteurEmissionService.findByCategorieAndType(
                        FacteurEmissionParametre.RESTAURATION_METHODE_DETAILLEE,
                        FacteurEmissionParametre.RESTAURATION_METHODE_DETAILLEE_.BOISSONS_MOYENNE).getFacteurEmission();
                resultat.setBoissonsMoyenne_Tonnes(
                        onglet.getAchatRestauration().getBoissonsMoyenne_Tonnes() * facteurEmissionParametre);

                facteurEmissionParametre = facteurEmissionService.findByCategorieAndType(
                        FacteurEmissionParametre.RESTAURATION_METHODE_DETAILLEE,
                        FacteurEmissionParametre.RESTAURATION_METHODE_DETAILLEE_.OEUFS_MOYENNE).getFacteurEmission();
                resultat.setOeufs_Tonnes(
                        onglet.getAchatRestauration().getOeufs_Tonnes() * facteurEmissionParametre);

                facteurEmissionParametre = facteurEmissionService.findByCategorieAndType(
                        FacteurEmissionParametre.RESTAURATION_METHODE_DETAILLEE,
                        FacteurEmissionParametre.RESTAURATION_METHODE_DETAILLEE_.CEREALES_MOYENNE).getFacteurEmission();
                resultat.setCerealesMoyenne_Tonnes(
                        onglet.getAchatRestauration().getCerealesMoyenne_Tonnes() * facteurEmissionParametre);

                facteurEmissionParametre = facteurEmissionService.findByCategorieAndType(
                        FacteurEmissionParametre.RESTAURATION_METHODE_DETAILLEE,
                        FacteurEmissionParametre.RESTAURATION_METHODE_DETAILLEE_.LEGUMES_MOYENNE).getFacteurEmission();
                resultat.setLegumesMoyenne_Tonnes(
                        onglet.getAchatRestauration().getLegumesMoyenne_Tonnes() * facteurEmissionParametre);

                facteurEmissionParametre = facteurEmissionService.findByCategorieAndType(
                        FacteurEmissionParametre.RESTAURATION_METHODE_DETAILLEE,
                        FacteurEmissionParametre.RESTAURATION_METHODE_DETAILLEE_.FRUITS_MOYENNE).getFacteurEmission();
                resultat.setFruitsMoyenne_Tonnes(
                        onglet.getAchatRestauration().getFruitsMoyenne_Tonnes() * facteurEmissionParametre);

                facteurEmissionParametre = facteurEmissionService.findByCategorieAndType(
                        FacteurEmissionParametre.RESTAURATION_METHODE_DETAILLEE,
                        FacteurEmissionParametre.RESTAURATION_METHODE_DETAILLEE_.LEGUMINEUSE_MOYENNE).getFacteurEmission();
                resultat.setLegumineuseMoyenne_Tonnes(
                        onglet.getAchatRestauration().getLegumineuseMoyenne_Tonnes() * facteurEmissionParametre);

                resultat.setTotalPosteRestauration(
                        resultat.getBoeufAgneauMouton_Tonnes()
                                + resultat.getPoulet_Tonnes()
                                + resultat.getCafe_Tonnes()
                                + resultat.getChocolat_Tonnes()
                                + resultat.getBeurre_Tonnes()
                                + resultat.getViandesMoyenne_Tonnes()
                                + resultat.getProduitsSucresMoyenne_Tonnes()
                                + resultat.getPoissonsMoyenne_Tonnes()
                                + resultat.getFromagesMoyenne_Tonnes()
                                + resultat.getOleagineuxMoyenne_Tonnes()
                                + resultat.getMatieresGrassesMoyenne_Tonnes()
                                + resultat.getBoissonsMoyenne_Tonnes()
                                + resultat.getOeufs_Tonnes()
                                + resultat.getCerealesMoyenne_Tonnes()
                                + resultat.getLegumesMoyenne_Tonnes()
                                + resultat.getFruitsMoyenne_Tonnes()
                                + resultat.getLegumineuseMoyenne_Tonnes()
                );
            }
        }

        return resultat;

    }
}
