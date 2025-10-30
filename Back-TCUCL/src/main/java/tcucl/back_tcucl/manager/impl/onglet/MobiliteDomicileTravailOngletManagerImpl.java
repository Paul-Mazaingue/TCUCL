package tcucl.back_tcucl.manager.impl.onglet;

import org.springframework.stereotype.Component;
import tcucl.back_tcucl.dto.onglet.mobiliteDomicileTravail.MobiliteDomicileTravailOngletDto;
import tcucl.back_tcucl.entity.onglet.MobiliteDomicileTravailOnglet;
import tcucl.back_tcucl.exceptionPersonnalisee.OngletNonTrouveIdException;
import tcucl.back_tcucl.repository.onglet.MobiliteDomicileTravailOngletRepository;
import tcucl.back_tcucl.manager.MobiliteDomicileTravailOngletManager;

@Component
public class MobiliteDomicileTravailOngletManagerImpl implements MobiliteDomicileTravailOngletManager {

    private final MobiliteDomicileTravailOngletRepository mobiliteDomicileTravailOngletRepository;

    public MobiliteDomicileTravailOngletManagerImpl(MobiliteDomicileTravailOngletRepository mobiliteDomicileTravailOngletRepository) {
        this.mobiliteDomicileTravailOngletRepository = mobiliteDomicileTravailOngletRepository;
    }

    @Override
    public MobiliteDomicileTravailOnglet getMobiliteDomicileTravailOngletById(Long ongletId) {
        return mobiliteDomicileTravailOngletRepository.findById(ongletId)
                .orElseThrow(() -> new OngletNonTrouveIdException("MobiliteDomicileTravailOnglet",ongletId));
    }


    @Override
    public void updateMobiliteDomicileTravailOngletPartiel(Long ongletId, MobiliteDomicileTravailOngletDto mobiliteDomicileTravailOngletDto) {
        MobiliteDomicileTravailOnglet mobiliteDomicileTravailOnglet = getMobiliteDomicileTravailOngletById(ongletId);

        if (mobiliteDomicileTravailOngletDto.getEstTermine() != null) mobiliteDomicileTravailOnglet.setEstTermine(mobiliteDomicileTravailOngletDto.getEstTermine());
        if (mobiliteDomicileTravailOngletDto.getNote() != null) mobiliteDomicileTravailOnglet.setNote(mobiliteDomicileTravailOngletDto.getNote());

        if (mobiliteDomicileTravailOngletDto.getVoitureThermiqueEtudiantKm() != null) mobiliteDomicileTravailOnglet.setVoitureThermiqueEtudiantKm(mobiliteDomicileTravailOngletDto.getVoitureThermiqueEtudiantKm());
        if (mobiliteDomicileTravailOngletDto.getVoitureElectriqueEtudiantKm() != null) mobiliteDomicileTravailOnglet.setVoitureElectriqueEtudiantKm(mobiliteDomicileTravailOngletDto.getVoitureElectriqueEtudiantKm());
        if (mobiliteDomicileTravailOngletDto.getVoitureHybrideEtudiantKm() != null) mobiliteDomicileTravailOnglet.setVoitureHybrideEtudiantKm(mobiliteDomicileTravailOngletDto.getVoitureHybrideEtudiantKm());
        if (mobiliteDomicileTravailOngletDto.getMotoEtudiantKm() != null) mobiliteDomicileTravailOnglet.setMotoEtudiantKm(mobiliteDomicileTravailOngletDto.getMotoEtudiantKm());
        if (mobiliteDomicileTravailOngletDto.getTrainRegionalEtudiantKm() != null) mobiliteDomicileTravailOnglet.setTrainRegionalEtudiantKm(mobiliteDomicileTravailOngletDto.getTrainRegionalEtudiantKm());
        if (mobiliteDomicileTravailOngletDto.getBusEtudiantKm() != null) mobiliteDomicileTravailOnglet.setBusEtudiantKm(mobiliteDomicileTravailOngletDto.getBusEtudiantKm());
        if (mobiliteDomicileTravailOngletDto.getMetroTramwayEtudiantKm() != null) mobiliteDomicileTravailOnglet.setMetroTramwayEtudiantKm(mobiliteDomicileTravailOngletDto.getMetroTramwayEtudiantKm());
        if (mobiliteDomicileTravailOngletDto.getVeloEtudiantKm() != null) mobiliteDomicileTravailOnglet.setVeloEtudiantKm(mobiliteDomicileTravailOngletDto.getVeloEtudiantKm());
        if (mobiliteDomicileTravailOngletDto.getTrottinetteElectriqueEtudiantKm() != null) mobiliteDomicileTravailOnglet.setTrottinetteElectriqueEtudiantKm(mobiliteDomicileTravailOngletDto.getTrottinetteElectriqueEtudiantKm());
        if (mobiliteDomicileTravailOngletDto.getVeloElectriqueEtudiantKm() != null) mobiliteDomicileTravailOnglet.setVeloElectriqueEtudiantKm(mobiliteDomicileTravailOngletDto.getVeloElectriqueEtudiantKm());
        if (mobiliteDomicileTravailOngletDto.getMarcheAPiedEtudiantKm() != null) mobiliteDomicileTravailOnglet.setMarcheAPiedEtudiantKm(mobiliteDomicileTravailOngletDto.getMarcheAPiedEtudiantKm());
        if (mobiliteDomicileTravailOngletDto.getNbJoursDeplacementEtudiant() != null) mobiliteDomicileTravailOnglet.setNbJoursDeplacementEtudiant(mobiliteDomicileTravailOngletDto.getNbJoursDeplacementEtudiant());

        if (mobiliteDomicileTravailOngletDto.getVoitureThermiqueSalarieKm() != null) mobiliteDomicileTravailOnglet.setVoitureThermiqueSalarieKm(mobiliteDomicileTravailOngletDto.getVoitureThermiqueSalarieKm());
        if (mobiliteDomicileTravailOngletDto.getVoitureElectriqueSalarieKm() != null) mobiliteDomicileTravailOnglet.setVoitureElectriqueSalarieKm(mobiliteDomicileTravailOngletDto.getVoitureElectriqueSalarieKm());
        if (mobiliteDomicileTravailOngletDto.getVoitureHybrideSalarieKm() != null) mobiliteDomicileTravailOnglet.setVoitureHybrideSalarieKm(mobiliteDomicileTravailOngletDto.getVoitureHybrideSalarieKm());
        if (mobiliteDomicileTravailOngletDto.getMotoSalarieKm() != null) mobiliteDomicileTravailOnglet.setMotoSalarieKm(mobiliteDomicileTravailOngletDto.getMotoSalarieKm());
        if (mobiliteDomicileTravailOngletDto.getTrainRegionalSalarieKm() != null) mobiliteDomicileTravailOnglet.setTrainRegionalSalarieKm(mobiliteDomicileTravailOngletDto.getTrainRegionalSalarieKm());
        if (mobiliteDomicileTravailOngletDto.getBusSalarieKm() != null) mobiliteDomicileTravailOnglet.setBusSalarieKm(mobiliteDomicileTravailOngletDto.getBusSalarieKm());
        if (mobiliteDomicileTravailOngletDto.getMetroTramwaySalarieKm() != null) mobiliteDomicileTravailOnglet.setMetroTramwaySalarieKm(mobiliteDomicileTravailOngletDto.getMetroTramwaySalarieKm());
        if (mobiliteDomicileTravailOngletDto.getVeloSalarieKm() != null) mobiliteDomicileTravailOnglet.setVeloSalarieKm(mobiliteDomicileTravailOngletDto.getVeloSalarieKm());
        if (mobiliteDomicileTravailOngletDto.getTrottinetteElectriqueSalarieKm() != null) mobiliteDomicileTravailOnglet.setTrottinetteElectriqueSalarieKm(mobiliteDomicileTravailOngletDto.getTrottinetteElectriqueSalarieKm());
        if (mobiliteDomicileTravailOngletDto.getVeloElectriqueSalarieKm() != null) mobiliteDomicileTravailOnglet.setVeloElectriqueSalarieKm(mobiliteDomicileTravailOngletDto.getVeloElectriqueSalarieKm());
        if (mobiliteDomicileTravailOngletDto.getMarcheAPiedSalarieKm() != null) mobiliteDomicileTravailOnglet.setMarcheAPiedSalarieKm(mobiliteDomicileTravailOngletDto.getMarcheAPiedSalarieKm());
        if (mobiliteDomicileTravailOngletDto.getNbJoursDeplacementSalarie() != null) mobiliteDomicileTravailOnglet.setNbJoursDeplacementSalarie(mobiliteDomicileTravailOngletDto.getNbJoursDeplacementSalarie());

        mobiliteDomicileTravailOngletRepository.save(mobiliteDomicileTravailOnglet);
    }
}