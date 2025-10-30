package tcucl.back_tcucl.controller.onglet;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tcucl.back_tcucl.annotationPersonnalisee.checkRoleOnglet;
import tcucl.back_tcucl.dto.onglet.parkingVoirie.ParkingVoirieOngletDto;
import tcucl.back_tcucl.dto.onglet.parkingVoirie.ParkingVoirieDto;
import tcucl.back_tcucl.entity.onglet.parkingVoirie.ParkingVoirieOnglet;
import tcucl.back_tcucl.service.ParkingVoirieOngletService;

import static tcucl.back_tcucl.controller.ControllerConstante.*;

@RestController
@RequestMapping(REST_API + REST_PARKING_VOIRIE_ONGLET + REST_ONGLET_ID)
public class ParkingVoirieOngletController {

    private final ParkingVoirieOngletService parkingVoirieOngletService;


    public ParkingVoirieOngletController(ParkingVoirieOngletService parkingVoirieOngletService) {
        this.parkingVoirieOngletService = parkingVoirieOngletService;
    }

    @GetMapping
    @checkRoleOnglet
    public ResponseEntity<?> getParkingOngletById(@PathVariable(name = "ongletId") Long ongletId) {
        ParkingVoirieOnglet parkingVoirieOngletById = parkingVoirieOngletService.getParkingVoirieOngletById(ongletId);
        ParkingVoirieOngletDto parkingVoirieOngletDto = new ParkingVoirieOngletDto(parkingVoirieOngletById);
        return ResponseEntity.ok(parkingVoirieOngletDto);
    }

    @PatchMapping
    @checkRoleOnglet
    public ResponseEntity<Void> updatePartiel(@PathVariable(name = "ongletId") Long ongletId,
                                              @RequestBody ParkingVoirieOngletDto parkingVoirieOngletDto) {
        parkingVoirieOngletService.updateParkingVoirieOngletPartiel(ongletId, parkingVoirieOngletDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping(REST_PARKING_VOIRIE)
    @checkRoleOnglet
    public ResponseEntity<Void> ajouterParkingVoirie(@PathVariable(name = "ongletId") Long ongletId,
                                              @RequestBody ParkingVoirieDto parkingVoirieDto) {
        parkingVoirieOngletService.ajouterParkingVoirie(ongletId, parkingVoirieDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(REST_PARKING_VOIRIE + REST_PARKING_VOIRIE_ID)
    @checkRoleOnglet
    public ResponseEntity<Void> supprimerParkingVoirie(@PathVariable(name = "ongletId") Long ongletId,
                                                @PathVariable(name = "parkingVoirieId") Long parkingVoirieId) {
        parkingVoirieOngletService.supprimerParkingVoirie(ongletId, parkingVoirieId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping(REST_PARKING_VOIRIE + REST_PARKING_VOIRIE_ID)
    @checkRoleOnglet
    public ResponseEntity<Void> updateParkingVoiriePartiel(@PathVariable(name = "ongletId") Long ongletId,
                                                    @PathVariable(name = "parkingVoirieId") Long parkingVoirieId,
                                                    @RequestBody ParkingVoirieDto parkingVoirieDto) {
        parkingVoirieOngletService.updateParkingVoiriePartiel(ongletId, parkingVoirieId, parkingVoirieDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping(REST_RESULTAT)
    @checkRoleOnglet
    public ResponseEntity<?> getParkingVoirieResult(@PathVariable(value = "ongletId") Long ongletId) {
        return ResponseEntity.ok(parkingVoirieOngletService.getParkingVoirieResult(ongletId));
    }
}
