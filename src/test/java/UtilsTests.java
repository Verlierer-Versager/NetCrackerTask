import contracts.Contract;
import contracts.DigitalTVContract;
import contracts.MobileContract;
import contracts.WiredInternetContract;
import junit.framework.Assert;
import org.testng.annotations.Test;
import person.Person;
import person.Sex;
import repository.Repository;
import utils.CSVUtils;
import utils.validator.Status;
import utils.validator.ValidationResult;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;

public class UtilsTests {
    Person person1 = new Person((long) 1, "Person1", LocalDate.of(2000, 5, 17), Sex.FEMALE, 2014664480);
    Person person2 = new Person((long) 2, "Person2", LocalDate.of(1978, 10, 18), Sex.FEMALE, 2002600912);
    Person person3 = new Person((long) 3, "Person3", LocalDate.of(2006, 8, 31), Sex.MALE, 2021628960);
    Contract contract1 = new DigitalTVContract(1, LocalDate.of(2019, 12, 21), LocalDate.of(2025, 12, 21), 123, person1, new String[]{"1", "2"});
    Contract contract2 = new MobileContract(2, LocalDate.of(2017, 5, 3), LocalDate.of(2022, 11, 30), 356, person2, 15, 13, 22);
    Contract contract3 = new WiredInternetContract(3, LocalDate.of(2010, 7, 13), LocalDate.of(2040, 7, 13), 556, person3, 50);

    /**
     * Test for readToRepository method
     */

    @Test
    public void readToRepository() {
        Repository repository = new Repository();
        try {
            CSVUtils csvUtils = new CSVUtils();
            csvUtils.readToRepository(".\\src\\main\\resources\\CSVtest.csv", repository);
            Assert.assertEquals(3, repository.getSize());
            Assert.assertEquals(contract1, repository.getByIndex(0, DigitalTVContract.class));
            Assert.assertEquals(contract2, repository.getByIndex(1, MobileContract.class));
            Assert.assertEquals(contract3, repository.getByIndex(2, WiredInternetContract.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Assert.assertEquals(3, repository.getSize());
    }

    @Test
    public void validate() {
        Repository repository = new Repository();
        try {
            CSVUtils csvUtils = new CSVUtils();
            ValidationResult validationResult = csvUtils.readToRepository(".\\src\\main\\resources\\CSVtest_bad.csv", repository);
            Assert.assertEquals(1, repository.getSize());
            Assert.assertEquals(Status.ERROR, validationResult.getStatus());

            Object[] warnings = {
                    "startDate is later than expirationDate",
                    "owner fullName is blank",
                    "owner sex is null or incorrect"
            };

            Assert.assertTrue(Arrays.equals(warnings, validationResult.getWarnings().toArray()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
