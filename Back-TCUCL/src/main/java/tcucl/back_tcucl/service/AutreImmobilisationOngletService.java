package tcucl.back_tcucl.service;

import tcucl.back_tcucl.dto.onglet.autreImmobilisation.AutreImmobilisationOngletDto;
import tcucl.back_tcucl.dto.onglet.autreImmobilisation.AutreImmobilisationResultatDto;
import tcucl.back_tcucl.entity.onglet.AutreImmobilisationOnglet;


public interface AutreImmobilisationOngletService {

    AutreImmobilisationOnglet getAutreImmobilisationOngletById(Long  ongletId);

    void updateAutreImmobilisationOngletPartiel(Long  ongletId, AutreImmobilisationOngletDto autreImmobilisationOngletDto);

    AutreImmobilisationResultatDto getAutreImmobilisationResultat(Long ongletId);

}
