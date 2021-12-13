package utils;


import au.com.bytecode.opencsv.CSVReader;
import contracts.DigitalTVContract;
import contracts.MobileContract;
import contracts.WiredInternetContract;
import person.Person;
import person.Sex;
import repository.Repository;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CSVUtils {
    public static void readToRepository(String fileName, Repository repository) throws IOException {
        CSVReader csvReader = new CSVReader(new FileReader(fileName), ';');
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String[] line;
        while ((line = csvReader.readNext()) != null) {
            LocalDate startDate = LocalDate.parse(line[0], pattern);
            LocalDate expirationDate = LocalDate.parse(line[1], pattern);
            long number = Long.parseLong(line[2]);
            String[] ownerData = line[3].split(",");
            Sex sex;
            int passportSeriesAndNumber = Integer.parseInt(ownerData[3]);
            if ("Female".equals(ownerData[2])) {
                sex = Sex.FEMALE;
            } else {
                sex = Sex.MALE;
            }
            Person owner = new Person(repository.createOwnerId(passportSeriesAndNumber), ownerData[0], LocalDate.parse(ownerData[1], pattern), sex, passportSeriesAndNumber);
            switch (line[4]) {
                case "TV" -> repository.add(new DigitalTVContract(repository.createContractId(), startDate, expirationDate, number, owner, line[5].split(",")));
                case "Mobile" -> {
                    String[] temp = line[5].split(",");
                    int[] tariff = new int[3];
                    for (int i = 0; i < temp.length; i++) {
                        tariff[i] = Integer.parseInt(temp[i]);
                    }
                    repository.add(new MobileContract(repository.createContractId(), startDate, expirationDate, number, owner, tariff[0], tariff[1], tariff[2]));
                }
                default -> repository.add(new WiredInternetContract(repository.createContractId(), startDate, expirationDate, number, owner, Integer.parseInt(line[5])));
            }
        }
        csvReader.close();
    }
}
