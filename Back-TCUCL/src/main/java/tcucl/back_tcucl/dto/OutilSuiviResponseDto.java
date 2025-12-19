package tcucl.back_tcucl.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Envelope returning both real values (calculées à partir de la base) et jeux factices
 * pour l'outil de suivi, plus les messages explicites pour le front.
 */
public class OutilSuiviResponseDto {
    private OutilSuiviDto real;
    private OutilSuiviDto mock;
    private List<String> warnings;
    private Map<Integer, List<String>> issuesByYear;

    public OutilSuiviResponseDto() {
        this.warnings = new ArrayList<>();
        this.issuesByYear = new HashMap<>();
    }

    public OutilSuiviResponseDto(OutilSuiviDto real, OutilSuiviDto mock, List<String> warnings, Map<Integer, List<String>> issuesByYear) {
        this.real = real;
        this.mock = mock;
        this.warnings = warnings != null ? warnings : new ArrayList<>();
        this.issuesByYear = issuesByYear != null ? issuesByYear : new HashMap<>();
    }

    public OutilSuiviDto getReal() { return real; }
    public void setReal(OutilSuiviDto real) { this.real = real; }

    public OutilSuiviDto getMock() { return mock; }
    public void setMock(OutilSuiviDto mock) { this.mock = mock; }

    public List<String> getWarnings() { return warnings; }
    public void setWarnings(List<String> warnings) { this.warnings = warnings != null ? warnings : new ArrayList<>(); }

    public Map<Integer, List<String>> getIssuesByYear() { return issuesByYear; }
    public void setIssuesByYear(Map<Integer, List<String>> issuesByYear) { this.issuesByYear = issuesByYear != null ? issuesByYear : new HashMap<>(); }
}
