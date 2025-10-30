package tcucl.back_tcucl.service;


import tcucl.back_tcucl.dto.*;
import tcucl.back_tcucl.dto.securite.UtilisateurSecuriteDto;
import tcucl.back_tcucl.entity.Utilisateur;

import java.util.List;

public interface UtilisateurService {

    void modifierEstAdmin(Long utilisateurId, Boolean estAdmin);

    void changePassword(ChangePasswordDto changePasswordDto);

    void save(Utilisateur utilisateur);

    void modifierUtilisateurParUtilisateur(Long utilisateurId, ModificationUtilisateurParUtilisateurDto modificationUtilisateurParUtilisateurDto);

    void modifierUtilisateurParAdmin(Long utilisateurId, ModificationUtilisateurParAdminDto modificationUtilisateurParAdminDto);

    void inscrireUtilisateur(InscriptionDto_SuperAdmin inscriptionDto_superAdmin);

    void supprimerUtilisateur(Long utilisateurId);

    Utilisateur getUtilisateurParEmail(String utilisateurEmail);

    Utilisateur getUtilisateurParId(Long utilisateurId);

    List<Utilisateur> getAllUtilisateurParEntiteId(Long entiteId);

    UtilisateurSecuriteDto findUtilisateurSecuriteDtoByEmail(String utilisateurEmail);

    Boolean isEmailDejaPris(String utilisateurEmail);

}
