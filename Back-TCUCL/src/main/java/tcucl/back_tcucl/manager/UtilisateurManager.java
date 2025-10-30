package tcucl.back_tcucl.manager;

import tcucl.back_tcucl.dto.securite.UtilisateurSecuriteDto;
import tcucl.back_tcucl.entity.Utilisateur;

import java.util.List;

public interface UtilisateurManager {

    void supprimerUtilisateur(Long utilisateurId);

    List<Utilisateur> getAllUtilisateurParEntiteId(Long entiteId);

    boolean isEmailDejaPris(String utilisateurEmail);

    Utilisateur save(Utilisateur utilisateur);

    Utilisateur getUtilisateurParId(Long utilisateurId);

    Utilisateur getUtilisateurParEmail(String utilisateurEmail);

    UtilisateurSecuriteDto findUtilisateurSecurityDTOByEmail(String utilisateurEmail);
}
