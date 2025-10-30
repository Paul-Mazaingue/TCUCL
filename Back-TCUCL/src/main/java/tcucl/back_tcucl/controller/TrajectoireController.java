package tcucl.back_tcucl.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static tcucl.back_tcucl.controller.ControllerConstante.*;

@RestController
@RequestMapping(REST_API + REST_TRAJECTOIRE + REST_ENTITE_ID)
public class TrajectoireController {

    @PreAuthorize("hasRole('ROLE_SUPERADMIN') or hasRole('ROLE_ENTITE_' + #entiteId)")
    @GetMapping()
    public String getTrajectoire(@PathVariable(value = "entiteId") Long entiteId) {
        // This method should return the trajectory for the given entity ID.
        // The actual implementation would depend on the business logic and data source.
        return "Trajectoire for entity ID: " + entiteId;
    }
}
