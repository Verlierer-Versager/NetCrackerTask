package utils.validator;

import java.util.List;

public class ValidationResult {
    private Status status;
    private List<String> warnings;

    public ValidationResult() {
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setWarnings(List<String> warnings) {
        this.warnings = warnings;
    }

    public Status getStatus() {
        return status;
    }

    public List<String> getWarnings() {
        return warnings;
    }

    public ValidationResult(Status status, List<String> warnings) {
        this.status = status;
        this.warnings = warnings;
    }

    public void addWarning(String warning) {
        warnings.add(warning);
    }

    public int getSize() {
        return warnings.size();
    }
}
