package tcucl.back_tcucl.service;

import tcucl.back_tcucl.dto.ListIdDto;
import tcucl.back_tcucl.entity.Entite;

import java.util.List;


public interface EntiteService {

    Entite getEntiteById(Long entiteId);

    Entite saveEntite(Entite entite);

    Entite creerEntite(String entiteNom, String entiteType, Boolean isSuperAdmin);

    List<Entite> getAllEntites();

    Boolean existEntiteByNomEtType(String nomEntite, String typeEntite);
}
