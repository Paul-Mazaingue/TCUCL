package tcucl.back_tcucl.annotationPersonnalisee;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD }) // seulement sur les méthodes
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasAuthority('ROLE_ONGLET_' + #ongletId)")
public @interface checkRoleOnglet {
}
