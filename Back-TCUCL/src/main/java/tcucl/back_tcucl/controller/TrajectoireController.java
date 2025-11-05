package tcucl.back_tcucl.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.validation.annotation.Validated;
import tcucl.back_tcucl.dto.TrajectoireDto;
import tcucl.back_tcucl.service.TrajectoireService;
import tcucl.back_tcucl.dto.TrajectoirePosteDto;
import tcucl.back_tcucl.dto.TrajectoirePosteReglageDto;
import java.util.List;

import static tcucl.back_tcucl.controller.ControllerConstante.*;

@RestController
@RequestMapping(REST_API + REST_TRAJECTOIRE + REST_ENTITE_ID)
public class TrajectoireController {

    private final TrajectoireService trajectoireService;

    public TrajectoireController(TrajectoireService trajectoireService) {
        this.trajectoireService = trajectoireService;
    }

    @PreAuthorize("hasRole('ROLE_SUPERADMIN') or hasRole('ROLE_ENTITE_' + #entiteId)")
    @GetMapping()
    public ResponseEntity<TrajectoireDto> getTrajectoire(@PathVariable("entiteId") Long entiteId) {
        TrajectoireDto dto = trajectoireService.getByEntiteId(entiteId);
        if (dto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    @PreAuthorize("hasRole('ROLE_SUPERADMIN') or hasRole('ROLE_ENTITE_' + #entiteId)")
    @PutMapping
    public ResponseEntity<TrajectoireDto> upsert(
            @PathVariable("entiteId") Long entiteId,
            @Validated @RequestBody TrajectoireDto body
    ) {
        // Sécurise l'entiteId à partir de l'URL
        body.setEntiteId(entiteId);
        // Validations simples (peuvent être renforcées ensuite)
        if (body.getReferenceYear() == null || body.getTargetYear() == null || body.getTargetPercentage() == null) {
            return ResponseEntity.badRequest().build();
        }
        if (body.getReferenceYear() > body.getTargetYear()) {
            return ResponseEntity.badRequest().build();
        }
        if (body.getTargetPercentage() < 0f || body.getTargetPercentage() > 100f) {
            return ResponseEntity.badRequest().build();
        }
        if (body.getPostesReglages() != null) {
            for (TrajectoirePosteReglageDto pr : body.getPostesReglages()) {
                if (pr.getId() == null || pr.getId().isBlank()) {
                    return ResponseEntity.badRequest().build();
                }
                Integer pct = pr.getReductionBasePct();
                if (pct == null || pct < 0 || pct > 100) {
                    return ResponseEntity.badRequest().build();
                }
            }
        }
        TrajectoireDto saved = trajectoireService.upsert(entiteId, body);
        return ResponseEntity.ok(saved);
    }

    @PreAuthorize("hasRole('ROLE_SUPERADMIN') or hasRole('ROLE_ENTITE_' + #entiteId)")
    @GetMapping("/postes-defaults")
    public ResponseEntity<List<TrajectoirePosteDto>> getPostesDefaults(@PathVariable("entiteId") Long entiteId) {
        return ResponseEntity.ok(trajectoireService.getPostesDefaults(entiteId));
    }
}
