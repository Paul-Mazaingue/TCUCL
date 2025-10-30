package tcucl.back_tcucl.service;

import tcucl.back_tcucl.dto.ListIdDto;
import tcucl.back_tcucl.dto.securite.AnneeSecuriteDto;

import java.util.List;
import java.util.Map;

public interface AnneeService {

    List<AnneeSecuriteDto> getAnneeSecuriteDtoByEntiteId(Long entiteId);

    ListIdDto getongletIdListForEntiteAndAnnee(Long entiteId, Integer anneeUniversitaire);

    Map<Long, Boolean> getAllEstTermineParAnneParEntite(Long entiteId, Integer anneeUniversitaire);
}
