package tcucl.back_tcucl.service;

import tcucl.back_tcucl.dto.TrajectoireDto;

public interface TrajectoireService {
    TrajectoireDto getByEntiteId(Long entiteId);
    TrajectoireDto upsert(Long entiteId, TrajectoireDto dto);
}
