package utils.validator;

import contracts.Contract;

public interface Validator {
    ValidationResult validate(Contract contract);
}
