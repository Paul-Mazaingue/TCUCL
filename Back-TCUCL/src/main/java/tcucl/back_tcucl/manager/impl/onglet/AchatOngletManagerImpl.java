package tcucl.back_tcucl.manager.impl.onglet;

import org.springframework.stereotype.Component;
import tcucl.back_tcucl.dto.onglet.achat.AchatConsommableDto;
import tcucl.back_tcucl.dto.onglet.achat.AchatOngletDto;
import tcucl.back_tcucl.dto.onglet.achat.AchatRestaurationDto;
import tcucl.back_tcucl.dto.onglet.achat.AchatTextileDto;
import tcucl.back_tcucl.entity.onglet.achat.AchatOnglet;
import tcucl.back_tcucl.entity.onglet.achat.AchatConsommable;
import tcucl.back_tcucl.entity.onglet.achat.AchatRestauration;
import tcucl.back_tcucl.entity.onglet.achat.AchatTextile;
import tcucl.back_tcucl.exceptionPersonnalisee.OngletNonTrouveIdException;
import tcucl.back_tcucl.manager.AchatOngletManager;
import tcucl.back_tcucl.repository.onglet.AchatOngletRepository;

@Component
public class AchatOngletManagerImpl implements AchatOngletManager {

    private final AchatOngletRepository achatOngletRepository;

    public AchatOngletManagerImpl(AchatOngletRepository achatOngletRepository) {
        this.achatOngletRepository = achatOngletRepository;
    }


    @Override
    public AchatOnglet getAchatOngletById(Long ongletId) {
        return achatOngletRepository.findById(ongletId).orElseThrow(() -> new OngletNonTrouveIdException("AchatOnglet", ongletId));
    }


    @Override
    public void updateAchatOngletPartiel(Long ongletId, AchatOngletDto achatOngletDto) {
        AchatOnglet achatOnglet = getAchatOngletById(ongletId);

        if (achatOngletDto.getEstTermine() != null) {
            achatOnglet.setEstTermine(achatOngletDto.getEstTermine());
        }
        if (achatOngletDto.getNote() != null) {
            achatOnglet.setNote(achatOngletDto.getNote());
        }

        // --- AchatConsommable ---
        if (achatOngletDto.getAchatConsommable() != null) {
            AchatConsommable achatConsommable = achatOnglet.getAchatConsommable();
            if (achatConsommable == null) {
                achatConsommable = new AchatConsommable();
                achatOnglet.setAchatConsommable(achatConsommable);
            }
            AchatConsommableDto achatConsommableDto = achatOngletDto.getAchatConsommable();
            if (achatConsommableDto.getPapier_T() != null)
                achatConsommable.setPapier_T(achatConsommableDto.getPapier_T());
            if (achatConsommableDto.getPapier_nb() != null)
                achatConsommable.setPapier_nb(achatConsommableDto.getPapier_nb());
            if (achatConsommableDto.getLivres_T() != null)
                achatConsommable.setLivres_T(achatConsommableDto.getLivres_T());
            if (achatConsommableDto.getLivres_nb() != null)
                achatConsommable.setLivres_nb(achatConsommableDto.getLivres_nb());
            if (achatConsommableDto.getCartonNeuf_T() != null)
                achatConsommable.setCartonNeuf_T(achatConsommableDto.getCartonNeuf_T());
            if (achatConsommableDto.getCartonRecycle_T() != null)
                achatConsommable.setCartonRecycle_T(achatConsommableDto.getCartonRecycle_T());
            if (achatConsommableDto.getPetitesFournitures_Eur() != null)
                achatConsommable.setPetitesFournitures_Eur(achatConsommableDto.getPetitesFournitures_Eur());
            if (achatConsommableDto.getNbFeuillesImprimeesJetEncre_Nb() != null)
                achatConsommable.setNbFeuillesImprimeesJetEncre_Nb(achatConsommableDto.getNbFeuillesImprimeesJetEncre_Nb());
            if (achatConsommableDto.getNbFeuillesImprimeesToner_Nb() != null)
                achatConsommable.setNbFeuillesImprimeesToner_Nb(achatConsommableDto.getNbFeuillesImprimeesToner_Nb());
            if (achatConsommableDto.getProduitsPharmaceutiques_Eur() != null)
                achatConsommable.setProduitsPharmaceutiques_Eur(achatConsommableDto.getProduitsPharmaceutiques_Eur());
            if (achatConsommableDto.getServices_imprimerie_publicite_architecture_ingenierie_maintenance_multi_technique_des_batiments() != null)
                achatConsommable.setServices_impPubArchiIngeMaintTechBat(
                        achatConsommableDto.getServices_imprimerie_publicite_architecture_ingenierie_maintenance_multi_technique_des_batiments());
            if (achatConsommableDto.getService_Enseignement() != null)
                achatConsommable.setService_Enseignement(achatConsommableDto.getService_Enseignement());
            if (achatConsommableDto.getService_Produits_informatiques_electroniques_et_optiques() != null)
                achatConsommable.setService_Produits_informatiques_electroniques_et_optiques(achatConsommableDto.getService_Produits_informatiques_electroniques_et_optiques());
            if(achatConsommableDto.getService_Reparation_et_installation_de_machines_et_d_equipements() != null)
                achatConsommable.setService_Reparation_et_installation_de_machines_et_d_equipements(achatConsommableDto.getService_Reparation_et_installation_de_machines_et_d_equipements());
            if (achatConsommableDto.getService_Transport_terrestre() != null)
                achatConsommable.setService_Transport_terrestre(achatConsommableDto.getService_Transport_terrestre());
            if (achatConsommableDto.getService_hebergement_et_restauration() != null)
                achatConsommable.setService_hebergement_et_restauration(achatConsommableDto.getService_hebergement_et_restauration());
            if (achatConsommableDto.getService_de_telecommunications() != null)
                achatConsommable.setService_de_telecommunications(achatConsommableDto.getService_de_telecommunications());
        }

                    // --- AchatRestauration ---
        if (achatOngletDto.getAchatRestauration() != null) {
            AchatRestauration achatRestauration = achatOnglet.getAchatRestauration();
            if (achatRestauration == null) {
                achatRestauration = new AchatRestauration();
                achatOnglet.setAchatRestauration(achatRestauration);
            }
            AchatRestaurationDto achatRestaurationDto = achatOngletDto.getAchatRestauration();
            if (achatRestaurationDto.getMethode() != null)
                achatRestauration.setMethodeCalcul(achatRestaurationDto.getMethode());
            if (achatRestaurationDto.getMethodeRapideNombrePersonnesServiesRegimeClassique() != null)
                achatRestauration.setMethodeRapideNombrePersonnesServiesRegimeClassique(achatRestaurationDto.getMethodeRapideNombrePersonnesServiesRegimeClassique());
            if (achatRestaurationDto.getMethodeRapideNombrePersonnesServiesRegimeFlexitarien() != null)
                achatRestauration.setMethodeRapideNombrePersonnesServiesRegimeFlexitarien(achatRestaurationDto.getMethodeRapideNombrePersonnesServiesRegimeFlexitarien());
            if (achatRestaurationDto.getNombreRepasServisDominanteAnimaleBoeuf() != null)
                achatRestauration.setNombreRepasServisDominanteAnimaleBoeuf(achatRestaurationDto.getNombreRepasServisDominanteAnimaleBoeuf());
            if (achatRestaurationDto.getNombreRepasServisDominanteAnimalePoulet() != null)
                achatRestauration.setNombreRepasServisDominanteAnimalePoulet(achatRestaurationDto.getNombreRepasServisDominanteAnimalePoulet());
            if (achatRestaurationDto.getNombreRepasServisDominanteVegetaleBoeuf() != null)
                achatRestauration.setNombreRepasServisDominanteVegetaleBoeuf(achatRestaurationDto.getNombreRepasServisDominanteVegetaleBoeuf());
            if (achatRestaurationDto.getNombreRepasServisDominanteVegetalePoulet() != null)
                achatRestauration.setNombreRepasServisDominanteVegetalePoulet(achatRestaurationDto.getNombreRepasServisDominanteVegetalePoulet());
            if (achatRestaurationDto.getNombreRepasServisDominanteClassiqueBoeuf() != null)
                achatRestauration.setNombreRepasServisDominanteClassiqueBoeuf(achatRestaurationDto.getNombreRepasServisDominanteClassiqueBoeuf());
            if (achatRestaurationDto.getNombreRepasServisDominanteClassiquePoulet() != null)
                achatRestauration.setNombreRepasServisDominanteClassiquePoulet(achatRestaurationDto.getNombreRepasServisDominanteClassiquePoulet());
            if (achatRestaurationDto.getNombreRepasServisRepasMoyen() != null)
                achatRestauration.setNombreRepasServisRepasMoyen(achatRestaurationDto.getNombreRepasServisRepasMoyen());
            if (achatRestaurationDto.getNombreRepasServisRepasVegetarien() != null)
                achatRestauration.setNombreRepasServisRepasVegetarien(achatRestaurationDto.getNombreRepasServisRepasVegetarien());
            if (achatRestaurationDto.getBoeufAgneauMouton_Tonnes() != null)
                achatRestauration.setBoeufAgneauMouton_Tonnes(achatRestaurationDto.getBoeufAgneauMouton_Tonnes());
            if (achatRestaurationDto.getPoulet_Tonnes() != null)
                achatRestauration.setPoulet_Tonnes(achatRestaurationDto.getPoulet_Tonnes());
            if (achatRestaurationDto.getCafe_Tonnes() != null)
                achatRestauration.setCafe_Tonnes(achatRestaurationDto.getCafe_Tonnes());
            if (achatRestaurationDto.getChocolat_Tonnes() != null)
                achatRestauration.setChocolat_Tonnes(achatRestaurationDto.getChocolat_Tonnes());
            if (achatRestaurationDto.getBeurre_Tonnes() != null)
                achatRestauration.setBeurre_Tonnes(achatRestaurationDto.getBeurre_Tonnes());
            if (achatRestaurationDto.getViandesMoyenne_Tonnes() != null)
                achatRestauration.setViandesMoyenne_Tonnes(achatRestaurationDto.getViandesMoyenne_Tonnes());
            if (achatRestaurationDto.getProduitsSucresMoyenne_Tonnes() != null)
                achatRestauration.setProduitsSucresMoyenne_Tonnes(achatRestaurationDto.getProduitsSucresMoyenne_Tonnes());
            if (achatRestaurationDto.getPoissonsMoyenne_Tonnes() != null)
                achatRestauration.setPoissonsMoyenne_Tonnes(achatRestaurationDto.getPoissonsMoyenne_Tonnes());
            if (achatRestaurationDto.getFromagesMoyenne_Tonnes() != null)
                achatRestauration.setFromagesMoyenne_Tonnes(achatRestaurationDto.getFromagesMoyenne_Tonnes());
            if (achatRestaurationDto.getOleagineuxMoyenne_Tonnes() != null)
                achatRestauration.setOleagineuxMoyenne_Tonnes(achatRestaurationDto.getOleagineuxMoyenne_Tonnes());
            if (achatRestaurationDto.getMatieresGrassesMoyenne_Tonnes() != null)
                achatRestauration.setMatieresGrassesMoyenne_Tonnes(achatRestaurationDto.getMatieresGrassesMoyenne_Tonnes());
            if (achatRestaurationDto.getBoissonsMoyenne_Tonnes() != null)
                achatRestauration.setBoissonsMoyenne_Tonnes(achatRestaurationDto.getBoissonsMoyenne_Tonnes());
            if (achatRestaurationDto.getOeufs_Tonnes() != null)
                achatRestauration.setOeufs_Tonnes(achatRestaurationDto.getOeufs_Tonnes());
            if (achatRestaurationDto.getCerealesMoyenne_Tonnes() != null)
                achatRestauration.setCerealesMoyenne_Tonnes(achatRestaurationDto.getCerealesMoyenne_Tonnes());
            if (achatRestaurationDto.getLegumesMoyenne_Tonnes() != null)
                achatRestauration.setLegumesMoyenne_Tonnes(achatRestaurationDto.getLegumesMoyenne_Tonnes());
            if (achatRestaurationDto.getFruitsMoyenne_Tonnes() != null)
                achatRestauration.setFruitsMoyenne_Tonnes(achatRestaurationDto.getFruitsMoyenne_Tonnes());
            if (achatRestaurationDto.getLegumineuseMoyenne_Tonnes() != null)
                achatRestauration.setLegumineuseMoyenne_Tonnes(achatRestaurationDto.getLegumineuseMoyenne_Tonnes());
        }

        // --- AchatTextile ---
        if (achatOngletDto.getAchatTextile() != null) {
            AchatTextile achatTextile = achatOnglet.getAchatTextile();
            if (achatTextile == null) {
                achatTextile = new AchatTextile();
                achatOnglet.setAchatTextile(achatTextile);
            }
            AchatTextileDto achatTextileDto = achatOngletDto.getAchatTextile();
            if (achatTextileDto.getChemise_nb() != null) achatTextile.setChemise_nb(achatTextileDto.getChemise_nb());
            if (achatTextileDto.getPolaire_nb() != null) achatTextile.setPolaire_nb(achatTextileDto.getPolaire_nb());
            if (achatTextileDto.getPull_Acrylique_nb() != null)
                achatTextile.setPull_Acrylique_nb(achatTextileDto.getPull_Acrylique_nb());
            if (achatTextileDto.getPull_Coton_nb() != null)
                achatTextile.setPull_Coton_nb(achatTextileDto.getPull_Coton_nb());
            if (achatTextileDto.getT_shirt_polyester_nb() != null)
                achatTextile.setT_shirt_polyester_nb(achatTextileDto.getT_shirt_polyester_nb());
            if (achatTextileDto.getJean_nb() != null) achatTextile.setJean_nb(achatTextileDto.getJean_nb());
            if (achatTextileDto.getSweat_nb() != null) achatTextile.setSweat_nb(achatTextileDto.getSweat_nb());
            if (achatTextileDto.getVeste_Anorak_nb() != null)
                achatTextile.setVeste_Anorak_nb(achatTextileDto.getVeste_Anorak_nb());
            if (achatTextileDto.getManteau_nb() != null) achatTextile.setManteau_nb(achatTextileDto.getManteau_nb());
            if (achatTextileDto.getChaussure_nb() != null)
                achatTextile.setChaussure_nb(achatTextileDto.getChaussure_nb());
        }

        achatOngletRepository.save(achatOnglet); // Hibernate mettra à jour en cascade
    }

}
