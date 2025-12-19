package tcucl.back_tcucl.service;

import tcucl.back_tcucl.dto.TrajectoireDto;
import tcucl.back_tcucl.dto.TrajectoirePosteDto;
import tcucl.back_tcucl.dto.TrajectoireResponseDto;
import java.util.List;

public interface TrajectoireService {
    TrajectoireResponseDto getByEntiteId(Long entiteId);
    TrajectoireDto upsert(Long entiteId, TrajectoireDto dto);
    List<TrajectoirePosteDto> getPostesDefaults(Long entiteId);
    void propagateGlobalToAll();
}
