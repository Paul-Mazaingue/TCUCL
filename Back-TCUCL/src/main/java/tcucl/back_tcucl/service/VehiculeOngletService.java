package tcucl.back_tcucl.service;

import tcucl.back_tcucl.dto.onglet.vehicule.VehiculeResultatDto;
import tcucl.back_tcucl.dto.onglet.vehicule.VehiculeDto;
import tcucl.back_tcucl.dto.onglet.vehicule.VehiculeOngletDto;
import tcucl.back_tcucl.entity.onglet.vehicule.VehiculeOnglet;
import tcucl.back_tcucl.entity.onglet.vehicule.Vehicule;

public interface VehiculeOngletService {

    VehiculeOnglet getVehiculeOngletById(Long ongletId);

    Vehicule getVehiculeById(Long ongletId, Long parkingId);

    void updateVehiculeOngletPartiel(Long ongletId, VehiculeOngletDto dto);

    void ajouterVehicule(Long ongletId, VehiculeDto vehiculeDto);

    void supprimerVehicule(Long ongletId, Long vehiculeId);

    void updateVehiculePartiel(Long ongletId, Long voyageId, VehiculeDto dto);

    VehiculeResultatDto getVehiculeResult(Long ongletId);

}
