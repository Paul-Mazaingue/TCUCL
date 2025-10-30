package tcucl.back_tcucl.service;

import tcucl.back_tcucl.dto.onglet.batimentImmobilisationMobilier.*;
import tcucl.back_tcucl.entity.onglet.batiment.BatimentImmobilisationMobilierOnglet;

public interface BatimentImmobilisationMobilierOngletService {

    BatimentImmobilisationMobilierOnglet getBatimentImmobilisationMobilierOngletById(Long ongletId);

    void updateBatimentImmobilisationMobilierOnglet(Long ongletId, BatimentImmobilisationMobilierOngletDto batimentImmobilisationMobilierOngletDto);

    void ajouterBatiment(Long ongletId, BatimentExistantOuNeufConstruitDto batimentExistantOuNeufConstruitDto);

    void supprimerBatiment(Long ongletId, Long batimentId);

    void updateBatimentPartiel(Long ongletId, Long batimentId, BatimentExistantOuNeufConstruitDto batimentExistantOuNeufConstruitDto);

    void ajouterEntretienCourant(Long ongletId, EntretienCourantDto entretienCourantDto);

    void supprimerEntretienCourant(Long ongletId, Long entretienCourantId);

    void updateEntretienCourantPartiel(Long ongletId, Long entretienCourantId, EntretienCourantDto entretienCourantDto);

    void ajouterMobilierElectromenager(Long ongletId, MobilierElectromenagerDto mobilierElectromenagerDto);

    void supprimerMobilierElectromenager(Long ongletId, Long mobilierElectromenagerId);

    void updateMobilierElectromenagerPartiel(Long ongletId, Long mobilierElectromenagerId, MobilierElectromenagerDto mobilierElectromenagerDto);

    BatimentImmobilisationMobilierResultatDto getBatimentImmobilisationMobilierResult(Long ongletId);

}
