package tcucl.back_tcucl.controller.onglet;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tcucl.back_tcucl.annotationPersonnalisee.checkRoleOnglet;
import tcucl.back_tcucl.dto.onglet.mobiliteDomicileTravail.MobiliteDomicileTravailOngletDto;
import tcucl.back_tcucl.entity.onglet.MobiliteDomicileTravailOnglet;
import tcucl.back_tcucl.service.MobiliteDomicileTravailOngletService;

import static tcucl.back_tcucl.controller.ControllerConstante.*;

@RestController
@RequestMapping(REST_API + REST_MOBILITE_DOMICILE_TRAVAIL_ONGLET + REST_ONGLET_ID)
public class MobiliteDomicileTravailOngletController {

    private final MobiliteDomicileTravailOngletService mobiliteDomicileTravailOngletService;

    public MobiliteDomicileTravailOngletController(MobiliteDomicileTravailOngletService mobiliteDomicileTravailOngletService) {
        this.mobiliteDomicileTravailOngletService = mobiliteDomicileTravailOngletService;
    }

    @GetMapping()
    @checkRoleOnglet
    public ResponseEntity<?> getMobiliteDomicileTravailOngletById(@PathVariable(value = "ongletId") Long ongletId) {
        MobiliteDomicileTravailOnglet mobiliteDomicileTravailOngletById = mobiliteDomicileTravailOngletService.getMobiliteDomicileTravailOngletById(ongletId);
        MobiliteDomicileTravailOngletDto mobiliteDomicileTravailOngletDto = new MobiliteDomicileTravailOngletDto(mobiliteDomicileTravailOngletById);
        return ResponseEntity.ok(mobiliteDomicileTravailOngletDto);
    }

    @PatchMapping()
    @checkRoleOnglet
    public ResponseEntity<Void> updateMobiliteDomicileTravailOngletPartiel(@PathVariable(value = "ongletId") Long ongletId,
                                                                           @RequestBody MobiliteDomicileTravailOngletDto mobiliteDomicileTravailOngletDto) {
        mobiliteDomicileTravailOngletService.updateMobiliteDomicileTravailOngletPartiel(ongletId, mobiliteDomicileTravailOngletDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping(REST_RESULTAT)
    @checkRoleOnglet
    public ResponseEntity<?> getMobiliteDomicileTravailResultat(@PathVariable(value = "ongletId") Long ongletId) {
        return ResponseEntity.ok(mobiliteDomicileTravailOngletService.getMobiliteDomicileTravailResultat(ongletId));
    }


}