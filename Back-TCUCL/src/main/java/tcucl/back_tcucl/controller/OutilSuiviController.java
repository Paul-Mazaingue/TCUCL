package tcucl.back_tcucl.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tcucl.back_tcucl.dto.OutilSuiviResponseDto;
import tcucl.back_tcucl.service.OutilSuiviService;

import static tcucl.back_tcucl.controller.ControllerConstante.*;

@RestController
@RequestMapping(REST_API + REST_OUTIL_SUIVI + REST_ENTITE_ID)
public class OutilSuiviController {

    private final OutilSuiviService outilSuiviService;

    public OutilSuiviController(OutilSuiviService outilSuiviService) {
        this.outilSuiviService = outilSuiviService;
    }

    @PreAuthorize("hasAuthority('ROLE_SUPERADMIN') or hasAuthority('ROLE_ENTITE_' + #p0)")
    @GetMapping
    public ResponseEntity<OutilSuiviResponseDto> get(@PathVariable("entiteId") Long entiteId) {
        return ResponseEntity.ok(outilSuiviService.loadForEntite(entiteId));
    }
}
