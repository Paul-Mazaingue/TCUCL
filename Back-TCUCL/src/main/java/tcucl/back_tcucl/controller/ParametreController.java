package tcucl.back_tcucl.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tcucl.back_tcucl.config.AnneeConfig;
import tcucl.back_tcucl.dto.*;
import tcucl.back_tcucl.entity.Utilisateur;
import tcucl.back_tcucl.exceptionPersonnalisee.AnneeUniversitaireDejaCreeException;
import tcucl.back_tcucl.service.FacteurEmissionService;
import tcucl.back_tcucl.service.ParametreService;

import java.util.List;

import static tcucl.back_tcucl.Constante.SUPERADMIN_FALSE;
import static tcucl.back_tcucl.controller.ControllerConstante.*;

@RestController
@RequestMapping(REST_API + REST_PARAMETRE)
public class ParametreController {

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(ParametreController.class);

    private final ParametreService parametreService;
    private final FacteurEmissionService facteurEmissionService;

    public ParametreController(ParametreService parametreService, FacteurEmissionService facteurEmissionService) {
        this.parametreService = parametreService;
        this.facteurEmissionService = facteurEmissionService;
    }

    //    Parametre perso
    @PreAuthorize("hasRole('ROLE_UTILISATEUR_' + #utilisateurId)")
    @PatchMapping(REST_MODIFIER_UTILISATEUR_UTILISATEUR + REST_UTILISATEUR_ID)
    public ResponseEntity<?> modifierUtilisateurParUtilisateur(@PathVariable("utilisateurId") Long utilisateurId, @RequestBody ModificationUtilisateurParUtilisateurDto modificationUtilisateurParUtilisateurDto) {
        parametreService.modifierUtilisateurParUtilisateur(utilisateurId, modificationUtilisateurParUtilisateurDto);
        return ResponseEntity.ok(REST_MESSAGE_UTILISATEUR_MODIFIE);
    }


    @PreAuthorize("@permissionService.utilisateurPeutChangerMdp(authentication, #changePasswordDto)")
    @PostMapping(REST_CHANGE_MDP)
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordDto changePasswordDto) {
        parametreService.changePassword(changePasswordDto);
        return ResponseEntity.ok(REST_MESSAGE_MDP_BIEN_MIS_A_JOUR);
    }

    //    Parametre Admin
    @PreAuthorize("hasRole('ROLE_SUPERADMIN') or (hasRole('ROLE_ADMIN_'+ #entiteId ) )")
    @PostMapping(REST_INSCRIRE_UTILISATEUR + REST_ENTITE_ID)
    public ResponseEntity<?> inscrireUtilisateur(@RequestBody InscriptionDto inscriptionDto,
                                                  @PathVariable("entiteId") Long entiteId) {
        parametreService.inscrireUtilisateur(new InscriptionDto_SuperAdmin(inscriptionDto, SUPERADMIN_FALSE, entiteId));
        return ResponseEntity.ok(REST_MESSAGE_UTILISATEUR_BIEN_INSCRIT);
    }

    @PreAuthorize("hasRole('ROLE_SUPERADMIN') or (hasRole('ROLE_ADMIN') and @permissionService.adminPeutModifierUtilisateur(authentication, #utilisateurId))")
    @PatchMapping(REST_MODIFIER_UTILISATEUR_ADMIN + REST_UTILISATEUR_ID)
    public ResponseEntity<?> modifierUtilisateurParAdmin(@PathVariable("utilisateurId") Long utilisateurId, @RequestBody ModificationUtilisateurParAdminDto modificationUtilisateurParAdminDto) {
        parametreService.modifierUtilisateurParAdmin(utilisateurId, modificationUtilisateurParAdminDto);
        return ResponseEntity.ok(REST_MESSAGE_UTILISATEUR_MODIFIE);
    }

    @PreAuthorize("hasRole('ROLE_SUPERADMIN') or (hasRole('ROLE_ADMIN') and @permissionService.adminPeutModifierUtilisateur(authentication, #utilisateurId))")
    @PatchMapping(REST_MODIFIER_EST_ADMIN + REST_UTILISATEUR_ID)
    public ResponseEntity<?> modifierEstAdmin(@PathVariable("utilisateurId") Long utilisateurId, @RequestParam(value = "estAdmin") boolean estAdmin) {
        parametreService.modifierEstAdmin(utilisateurId, estAdmin);
        return ResponseEntity.ok(REST_MESSAGE_CHANGE_STATUT_ADMIN);
    }

    @PreAuthorize("hasRole('ROLE_SUPERADMIN') or (hasRole('ROLE_ADMIN') and @permissionService.adminPeutModifierUtilisateur(authentication, #utilisateurId))")
    @DeleteMapping(REST_SUPPRIMER_UTILISATEUR + REST_UTILISATEUR_ID)
    public ResponseEntity<?> supprimerUtilisateur(@PathVariable("utilisateurId") Long utilisateurId) {
        parametreService.supprimerUtilisateur(utilisateurId);
        return ResponseEntity.ok(REST_MESSAGE_UTILISATEUR_SUPPRIME);
    }

    //Parametre Super Admin
    @PreAuthorize("hasRole('ROLE_SUPERADMIN')")
    @PostMapping(REST_CREER_ENTITE)
    public ResponseEntity<?> creerEntite(@RequestBody CreationEntiteEtAdminDto creationEntiteEtAdminDto) {
        parametreService.creerEntiteEtAdmin(new CreationEntiteEtAdminDto_SuperAdmin(creationEntiteEtAdminDto, SUPERADMIN_FALSE));
        return ResponseEntity.ok(REST_MESSAGE_ENTITE_CREEE);
    }

    @PreAuthorize("hasRole('ROLE_SUPERADMIN')")
    @PostMapping(REST_IMPORT_FACTEURS_EMISSION)
    public ResponseEntity<?> importFacteurEmissionFromExcel(@RequestParam("file") MultipartFile file) {
        facteurEmissionService.importFacteurEmissionFromExcel(file);
        return ResponseEntity.ok(REST_MESSAGE_IMPORT_FACTEURS_EMISSION);
    }

    @PreAuthorize("hasRole('ROLE_SUPERADMIN')")
    @PostMapping(REST_CREER_ANNEE_SUIVANTE)
    public ResponseEntity<?> creerAnneeSuivante() {
        try {
            parametreService.creerAnneeSuivante();
        } catch (AnneeUniversitaireDejaCreeException e) {
            logger.warn("PARAMETRE CONTROLLER : L'année universitaire {} est déjà créée pour toutes les entités, aucune action effectuée.", AnneeConfig.getAnneeCourante());
            throw e;
        } catch (Exception e) {
            if(e instanceof AnneeUniversitaireDejaCreeException) {
                throw e; // Lancement de l'exception pour que le message soit géré par le GestionnaireErreurController
            }else{
                logger.error("PARAMETRE CONTROLLER : Erreur lors de la création de l'année universitaire pour toutes les entités: {}", e.getMessage());
            }
        }
        return ResponseEntity.ok(REST_MESSAGE_ANNEE_SUIVANTE_CREEE);
    }

    //Initialisation
    @PreAuthorize("hasRole('ROLE_SUPERADMIN') or (hasRole('ROLE_ENTITE_' + #entiteId))")
    @GetMapping(REST_UTILISATEUR_ENTITE + REST_ENTITE_ID)
    public ResponseEntity<?> getAllUtilisateurParEntiteId(@PathVariable("entiteId") Long entiteId) {
        List<Utilisateur> allUtilisateurParEntiteId = parametreService.getAllUtilisateurParEntiteId(entiteId);
        List<UtilisateurDto> allUtilisateurParEntiteIdDto = allUtilisateurParEntiteId.stream().map(UtilisateurDto::new).toList();
        return ResponseEntity.ok(allUtilisateurParEntiteIdDto);
    }

    @PreAuthorize("hasRole('ROLE_SUPERADMIN')")
    @GetMapping(REST_PEUT_CREER_NOUVELLE_ANNEE)
    public ResponseEntity<?> peutCreerUneNouvelleAnnee() {
        return ResponseEntity.ok(parametreService.peutCreerUneNouvelleAnnee());
    }

    @PreAuthorize("hasRole('ROLE_SUPERADMIN')")
    @GetMapping(REST_ALL_ENTITE_NOM_ID)
    public ResponseEntity<?> getAllEntiteNomId() {
        List<EntiteNomIdDto> allEntiteNomId = parametreService.getAllEntiteNomId();
        return ResponseEntity.ok(allEntiteNomId);
    }

}
