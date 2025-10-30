package tcucl.back_tcucl.manager.impl.onglet;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tcucl.back_tcucl.dto.onglet.mobInternational.MobInternationalOngletDto;
import tcucl.back_tcucl.dto.onglet.mobInternational.VoyageDto;
import tcucl.back_tcucl.entity.onglet.mobInternationale.MobInternationalOnglet;
import tcucl.back_tcucl.entity.onglet.mobInternationale.Voyage;
import tcucl.back_tcucl.exceptionPersonnalisee.ElementNontrouveException;
import tcucl.back_tcucl.exceptionPersonnalisee.OngletNonTrouveIdException;
import tcucl.back_tcucl.exceptionPersonnalisee.ValidationCustomException;
import tcucl.back_tcucl.exceptionPersonnalisee.VoyageDejaExistantException;
import tcucl.back_tcucl.manager.MobInternationalOngletManager;
import tcucl.back_tcucl.repository.onglet.MobInternationalOngletRepository;

import java.util.Set;

@Component
public class MobInternationalOngletManagerImpl implements MobInternationalOngletManager {

    @Autowired
    private Validator validator;
    
    private final MobInternationalOngletRepository mobInternationalOngletRepository;

    public MobInternationalOngletManagerImpl(MobInternationalOngletRepository mobInternationalOngletRepository) {
        this.mobInternationalOngletRepository = mobInternationalOngletRepository;
    }

    @Override
    public MobInternationalOnglet getMobInternationalOngletById(Long ongletId) {
        return mobInternationalOngletRepository.findById(ongletId).orElseThrow(
                () -> new OngletNonTrouveIdException("MobInternational", ongletId));
    }

    @Override
    public Voyage getVoyageById(Long ongletId, Long voyageId) {
        MobInternationalOnglet mobInternationalOnglet = getMobInternationalOngletById(ongletId);
        return mobInternationalOnglet.getVoyages().stream()
                .filter(m -> m.getId().equals(voyageId))
                .findFirst()
                .orElseThrow(() -> new ElementNontrouveException("Voyage", voyageId));
    }

    @Override
    public void updateMobInternationalOngletPartiel(Long ongletId, MobInternationalOngletDto mobInternationalOngletDto) {
        MobInternationalOnglet mobInternationalOnglet = getMobInternationalOngletById(ongletId);

        if (mobInternationalOngletDto.getEstTermine() != null)
            mobInternationalOnglet.setEstTermine(mobInternationalOngletDto.getEstTermine());
        if (mobInternationalOngletDto.getNote() != null)
            mobInternationalOnglet.setNote(mobInternationalOngletDto.getNote());

        if (mobInternationalOngletDto.getVoyageVersUneDestinationMobInternationale() != null) {
            // On supprime les voyages existants et on les remplace par les nouveaux
            mobInternationalOnglet.getVoyages().clear();
            for (VoyageDto voyageDto : mobInternationalOngletDto.getVoyageVersUneDestinationMobInternationale()) {
                mobInternationalOnglet.ajouterVoyageViaDto(voyageDto);
            }
        }
        
        Set<ConstraintViolation<MobInternationalOnglet>> violations = validator.validate(mobInternationalOnglet);
        if(!violations.isEmpty()) {
            throw new ValidationCustomException(violations);
        }
        mobInternationalOngletRepository.save(mobInternationalOnglet);
    }

    @Override
    public void ajouterVoyage(Long ongletId, VoyageDto voyageDto) {
        MobInternationalOnglet mobInternationalOnglet = getMobInternationalOngletById(ongletId);

        // Vérification si le voyage (destination) existe déjà
        Voyage existingVoyage = mobInternationalOnglet.getVoyages()
                .stream()
                .filter(v -> v.getPays().equals(voyageDto.getNomPays()))
                .findFirst()
                .orElse(null);

        if(existingVoyage != null) {
            throw new VoyageDejaExistantException(voyageDto.getNomPays());
        }else{
            mobInternationalOnglet.ajouterVoyageViaDto(voyageDto);
            
        Set<ConstraintViolation<MobInternationalOnglet>> violations = validator.validate(mobInternationalOnglet);
        if(!violations.isEmpty()) {
            throw new ValidationCustomException(violations);
        }
        mobInternationalOngletRepository.save(mobInternationalOnglet);
        }

    }

    @Override
    public void supprimerVoyage(Long ongletId, Long voyageId) {
        MobInternationalOnglet mobInternationalOnglet = getMobInternationalOngletById(ongletId);

        Voyage voyage = mobInternationalOnglet.getVoyages()
                .stream()
                .filter(v -> v.getId().equals(voyageId))
                .findFirst()
                .orElseThrow(() -> new ElementNontrouveException("Voyage", voyageId));

        mobInternationalOnglet.getVoyages().remove(voyage);

        
        Set<ConstraintViolation<MobInternationalOnglet>> violations = validator.validate(mobInternationalOnglet);
        if(!violations.isEmpty()) {
            throw new ValidationCustomException(violations);
        }
        mobInternationalOngletRepository.save(mobInternationalOnglet);
    }

    @Override
    public void updateVoyagePartiel(Long ongletId, Long voyageId, VoyageDto voyageDto) {
        MobInternationalOnglet mobInternationalOnglet = getMobInternationalOngletById(ongletId);

        Voyage voyage = mobInternationalOnglet.getVoyages()
                .stream()
                .filter(v -> v.getId().equals(voyageId))
                .findFirst()
                .orElseThrow(() -> new ElementNontrouveException("Voyage", voyageId));

        if (voyageDto.getProsAvion() != null)
            voyage.setProsAvion(voyageDto.getProsAvion());
        if (voyageDto.getProsTrain() != null)
            voyage.setProsTrain(voyageDto.getProsTrain());
        if (voyageDto.getStagesEtudiantsAvion() != null)
            voyage.setStagesEtudiantsAvion(voyageDto.getStagesEtudiantsAvion());
        if (voyageDto.getStagesEtudiantsTrain() != null)
            voyage.setStagesEtudiantsTrain(voyageDto.getStagesEtudiantsTrain());
        if (voyageDto.getSemestresEtudiantsAvion() != null)
            voyage.setSemestresEtudiantsAvion(voyageDto.getSemestresEtudiantsAvion());
        if (voyageDto.getSemestresEtudiantsTrain() != null)
            voyage.setSemestresEtudiantsTrain(voyageDto.getSemestresEtudiantsTrain());
        if(voyageDto.getFormationContinueAvion() != null) // A dupliquer si ajout de colonne
            voyage.setFormationContinueAvion(voyageDto.getFormationContinueAvion());
        if (voyageDto.getFormationContinueTrain() != null)
            voyage.setFormationContinueTrain(voyageDto.getFormationContinueTrain());

        mobInternationalOnglet.getVoyages().add(voyage);

        
        Set<ConstraintViolation<MobInternationalOnglet>> violations = validator.validate(mobInternationalOnglet);
        if(!violations.isEmpty()) {
            throw new ValidationCustomException(violations);
        }
        mobInternationalOngletRepository.save(mobInternationalOnglet);
    }

    @Override
    public void save(MobInternationalOnglet mobInternationalOnglet) {
        mobInternationalOngletRepository.save(mobInternationalOnglet);
    }
}
