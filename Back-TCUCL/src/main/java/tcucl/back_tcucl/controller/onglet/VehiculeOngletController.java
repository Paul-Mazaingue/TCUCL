package tcucl.back_tcucl.controller.onglet;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tcucl.back_tcucl.annotationPersonnalisee.checkRoleOnglet;
import tcucl.back_tcucl.dto.onglet.vehicule.VehiculeDto;
import tcucl.back_tcucl.dto.onglet.vehicule.VehiculeOngletDto;
import tcucl.back_tcucl.entity.onglet.vehicule.VehiculeOnglet;
import tcucl.back_tcucl.service.VehiculeOngletService;

import static tcucl.back_tcucl.controller.ControllerConstante.*;

@RestController
@RequestMapping(REST_API + REST_VEHICULE_ONGLET + REST_ONGLET_ID)
public class VehiculeOngletController {

    private final VehiculeOngletService vehiculeOngletService;


    public VehiculeOngletController(VehiculeOngletService vehiculeOngletService) {
        this.vehiculeOngletService = vehiculeOngletService;
    }

    @GetMapping
    @checkRoleOnglet
    public ResponseEntity<?> getVehiculeOngletById(@PathVariable(name = "ongletId") Long ongletId) {
        VehiculeOnglet vehiculeOngletById = vehiculeOngletService.getVehiculeOngletById(ongletId);
        VehiculeOngletDto vehiculeOngletDto = new VehiculeOngletDto(vehiculeOngletById);
        return ResponseEntity.ok(vehiculeOngletDto);
    }

    @PatchMapping
    @checkRoleOnglet
    public ResponseEntity<Void> updatePartiel(@PathVariable(name = "ongletId") Long ongletId,
                                              @RequestBody VehiculeOngletDto vehiculeOngletDto) {
        vehiculeOngletService.updateVehiculeOngletPartiel(ongletId, vehiculeOngletDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping(REST_VEHICULE)
    @checkRoleOnglet
    public ResponseEntity<Void> ajouterVehicule(@PathVariable(name = "ongletId") Long ongletId,
                                                     @RequestBody VehiculeDto vehiculeDto) {
        vehiculeOngletService.ajouterVehicule(ongletId, vehiculeDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(REST_VEHICULE + REST_VEHICULE_ID)
    @checkRoleOnglet
    public ResponseEntity<Void> supprimerVehicule(@PathVariable(name = "ongletId") Long ongletId,
                                                       @PathVariable(name = "vehiculeId") Long vehiculeId) {
        vehiculeOngletService.supprimerVehicule(ongletId, vehiculeId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping(REST_VEHICULE + REST_VEHICULE_ID)
    @checkRoleOnglet
    public ResponseEntity<Void> updateVehiculePartiel(@PathVariable(name = "ongletId") Long ongletId,
                                                           @PathVariable(name = "vehiculeId") Long vehiculeId,
                                                           @RequestBody VehiculeDto vehiculeDto) {
        vehiculeOngletService.updateVehiculePartiel(ongletId, vehiculeId, vehiculeDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping(REST_RESULTAT)
    @checkRoleOnglet
    public ResponseEntity<?> getVehiculeResult(@PathVariable(value = "ongletId") Long ongletId) {
        return ResponseEntity.ok(vehiculeOngletService.getVehiculeResult(ongletId));
    }
}