import contracts.Contract;
import contracts.DigitalTVContract;
import contracts.MobileContract;
import contracts.WiredInternetContract;
import junit.framework.Assert;
import person.Person;
import person.Sex;
import org.testng.annotations.Test;
import repository.Repository;

import java.time.LocalDate;
import java.util.function.Predicate;


public class RepositoryTest {
    Person person1 = new Person((long) 1, "Person1", LocalDate.of(2000, 5, 17), Sex.FEMALE, 2014664480);
    Person person2 = new Person((long) 2, "Person2", LocalDate.of(1978, 10, 18), Sex.FEMALE, 2002600912);
    Person person3 = new Person((long) 3, "Person3", LocalDate.of(2006, 8, 31), Sex.MALE, 2021628960);
    Contract contract1 = new DigitalTVContract(1, LocalDate.of(2019, 12, 21), LocalDate.of(2025, 12, 21), 123, person1, new String[]{"1", "2"});
    Contract contract2 = new MobileContract(2, LocalDate.of(2017, 5, 3), LocalDate.of(2022, 11, 30), 356, person2, 15, 13, 22);
    Contract contract3 = new WiredInternetContract(3, LocalDate.of(2010, 7, 13), LocalDate.of(2040, 7, 13), 556, person3, 50);

    /**
     * Test for add method
     **/

    @Test
    public void testAdd() {
        Repository repository = new Repository();
        repository.add(contract1);
        repository.add(contract2);
        repository.add(contract3);
        Assert.assertEquals(3, repository.getSize());
    }

    /**
     * Test for getById method
     **/

    @Test
    public void testGetById() {
        Repository repository = new Repository();
        repository.add(contract1);
        repository.add(contract2);
        repository.add(contract3);
        Assert.assertEquals(contract1, repository.getById(1, DigitalTVContract.class));
        Assert.assertEquals(contract2, repository.getById(2, MobileContract.class));
        Assert.assertEquals(contract3, repository.getById(3, WiredInternetContract.class));
    }

    /**
     * Test for removeById method
     **/

    @Test
    public void testRemoveById() {
        Repository repository = new Repository();
        repository.add(contract1);
        repository.add(contract2);
        repository.add(contract3);
        repository.removeById(1);
        Assert.assertNull(repository.getById(1, DigitalTVContract.class));
        repository.removeById(2);
        Assert.assertNull(repository.getById(2, DigitalTVContract.class));
        repository.removeById(3);
        Assert.assertNull(repository.getById(3, DigitalTVContract.class));
        Assert.assertEquals(0, repository.getSize());
    }

    /**
     * Test for search method
     **/

    @Test
    public void testSearch() {
        Repository repository = new Repository();
        repository.add(contract1);
        repository.add(contract2);
        repository.add(contract3);
        Predicate<Contract> predicate1 = contract -> contract.getStartDate().equals(contract1.getStartDate());
        Assert.assertEquals(contract1, repository.search(predicate1, DigitalTVContract.class));
        Predicate<Contract> predicate2 = contract -> contract.getOwner().equals(contract2.getOwner());
        Assert.assertEquals(contract2, repository.search(predicate2, MobileContract.class));
        Predicate<Contract> predicate3 = contract -> contract.getExpirationDate().equals(contract3.getExpirationDate());
        Assert.assertEquals(contract3, repository.search(predicate3, WiredInternetContract.class));
    }
}
