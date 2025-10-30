package tcucl.back_tcucl.manager;

import tcucl.back_tcucl.dto.onglet.batimentImmobilisationMobilier.BatimentExistantOuNeufConstruitDto;
import tcucl.back_tcucl.dto.onglet.batimentImmobilisationMobilier.BatimentImmobilisationMobilierOngletDto;
import tcucl.back_tcucl.dto.onglet.batimentImmobilisationMobilier.EntretienCourantDto;
import tcucl.back_tcucl.dto.onglet.batimentImmobilisationMobilier.MobilierElectromenagerDto;
import tcucl.back_tcucl.entity.onglet.batiment.BatimentImmobilisationMobilierOnglet;
import tcucl.back_tcucl.entity.onglet.batiment.BatimentExistantOuNeufConstruit;
import tcucl.back_tcucl.entity.onglet.batiment.EntretienCourant;
import tcucl.back_tcucl.entity.onglet.batiment.MobilierElectromenager;

public interface BatimentImmobilisationMobilierOngletManager {

    BatimentImmobilisationMobilierOnglet getBatimentImmobilisationMobilierOngletById(Long ongletId);

    void updateBatimentImmobilisationMobilierOnglet(Long ongletId, BatimentImmobilisationMobilierOngletDto batimentImmobilisationMobilierOngletDto);

    BatimentExistantOuNeufConstruit getBatimentById(Long ongletId, Long batimentId);

    void ajouterBatiment(BatimentImmobilisationMobilierOnglet batimentImmobilisationMobilierOnglet, BatimentExistantOuNeufConstruit batimentExistantOuNeufConstruit);

    void supprimerBatimentFromOnglet(Long ongletId, Long batimentId);

    void updateBatimentPartiel(Long ongletId, Long batimentId, BatimentExistantOuNeufConstruitDto batimentExistantOuNeufConstruitDto);

    EntretienCourant getEntretienCourantById(Long ongletId, Long entretienCourantId);

    void ajouterEntretienCourant(BatimentImmobilisationMobilierOnglet batimentImmobilisationMobilierOnglet, EntretienCourant entretienCourant);

    void supprimerEntretienCourantFromOnglet(Long ongletId, Long entretienCourantId);

    void updateEntretienCourantPartiel(Long ongletId, Long entretienCourantId, EntretienCourantDto entretienCourantDto);

    MobilierElectromenager getMobilierElectromenagerById(Long ongletId, Long mobilierElectromenagerId);

    void ajouterMobilierElectromenager(Long ongletId, MobilierElectromenagerDto mobilierElectromenagerDto);

    void supprimerMobilierElectromenagerFromOnglet(Long ongletId, Long mobilierElectromenagerId);

    void updateMobilierElectromenagerPartiel(Long ongletId, Long mobilierElectromenagerId, MobilierElectromenagerDto mobilierElectromenagerDto);

}
