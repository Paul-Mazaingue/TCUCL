package tcucl.back_tcucl.dto;

import java.util.ArrayList;
import java.util.List;

public class TrajectoireResponseDto {
    private TrajectoireDto real;
    private TrajectoireDto mock;
    private List<String> warnings;

    public TrajectoireResponseDto() {
        this.warnings = new ArrayList<>();
    }

    public TrajectoireResponseDto(TrajectoireDto real, TrajectoireDto mock, List<String> warnings) {
        this.real = real;
        this.mock = mock;
        this.warnings = warnings != null ? warnings : new ArrayList<>();
    }

    public TrajectoireDto getReal() {
        return real;
    }

    public void setReal(TrajectoireDto real) {
        this.real = real;
    }

    public TrajectoireDto getMock() {
        return mock;
    }

    public void setMock(TrajectoireDto mock) {
        this.mock = mock;
    }

    public List<String> getWarnings() {
        return warnings;
    }

    public void setWarnings(List<String> warnings) {
        this.warnings = warnings != null ? warnings : new ArrayList<>();
    }
}
