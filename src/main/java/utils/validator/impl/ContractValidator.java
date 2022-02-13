package utils.validator.impl;

import contracts.Contract;
import person.Person;
import utils.validator.Status;
import utils.validator.ValidationResult;
import utils.validator.Validator;

import java.time.LocalDate;

public class ContractValidator implements Validator {
    @Override
    public ValidationResult validate(Contract contract) {
        ValidationResult validationResult = new ValidationResult();

        LocalDate startDate = contract.getStartDate();
        LocalDate expirationDate =  contract.getExpirationDate();

        if (startDate == null) {
            validationResult.addWarning("startDate is null");
        }
        if (contract.getExpirationDate() == null) {
            validationResult.addWarning("expirationDate is null");
        }
        if (contract.getNumber() <= 0) {
            validationResult.addWarning("number is incorrect");
        }
        if (contract.getOwner() == null) {
            validationResult.setStatus(Status.ERROR);
            validationResult.addWarning("owner is null");
            return validationResult;
        }

        if (startDate != null && expirationDate != null) {
            if (startDate.compareTo(expirationDate) > 0) {
                validationResult.addWarning("startDate is later than expirationDate");
            }
        }

        Person owner = contract.getOwner();
        String fullName = owner.getFullName();

        if (fullName == null) {
            validationResult.addWarning("owner fullName is null");
        } else if (fullName.isBlank()) {
            validationResult.addWarning("owner fullName is blank");
        }

        if (owner.getDateOfBirth() == null) {
            validationResult.addWarning("owner dateOfBirth is null");
        } else if (owner.getAge() < 0 || owner.getAge() > 150) {
            validationResult.addWarning("owner dateOfBirth is incorrect");
        }
        if (owner.getSex() == null) {
            validationResult.addWarning("owner sex is null or incorrect");
        }
        if (owner.getPassportSeriesAndNumber() < 0) {
            validationResult.addWarning("owner passportSeriesAndNumber is incorrect");
        }

        if (validationResult.getSize() > 0) {
            validationResult.setStatus(Status.ERROR);
        } else {
            validationResult.setStatus(Status.OK);
        }
        return validationResult;
    }
}
