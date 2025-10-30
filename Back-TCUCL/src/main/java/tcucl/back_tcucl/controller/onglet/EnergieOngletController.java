package tcucl.back_tcucl.controller.onglet;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tcucl.back_tcucl.annotationPersonnalisee.checkRoleOnglet;
import tcucl.back_tcucl.dto.onglet.energie.EnergieOngletDto;
import tcucl.back_tcucl.entity.onglet.energie.EnergieOnglet;
import tcucl.back_tcucl.service.EnergieOngletService;

import static tcucl.back_tcucl.controller.ControllerConstante.*;

@RestController
@RequestMapping(REST_API + REST_ENERGIE_ONGLET + REST_ONGLET_ID)
public class EnergieOngletController {

    private final EnergieOngletService energieOngletService;

    public EnergieOngletController(EnergieOngletService energieOngletService) {
        this.energieOngletService = energieOngletService;
    }

    @GetMapping()
    @checkRoleOnglet
    public ResponseEntity<?> getEnergieOngletById(@PathVariable(value = "ongletId") Long ongletId) {
        EnergieOnglet energieOngletById = energieOngletService.getEnergieOngletById(ongletId);
        EnergieOngletDto energieOngletDto = new EnergieOngletDto(energieOngletById);
        return ResponseEntity.ok(energieOngletDto);
    }

    @PatchMapping()
    @checkRoleOnglet
    public ResponseEntity<Void> updateEnergieOngletPartiel(@PathVariable(value = "ongletId") Long ongletId,
                                                           @RequestBody EnergieOngletDto energieOngletDto) {
        energieOngletService.updateEnergieOngletPartiel(ongletId, energieOngletDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping(REST_RESULTAT)
    @checkRoleOnglet
    public ResponseEntity<?> getEnergieResultat(@PathVariable(value = "ongletId") Long ongletId) {
        return ResponseEntity.ok(energieOngletService.getEnergieResultat(ongletId));
    }


}
