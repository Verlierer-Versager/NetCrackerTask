package person;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class Person {
    private long id;
    private String fullName;
    private LocalDate dateOfBirth;
    private Sex sex;
    private int passportSeriesAndNumber;

    public Person(long id, String fullName, LocalDate dateOfBirth, Sex sex, int passportSeriesAndNumber) {
        this.id = id;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.sex = sex;
        this.passportSeriesAndNumber = passportSeriesAndNumber;
    }

    public int getAge() {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public int getPassportSeriesAndNumber() {
        return passportSeriesAndNumber;
    }

    public void setPassportSeriesAndNumber(int passportSeriesAndNumber) {
        this.passportSeriesAndNumber = passportSeriesAndNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && passportSeriesAndNumber == person.passportSeriesAndNumber && fullName.equals(person.fullName) && dateOfBirth.equals(person.dateOfBirth) && sex == person.sex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, dateOfBirth, sex, passportSeriesAndNumber);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", sex=" + sex +
                ", passportSeriesAndNumber=" + passportSeriesAndNumber +
                '}';
    }
}
