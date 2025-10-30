package tcucl.back_tcucl.manager;

import tcucl.back_tcucl.entity.onglet.batiment.BatimentExistantOuNeufConstruit;
import tcucl.back_tcucl.entity.onglet.batiment.EntretienCourant;

public interface BatimentExistantOuNeufConstruitManager {

    BatimentExistantOuNeufConstruit save(BatimentExistantOuNeufConstruit batimentExistantOuNeufConstruit);

    BatimentExistantOuNeufConstruit findBatimentExistantOuNeufConstruitById(Long entretienCourantId);
}
