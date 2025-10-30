package tcucl.back_tcucl.manager;

import tcucl.back_tcucl.dto.onglet.autreMobFr.AutreMobFrOngletDto;
import tcucl.back_tcucl.entity.onglet.AutreMobFrOnglet;

public interface AutreMobFrOngletManager {

    AutreMobFrOnglet getAutreMobFrOngletById(Long ongletId);

    void updateAutreMobFrOngletPartiel(Long ongletId, AutreMobFrOngletDto autreMobFrOngletDto);
}
