package tcucl.back_tcucl.controller.onglet;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tcucl.back_tcucl.annotationPersonnalisee.checkRoleOnglet;
import tcucl.back_tcucl.dto.onglet.achat.AchatOngletDto;
import tcucl.back_tcucl.entity.onglet.achat.AchatOnglet;
import tcucl.back_tcucl.service.AchatOngletService;

import static tcucl.back_tcucl.controller.ControllerConstante.*;

@RestController
@RequestMapping(REST_API + REST_ACHAT_ONGLET + REST_ONGLET_ID)
public class AchatController {

    private final AchatOngletService achatOngletService;

    public AchatController(AchatOngletService achatOngletService) {
        this.achatOngletService = achatOngletService;
    }

    @GetMapping()
    @checkRoleOnglet
    public ResponseEntity<?> getAchatOngletById(@PathVariable(value = "ongletId") Long ongletId) {
        AchatOnglet achatOngletById = achatOngletService.getAchatOngletById(ongletId);
        AchatOngletDto achatOngletDto = new AchatOngletDto(achatOngletById);
        return ResponseEntity.ok(achatOngletDto);
    }

    @PatchMapping()
    @checkRoleOnglet
    public ResponseEntity<Void> updateAchatOngletPartiel(@PathVariable(value = "ongletId") Long ongletId,
                                                           @RequestBody AchatOngletDto achatOngletDto) {
        achatOngletService.updateAchatOngletPartiel(ongletId, achatOngletDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping(REST_RESULTAT)
    @checkRoleOnglet
    public ResponseEntity<?> getAchatResultat(@PathVariable(value = "ongletId") Long ongletId) {
        return ResponseEntity.ok(achatOngletService.getAchatResultat(ongletId));
    }
}
