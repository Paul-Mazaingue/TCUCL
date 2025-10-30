package tcucl.back_tcucl.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tcucl.back_tcucl.service.SyntheseEGESService;

import static tcucl.back_tcucl.controller.ControllerConstante.*;

@RestController
@RequestMapping(REST_API + REST_SYNTHESE_EGES)
public class SyntheseEGESController {
    private final SyntheseEGESService syntheseEGESService;

    public SyntheseEGESController(SyntheseEGESService syntheseEGESService) {
        this.syntheseEGESService = syntheseEGESService;
    }

    @GetMapping(REST_ENTITE_ID)
    @PreAuthorize("hasRole('ROLE_SUPERADMIN') or hasRole('ROLE_ENTITE_' + #entiteId)")
    public ResponseEntity<?> getSyntheseEGESResultat(@PathVariable(value = "entiteId") Long entiteId, @RequestParam(name = "annee") Integer annee) {
        return ResponseEntity.ok(syntheseEGESService.getSyntheseEGESResultat(entiteId, annee));
    }
}
