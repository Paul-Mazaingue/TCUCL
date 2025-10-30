package tcucl.back_tcucl.manager.impl.onglet;

import org.springframework.stereotype.Component;
import tcucl.back_tcucl.dto.onglet.autreMobFr.AutreMobFrOngletDto;
import tcucl.back_tcucl.entity.onglet.AutreMobFrOnglet;
import tcucl.back_tcucl.exceptionPersonnalisee.OngletNonTrouveIdException;
import tcucl.back_tcucl.manager.AutreMobFrOngletManager;
import tcucl.back_tcucl.repository.onglet.AutreMobFrOngletRepository;

@Component
public class AutreMobFrOngletManagerImpl implements AutreMobFrOngletManager {

    private final AutreMobFrOngletRepository autreMobFrOngletRepository;

    public AutreMobFrOngletManagerImpl(AutreMobFrOngletRepository autreMobFrOngletRepository) {
        this.autreMobFrOngletRepository = autreMobFrOngletRepository;
    }

    @Override
    public AutreMobFrOnglet getAutreMobFrOngletById(Long ongletId) {
        return autreMobFrOngletRepository.findById(ongletId).orElseThrow(
                () -> new OngletNonTrouveIdException("AutreMobFrOnglet",ongletId));
    }

    @Override
    public void updateAutreMobFrOngletPartiel(Long ongletId, AutreMobFrOngletDto autreMobFrOngletDto) {
        AutreMobFrOnglet autreMobFrOnglet = getAutreMobFrOngletById(ongletId);

        if (autreMobFrOngletDto.getEstTermine() != null){
            autreMobFrOnglet.setEstTermine(autreMobFrOngletDto.getEstTermine());
        }
        if (autreMobFrOngletDto.getNote() != null)
            autreMobFrOnglet.setNote(autreMobFrOngletDto.getNote());

        if (autreMobFrOngletDto.getSalarieNbAllerSimple_VoitureThermique() != null)
            autreMobFrOnglet.setSalarieNbAllerSimple_VoitureThermique(autreMobFrOngletDto.getSalarieNbAllerSimple_VoitureThermique());

        if (autreMobFrOngletDto.getSalarieNbAllerSimple_VoitureElectrique() != null)
            autreMobFrOnglet.setSalarieNbAllerSimple_VoitureElectrique(autreMobFrOngletDto.getSalarieNbAllerSimple_VoitureElectrique());

        if (autreMobFrOngletDto.getSalarieNbAllerSimple_Avion() != null)
            autreMobFrOnglet.setSalarieNbAllerSimple_Avion(autreMobFrOngletDto.getSalarieNbAllerSimple_Avion());

        if (autreMobFrOngletDto.getSalarieNbAllerSimple_France_TrainRegional() != null)
            autreMobFrOnglet.setSalarieNbAllerSimple_France_TrainRegional(autreMobFrOngletDto.getSalarieNbAllerSimple_France_TrainRegional());

        if (autreMobFrOngletDto.getSalarieNbAllerSimple_France_TrainGrandesLignes() != null)
            autreMobFrOnglet.setSalarieNbAllerSimple_France_TrainGrandesLignes(autreMobFrOngletDto.getSalarieNbAllerSimple_France_TrainGrandesLignes());

        if (autreMobFrOngletDto.getSalarieNbAllerSimple_Autocar() != null)
            autreMobFrOnglet.setSalarieNbAllerSimple_Autocar(autreMobFrOngletDto.getSalarieNbAllerSimple_Autocar());

        if (autreMobFrOngletDto.getSalarieDistanceTotale_VoitureThermique() != null)
            autreMobFrOnglet.setSalarieDistanceTotale_VoitureThermique(autreMobFrOngletDto.getSalarieDistanceTotale_VoitureThermique());

        if (autreMobFrOngletDto.getSalarieDistanceTotale_VoitureElectrique() != null)
            autreMobFrOnglet.setSalarieDistanceTotale_VoitureElectrique(autreMobFrOngletDto.getSalarieDistanceTotale_VoitureElectrique());

        if (autreMobFrOngletDto.getSalarieDistanceTotale_Avion() != null)
            autreMobFrOnglet.setSalarieDistanceTotale_Avion(autreMobFrOngletDto.getSalarieDistanceTotale_Avion());

        if (autreMobFrOngletDto.getSalarieDistanceTotale_France_TrainRegional() != null)
            autreMobFrOnglet.setSalarieDistanceTotale_France_TrainRegional(autreMobFrOngletDto.getSalarieDistanceTotale_France_TrainRegional());

        if (autreMobFrOngletDto.getSalarieDistanceTotale_France_TrainGrandesLignes() != null)
            autreMobFrOnglet.setSalarieDistanceTotale_France_TrainGrandesLignes(autreMobFrOngletDto.getSalarieDistanceTotale_France_TrainGrandesLignes());

        if (autreMobFrOngletDto.getSalarieDistanceTotale_Autocar() != null)
            autreMobFrOnglet.setSalarieDistanceTotale_Autocar(autreMobFrOngletDto.getSalarieDistanceTotale_Autocar());

        if (autreMobFrOngletDto.getEtudiantNbAllerSimple_VoitureThermique() != null)
            autreMobFrOnglet.setEtudiantNbAllerSimple_VoitureThermique(autreMobFrOngletDto.getEtudiantNbAllerSimple_VoitureThermique());

        if (autreMobFrOngletDto.getEtudiantNbAllerSimple_VoitureElectrique() != null)
            autreMobFrOnglet.setEtudiantNbAllerSimple_VoitureElectrique(autreMobFrOngletDto.getEtudiantNbAllerSimple_VoitureElectrique());

        if (autreMobFrOngletDto.getEtudiantNbAllerSimple_Avion() != null)
            autreMobFrOnglet.setEtudiantNbAllerSimple_Avion(autreMobFrOngletDto.getEtudiantNbAllerSimple_Avion());

        if (autreMobFrOngletDto.getEtudiantNbAllerSimple_France_TrainRegional() != null)
            autreMobFrOnglet.setEtudiantNbAllerSimple_France_TrainRegional(autreMobFrOngletDto.getEtudiantNbAllerSimple_France_TrainRegional());

        if (autreMobFrOngletDto.getEtudiantNbAllerSimple_France_TrainGrandesLignes() != null)
            autreMobFrOnglet.setEtudiantNbAllerSimple_France_TrainGrandesLignes(autreMobFrOngletDto.getEtudiantNbAllerSimple_France_TrainGrandesLignes());

        if (autreMobFrOngletDto.getEtudiantNbAllerSimple_Autocar() != null)
            autreMobFrOnglet.setEtudiantNbAllerSimple_Autocar(autreMobFrOngletDto.getEtudiantNbAllerSimple_Autocar());

        if (autreMobFrOngletDto.getEtudiantDistanceTotale_VoitureThermique() != null)
            autreMobFrOnglet.setEtudiantDistanceTotale_VoitureThermique(autreMobFrOngletDto.getEtudiantDistanceTotale_VoitureThermique());

        if (autreMobFrOngletDto.getEtudiantDistanceTotale_VoitureElectrique() != null)
            autreMobFrOnglet.setEtudiantDistanceTotale_VoitureElectrique(autreMobFrOngletDto.getEtudiantDistanceTotale_VoitureElectrique());

        if (autreMobFrOngletDto.getEtudiantDistanceTotale_Avion() != null)
            autreMobFrOnglet.setEtudiantDistanceTotale_Avion(autreMobFrOngletDto.getEtudiantDistanceTotale_Avion());

        if (autreMobFrOngletDto.getEtudiantDistanceTotale_France_TrainRegional() != null)
            autreMobFrOnglet.setEtudiantDistanceTotale_France_TrainRegional(autreMobFrOngletDto.getEtudiantDistanceTotale_France_TrainRegional());

        if (autreMobFrOngletDto.getEtudiantDistanceTotale_France_TrainGrandesLignes() != null)
            autreMobFrOnglet.setEtudiantDistanceTotale_France_TrainGrandesLignes(autreMobFrOngletDto.getEtudiantDistanceTotale_France_TrainGrandesLignes());

        if (autreMobFrOngletDto.getEtudiantDistanceTotale_Autocar() != null)
            autreMobFrOnglet.setEtudiantDistanceTotale_Autocar(autreMobFrOngletDto.getEtudiantDistanceTotale_Autocar());

        autreMobFrOngletRepository.save(autreMobFrOnglet);
    }
}
