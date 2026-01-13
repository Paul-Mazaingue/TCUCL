package tcucl.back_tcucl;

public class Constante {

    private Constante() {
        // Constructeur privé pour empêcher l'instanciation
    }

    



    //MESSAGE
    public static final String MESSAGE_PREMIERE_CONNEXION = "Vous devez changer votre mot de passe.";

    //ERREUR 
    public static final String ERREUR_EMAIL_OU_MDP_INVALIDE = "L'adresse e-mail ou le mot de passe est invalide.";
    public static final String ERREUR_AUTHENTIFICATION = "Une erreur est survenue lors de la connexion";
    public static final String ERREUR_EMAIL_DEJA_PRIS = "L'adresse e-mail est déjà utilisé pour un compte : ";
    public static final String ERREUR_UTILISATEUR_NON_TROUVE = "Utilisateur non trouvé en base.";
    public static final String ERREUR_MAUVAIS_ANCIEN_MDP = "Ancien mot de passe incorrect.";
    public static final String ERREUR_PASSWORD_POLICY = "Le mot de passe doit contenir au moins 10 caractères, une majuscule, une minuscule, un chiffre et un caractère spécial.";
    public static final String ERREUR_RESET_TOKEN_INVALIDE = "Lien de réinitialisation invalide.";
    public static final String ERREUR_RESET_TOKEN_EXPIRE = "Lien de réinitialisation expiré.";
    public static final String ERREUR_RESET_PASSWORD_RATE_LIMIT = "Trop de demandes de réinitialisation. Réessayez dans quelques minutes.";
    public static final String ERREUR_UTILISATEUR_NON_TROUVE_ID = "Utilisateur introuvable avec l'Id: ";
    public static final String ERREUR_UTILISATEUR_NON_TROUVE_MAIL = "Utilisateur introuvable avec le mail : ";
    public static final String ERREUR_ENTITE_NON_TROUVE_ID = "Entite introuvable avec l'Id: ";
    public static final String ERREUR_ENTITE_EXISTE_DEJA = "L'entité existe déjà avec ce nom et ce type.";
    public static final String ERREUR_ENTITE_NON_TROUVE = "Entite non trouvée en base";
    public static final String ERREUR_AUCUN_BATIMENT_CREE = "Aucun batiment n'est crée ou aucun n'a de surface";
    public static final String ERREUR_AUCUN_SALARIE_ENREGISTRE = "Aucun salarié n'est enregistré dans l'application pour cette année";
    public static final String ERREUR_AUCUN_ETUDIANT_ENREGISTRE = "Aucun étudiant n'est enregistré dans l'application pour cette année";
    public static final String ERREUR_NON_TROUVE_ID = " non trouvé en base avec l'id : ";
    public static final String ERREUR_VOYAGE_EXISTE_DEJA = "Un voyage existe déjà pour cette destination : ";
    public static final String ERREUR_INTERNE = "Une erreur interne s'est produite.";
    //AUTRE
    public static final String JETON = "jeton";
    public static final String MESSAGE = "message";
    public static final String TYPE = "type";
    public static final String BEARER = "Bearer";
    public static final String CHARACTERE_AUTORISE = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+";
    public static final String ROLE_USER = "ROLE_USER";
    public static final Boolean PREMIERE_CONNEXION_TRUE = true;
    public static final Boolean PREMIERE_CONNEXION_FALSE = false;
    public static final Boolean SUPERADMIN_FALSE = false;
    public static final Boolean SUPERADMIN_TRUE = true;
    public static final Boolean ADMIN_FALSE = false;
    public static final Boolean ADMIN_TRUE = true;

//Password reset / mail
    public static final String MESSAGE_RESET_PASSWORD_REQUEST = "Si un compte correspond à cette adresse, un email de réinitialisation a été envoyé.";
    public static final String MESSAGE_RESET_PASSWORD_SUCCESS = "Le mot de passe a été réinitialisé.";
    public static final String MAIL_SUJET_RESET_PASSWORD = "Réinitialisation de votre mot de passe TCUCL";
    public static final String MAIL_MESSAGE_RESET_PASSWORD_PREFIX = "Bonjour ";
    public static final String MAIL_MESSAGE_RESET_PASSWORD_BODY = ",\n\nVous avez demandé à réinitialiser votre mot de passe sur TCUCL.\nCliquez sur le lien ci-dessous (valable 10 minutes) pour définir un nouveau mot de passe :\n";
    public static final String MAIL_MESSAGE_RESET_PASSWORD_SUFFIX = "\n\nSi vous n'êtes pas à l'origine de cette demande, vous pouvez ignorer cet email.\n\nCordialement,\nL'équipe TCUCL\n\nCe mail est envoyé depuis une boîte non surveillée. En cas de problème, contactez gregoire.destombes@univ-catholille.fr.";


//Message Mail
    public static final String MAIL_SUJET_INSCRIPTION_DEBUT = "Création de votre compte sur TCUCL";
    public static final String MAIL_MESSAGE_INSCRIPTION_DEBUT = "Bonjour ";
    public static final String MAIL_MESSAGE_INSCRIPTION_MILIEU = ",\n\n" +
            "Bienvenue sur TCUCL, votre compte a été créé avec succès.\n" +
            "Pour activer votre compte, connectez-vous avec votre e-mail et ce mot de passe : ";
    public static final String MAIL_MESSAGE_INSCRIPTION_FIN = "\n\n" +
                    "Cordialement,\n" +
                    "L'équipe TCUCL\n\n" +
                    "Ce mail est envoyé depuis une boîte non surveillée. En cas de problème, contactez gregoire.destombes@univ-catholille.fr.";



//
    //ENDPOINTS

    //MESSAGE

    //ERREUR

    //AUTRE


}
