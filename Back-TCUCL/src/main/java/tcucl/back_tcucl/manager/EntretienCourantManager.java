package tcucl.back_tcucl.manager;

import tcucl.back_tcucl.entity.onglet.batiment.EntretienCourant;

public interface EntretienCourantManager {
    EntretienCourant save(EntretienCourant entretienCourant);

    EntretienCourant findEntretienCourantById(Long entretienCourantId);
}
