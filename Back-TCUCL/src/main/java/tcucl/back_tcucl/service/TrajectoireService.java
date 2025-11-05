package tcucl.back_tcucl.service;

import tcucl.back_tcucl.dto.TrajectoireDto;
import tcucl.back_tcucl.dto.TrajectoirePosteDto;
import java.util.List;

public interface TrajectoireService {
    TrajectoireDto getByEntiteId(Long entiteId);
    TrajectoireDto upsert(Long entiteId, TrajectoireDto dto);
    List<TrajectoirePosteDto> getPostesDefaults(Long entiteId);
}
