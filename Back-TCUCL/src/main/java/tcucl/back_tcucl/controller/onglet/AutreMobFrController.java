package tcucl.back_tcucl.controller.onglet;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tcucl.back_tcucl.annotationPersonnalisee.checkRoleOnglet;
import tcucl.back_tcucl.dto.onglet.autreMobFr.AutreMobFrOngletDto;
import tcucl.back_tcucl.entity.onglet.AutreMobFrOnglet;
import tcucl.back_tcucl.service.AutreMobFrOngletService;

import static tcucl.back_tcucl.controller.ControllerConstante.*;

@RestController
@RequestMapping(REST_API + REST_AUTRE_MOB_FR + REST_ONGLET_ID)
public class AutreMobFrController {

    private final AutreMobFrOngletService autreMobFrOngletService;

    public AutreMobFrController(AutreMobFrOngletService autreMobFrOngletService) {
        this.autreMobFrOngletService = autreMobFrOngletService;
    }

    @GetMapping()
    @checkRoleOnglet
    public ResponseEntity<?> getAutreMobFrOngletById(@PathVariable(value = "ongletId") Long ongletId) {
        AutreMobFrOnglet autreMobFrOnglet = autreMobFrOngletService.getAutreMobFrOngletById(ongletId);
        AutreMobFrOngletDto autreMobFrOngletDto = new AutreMobFrOngletDto(autreMobFrOnglet);
        return ResponseEntity.ok(autreMobFrOngletDto);
    }

    @PatchMapping
    @checkRoleOnglet
    public ResponseEntity<Void> updateAutreMobFrOngletPartiel(@PathVariable(value = "ongletId") Long ongletId,
                                                              @RequestBody AutreMobFrOngletDto autreMobFrOngletDto) {
        autreMobFrOngletService.updateAutreMobFrOngletPartiel(ongletId, autreMobFrOngletDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping(REST_RESULTAT)
    @checkRoleOnglet
    public ResponseEntity<?> getAutreMobFrResultat(@PathVariable(value = "ongletId") Long ongletId) {
        return ResponseEntity.ok(autreMobFrOngletService.getAutreMobFrResultat(ongletId));
    }
}
