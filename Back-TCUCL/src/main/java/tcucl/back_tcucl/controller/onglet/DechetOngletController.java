package tcucl.back_tcucl.controller.onglet;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tcucl.back_tcucl.annotationPersonnalisee.checkRoleOnglet;
import tcucl.back_tcucl.dto.onglet.dechet.DechetOngletDto;
import tcucl.back_tcucl.entity.onglet.dechet.DechetOnglet;
import tcucl.back_tcucl.service.DechetOngletService;

import static tcucl.back_tcucl.controller.ControllerConstante.*;

@RestController
@RequestMapping(REST_API + REST_DECHET_ONGLET + REST_ONGLET_ID)
public class DechetOngletController {

    private final DechetOngletService dechetOngletService;

    public DechetOngletController(DechetOngletService dechetOngletService) {
        this.dechetOngletService = dechetOngletService;
    }

    @GetMapping()
    @checkRoleOnglet
    public ResponseEntity<?> getDechetOngletById(@PathVariable(value = "ongletId") Long ongletId) {
        DechetOnglet dechetOngletById = dechetOngletService.getDechetOngletById(ongletId);
        DechetOngletDto dechetOngletDto = new DechetOngletDto(dechetOngletById);
        return ResponseEntity.ok(dechetOngletDto);
    }

    @PatchMapping()
    @checkRoleOnglet
    public ResponseEntity<Void> updateDechetOngletPartiel(@PathVariable(value = "ongletId") Long ongletId,
                                                           @RequestBody DechetOngletDto dechetOngletDto) {
        dechetOngletService.updateDechetOngletPartiel(ongletId, dechetOngletDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping(REST_RESULTAT)
    @checkRoleOnglet
    public ResponseEntity<?> getDechetResultat(@PathVariable(value = "ongletId") Long ongletId) {
        return ResponseEntity.ok(dechetOngletService.getDechetResultat(ongletId));
    }


}
