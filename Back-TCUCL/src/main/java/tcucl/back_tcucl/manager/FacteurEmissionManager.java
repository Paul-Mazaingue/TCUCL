package tcucl.back_tcucl.manager;

import tcucl.back_tcucl.entity.facteurEmission.FacteurEmission;

public interface FacteurEmissionManager {

    void deleteAll();

    void save(FacteurEmission facteurEmission);

    FacteurEmission findByCategorieAndTypeAndUnite(String categorie, String type, String unite);
    FacteurEmission findByCategorieAndType(String categorie, String type);
}
