package tcucl.back_tcucl.controller.onglet;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tcucl.back_tcucl.annotationPersonnalisee.checkRoleOnglet;
import tcucl.back_tcucl.dto.onglet.autreImmobilisation.AutreImmobilisationOngletDto;
import tcucl.back_tcucl.entity.onglet.AutreImmobilisationOnglet;
import tcucl.back_tcucl.service.AutreImmobilisationOngletService;

import static tcucl.back_tcucl.controller.ControllerConstante.*;

@RestController
@RequestMapping(REST_API + REST_AUTRE_IMMOBILISATION + REST_ONGLET_ID)
public class AutreImmobilisationController {


    private final AutreImmobilisationOngletService autreImmobilisationOngletService;


    public AutreImmobilisationController(AutreImmobilisationOngletService autreImmobilisationOngletService) {
        this.autreImmobilisationOngletService = autreImmobilisationOngletService;
    }

    @GetMapping()
    @checkRoleOnglet
    public ResponseEntity<?> getAutreImmobilisationOngletById(@PathVariable(value = "ongletId") Long ongletId) {
        AutreImmobilisationOnglet autreImmobilisationOngletById = autreImmobilisationOngletService.getAutreImmobilisationOngletById(ongletId);
        AutreImmobilisationOngletDto autreImmobilisationOngletDto = new AutreImmobilisationOngletDto(autreImmobilisationOngletById);
        return ResponseEntity.ok(autreImmobilisationOngletDto);
    }

    @PatchMapping()
    @checkRoleOnglet
    public ResponseEntity<Void> updateAutreImmobilisationOngletPartiel(@PathVariable(value = "ongletId") Long ongletId,
                                                           @RequestBody AutreImmobilisationOngletDto autreImmobilisationOngletDto) {
        autreImmobilisationOngletService.updateAutreImmobilisationOngletPartiel(ongletId, autreImmobilisationOngletDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping(REST_RESULTAT)
    @checkRoleOnglet
    public ResponseEntity<?> getAutreImmobilisationResultat(@PathVariable(value = "ongletId") Long ongletId) {
        return ResponseEntity.ok(autreImmobilisationOngletService.getAutreImmobilisationResultat(ongletId));
    }

}
