package tcucl.back_tcucl.manager;

import tcucl.back_tcucl.dto.onglet.vehicule.VehiculeDto;
import tcucl.back_tcucl.dto.onglet.vehicule.VehiculeOngletDto;
import tcucl.back_tcucl.entity.onglet.vehicule.VehiculeOnglet;
import tcucl.back_tcucl.entity.onglet.vehicule.Vehicule;

public interface VehiculeOngletManager {
    VehiculeOnglet getVehiculeOngletById(Long ongletId);

    Vehicule getVehiculeById(Long ongletId, Long parkingId);

    void updateVehiculeOngletPartiel(Long ongletId, VehiculeOngletDto vehiculeOngletDto);

    void ajouterVehicule(Long ongletId, VehiculeDto vehiculeDto);

    void supprimerVehicule(Long ongletId, Long vehiculeId);

    void updateVehiculePartiel(Long ongletId, Long voyageId, VehiculeDto vehiculeDto);
}
