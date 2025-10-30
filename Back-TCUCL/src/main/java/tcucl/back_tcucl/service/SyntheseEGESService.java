package tcucl.back_tcucl.service;

import tcucl.back_tcucl.dto.SyntheseEGESResultatDto;

public interface SyntheseEGESService {
    SyntheseEGESResultatDto getSyntheseEGESResultat(Long entiteId, Integer annee);

}
