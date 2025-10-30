package tcucl.back_tcucl.manager.impl;

import org.springframework.stereotype.Component;
import tcucl.back_tcucl.entity.facteurEmission.FacteurEmission;
import tcucl.back_tcucl.exceptionPersonnalisee.NonTrouveGeneralCustomException;
import tcucl.back_tcucl.manager.FacteurEmissionManager;
import tcucl.back_tcucl.repository.FacteurEmissionRepository;

@Component
public class FacteurEmissionManagerImpl implements FacteurEmissionManager {

    public final FacteurEmissionRepository facteurEmissionRepository;

    public FacteurEmissionManagerImpl(FacteurEmissionRepository facteurEmissionRepository) {
        this.facteurEmissionRepository = facteurEmissionRepository;
    }
    @Override
    public void deleteAll(){
        facteurEmissionRepository.deleteAll();
    }

    @Override
    public void save(FacteurEmission facteurEmission){
        facteurEmissionRepository.save(facteurEmission);
    }

    @Override
    public FacteurEmission findByCategorieAndTypeAndUnite(String categorie, String type, String unite){
        return facteurEmissionRepository.findByCategorieAndTypeAndUnite(categorie, type, unite)
                .orElseThrow(() -> new NonTrouveGeneralCustomException("Ce facteur d'emission n'existe pas" +
                        " pour la categorie : " + categorie + ", le type : " + type + " et l'unité : " + unite));
    }

    @Override
    public FacteurEmission findByCategorieAndType(String categorie, String type) {
        return facteurEmissionRepository.findByCategorieAndType(categorie, type)
                .orElseThrow(() -> new NonTrouveGeneralCustomException("Ce facteur d'emission n'existe pas" +
                        " pour la categorie : " + categorie + " et le type : " + type));
    }
}
