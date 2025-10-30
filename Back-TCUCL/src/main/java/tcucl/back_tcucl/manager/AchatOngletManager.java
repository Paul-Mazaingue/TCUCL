package tcucl.back_tcucl.manager;

import tcucl.back_tcucl.dto.onglet.achat.AchatOngletDto;
import tcucl.back_tcucl.entity.onglet.achat.AchatOnglet;

public interface AchatOngletManager {
    AchatOnglet getAchatOngletById(Long ongletId);

    void updateAchatOngletPartiel(Long ongletId, AchatOngletDto achatOngletDto);
}
