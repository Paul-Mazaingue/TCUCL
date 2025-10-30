package tcucl.back_tcucl.manager;

import tcucl.back_tcucl.dto.onglet.numerique.EquipementNumeriqueDto;
import tcucl.back_tcucl.dto.onglet.numerique.NumeriqueOngletDto;
import tcucl.back_tcucl.entity.onglet.numerique.NumeriqueOnglet;
import tcucl.back_tcucl.entity.onglet.numerique.EquipementNumerique;

public interface NumeriqueOngletManager {
    NumeriqueOnglet getNumeriqueOngletById(Long ongletId);

    EquipementNumerique getEquipementNumeriqueById(Long ongletId, Long equipementId);

    void updateNumeriqueOngletPartiel(Long ongletId, NumeriqueOngletDto numeriqueOngletDto);

    void ajouterEquipementNumerique(Long ongletId, EquipementNumeriqueDto equipementNumeriqueDto);

    void supprimerEquipementNumerique(Long ongletId, Long equipementId);

    void updateEquipementNumeriquePartiel(Long ongletId, Long equipementId, EquipementNumeriqueDto equipementNumeriqueDto);
}
