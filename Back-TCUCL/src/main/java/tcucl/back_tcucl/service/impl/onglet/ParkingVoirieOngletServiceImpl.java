package tcucl.back_tcucl.service.impl.onglet;

import org.springframework.stereotype.Service;
import tcucl.back_tcucl.dto.onglet.parkingVoirie.ParkingVoirieResultatDto;
import tcucl.back_tcucl.dto.onglet.parkingVoirie.ParkingVoirieDto;
import tcucl.back_tcucl.dto.onglet.parkingVoirie.ParkingVoirieOngletDto;
import tcucl.back_tcucl.entity.facteurEmission.FacteurEmission;
import tcucl.back_tcucl.entity.facteurEmission.FacteurEmissionParametre;
import tcucl.back_tcucl.entity.onglet.parkingVoirie.ParkingVoirieOnglet;
import tcucl.back_tcucl.entity.onglet.parkingVoirie.ParkingVoirie;
import tcucl.back_tcucl.entity.onglet.parkingVoirie.enums.EnumParkingVoirie_Type;
import tcucl.back_tcucl.manager.ParkingVoirieOngletManager;
import tcucl.back_tcucl.service.FacteurEmissionService;
import tcucl.back_tcucl.service.ParkingVoirieOngletService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ParkingVoirieOngletServiceImpl implements ParkingVoirieOngletService {

    private final ParkingVoirieOngletManager parkingVoirieOngletManager;
    private final FacteurEmissionService facteurEmissionService;


    public ParkingVoirieOngletServiceImpl(ParkingVoirieOngletManager parkingVoirieOngletManager, FacteurEmissionService facteurEmissionService) {
        this.parkingVoirieOngletManager = parkingVoirieOngletManager;
        this.facteurEmissionService = facteurEmissionService;
    }

    @Override
    public ParkingVoirieOnglet getParkingVoirieOngletById(Long ongletId) {
        return parkingVoirieOngletManager.getParkingVoirieOngletById(ongletId);
    }

    @Override
    public ParkingVoirie getParkingVoirieById(Long ongletId, Long parkingId) {
        return parkingVoirieOngletManager.getParkingVoirieById(ongletId, parkingId);
    }

    @Override
    public void updateParkingVoirieOngletPartiel(Long ongletId, ParkingVoirieOngletDto parkingVoirieOngletDto) {
        parkingVoirieOngletManager.updateParkingVoirieOngletPartiel(ongletId, parkingVoirieOngletDto);
    }

    @Override
    public void ajouterParkingVoirie(Long ongletId, ParkingVoirieDto parkingVoirieDto) {
        parkingVoirieOngletManager.ajouterParkingVoirie(ongletId, parkingVoirieDto);
    }

    @Override
    public void supprimerParkingVoirie(Long ongletId, Long parkingVoirieId) {
        parkingVoirieOngletManager.supprimerParkingVoirie(ongletId, parkingVoirieId);
    }

    @Override
    public void updateParkingVoiriePartiel(Long ongletId, Long voyageId, ParkingVoirieDto parkingVoirieDto) {
        parkingVoirieOngletManager.updateParkingVoiriePartiel(ongletId, voyageId, parkingVoirieDto);
    }
    
    @Override
    public ParkingVoirieResultatDto getParkingVoirieResult(Long ongletId) {
        ParkingVoirieOnglet parkingVoirieOnglet = parkingVoirieOngletManager.getParkingVoirieOngletById(ongletId);
        List<ParkingVoirie> parkingVoiries = parkingVoirieOnglet.getParkingVoirieList();

        Map<Long, Float> emissionsParParkingVoiries = parkingVoiries.stream()
                .collect(Collectors.toMap(
                        ParkingVoirie::getId,
                        parkingVoirie -> {
                            FacteurEmission facteurEmission = null;
                            if (parkingVoirie.getType() == EnumParkingVoirie_Type.PARKING){
                                facteurEmission = facteurEmissionService.findByCategorieAndType(
                                        FacteurEmissionParametre.PARKING_ET_VOIRIES_PARKING,
                                        parkingVoirie.getTypeStructure().toString());
                            } else if (parkingVoirie.getType() == EnumParkingVoirie_Type.VOIRIE){
                                facteurEmission = facteurEmissionService.findByCategorieAndType(
                                        FacteurEmissionParametre.PARKING_ET_VOIRIES_VOIRIES,
                                        parkingVoirie.getTypeStructure().toString());
                            }

                            float emissionGesParkingVoiries = 0f;

                            if (Boolean.TRUE.equals(parkingVoirie.getEmissionsGesConnues()) && parkingVoirie.getEmissionsGesReelles() != null) {
                                emissionGesParkingVoiries = parkingVoirie.getEmissionsGesReelles();
                            } else if (facteurEmission != null) {
                                emissionGesParkingVoiries = (facteurEmission.getFacteurEmission() * parkingVoirie.getNombreM2()) / (10*1000);
                            }
                            return emissionGesParkingVoiries;
                        }
                ));

        float total = emissionsParParkingVoiries.values().stream()
                .reduce(0f, Float::sum);

        ParkingVoirieResultatDto resultatDto = new ParkingVoirieResultatDto();
        resultatDto.setEmissionGESParkingVoirie(emissionsParParkingVoiries);
        resultatDto.setTotalEmissionGES(total);

        return resultatDto;
    }
}
