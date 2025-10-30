package tcucl.back_tcucl.service;

import tcucl.back_tcucl.dto.onglet.achat.AchatOngletDto;
import tcucl.back_tcucl.dto.onglet.achat.AchatResultatDto;
import tcucl.back_tcucl.entity.onglet.achat.AchatOnglet;

public interface AchatOngletService {

    AchatOnglet getAchatOngletById(Long ongletId);

    void updateAchatOngletPartiel(Long ongletId, AchatOngletDto achatOngletDto);

    AchatResultatDto getAchatResultat(Long ongletId);
}
