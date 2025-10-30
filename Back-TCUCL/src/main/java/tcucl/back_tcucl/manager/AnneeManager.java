package tcucl.back_tcucl.manager;

import tcucl.back_tcucl.dto.ListIdDto;
import tcucl.back_tcucl.dto.securite.AnneeSecuriteDto;

import java.util.List;
import java.util.Map;


public interface AnneeManager {

    List<AnneeSecuriteDto> findAnneesSecurityByEntiteId(Long entiteId);

    ListIdDto getongletIdListForEntiteAndAnnee(Long entiteId, int anneeValeur);

    Map<Long, Boolean> getAllEstTermineParAnneParEntite(Long entiteId, Integer anneeUniversitaire);
}
