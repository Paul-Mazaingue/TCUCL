package tcucl.back_tcucl.service;

import tcucl.back_tcucl.dto.OutilSuiviResponseDto;

public interface OutilSuiviService {
    OutilSuiviResponseDto loadForEntite(Long entiteId);
}
