package tcucl.back_tcucl.service;

import tcucl.back_tcucl.dto.onglet.energie.EnergieOngletDto;
import tcucl.back_tcucl.dto.onglet.energie.EnergieResultatDto;
import tcucl.back_tcucl.entity.onglet.energie.EnergieOnglet;

public interface EnergieOngletService {

    public EnergieOnglet getEnergieOngletById(Long ongletId);

    public void updateEnergieOngletPartiel(Long ongletId, EnergieOngletDto dto);

    EnergieResultatDto getEnergieResultat(Long ongletId);
}
