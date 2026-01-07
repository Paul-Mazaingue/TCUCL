package tcucl.back_tcucl.service.impl;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.regex.Pattern;

import static tcucl.back_tcucl.Constante.CHARACTERE_AUTORISE;
import static tcucl.back_tcucl.Constante.PREMIERE_CONNEXION_FALSE;
import static tcucl.back_tcucl.Constante.PREMIERE_CONNEXION_TRUE;
import static tcucl.back_tcucl.Constante.ROLE_USER;
import tcucl.back_tcucl.dto.ChangePasswordDto;
import tcucl.back_tcucl.dto.InscriptionDto_SuperAdmin;
import tcucl.back_tcucl.dto.ModificationUtilisateurParAdminDto;
import tcucl.back_tcucl.dto.ModificationUtilisateurParUtilisateurDto;
import tcucl.back_tcucl.dto.securite.UtilisateurSecuriteDto;
import tcucl.back_tcucl.entity.Utilisateur;
import tcucl.back_tcucl.exceptionPersonnalisee.EmailDejaPrisException;
import tcucl.back_tcucl.exceptionPersonnalisee.MauvaisAncienMdpException;
import tcucl.back_tcucl.exceptionPersonnalisee.PasswordPolicyException;
import tcucl.back_tcucl.manager.UtilisateurManager;
import tcucl.back_tcucl.service.EmailService;
import tcucl.back_tcucl.service.EntiteService;
import tcucl.back_tcucl.service.UtilisateurService;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    Logger logger = LoggerFactory.getLogger(UtilisateurServiceImpl.class);

    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^a-zA-Z\\d]).{10,}$");

    private final PasswordEncoder passwordEncoder;
    private final UtilisateurManager utilisateurManager;
    private final EntiteService entiteService;
    private final EmailService emailService;

    public UtilisateurServiceImpl(UtilisateurManager utilisateurManager
            , PasswordEncoder passwordEncoder, EntiteService entiteService, EmailService emailService) {
        this.utilisateurManager = utilisateurManager;
        this.passwordEncoder = passwordEncoder;
        this.entiteService = entiteService;
        this.emailService = emailService;
    }

    @Override
    public Boolean isEmailDejaPris(String utilisateurEmail){
        return utilisateurManager.isEmailDejaPris(utilisateurEmail);
    }

    @Override
    public void modifierUtilisateurParUtilisateur(Long utilisateurId, ModificationUtilisateurParUtilisateurDto modificationUtilisateurParUtilisateurDto){
        if(utilisateurManager.isEmailDejaPris(modificationUtilisateurParUtilisateurDto.getEmail()))
        {
            throw new EmailDejaPrisException(modificationUtilisateurParUtilisateurDto.getEmail());
        }
        Utilisateur utilisateur = utilisateurManager.getUtilisateurParId(utilisateurId);
        utilisateur.setNom(modificationUtilisateurParUtilisateurDto.getNom());
        utilisateur.setPrenom(modificationUtilisateurParUtilisateurDto.getPrenom());
        utilisateur.setEmail(modificationUtilisateurParUtilisateurDto.getEmail());
        utilisateurManager.save(utilisateur);
    }

    @Override
    public void modifierUtilisateurParAdmin(Long utilisateurId, ModificationUtilisateurParAdminDto modificationUtilisateurParAdminDto) {
        if(utilisateurManager.isEmailDejaPris(modificationUtilisateurParAdminDto.getEmail()))
        {
            throw new EmailDejaPrisException(modificationUtilisateurParAdminDto.getEmail());
        }
        Utilisateur utilisateur = utilisateurManager.getUtilisateurParId(utilisateurId);
        utilisateur.setNom(modificationUtilisateurParAdminDto.getNom());
        utilisateur.setPrenom(modificationUtilisateurParAdminDto.getPrenom());
        utilisateur.setEmail(modificationUtilisateurParAdminDto.getEmail());
        utilisateur.setEstAdmin(modificationUtilisateurParAdminDto.isEstAdmin());
        utilisateurManager.save(utilisateur);
    }

    @Override
    public Utilisateur getUtilisateurParEmail(String utilisateurEmail) {
        return utilisateurManager.getUtilisateurParEmail(utilisateurEmail);
    }

    @Override
    public Utilisateur getUtilisateurParId(Long utilisateurId) {
        return utilisateurManager.getUtilisateurParId(utilisateurId);
    }

    @Override
    public void inscrireUtilisateur(InscriptionDto_SuperAdmin inscriptionDto_superAdmin){
        //Est ce que le mail est déjà pris
        String email = inscriptionDto_superAdmin.getEmail();
        if (utilisateurManager.isEmailDejaPris(email)){
            throw new EmailDejaPrisException(email);
        }

        //Génération du mot de passe aléatoire
        String mdpAleatoire = genererMdpAleatoire();
        // todo_toProd Supprimer ce log en prod
        logger.info("Mot de passe généré: " + mdpAleatoire);  // Log du mot de passe généré

        //Création de l'utilisateur
        Utilisateur nouvelUtilisateur = new Utilisateur(
                inscriptionDto_superAdmin.getNom(),
                inscriptionDto_superAdmin.getPrenom(),
                passwordEncoder.encode(mdpAleatoire),
                email,
                PREMIERE_CONNEXION_TRUE,
                ROLE_USER,
                inscriptionDto_superAdmin.isEstAdmin(),
                inscriptionDto_superAdmin.isEstSuperAdmin(),
                entiteService.getEntiteById(inscriptionDto_superAdmin.getEntiteId()));

        //envoi du mail
        emailService.sendSimpleEmail(
                inscriptionDto_superAdmin.getPrenom(),
                inscriptionDto_superAdmin.getNom(),
                email,
                mdpAleatoire);
        utilisateurManager.save(nouvelUtilisateur);
    }


    @Override
    public void modifierEstAdmin(Long utilisateurId, Boolean estAdmin) {
        Utilisateur utilisateur = utilisateurManager.getUtilisateurParId(utilisateurId);
        utilisateur.setEstAdmin(estAdmin);
        utilisateurManager.save(utilisateur);
    }

    @Override
    public List<Utilisateur> getAllUtilisateurParEntiteId(Long entiteId) {
        return utilisateurManager.getAllUtilisateurParEntiteId(entiteId);
    }


    @Override
    public void save(Utilisateur utilisateur) {
        utilisateurManager.save(utilisateur);
    }

    @Override
    public void changePassword(ChangePasswordDto changePasswordDto) {
        Utilisateur utilisateur = utilisateurManager.getUtilisateurParEmail(changePasswordDto.getEmail());
        if (!passwordEncoder.matches(changePasswordDto.getAncienMdp(), utilisateur.getMdp())) {
            throw new MauvaisAncienMdpException();
        }
        if (!PASSWORD_PATTERN.matcher(changePasswordDto.getNouveauMdp()).matches()) {
            throw new PasswordPolicyException();
        }
        utilisateur.setMdp(passwordEncoder.encode(changePasswordDto.getNouveauMdp()));
        utilisateur.setEstPremiereConnexion(PREMIERE_CONNEXION_FALSE);
        utilisateur.setPasswordChangedAt(Instant.now());
        utilisateurManager.save(utilisateur);

    }

    @Override
    public UtilisateurSecuriteDto findUtilisateurSecuriteDtoByEmail(String utilisateurEmail) {
        return utilisateurManager.findUtilisateurSecurityDTOByEmail(utilisateurEmail);
    }

    @Override
    public void supprimerUtilisateur(Long utilisateurId) {
        utilisateurManager.supprimerUtilisateur(utilisateurId);
    }

    private String genererMdpAleatoire() {
        String characters = CHARACTERE_AUTORISE;
        Random random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            password.append(characters.charAt(random.nextInt(characters.length())));
        }
        return password.toString();
    }
}
