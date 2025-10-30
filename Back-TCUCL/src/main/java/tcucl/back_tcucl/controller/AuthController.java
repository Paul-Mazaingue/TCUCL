package tcucl.back_tcucl.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tcucl.back_tcucl.dto.*;
import tcucl.back_tcucl.service.AuthentificationService;
import tcucl.back_tcucl.service.ParametreService;

import static tcucl.back_tcucl.Constante.SUPERADMIN_FALSE;
import static tcucl.back_tcucl.controller.ControllerConstante.*;

@RestController
@RequestMapping(REST_API + REST_AUTH)
public class AuthController {

    private final AuthentificationService authentificationService;

    public AuthController(AuthentificationService authentificationService) {
        this.authentificationService = authentificationService;
    }

    //connexion de base
    @PostMapping(REST_CONNEXION)
    public ResponseEntity<?> connexion(@RequestBody ConnexionDto connexionDto) {
        return ResponseEntity.ok(authentificationService.connexion(connexionDto));
    }

    //processus d'inscription
    @PostMapping(REST_CHANGE_MDP_PREMIERE_CONNEXION)
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordDto changePasswordDto) {
        authentificationService.changePassword(changePasswordDto);
        return ResponseEntity.ok(REST_MESSAGE_MDP_BIEN_MIS_A_JOUR_PREMIERE_CONNEXION);

    }


}

