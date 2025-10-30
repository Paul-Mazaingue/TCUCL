package tcucl.back_tcucl.service.impl;

import org.springframework.stereotype.Service;
import tcucl.back_tcucl.dto.ListIdDto;
import tcucl.back_tcucl.dto.securite.AnneeSecuriteDto;
import tcucl.back_tcucl.manager.AnneeManager;
import tcucl.back_tcucl.service.AnneeService;

import java.util.List;
import java.util.Map;

@Service
public class AnneeServiceImpl implements AnneeService {

    private final AnneeManager anneeManager;

    public AnneeServiceImpl(AnneeManager anneeManager) {
        this.anneeManager = anneeManager;
    }

    @Override
    public List<AnneeSecuriteDto> getAnneeSecuriteDtoByEntiteId(Long entiteId) {
        return anneeManager.findAnneesSecurityByEntiteId(entiteId);
    }

    @Override
    public ListIdDto getongletIdListForEntiteAndAnnee(Long entiteId, Integer anneeUniversitaire) {
        return anneeManager.getongletIdListForEntiteAndAnnee(entiteId, anneeUniversitaire);
    }

    @Override
    public Map<Long, Boolean> getAllEstTermineParAnneParEntite(Long entiteId, Integer anneeUniversitaire) {
       return anneeManager.getAllEstTermineParAnneParEntite(entiteId, anneeUniversitaire);
    }
}
