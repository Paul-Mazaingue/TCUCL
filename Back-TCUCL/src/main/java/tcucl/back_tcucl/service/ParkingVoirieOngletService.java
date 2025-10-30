package tcucl.back_tcucl.service;

import tcucl.back_tcucl.dto.onglet.parkingVoirie.ParkingVoirieResultatDto;
import tcucl.back_tcucl.dto.onglet.parkingVoirie.ParkingVoirieDto;
import tcucl.back_tcucl.dto.onglet.parkingVoirie.ParkingVoirieOngletDto;
import tcucl.back_tcucl.entity.onglet.parkingVoirie.ParkingVoirieOnglet;
import tcucl.back_tcucl.entity.onglet.parkingVoirie.ParkingVoirie;

public interface ParkingVoirieOngletService {

    ParkingVoirieOnglet getParkingVoirieOngletById(Long ongletId);

    ParkingVoirie getParkingVoirieById(Long ongletId, Long parkingId);

    void updateParkingVoirieOngletPartiel(Long ongletId, ParkingVoirieOngletDto dto);

    void ajouterParkingVoirie(Long ongletId, ParkingVoirieDto parkingVoirieDto);

    void supprimerParkingVoirie(Long ongletId, Long parkingVoirieId);

    void updateParkingVoiriePartiel(Long ongletId, Long voyageId, ParkingVoirieDto dto);

    ParkingVoirieResultatDto getParkingVoirieResult(Long ongletId);

}
