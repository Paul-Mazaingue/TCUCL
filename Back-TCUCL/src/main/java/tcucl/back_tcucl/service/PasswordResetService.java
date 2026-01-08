package tcucl.back_tcucl.service;

import tcucl.back_tcucl.dto.PasswordResetConfirmDto;
import tcucl.back_tcucl.dto.PasswordResetRequestDto;

public interface PasswordResetService {

    void requestReset(PasswordResetRequestDto requestDto, String requestIp, String userAgent);

    void confirmReset(PasswordResetConfirmDto confirmDto);
}
