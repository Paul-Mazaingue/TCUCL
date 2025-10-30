package tcucl.back_tcucl.manager;

import tcucl.back_tcucl.dto.onglet.energie.EnergieOngletDto;
import tcucl.back_tcucl.entity.onglet.energie.EnergieOnglet;

public interface EnergieOngletManager {

    public EnergieOnglet getEnergieOngletById(Long ongletId);

    public void updateEnergieOngletPartiel(Long ongletId, EnergieOngletDto energieOngletDto);


}

