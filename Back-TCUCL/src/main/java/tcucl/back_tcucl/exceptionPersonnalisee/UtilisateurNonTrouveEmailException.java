package tcucl.back_tcucl.exceptionPersonnalisee;


import static tcucl.back_tcucl.Constante.ERREUR_UTILISATEUR_NON_TROUVE_MAIL;

public class UtilisateurNonTrouveEmailException extends NonTrouveGeneralCustomException {

    public UtilisateurNonTrouveEmailException(String utilisateurEmail) {
        super(ERREUR_UTILISATEUR_NON_TROUVE_MAIL + utilisateurEmail);
    }
}
