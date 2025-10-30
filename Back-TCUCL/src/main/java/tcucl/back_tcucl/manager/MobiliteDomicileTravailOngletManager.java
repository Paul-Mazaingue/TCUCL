package tcucl.back_tcucl.manager;

import tcucl.back_tcucl.dto.onglet.mobiliteDomicileTravail.MobiliteDomicileTravailOngletDto;
import tcucl.back_tcucl.entity.onglet.MobiliteDomicileTravailOnglet;

public interface MobiliteDomicileTravailOngletManager {

    MobiliteDomicileTravailOnglet getMobiliteDomicileTravailOngletById(Long ongletId);

    void updateMobiliteDomicileTravailOngletPartiel(Long ongletId, MobiliteDomicileTravailOngletDto mobiliteDomicileTravailOngletDto);

}