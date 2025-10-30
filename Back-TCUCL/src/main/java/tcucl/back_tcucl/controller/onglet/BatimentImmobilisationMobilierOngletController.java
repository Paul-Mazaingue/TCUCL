package tcucl.back_tcucl.controller.onglet;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tcucl.back_tcucl.annotationPersonnalisee.checkRoleOnglet;
import tcucl.back_tcucl.dto.onglet.batimentImmobilisationMobilier.BatimentExistantOuNeufConstruitDto;
import tcucl.back_tcucl.dto.onglet.batimentImmobilisationMobilier.EntretienCourantDto;
import tcucl.back_tcucl.dto.onglet.batimentImmobilisationMobilier.MobilierElectromenagerDto;
import tcucl.back_tcucl.dto.onglet.batimentImmobilisationMobilier.BatimentImmobilisationMobilierOngletDto;
import tcucl.back_tcucl.entity.onglet.batiment.BatimentImmobilisationMobilierOnglet;
import tcucl.back_tcucl.service.BatimentImmobilisationMobilierOngletService;

import static tcucl.back_tcucl.controller.ControllerConstante.*;

@RestController
@RequestMapping(REST_API + REST_BATIMENT_IMMOBILISATION_MOBILIER_ONGLET+REST_ONGLET_ID)
public class BatimentImmobilisationMobilierOngletController {

    private final BatimentImmobilisationMobilierOngletService batimentImmobilisationMobilierOngletService;

    public BatimentImmobilisationMobilierOngletController(BatimentImmobilisationMobilierOngletService batimentImmobilisationMobilierOngletService) {
        this.batimentImmobilisationMobilierOngletService = batimentImmobilisationMobilierOngletService;
    }

    @GetMapping()
    @checkRoleOnglet
    public ResponseEntity<?> getBatimentImmobilisationMobilierOngletById(@PathVariable(name = "ongletId") Long ongletId) {
        BatimentImmobilisationMobilierOnglet batimentImmobilisationMobilierOngletById = batimentImmobilisationMobilierOngletService.getBatimentImmobilisationMobilierOngletById(ongletId);
        BatimentImmobilisationMobilierOngletDto batimentImmobilisationMobilierOngletDto = new BatimentImmobilisationMobilierOngletDto(batimentImmobilisationMobilierOngletById);
        return ResponseEntity.ok(batimentImmobilisationMobilierOngletDto);
    }

    @PatchMapping
    @checkRoleOnglet
    public ResponseEntity<Void> updateBatimentImmobilisationMobilierOnglet(@PathVariable(name = "ongletId") Long ongletId,
                                                                           @RequestBody BatimentImmobilisationMobilierOngletDto batimentImmobilisationMobilierOngletDto) {
        batimentImmobilisationMobilierOngletService.updateBatimentImmobilisationMobilierOnglet(ongletId, batimentImmobilisationMobilierOngletDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping(REST_BATIMENT_EXISTANT_OU_NEUF_CONSTRUIT)
    @checkRoleOnglet
    public ResponseEntity<Void> ajouterBatiment(@PathVariable(name = "ongletId") Long ongletId,
                                                @RequestBody BatimentExistantOuNeufConstruitDto batimentExistantOuNeufConstruitDto) {
        batimentImmobilisationMobilierOngletService.ajouterBatiment(ongletId, batimentExistantOuNeufConstruitDto);
        return ResponseEntity.ok().build();
    }
    @PostMapping(REST_ENTRETIEN_COURANT)
    @checkRoleOnglet
    public ResponseEntity<Void> ajouterEntretienCourant(@PathVariable(name = "ongletId") Long ongletId,
                                                        @RequestBody EntretienCourantDto entretienCourantDto) {
        batimentImmobilisationMobilierOngletService.ajouterEntretienCourant(ongletId, entretienCourantDto);
        return ResponseEntity.ok().build();
    }
    @PostMapping(REST_MOBILIER_ELECTROMENAGER)
    @checkRoleOnglet
    public ResponseEntity<Void> ajouterMobilierElectromenager(@PathVariable(name = "ongletId") Long ongletId,
                                                              @RequestBody MobilierElectromenagerDto mobilierElectromenagerDto) {
        batimentImmobilisationMobilierOngletService.ajouterMobilierElectromenager(ongletId, mobilierElectromenagerDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(REST_BATIMENT_EXISTANT_OU_NEUF_CONSTRUIT + REST_BATIMENT_EXISTANT_OU_NEUF_CONSTRUIT_ID)
    @checkRoleOnglet
    public ResponseEntity<Void> supprimerBatiment(@PathVariable(name = "ongletId") Long ongletId,
                                                  @PathVariable(name = "batimentExistantOuNeufConstruitId") Long batimentId) {
        batimentImmobilisationMobilierOngletService.supprimerBatiment(ongletId, batimentId);
        return ResponseEntity.ok().build();

    }
    @DeleteMapping(REST_ENTRETIEN_COURANT + REST_ENTRETIEN_COURANT_ID)
    @checkRoleOnglet
    public ResponseEntity<Void> supprimerEntretienCourant(@PathVariable(name = "ongletId") Long ongletId,
                                                          @PathVariable(name = "entretienCourantId") Long entretienCourantId) {
        batimentImmobilisationMobilierOngletService.supprimerEntretienCourant(ongletId, entretienCourantId);
        return ResponseEntity.ok().build();

    }
    @DeleteMapping(REST_MOBILIER_ELECTROMENAGER + REST_MOBILIER_ELECTROMENAGER_ID)
    @checkRoleOnglet
    public ResponseEntity<Void> supprimerMobilierElectromenager(@PathVariable(name = "ongletId") Long ongletId,
                                                                @PathVariable(name = "mobilierElectromenagerId") Long mobilierElectromenagerId) {
        batimentImmobilisationMobilierOngletService.supprimerMobilierElectromenager(ongletId, mobilierElectromenagerId);
        return ResponseEntity.ok().build();

    }
    @PatchMapping(REST_BATIMENT_EXISTANT_OU_NEUF_CONSTRUIT + REST_BATIMENT_EXISTANT_OU_NEUF_CONSTRUIT_ID)
    @checkRoleOnglet
    public ResponseEntity<Void> updateBatimentPartiel(@PathVariable(name = "ongletId") Long ongletId,
                                                      @PathVariable(name = "batimentExistantOuNeufConstruitId") Long batimentId,
                                                      @RequestBody BatimentExistantOuNeufConstruitDto batimentExistantOuNeufConstruitDto) {
        batimentImmobilisationMobilierOngletService.updateBatimentPartiel(ongletId, batimentId, batimentExistantOuNeufConstruitDto);
        return ResponseEntity.ok().build();
    }
    @PatchMapping(REST_ENTRETIEN_COURANT + REST_ENTRETIEN_COURANT_ID)
    @checkRoleOnglet
    public ResponseEntity<Void> updateEntretienCourantPartiel(@PathVariable(name = "ongletId") Long ongletId,
                                                              @PathVariable(name = "entretienCourantId") Long entretienCourantId,
                                                              @RequestBody EntretienCourantDto entretienCourantDto) {
        batimentImmobilisationMobilierOngletService.updateEntretienCourantPartiel(ongletId, entretienCourantId, entretienCourantDto);
        return ResponseEntity.ok().build();
    }
    @PatchMapping(REST_MOBILIER_ELECTROMENAGER + REST_MOBILIER_ELECTROMENAGER_ID)
    @checkRoleOnglet
    public ResponseEntity<Void> updateMobilierElectromenagerPartiel(@PathVariable(name = "ongletId") Long ongletId,
                                                                    @PathVariable(name = "mobilierElectromenagerId") Long mobilierElectromenagerId,
                                                                    @RequestBody MobilierElectromenagerDto mobilierElectromenagerDto) {
        batimentImmobilisationMobilierOngletService.updateMobilierElectromenagerPartiel(ongletId, mobilierElectromenagerId, mobilierElectromenagerDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping(REST_RESULTAT)
    @checkRoleOnglet
    public ResponseEntity<?> getBatimentImmobilisationMobilierResult(@PathVariable(value = "ongletId") Long ongletId) {
        return ResponseEntity.ok(batimentImmobilisationMobilierOngletService.getBatimentImmobilisationMobilierResult(ongletId));
    }
}
