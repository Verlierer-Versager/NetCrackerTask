package utils;


import au.com.bytecode.opencsv.CSVReader;
import contracts.Contract;
import contracts.DigitalTVContract;
import contracts.MobileContract;
import contracts.WiredInternetContract;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import person.Person;
import person.Sex;
import repository.Repository;
import utils.validator.Status;
import utils.validator.ValidationResult;
import utils.validator.Validator;
import utils.validator.impl.ContractValidator;
import utils.validator.impl.DigitalTVContractValidator;
import utils.validator.impl.MobileContractValidator;
import utils.validator.impl.WiredInternetContractValidator;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class CSVUtils {
    private static final Logger logger = LogManager.getLogger(CSVUtils.class);

    private Validator[] validators = {new ContractValidator(),
            new DigitalTVContractValidator(),
            new MobileContractValidator(),
            new WiredInternetContractValidator()
    };

    public void readToRepository(String fileName, Repository repository) throws IOException {
        CSVReader csvReader = new CSVReader(new FileReader(fileName), ';');
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String[] line;
        while ((line = csvReader.readNext()) != null) {
            try {
                LocalDate startDate;
                LocalDate expirationDate;
                long number;
                startDate = LocalDate.parse(line[0], pattern);
                expirationDate = LocalDate.parse(line[1], pattern);
                number = Long.parseLong(line[2]);
                Sex sex = null;
                String[] ownerData = line[3].split(",");
                long passportSeriesAndNumber = Long.parseLong(ownerData[3]);
                if ("Female".equals(ownerData[2])) {
                    sex = Sex.FEMALE;
                } else if ("Male".equals(ownerData[2])) {
                    sex = Sex.MALE;
                }
                Person owner = new Person(repository.createOwnerId(passportSeriesAndNumber), ownerData[0], LocalDate.parse(ownerData[1], pattern), sex, passportSeriesAndNumber);
                switch (line[4]) {
                    case "TV" -> {
                        Contract contract = new DigitalTVContract(repository.createContractId(), startDate, expirationDate, number, owner, line[5].split(","));
                        ValidationResult validationResult = validate(contract);
                        if (validationResult.getStatus().equals(Status.OK)) {
                            repository.add(contract);
                        } else {
                            for (String warning : validationResult.getWarnings()) {
                                logger.warn(warning);
                            }
                        }
                    }
                    case "Mobile" -> {
                        String[] temp = line[5].split(",");
                        int[] tariff = new int[3];
                        for (int i = 0; i < temp.length; i++) {
                            tariff[i] = Integer.parseInt(temp[i]);
                        }
                        Contract contract = new MobileContract(repository.createContractId(), startDate, expirationDate, number, owner, tariff[0], tariff[1], tariff[2]);
                        ValidationResult validationResult = validate(contract);
                        if (validationResult.getStatus().equals(Status.OK)) {
                            repository.add(contract);
                        } else {
                            for (String warning : validationResult.getWarnings()) {
                                logger.warn(warning);
                            }
                        }
                    }
                    default -> {
                        Contract contract = new WiredInternetContract(repository.createContractId(), startDate, expirationDate, number, owner, Integer.parseInt(line[5]));
                        ValidationResult validationResult = validate(contract);
                        if (validationResult.getStatus().equals(Status.OK)) {
                            repository.add(contract);
                        } else {
                            for (String warning : validationResult.getWarnings()) {
                                logger.warn(warning);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                csvReader.readNext();
            }
        }
        csvReader.close();
    }

    private ValidationResult validate(Contract contract) {
        ValidationResult validationResult = new ValidationResult();
        validationResult.setStatus(Status.OK);
        ValidationResult temp = new ValidationResult();
        for (Validator validator : validators) {
            temp = validator.validate(contract);
            if (temp.getStatus() != null && temp.getStatus() == Status.ERROR) {
                validationResult.setStatus(Status.ERROR);
                List<String> newWarnings = validationResult.getWarnings();
                newWarnings.addAll(temp.getWarnings());
                validationResult.setWarnings(newWarnings);
            }
        }
        return validationResult;
    }
}
