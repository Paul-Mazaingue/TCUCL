package tcucl.back_tcucl.manager;

import tcucl.back_tcucl.dto.onglet.dechet.DechetOngletDto;
import tcucl.back_tcucl.entity.onglet.dechet.DechetOnglet;

public interface DechetOngletManager {
    DechetOnglet getDechetOngletById(Long ongletId);

    void updateDechetOngletPartiel(Long ongletId, DechetOngletDto dechetOngletDto);
}
