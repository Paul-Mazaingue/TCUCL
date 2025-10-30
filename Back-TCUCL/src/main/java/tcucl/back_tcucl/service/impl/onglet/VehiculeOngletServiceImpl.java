package tcucl.back_tcucl.service.impl.onglet;

import org.springframework.stereotype.Service;
import tcucl.back_tcucl.dto.onglet.vehicule.VehiculeResultatDto;
import tcucl.back_tcucl.dto.onglet.vehicule.VehiculeDto;
import tcucl.back_tcucl.dto.onglet.vehicule.VehiculeOngletDto;
import tcucl.back_tcucl.entity.onglet.vehicule.Vehicule;
import tcucl.back_tcucl.entity.onglet.vehicule.VehiculeOnglet;
import tcucl.back_tcucl.entity.onglet.vehicule.enums.EnumVehicule_Type;
import tcucl.back_tcucl.manager.VehiculeOngletManager;
import tcucl.back_tcucl.service.VehiculeOngletService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class VehiculeOngletServiceImpl implements VehiculeOngletService {

    private final VehiculeOngletManager vehiculeOngletManager;

    public VehiculeOngletServiceImpl(VehiculeOngletManager vehiculeOngletManager) {
        this.vehiculeOngletManager = vehiculeOngletManager;
    }

    @Override
    public VehiculeOnglet getVehiculeOngletById(Long ongletId) {
        return vehiculeOngletManager.getVehiculeOngletById(ongletId);
    }

    @Override
    public Vehicule getVehiculeById(Long ongletId, Long parkingId) {
        return vehiculeOngletManager.getVehiculeById(ongletId, parkingId);
    }

    @Override
    public void updateVehiculeOngletPartiel(Long ongletId, VehiculeOngletDto vehiculeOngletDto) {
        vehiculeOngletManager.updateVehiculeOngletPartiel(ongletId, vehiculeOngletDto);
    }

    @Override
    public void ajouterVehicule(Long ongletId, VehiculeDto vehiculeDto) {
        vehiculeOngletManager.ajouterVehicule(ongletId, vehiculeDto);
    }

    @Override
    public void supprimerVehicule(Long ongletId, Long vehiculeId) {
        vehiculeOngletManager.supprimerVehicule(ongletId, vehiculeId);
    }

    @Override
    public void updateVehiculePartiel(Long ongletId, Long voyageId, VehiculeDto vehiculeDto) {
        vehiculeOngletManager.updateVehiculePartiel(ongletId, voyageId, vehiculeDto);
    }

    @Override
    public VehiculeResultatDto getVehiculeResult(Long ongletId) {
        VehiculeOnglet vehiculeOnglet = vehiculeOngletManager.getVehiculeOngletById(ongletId);
        List<Vehicule> vehicules = vehiculeOnglet.getVehiculeList();

        final Float[] emissionGesVehiculesFabrication = {0f};
        final Float[] emissionGesVehiculesPetrole = {0f};
        final Float[] emissionGesVehiculesElectrique = {0f};

        Map<Long, Float> emissionsParVehicules = vehicules.stream()
                .collect(Collectors.toMap(
                        Vehicule::getId,
                        vehicule -> {
                            float fabrication = 0f;
                            float petrole = 0f;
                            float electrique = 0f;

                            if (vehicule.getTypeVehicule() == EnumVehicule_Type.VEHICULE_ELECTRIQUE) {
                                fabrication = vehicule.getNombreKilometresParVoitureMoyen() * vehicule.getNombreVehiculesIdentiques() * 0.096f / 1000f;
                                electrique = vehicule.getNombreKilometresParVoitureMoyen() * vehicule.getNombreVehiculesIdentiques() * 0.022f / 1000f;
                            } else if (vehicule.getTypeVehicule() == EnumVehicule_Type.VEHICULE_THERMIQUE) {
                                fabrication = vehicule.getNombreKilometresParVoitureMoyen() * vehicule.getNombreVehiculesIdentiques() * 0.026f / 1000f;
                                petrole = vehicule.getNombreKilometresParVoitureMoyen() * vehicule.getNombreVehiculesIdentiques() * 0.192f / 1000f;
                            } else if (vehicule.getTypeVehicule() == EnumVehicule_Type.VEHICULE_HYBRIDE){
                                fabrication = vehicule.getNombreKilometresParVoitureMoyen() * vehicule.getNombreVehiculesIdentiques() * 0.051f / 1000f;
                                petrole = vehicule.getNombreKilometresParVoitureMoyen() * vehicule.getNombreVehiculesIdentiques() * 0.138f / 1000f;

                            }

                            emissionGesVehiculesFabrication[0] += fabrication;
                            emissionGesVehiculesPetrole[0] += petrole;
                            emissionGesVehiculesElectrique[0] += electrique;
                            return fabrication + electrique + petrole;
                        }
                ));

        Float total = emissionGesVehiculesFabrication[0] + emissionGesVehiculesPetrole[0] + emissionGesVehiculesElectrique[0];

        VehiculeResultatDto resultatDto = new VehiculeResultatDto();
        resultatDto.setEmissionGESVehicule(emissionsParVehicules);
        resultatDto.setTotalEmissionGES(total);
        resultatDto.setTotalEmissionGESFabrication(emissionGesVehiculesFabrication[0]);
        resultatDto.setTotalEmissionGESElectrique(emissionGesVehiculesElectrique[0]);
        resultatDto.setTotalEmissionGESPetrole(emissionGesVehiculesPetrole[0]);

        return resultatDto;
    }
}
