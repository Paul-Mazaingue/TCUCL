package tcucl.back_tcucl.service;

import org.springframework.security.core.Authentication;
import tcucl.back_tcucl.dto.ChangePasswordDto;


public interface PermissionService {

    boolean utilisateurPeutChangerMdp(Authentication authentication, ChangePasswordDto changePasswordDto);

    boolean adminPeutModifierUtilisateur(Authentication authentication, Long utilisateurId);
}
