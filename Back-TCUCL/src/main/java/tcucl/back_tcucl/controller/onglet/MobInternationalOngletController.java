package tcucl.back_tcucl.controller.onglet;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tcucl.back_tcucl.annotationPersonnalisee.checkRoleOnglet;
import tcucl.back_tcucl.dto.onglet.mobInternational.MobInternationalOngletDto;
import tcucl.back_tcucl.dto.onglet.mobInternational.VoyageDto;
import tcucl.back_tcucl.entity.onglet.mobInternationale.MobInternationalOnglet;
import tcucl.back_tcucl.service.MobInternationalOngletService;

import static tcucl.back_tcucl.controller.ControllerConstante.*;

@RestController
@RequestMapping(REST_API + REST_MOB_INTERNATIONALE_ONGLET + REST_ONGLET_ID)
public class MobInternationalOngletController {

    private final MobInternationalOngletService mobInternationalOngletService;

    public MobInternationalOngletController(MobInternationalOngletService mobInternationalOngletService) {
        this.mobInternationalOngletService = mobInternationalOngletService;
    }

    @GetMapping
    @checkRoleOnglet
    public ResponseEntity<?> getById(@PathVariable(name = "ongletId") Long ongletId) {
        MobInternationalOnglet mobInternationalOngletById = mobInternationalOngletService.getMobInternationalOngletById(ongletId);
        MobInternationalOngletDto mobInternationalOngletDto = new MobInternationalOngletDto(mobInternationalOngletById);
        return ResponseEntity.ok(mobInternationalOngletDto);
    }

    @PatchMapping
    @checkRoleOnglet
    public ResponseEntity<Void> updatePartiel(@PathVariable(name = "ongletId") Long ongletId,
                                              @RequestBody MobInternationalOngletDto mobInternationalOngletDto) {
        mobInternationalOngletService.updateMobInternationalOngletPartiel(ongletId, mobInternationalOngletDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping(REST_VOYAGE)
    @checkRoleOnglet
    public ResponseEntity<Void> ajouterVoyage(@PathVariable(name = "ongletId") Long ongletId,
                                              @RequestBody VoyageDto voyageDto) {
        mobInternationalOngletService.ajouterVoyage(ongletId, voyageDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(REST_VOYAGE + REST_VOYAGE_ID)
    @checkRoleOnglet
    public ResponseEntity<Void> supprimerVoyage(@PathVariable(name = "ongletId") Long ongletId,
                                                @PathVariable(name = "voyageId") Long voyageId) {
        mobInternationalOngletService.supprimerVoyage(ongletId, voyageId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping(REST_VOYAGE + REST_VOYAGE_ID)
    @checkRoleOnglet
    public ResponseEntity<Void> updateVoyagePartiel(@PathVariable(name = "ongletId") Long ongletId,
                                                    @PathVariable(name = "voyageId") Long voyageId,
                                                    @RequestBody VoyageDto voyageDto) {
        mobInternationalOngletService.updateVoyagePartiel(ongletId, voyageId, voyageDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping(REST_IMPORT_VOYAGES)
    @checkRoleOnglet
    public ResponseEntity<?> importVoyagesFromExcel(@PathVariable(name = "ongletId") Long ongletId,
                                                    @RequestParam("file") MultipartFile file,
                                                    @RequestParam(name = "rajouter", defaultValue = "false") boolean rajouter) {
        mobInternationalOngletService.importVoyagesFromExcel(ongletId, file, rajouter);
        return ResponseEntity.ok(REST_MESSAGE_IMPORT_VOYAGE);
    }

    @GetMapping(REST_RESULTAT)
    @checkRoleOnglet
    public ResponseEntity<?> getMobInternationalResultat(@PathVariable(value = "ongletId") Long ongletId) {
        return ResponseEntity.ok(mobInternationalOngletService.getMobInternationalResultat(ongletId));
    }
}