package tcucl.back_tcucl.manager;

import tcucl.back_tcucl.dto.onglet.parkingVoirie.ParkingVoirieDto;
import tcucl.back_tcucl.dto.onglet.parkingVoirie.ParkingVoirieOngletDto;
import tcucl.back_tcucl.entity.onglet.parkingVoirie.ParkingVoirieOnglet;
import tcucl.back_tcucl.entity.onglet.parkingVoirie.ParkingVoirie;

public interface ParkingVoirieOngletManager {
    ParkingVoirieOnglet getParkingVoirieOngletById(Long ongletId);

    ParkingVoirie getParkingVoirieById(Long ongletId, Long parkingId);

    void updateParkingVoirieOngletPartiel(Long ongletId, ParkingVoirieOngletDto parkingVoirieOngletDto);

    void ajouterParkingVoirie(Long ongletId, ParkingVoirieDto parkingVoirieDto);

    void supprimerParkingVoirie(Long ongletId, Long parkingVoirieId);

    void updateParkingVoiriePartiel(Long ongletId, Long voyageId, ParkingVoirieDto parkingVoirieDto);
}
