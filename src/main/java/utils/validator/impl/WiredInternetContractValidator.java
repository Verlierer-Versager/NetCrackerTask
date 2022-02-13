package utils.validator.impl;

import contracts.Contract;
import contracts.WiredInternetContract;
import utils.validator.Status;
import utils.validator.ValidationResult;
import utils.validator.Validator;

public class WiredInternetContractValidator implements Validator {
    @Override
    public ValidationResult validate(Contract contract) {
        ValidationResult validationResult = new ValidationResult();
        if (WiredInternetContract.class.equals(contract.getClass())) {
            WiredInternetContract wiredInternetContract = (WiredInternetContract) contract;
            if (wiredInternetContract.getConnectionSpeed() <= 0) {
                validationResult.setStatus(Status.ERROR);
                validationResult.addWarning("connectionSpeed is incorrect");
            }
        }
        return validationResult;
    }
}
