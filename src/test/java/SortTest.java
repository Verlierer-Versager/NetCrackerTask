import contracts.Contract;
import contracts.DigitalTVContract;
import contracts.MobileContract;
import contracts.WiredInternetContract;
import junit.framework.Assert;
import person.Person;
import person.Sex;
import org.testng.annotations.Test;
import repository.Repository;
import sort.BubbleSort;
import sort.ISorter;
import sort.InsertionSort;
import sort.SelectionSort;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.function.Predicate;

public class SortTest {
    Person person1 = new Person((long) 1, "Person1", LocalDate.of(2000, 5, 17), Sex.FEMALE, 2014664480);
    Person person2 = new Person((long) 2, "Person2", LocalDate.of(1978, 10, 18), Sex.FEMALE, 2002600912);
    Person person3 = new Person((long) 3, "Person3", LocalDate.of(2006, 8, 31), Sex.MALE, 2021628960);
    Contract contract1 = new DigitalTVContract(1, LocalDate.of(2019, 12, 21), LocalDate.of(2025, 12, 21), 123, person1, new String[]{"1", "2"});
    Contract contract2 = new MobileContract(2, LocalDate.of(2017, 5, 3), LocalDate.of(2022, 11, 30), 356, person2, 15, 13, 22);
    Contract contract3 = new WiredInternetContract(3, LocalDate.of(2010, 7, 13), LocalDate.of(2040, 7, 13), 556, person3, 50);

    /**
     * Test for Bubble sort
     **/

    @Test
    public void testBubbleSort() {
        Repository repository = new Repository();
        ISorter bubbleSort = new BubbleSort();
        repository.add(contract2);
        repository.add(contract3);
        repository.add(contract1);
        Comparator<Contract> comparator = Comparator.comparingLong(Contract::getId);
        bubbleSort.sort(repository, comparator);
        Assert.assertEquals(contract1, repository.getByIndex(0, DigitalTVContract.class));
        Assert.assertEquals(contract2, repository.getByIndex(1, MobileContract.class));
        Assert.assertEquals(contract3, repository.getByIndex(2, WiredInternetContract.class));
    }

    /**
     * Test for Insertion sort
     **/

    @Test
    public void testInsertionSort() {
        Repository repository = new Repository();
        ISorter insertionSort = new InsertionSort();
        repository.add(contract2);
        repository.add(contract3);
        repository.add(contract1);
        Comparator<Contract> comparator = Comparator.comparing(Contract::getExpirationDate);
        insertionSort.sort(repository, comparator);
        Assert.assertEquals(contract2, repository.getByIndex(0, MobileContract.class));
        Assert.assertEquals(contract1, repository.getByIndex(1, DigitalTVContract.class));
        Assert.assertEquals(contract3, repository.getByIndex(2, WiredInternetContract.class));
    }

    /**
     * Test for ISelection sort
     **/

    @Test
    public void testSelectionSort() {
        Repository repository = new Repository();
        ISorter selectionSort = new SelectionSort();
        repository.add(contract2);
        repository.add(contract3);
        repository.add(contract1);
        Comparator<Contract> comparator = Comparator.comparing(Contract::getExpirationDate);
        selectionSort.sort(repository, comparator);
        Assert.assertEquals(contract2, repository.getByIndex(0, MobileContract.class));
        Assert.assertEquals(contract1, repository.getByIndex(1, DigitalTVContract.class));
        Assert.assertEquals(contract3, repository.getByIndex(2, WiredInternetContract.class));
    }
}
