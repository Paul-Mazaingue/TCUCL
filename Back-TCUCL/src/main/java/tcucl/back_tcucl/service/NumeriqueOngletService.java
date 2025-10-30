package tcucl.back_tcucl.service;

import tcucl.back_tcucl.dto.onglet.numerique.EquipementNumeriqueDto;
import tcucl.back_tcucl.dto.onglet.numerique.NumeriqueOngletDto;
import tcucl.back_tcucl.dto.onglet.numerique.NumeriqueResultatDto;
import tcucl.back_tcucl.entity.onglet.numerique.NumeriqueOnglet;
import tcucl.back_tcucl.entity.onglet.numerique.EquipementNumerique;

public interface NumeriqueOngletService {
    NumeriqueOnglet getNumeriqueOngletById(Long ongletId);

    EquipementNumerique getEquipementNumeriqueById(Long ongletId, Long voyageId);

    void updateNumeriqueOngletPartiel(Long ongletId, NumeriqueOngletDto dto);

    void ajouterEquipementNumerique(Long ongletId, EquipementNumeriqueDto voyageDto);

    void supprimerEquipementNumerique(Long ongletId, Long voyageId);

    void updateEquipementNumeriquePartiel(Long ongletId, Long voyageId, EquipementNumeriqueDto dto);

    NumeriqueResultatDto getNumeriqueResultat(Long ongletId);
}
