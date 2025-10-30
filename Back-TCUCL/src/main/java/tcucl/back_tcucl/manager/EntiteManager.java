package tcucl.back_tcucl.manager;

import tcucl.back_tcucl.entity.Entite;

import java.util.List;

public interface EntiteManager {
    Entite getEntitebyId(Long entiteId);

    Entite save(Entite entite);

    List<Entite> getAllEntites();

    boolean existsEntiteByNomAndType(String nomEntite, String typeEntite);
}
