package tcucl.back_tcucl.manager.impl.onglet;

import org.springframework.stereotype.Component;
import tcucl.back_tcucl.dto.onglet.energie.EnergieOngletDto;
import tcucl.back_tcucl.entity.onglet.energie.EnergieOnglet;
import tcucl.back_tcucl.exceptionPersonnalisee.OngletNonTrouveIdException;
import tcucl.back_tcucl.repository.onglet.EnergieOngletRepository;
import tcucl.back_tcucl.manager.EnergieOngletManager;

@Component
public class EnergieOngletManagerImpl implements EnergieOngletManager {

    private final EnergieOngletRepository energieOngletRepository;

    public EnergieOngletManagerImpl(EnergieOngletRepository energieOngletRepository) {
        this.energieOngletRepository = energieOngletRepository;
    }

    @Override
    public EnergieOnglet getEnergieOngletById(Long ongletId) {
        return energieOngletRepository.findById(ongletId).orElseThrow(() -> new OngletNonTrouveIdException("EnergieOnglet",ongletId));
    }

    @Override
    public void updateEnergieOngletPartiel(Long ongletId, EnergieOngletDto energieOngletDto) {
        EnergieOnglet energieOnglet = getEnergieOngletById(ongletId);

        if (energieOngletDto.getEstTermine()){
            energieOnglet.setEstTermine(energieOngletDto.getEstTermine());
        }
        if (energieOngletDto.getNote() != null) {
            energieOnglet.setNote(energieOngletDto.getNote());
        }

        if (energieOngletDto.getConsoGaz() != null) {
            energieOnglet.setConsoGaz(energieOngletDto.getConsoGaz());
        }
        if (energieOngletDto.getConsoFioul() != null) {
            energieOnglet.setConsoFioul(energieOngletDto.getConsoFioul());
        }
        if (energieOngletDto.getConsoBois() != null) {
            energieOnglet.setConsoBois(energieOngletDto.getConsoBois());
        }
        if (energieOngletDto.getConsoReseauVille() != null) {
            energieOnglet.setConsoReseauVille(energieOngletDto.getConsoReseauVille());
        }
        if (energieOngletDto.getConsoElecChauffage() != null) {
            energieOnglet.setConsoElecChauffage(energieOngletDto.getConsoElecChauffage());
        }
        if (energieOngletDto.getConsoElecSpecifique() != null) {
            energieOnglet.setConsoElecSpecifique(energieOngletDto.getConsoElecSpecifique());
        }
        if (energieOngletDto.getConsoEau() != null) {
            energieOnglet.setConsoEau(energieOngletDto.getConsoEau());
        }
        if (energieOngletDto.getNote() != null) {
            energieOnglet.setNote(energieOngletDto.getNote());
        }
        if (energieOngletDto.getNomReseauVille() != null) {
            energieOnglet.setNomReseauVille(energieOngletDto.getNomReseauVille());
        }
        if (energieOngletDto.getUniteBois() != null) {
            energieOnglet.setUniteBois(energieOngletDto.getUniteBois());
        }
        if (energieOngletDto.getUniteFioul() != null) {
            energieOnglet.setUniteFioul(energieOngletDto.getUniteFioul());
        }
        if (energieOngletDto.getUniteGaz() != null) {
            energieOnglet.setUniteGaz(energieOngletDto.getUniteGaz());
        }
        energieOngletRepository.save(energieOnglet); // Hibernate gère la mise à jour
    }

}
