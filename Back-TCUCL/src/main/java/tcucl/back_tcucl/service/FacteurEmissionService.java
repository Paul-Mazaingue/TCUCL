package tcucl.back_tcucl.service;

import org.springframework.web.multipart.MultipartFile;
import tcucl.back_tcucl.entity.facteurEmission.FacteurEmission;

public interface FacteurEmissionService {

    void importFacteurEmissionFromExcel(MultipartFile file);

    FacteurEmission findByCategorieAndTypeAndUnite(String categorie, String type, String unite);
    FacteurEmission findByCategorieAndType(String categorie, String type);
}
