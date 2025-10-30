package tcucl.back_tcucl.manager;

import tcucl.back_tcucl.dto.onglet.mobInternational.MobInternationalOngletDto;
import tcucl.back_tcucl.dto.onglet.mobInternational.VoyageDto;
import tcucl.back_tcucl.entity.onglet.mobInternationale.MobInternationalOnglet;
import tcucl.back_tcucl.entity.onglet.mobInternationale.Voyage;

public interface MobInternationalOngletManager {

    MobInternationalOnglet getMobInternationalOngletById(Long ongletId);

    Voyage getVoyageById(Long ongletId, Long voyageId);

    void updateMobInternationalOngletPartiel(Long ongletId, MobInternationalOngletDto mobInternationalOngletDto);

    void ajouterVoyage(Long ongletId, VoyageDto voyageDto);

    void supprimerVoyage(Long ongletId, Long voyageId);

    void updateVoyagePartiel(Long ongletId, Long voyageId, VoyageDto voyageDto);

    void save(MobInternationalOnglet mobInternationalOnglet);
}
