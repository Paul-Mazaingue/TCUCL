package tcucl.back_tcucl.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tcucl.back_tcucl.dto.*;
import tcucl.back_tcucl.service.AuthentificationService;
import tcucl.back_tcucl.service.PasswordResetService;

import static tcucl.back_tcucl.Constante.SUPERADMIN_FALSE;
import static tcucl.back_tcucl.Constante.MESSAGE_RESET_PASSWORD_REQUEST;
import static tcucl.back_tcucl.Constante.MESSAGE_RESET_PASSWORD_SUCCESS;
import static tcucl.back_tcucl.controller.ControllerConstante.*;

@RestController
@RequestMapping(REST_API + REST_AUTH)
public class AuthController {

    private final AuthentificationService authentificationService;
    private final PasswordResetService passwordResetService;

    public AuthController(AuthentificationService authentificationService, PasswordResetService passwordResetService) {
        this.authentificationService = authentificationService;
        this.passwordResetService = passwordResetService;
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

    @PostMapping(REST_FORGOT_PASSWORD)
    public ResponseEntity<String> requestPasswordReset(@RequestBody PasswordResetRequestDto requestDto, HttpServletRequest httpServletRequest) {
        String requestIp = httpServletRequest.getRemoteAddr();
        String userAgent = httpServletRequest.getHeader("User-Agent");
        passwordResetService.requestReset(requestDto, requestIp, userAgent);
        return ResponseEntity.ok(MESSAGE_RESET_PASSWORD_REQUEST);
    }

    @PostMapping(REST_RESET_PASSWORD)
    public ResponseEntity<String> confirmPasswordReset(@RequestBody PasswordResetConfirmDto confirmDto) {
        passwordResetService.confirmReset(confirmDto);
        return ResponseEntity.ok(MESSAGE_RESET_PASSWORD_SUCCESS);
    }


}

