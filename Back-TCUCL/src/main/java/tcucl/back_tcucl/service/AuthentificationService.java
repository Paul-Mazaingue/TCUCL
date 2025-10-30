package tcucl.back_tcucl.service;

import tcucl.back_tcucl.dto.ChangePasswordDto;
import tcucl.back_tcucl.dto.ConnexionDto;
import tcucl.back_tcucl.dto.InscriptionDto;

import java.util.Map;

public interface AuthentificationService {

    void changePassword(ChangePasswordDto requete);

    Map<String, Object> connexion(ConnexionDto connexionDto);
}
