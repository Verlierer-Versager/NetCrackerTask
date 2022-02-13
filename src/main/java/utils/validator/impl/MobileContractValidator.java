package utils.validator.impl;

import contracts.Contract;
import contracts.MobileContract;
import utils.validator.Status;
import utils.validator.ValidationResult;
import utils.validator.Validator;

public class MobileContractValidator implements Validator {
    @Override
    public ValidationResult validate(Contract contract) {
        ValidationResult validationResult = new ValidationResult();
        if (MobileContract.class.equals(contract.getClass())) {
            MobileContract mobileContract = (MobileContract) contract;
            if (mobileContract.getMinutes() <= 0 &&
                    mobileContract.getSms() <= 0 &&
                    mobileContract.getGigabytes() <= 0) {
                validationResult.setStatus(Status.ERROR);
                validationResult.addWarning("minutes, sms and gigabytes are incorrect");
            }
        }
        return validationResult;
    }
}
