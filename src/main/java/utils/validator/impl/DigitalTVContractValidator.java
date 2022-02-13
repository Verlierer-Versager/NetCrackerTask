package utils.validator.impl;

import contracts.Contract;
import contracts.DigitalTVContract;
import utils.validator.Status;
import utils.validator.ValidationResult;
import utils.validator.Validator;

public class DigitalTVContractValidator implements Validator {
    @Override
    public ValidationResult validate(Contract contract) {
        ValidationResult validationResult = new ValidationResult();
        if (DigitalTVContract.class.equals(contract.getClass())) {
            DigitalTVContract digitalTVContract = (DigitalTVContract) contract;

            if (digitalTVContract.getSize() == 0) {
                validationResult.setStatus(Status.ERROR);
                validationResult.addWarning("channelPackage is empty");
            }
        }
        return validationResult;
    }
}
