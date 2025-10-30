package tcucl.back_tcucl.service;

import tcucl.back_tcucl.dto.onglet.mobiliteDomicileTravail.MobiliteDomicileTravailOngletDto;
import tcucl.back_tcucl.dto.onglet.mobiliteDomicileTravail.MobiliteDomicileTravailResultatDto;
import tcucl.back_tcucl.entity.onglet.MobiliteDomicileTravailOnglet;

public interface MobiliteDomicileTravailOngletService {
    MobiliteDomicileTravailOnglet getMobiliteDomicileTravailOngletById(Long ongletId);

    void updateMobiliteDomicileTravailOngletPartiel(Long ongletId, MobiliteDomicileTravailOngletDto dto);

    MobiliteDomicileTravailResultatDto getMobiliteDomicileTravailResultat(Long ongletId);
}