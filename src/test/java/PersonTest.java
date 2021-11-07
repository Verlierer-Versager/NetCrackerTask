import junit.framework.Assert;
import person.Person;
import person.Sex;
import org.testng.annotations.Test;

import java.time.LocalDate;

public class PersonTest {
    Person person1 = new Person((long) 1, "Person1", LocalDate.of(2000, 5, 17), Sex.FEMALE, 2014664480);
    Person person2 = new Person((long) 2, "Person2", LocalDate.of(1978, 10, 18), Sex.FEMALE, 2002600912);
    Person person3 = new Person((long) 3, "Person3", LocalDate.of(2006, 8, 31), Sex.MALE, 2021628960);

    /**
     * Test for getAge method
     **/

    @Test
    public void testGetAge() {
        Assert.assertEquals(21, person1.getAge());
        Assert.assertEquals(43, person2.getAge());
        Assert.assertEquals(15, person3.getAge());
    }

}
