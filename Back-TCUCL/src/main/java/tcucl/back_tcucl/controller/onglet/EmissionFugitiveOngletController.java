package tcucl.back_tcucl.controller.onglet;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tcucl.back_tcucl.annotationPersonnalisee.checkRoleOnglet;
import tcucl.back_tcucl.dto.onglet.emissionFugitive.MachineEmissionFugitiveDto;
import tcucl.back_tcucl.dto.onglet.emissionFugitive.EmissionFugitiveOngletDto;
import tcucl.back_tcucl.entity.onglet.emissionFugitive.EmissionFugitiveOnglet;
import tcucl.back_tcucl.service.EmissionFugitiveOngletService;

import static tcucl.back_tcucl.controller.ControllerConstante.*;

@RestController
@RequestMapping(REST_API + REST_EMISSION_FUGITIVE_ONGLET+REST_ONGLET_ID)
public class EmissionFugitiveOngletController {

    private final EmissionFugitiveOngletService emissionFugitiveOngletService;

    public EmissionFugitiveOngletController(EmissionFugitiveOngletService emissionFugitiveOngletService) {
        this.emissionFugitiveOngletService = emissionFugitiveOngletService;
    }

    @GetMapping()
    @checkRoleOnglet
    public ResponseEntity<?> getEmissionFugitiveOngletById(@PathVariable(name = "ongletId") Long ongletId) {
        EmissionFugitiveOnglet emissionFugitiveOngletById = emissionFugitiveOngletService.getEmissionFugitiveOngletById(ongletId);
        EmissionFugitiveOngletDto emissionFugitiveOngletDto = new EmissionFugitiveOngletDto(emissionFugitiveOngletById);
        return ResponseEntity.ok(emissionFugitiveOngletDto);
    }

    @PatchMapping
    @checkRoleOnglet
    public ResponseEntity<Void> updateEmissionFugitiveOnglet(@PathVariable(name = "ongletId") Long ongletId,
                                                             @RequestBody EmissionFugitiveOngletDto emissionFugitiveOngletDto) {
        emissionFugitiveOngletService.updateEmissionFugitiveOnglet(ongletId, emissionFugitiveOngletDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping(REST_MACHINE)
    @checkRoleOnglet
    public ResponseEntity<Void> ajouterMachine(@PathVariable(name = "ongletId") Long ongletId,
                                               @RequestBody MachineEmissionFugitiveDto machineEmissionFugitiveDto) {
        emissionFugitiveOngletService.ajouterMachine(ongletId, machineEmissionFugitiveDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(REST_MACHINE + REST_MACHINE_ID)
    @checkRoleOnglet
    public ResponseEntity<Void> supprimerMachine(@PathVariable(name = "ongletId") Long ongletId,
                                                 @PathVariable(name = "machineId") Long machineId) {
        emissionFugitiveOngletService.supprimerMachine(ongletId, machineId);
        return ResponseEntity.ok().build();

    }

    @PatchMapping(REST_MACHINE + REST_MACHINE_ID)
    @checkRoleOnglet
    public ResponseEntity<Void> updateMachinePartiel(@PathVariable(name = "ongletId") Long ongletId,
                                                     @PathVariable(name = "machineId") Long machineId,
                                                     @RequestBody MachineEmissionFugitiveDto machineEmissionFugitiveDto) {
        emissionFugitiveOngletService.updateMachinePartiel(ongletId, machineId, machineEmissionFugitiveDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping(REST_RESULTAT)
    @checkRoleOnglet
    public ResponseEntity<?> getEmissionFugitiveResult(@PathVariable(value = "ongletId") Long ongletId) {
        return ResponseEntity.ok(emissionFugitiveOngletService.getEmissionFugitiveResult(ongletId));
    }

}
