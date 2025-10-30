package tcucl.back_tcucl.controller.onglet;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tcucl.back_tcucl.annotationPersonnalisee.checkRoleOnglet;
import tcucl.back_tcucl.dto.onglet.numerique.EquipementNumeriqueDto;
import tcucl.back_tcucl.dto.onglet.numerique.NumeriqueOngletDto;
import tcucl.back_tcucl.entity.onglet.numerique.NumeriqueOnglet;
import tcucl.back_tcucl.service.NumeriqueOngletService;

import static tcucl.back_tcucl.controller.ControllerConstante.*;

@RestController
@RequestMapping(REST_API + REST_NUMERIQUE_ONGLET + REST_ONGLET_ID)
public class NumeriqueController {

    private final NumeriqueOngletService numeriqueOngletService;


    public NumeriqueController(NumeriqueOngletService numeriqueOngletService) {
        this.numeriqueOngletService = numeriqueOngletService;
    }

    @GetMapping
    @checkRoleOnglet
    public ResponseEntity<?> getById(@PathVariable(name = "ongletId") Long ongletId) {
        NumeriqueOnglet numeriqueOngletById = numeriqueOngletService.getNumeriqueOngletById(ongletId);
        NumeriqueOngletDto numeriqueOngletDto = new NumeriqueOngletDto(numeriqueOngletById);
        return ResponseEntity.ok(numeriqueOngletDto);
    }

    @PatchMapping
    @checkRoleOnglet
    public ResponseEntity<Void> updateNumeriqueOngletPartiel(@PathVariable(name = "ongletId") Long ongletId,
                                                             @RequestBody NumeriqueOngletDto numeriqueOngletDto) {
        numeriqueOngletService.updateNumeriqueOngletPartiel(ongletId, numeriqueOngletDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping(REST_EQUIPEMENT_NUMERIQUE)
    @checkRoleOnglet
    public ResponseEntity<Void> ajouterEquipementNumerique(@PathVariable(name = "ongletId") Long ongletId,
                                                           @RequestBody EquipementNumeriqueDto equipementNumeriqueDto) {
        numeriqueOngletService.ajouterEquipementNumerique(ongletId, equipementNumeriqueDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(REST_EQUIPEMENT_NUMERIQUE + REST_EQUIPEMENT_NUMERIQUE_ID)
    @checkRoleOnglet
    public ResponseEntity<Void> supprimerEquipementNumerique(@PathVariable(name = "ongletId") Long ongletId,
                                                @PathVariable(name = "equipementNumeriqueId") Long equipementNumeriqueId) {
        numeriqueOngletService.supprimerEquipementNumerique(ongletId, equipementNumeriqueId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping(REST_EQUIPEMENT_NUMERIQUE + REST_EQUIPEMENT_NUMERIQUE_ID)
    @checkRoleOnglet
    public ResponseEntity<Void> updateVoyagePartiel(@PathVariable(name = "ongletId") Long ongletId,
                                                    @PathVariable(name = "equipementNumeriqueId") Long equipementNumeriqueId,
                                                    @RequestBody EquipementNumeriqueDto equipementNumeriqueDto) {
        numeriqueOngletService.updateEquipementNumeriquePartiel(ongletId, equipementNumeriqueId, equipementNumeriqueDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping(REST_RESULTAT)
    @checkRoleOnglet
    public ResponseEntity<?> getNumeriqueResultat(@PathVariable(value = "ongletId") Long ongletId) {
        return ResponseEntity.ok(numeriqueOngletService.getNumeriqueResultat(ongletId));
    }
}
