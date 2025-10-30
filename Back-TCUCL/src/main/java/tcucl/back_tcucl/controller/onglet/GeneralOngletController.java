package tcucl.back_tcucl.controller.onglet;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tcucl.back_tcucl.annotationPersonnalisee.checkRoleOnglet;
import tcucl.back_tcucl.dto.onglet.general.GeneralOngletDto;
import tcucl.back_tcucl.entity.onglet.GeneralOnglet;
import tcucl.back_tcucl.service.GeneralOngletService;

import static tcucl.back_tcucl.controller.ControllerConstante.*;

@RestController
@RequestMapping(REST_API + REST_GENERAL_ONGLET + REST_ONGLET_ID)
public class GeneralOngletController {

    private final GeneralOngletService generalOngletService;

    public GeneralOngletController(GeneralOngletService generalOngletService) {
        this.generalOngletService = generalOngletService;
    }

    @GetMapping()
    @checkRoleOnglet
    public ResponseEntity<?> getGeneralOngletById(@PathVariable(value = "ongletId") Long ongletId) {
        GeneralOnglet generalOngletById = generalOngletService.getGeneralOngletById(ongletId);
        GeneralOngletDto generalOngletDto = new GeneralOngletDto(generalOngletById);
        return ResponseEntity.ok(generalOngletDto);
    }

    @PatchMapping()
    @checkRoleOnglet
    public ResponseEntity<Void> updateGeneralOngletPartiel(@PathVariable(value = "ongletId") Long ongletId,
                                                           @RequestBody GeneralOngletDto generalOngletDto) {
        generalOngletService.updateGeneralOngletPartiel(ongletId, generalOngletDto);
        return ResponseEntity.ok().build();
    }


}
