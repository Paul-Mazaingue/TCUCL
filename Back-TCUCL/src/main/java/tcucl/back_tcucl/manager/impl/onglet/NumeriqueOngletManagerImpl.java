package tcucl.back_tcucl.manager.impl.onglet;


import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tcucl.back_tcucl.dto.onglet.numerique.EquipementNumeriqueDto;
import tcucl.back_tcucl.dto.onglet.numerique.NumeriqueOngletDto;
import tcucl.back_tcucl.entity.onglet.mobInternationale.MobInternationalOnglet;
import tcucl.back_tcucl.entity.onglet.numerique.NumeriqueOnglet;
import tcucl.back_tcucl.entity.onglet.numerique.EquipementNumerique;
import tcucl.back_tcucl.exceptionPersonnalisee.ElementNontrouveException;
import tcucl.back_tcucl.exceptionPersonnalisee.OngletNonTrouveIdException;
import tcucl.back_tcucl.exceptionPersonnalisee.ValidationCustomException;
import tcucl.back_tcucl.manager.NumeriqueOngletManager;
import tcucl.back_tcucl.repository.onglet.NumeriqueOngletRepository;

import java.util.Set;

@Component
public class NumeriqueOngletManagerImpl implements NumeriqueOngletManager {
    
    @Autowired
    private Validator validator;

    public final NumeriqueOngletRepository numeriqueOngletRepository;

    public NumeriqueOngletManagerImpl(NumeriqueOngletRepository numeriqueOngletRepository) {
        this.numeriqueOngletRepository = numeriqueOngletRepository;
    }

    @Override
    public NumeriqueOnglet getNumeriqueOngletById(Long ongletId) {
        return numeriqueOngletRepository.findById(ongletId).orElseThrow(
                () -> new OngletNonTrouveIdException("Numerique", ongletId));
    }

    @Override
    public EquipementNumerique getEquipementNumeriqueById(Long ongletId, Long equipementId) {
        NumeriqueOnglet numeriqueOnglet = getNumeriqueOngletById(ongletId);
        return numeriqueOnglet.getEquipementNumeriqueList().stream()
                .filter(m -> m.getId().equals(equipementId))
                .findFirst()
                .orElseThrow(() -> new ElementNontrouveException("Equipement Numérique",equipementId));
    }

    @Override
    public void updateNumeriqueOngletPartiel(Long ongletId, NumeriqueOngletDto numeriqueOngletDto) {
        NumeriqueOnglet numeriqueOnglet = getNumeriqueOngletById(ongletId);

        if (numeriqueOngletDto.getEstTermine() != null)
            numeriqueOnglet.setEstTermine(numeriqueOngletDto.getEstTermine());
        if (numeriqueOngletDto.getNote() != null) numeriqueOnglet.setNote(numeriqueOngletDto.getNote());

        if (numeriqueOngletDto.getUseMethodSimplifiee() != null)
            numeriqueOnglet.setUseMethodSimplifiee(numeriqueOngletDto.getUseMethodSimplifiee());
        if (numeriqueOngletDto.getTraficCloudUtilisateur() != null)
            numeriqueOnglet.setTraficCloudUtilisateur(numeriqueOngletDto.getTraficCloudUtilisateur());
        if (numeriqueOngletDto.getTraficTipUtilisateur() != null)
            numeriqueOnglet.setTraficTipUtilisateur(numeriqueOngletDto.getTraficTipUtilisateur());
        if (numeriqueOngletDto.getPartTraficFranceEtranger() != null)
            numeriqueOnglet.setPartTraficFranceEtranger(numeriqueOngletDto.getPartTraficFranceEtranger());


        if (numeriqueOngletDto.getEquipementNumeriqueList() != null) {
            // On supprime les equipementNumeriques existants et on les remplace par les nouveaux
            numeriqueOnglet.getEquipementNumeriqueList().clear();
            for (EquipementNumeriqueDto equipementNumeriqueDto : numeriqueOngletDto.getEquipementNumeriqueList()) {
                numeriqueOnglet.ajouterEquipementNumeriqueViaDto(equipementNumeriqueDto);
            }
        }
        
        Set<ConstraintViolation<NumeriqueOnglet>> violations = validator.validate(numeriqueOnglet);
        if(!violations.isEmpty()) {
            throw new ValidationCustomException(violations);
        }
        numeriqueOngletRepository.save(numeriqueOnglet);
    }

    @Override
    public void ajouterEquipementNumerique(Long ongletId, EquipementNumeriqueDto equipementNumeriqueDto) {
        NumeriqueOnglet numeriqueOnglet = getNumeriqueOngletById(ongletId);
        numeriqueOnglet.ajouterEquipementNumeriqueViaDto(equipementNumeriqueDto);
        
        Set<ConstraintViolation<NumeriqueOnglet>> violations = validator.validate(numeriqueOnglet);
        if(!violations.isEmpty()) {
            throw new ValidationCustomException(violations);
        }
        numeriqueOngletRepository.save(numeriqueOnglet);
    }

    @Override
    public void supprimerEquipementNumerique(Long ongletId, Long equipementId) {
        NumeriqueOnglet numeriqueOnglet = getNumeriqueOngletById(ongletId);

        EquipementNumerique equipementNumerique = numeriqueOnglet.getEquipementNumeriqueList()
                .stream()
                .filter(v -> v.getId().equals(equipementId))
                .findFirst()
                .orElseThrow(() -> new ElementNontrouveException("Equipement Numérique",equipementId));

        numeriqueOnglet.getEquipementNumeriqueList().remove(equipementNumerique);

        
        Set<ConstraintViolation<NumeriqueOnglet>> violations = validator.validate(numeriqueOnglet);
        if(!violations.isEmpty()) {
            throw new ValidationCustomException(violations);
        }
        numeriqueOngletRepository.save(numeriqueOnglet);
    }

    @Override
    public void updateEquipementNumeriquePartiel(Long ongletId, Long equipementId, EquipementNumeriqueDto equipementNumeriqueDto) {
        NumeriqueOnglet numeriqueOnglet = getNumeriqueOngletById(ongletId);

        EquipementNumerique equipementNumerique = numeriqueOnglet.getEquipementNumeriqueList()
                .stream()
                .filter(v -> v.getId().equals(equipementId))
                .findFirst()
                .orElseThrow(() -> new ElementNontrouveException("Equipement Numérique",equipementId));

        if (equipementNumeriqueDto.getEquipement() != null)
            equipementNumerique.setEquipement(equipementNumeriqueDto.getEquipement());
        if (equipementNumeriqueDto.getNombre() != null)
            equipementNumerique.setNombre(equipementNumeriqueDto.getNombre());
        if (equipementNumeriqueDto.getDureeAmortissement() != null)
            equipementNumerique.setDureeAmortissement(equipementNumeriqueDto.getDureeAmortissement());
        if (equipementNumeriqueDto.getEmissionsGesPrecisesConnues() != null)
            equipementNumerique.setEmissionsGesPrecisesConnues(equipementNumeriqueDto.getEmissionsGesPrecisesConnues());
        if (equipementNumeriqueDto.getEmissionsReellesParProduitKgCO2e() != null)
            equipementNumerique.setEmissionsReellesParProduitKgCO2e(equipementNumeriqueDto.getEmissionsReellesParProduitKgCO2e());

        numeriqueOnglet.getEquipementNumeriqueList().add(equipementNumerique);

        
        Set<ConstraintViolation<NumeriqueOnglet>> violations = validator.validate(numeriqueOnglet);
        if(!violations.isEmpty()) {
            throw new ValidationCustomException(violations);
        }
        numeriqueOngletRepository.save(numeriqueOnglet);
    }
}
