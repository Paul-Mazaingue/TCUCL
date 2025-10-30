package tcucl.back_tcucl.manager.impl.onglet;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tcucl.back_tcucl.dto.onglet.autreImmobilisation.AutreImmobilisationOngletDto;
import tcucl.back_tcucl.entity.onglet.AutreImmobilisationOnglet;
import tcucl.back_tcucl.entity.onglet.numerique.NumeriqueOnglet;
import tcucl.back_tcucl.exceptionPersonnalisee.OngletNonTrouveIdException;
import tcucl.back_tcucl.exceptionPersonnalisee.ValidationCustomException;
import tcucl.back_tcucl.manager.AutreImmobilisationOngletManager;
import tcucl.back_tcucl.repository.onglet.AutreImmobilisationOngletRepository;

import java.util.Set;

@Component
public class AutreImmobilisationOngletManagerImpl implements AutreImmobilisationOngletManager {

    @Autowired
    private Validator validator;

    private final AutreImmobilisationOngletRepository autreImmobilisationOngletRepository;

    public AutreImmobilisationOngletManagerImpl(AutreImmobilisationOngletRepository autreImmobilisationOngletRepository) {
        this.autreImmobilisationOngletRepository = autreImmobilisationOngletRepository;
    }

    @Override
    public AutreImmobilisationOnglet getAutreImmobilisationOngletById(Long ongletId) {
        return autreImmobilisationOngletRepository.findById(ongletId).orElseThrow(() -> new OngletNonTrouveIdException("AutreImmobilisationOnglet",ongletId));
    }

    @Override
    public void updateAutreImmobilisationOngletPartiel(Long ongletId, AutreImmobilisationOngletDto autreImmobilisationOngletDto) {
        AutreImmobilisationOnglet autreImmobilisationOnglet = getAutreImmobilisationOngletById(ongletId);

        if (autreImmobilisationOngletDto.getNote() != null) {
            autreImmobilisationOnglet.setNote(autreImmobilisationOngletDto.getNote());
        }
        if (autreImmobilisationOngletDto.getEstTermine() != null) {
            autreImmobilisationOnglet.setEstTermine(autreImmobilisationOngletDto.getEstTermine());
        }

        // Installation complète
        if (autreImmobilisationOngletDto.getInstallationComplete_IsEmissionGESConnues() != null)
            autreImmobilisationOnglet.setInstallationComplete_IsEmissionGESConnues(autreImmobilisationOngletDto.getInstallationComplete_IsEmissionGESConnues());
        if (autreImmobilisationOngletDto.getInstallationComplete_EmissionDeGes() != null)
            autreImmobilisationOnglet.setInstallationComplete_EmissionDeGes(autreImmobilisationOngletDto.getInstallationComplete_EmissionDeGes());

        // Panneaux
        if (autreImmobilisationOngletDto.getPanneaux_PuissanceTotale() != null)
            autreImmobilisationOnglet.setPanneaux_PuissanceTotale(autreImmobilisationOngletDto.getPanneaux_PuissanceTotale());
        if (autreImmobilisationOngletDto.getPanneaux_DureeDeVie() != null)
            autreImmobilisationOnglet.setPanneaux_DureeDeVie(autreImmobilisationOngletDto.getPanneaux_DureeDeVie());
        if (autreImmobilisationOngletDto.getPanneaux_IsEmissionGESConnues() != null)
            autreImmobilisationOnglet.setPanneaux_IsEmissionGESConnues(autreImmobilisationOngletDto.getPanneaux_IsEmissionGESConnues());
        if (autreImmobilisationOngletDto.getPanneaux_EmissionDeGes() != null)
            autreImmobilisationOnglet.setPanneaux_EmissionDeGes(autreImmobilisationOngletDto.getPanneaux_EmissionDeGes());

        // Onduleur
        if (autreImmobilisationOngletDto.getOnduleur_PuissanceTotale() != null)
            autreImmobilisationOnglet.setOnduleur_PuissanceTotale(autreImmobilisationOngletDto.getOnduleur_PuissanceTotale());
        if (autreImmobilisationOngletDto.getOnduleur_DureeDeVie() != null)
            autreImmobilisationOnglet.setOnduleur_DureeDeVie(autreImmobilisationOngletDto.getOnduleur_DureeDeVie());
        if (autreImmobilisationOngletDto.getOnduleur_IsEmissionGESConnues() != null)
            autreImmobilisationOnglet.setOnduleur_IsEmissionGESConnues(autreImmobilisationOngletDto.getOnduleur_IsEmissionGESConnues());
        if (autreImmobilisationOngletDto.getOnduleur_EmissionDeGes() != null)
            autreImmobilisationOnglet.setOnduleur_EmissionDeGes(autreImmobilisationOngletDto.getOnduleur_EmissionDeGes());

        // Groupes électrogènes
        if (autreImmobilisationOngletDto.getGroupesElectrogenes_Nombre() != null)
            autreImmobilisationOnglet.setGroupesElectrogenes_Nombre(autreImmobilisationOngletDto.getGroupesElectrogenes_Nombre());
        if (autreImmobilisationOngletDto.getGroupesElectrogenes_PoidsDuProduit() != null)
            autreImmobilisationOnglet.setGroupesElectrogenes_PoidsDuProduit(autreImmobilisationOngletDto.getGroupesElectrogenes_PoidsDuProduit());
        if (autreImmobilisationOngletDto.getGroupesElectrogenes_DureeAmortissement() != null)
            autreImmobilisationOnglet.setGroupesElectrogenes_DureeAmortissement(autreImmobilisationOngletDto.getGroupesElectrogenes_DureeAmortissement());
        if (autreImmobilisationOngletDto.getGroupesElectrogenes_IsEmissionConnue() != null)
            autreImmobilisationOnglet.setGroupesElectrogenes_IsEmissionConnue(autreImmobilisationOngletDto.getGroupesElectrogenes_IsEmissionConnue());
        if (autreImmobilisationOngletDto.getGroupesElectrogenes_EmissionReelle() != null)
            autreImmobilisationOnglet.setGroupesElectrogenes_EmissionReelle(autreImmobilisationOngletDto.getGroupesElectrogenes_EmissionReelle());

        // Moteur électrique
        if (autreImmobilisationOngletDto.getMoteurElectrique_Nombre() != null)
            autreImmobilisationOnglet.setMoteurElectrique_Nombre(autreImmobilisationOngletDto.getMoteurElectrique_Nombre());
        if (autreImmobilisationOngletDto.getMoteurElectrique_PoidsDuProduit() != null)
            autreImmobilisationOnglet.setMoteurElectrique_PoidsDuProduit(autreImmobilisationOngletDto.getMoteurElectrique_PoidsDuProduit());
        if (autreImmobilisationOngletDto.getMoteurElectrique_DureeAmortissement() != null)
            autreImmobilisationOnglet.setMoteurElectrique_DureeAmortissement(autreImmobilisationOngletDto.getMoteurElectrique_DureeAmortissement());
        if (autreImmobilisationOngletDto.getMoteurElectrique_IsEmissionConnue() != null)
            autreImmobilisationOnglet.setMoteurElectrique_IsEmissionConnue(autreImmobilisationOngletDto.getMoteurElectrique_IsEmissionConnue());
        if (autreImmobilisationOngletDto.getMoteurElectrique_EmissionReelle() != null)
            autreImmobilisationOnglet.setMoteurElectrique_EmissionReelle(autreImmobilisationOngletDto.getMoteurElectrique_EmissionReelle());

        // Autres machines (Kg)
        if (autreImmobilisationOngletDto.getAutresMachinesKg_Nombre() != null)
            autreImmobilisationOnglet.setAutresMachinesKg_Nombre(autreImmobilisationOngletDto.getAutresMachinesKg_Nombre());
        if (autreImmobilisationOngletDto.getAutresMachinesKg_PoidsDuProduit() != null)
            autreImmobilisationOnglet.setAutresMachinesKg_PoidsDuProduit(autreImmobilisationOngletDto.getAutresMachinesKg_PoidsDuProduit());
        if (autreImmobilisationOngletDto.getAutresMachinesKg_DureeAmortissement() != null)
            autreImmobilisationOnglet.setAutresMachinesKg_DureeAmortissement(autreImmobilisationOngletDto.getAutresMachinesKg_DureeAmortissement());
        if (autreImmobilisationOngletDto.getAutresMachinesKg_IsEmissionConnue() != null)
            autreImmobilisationOnglet.setAutresMachinesKg_IsEmissionConnue(autreImmobilisationOngletDto.getAutresMachinesKg_IsEmissionConnue());
        if (autreImmobilisationOngletDto.getAutresMachinesKg_EmissionReelle() != null)
            autreImmobilisationOnglet.setAutresMachinesKg_EmissionReelle(autreImmobilisationOngletDto.getAutresMachinesKg_EmissionReelle());

        // Autres machines (Eur)
        if (autreImmobilisationOngletDto.getAutresMachinesEur_Nombre() != null)
            autreImmobilisationOnglet.setAutresMachinesEur_Nombre(autreImmobilisationOngletDto.getAutresMachinesEur_Nombre());
        if (autreImmobilisationOngletDto.getAutresMachinesEur_PoidsDuProduit() != null)
            autreImmobilisationOnglet.setAutresMachinesEur_PoidsDuProduit(autreImmobilisationOngletDto.getAutresMachinesEur_PoidsDuProduit());
        if (autreImmobilisationOngletDto.getAutresMachinesEur_DureeAmortissement() != null)
            autreImmobilisationOnglet.setAutresMachinesEur_DureeAmortissement(autreImmobilisationOngletDto.getAutresMachinesEur_DureeAmortissement());
        if (autreImmobilisationOngletDto.getAutresMachinesEur_IsEmissionConnue() != null)
            autreImmobilisationOnglet.setAutresMachinesEur_IsEmissionConnue(autreImmobilisationOngletDto.getAutresMachinesEur_IsEmissionConnue());
        if (autreImmobilisationOngletDto.getAutresMachinesEur_EmissionReelle() != null)
            autreImmobilisationOnglet.setAutresMachinesEur_EmissionReelle(autreImmobilisationOngletDto.getAutresMachinesEur_EmissionReelle());


        Set<ConstraintViolation<AutreImmobilisationOnglet>> violations = validator.validate(autreImmobilisationOnglet);
        if(!violations.isEmpty()) {
            throw new ValidationCustomException(violations);
        }
        autreImmobilisationOngletRepository.save(autreImmobilisationOnglet);
    }

}
