package tcucl.back_tcucl.service;

import tcucl.back_tcucl.dto.onglet.autreMobFr.AutreMobFrOngletDto;
import tcucl.back_tcucl.dto.onglet.autreMobFr.AutreMobFrResultatDto;
import tcucl.back_tcucl.entity.onglet.AutreMobFrOnglet;

public interface AutreMobFrOngletService {
    AutreMobFrOnglet getAutreMobFrOngletById(Long ongletId);

    void updateAutreMobFrOngletPartiel(Long ongletId, AutreMobFrOngletDto autreMobFrOngletDto);

    AutreMobFrResultatDto getAutreMobFrResultat(Long ongletId);
}
