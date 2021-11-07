package contracts;

import person.Person;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Contract {
    private long id;
    private LocalDate startDate;
    private LocalDate expirationDate;
    private long number;
    private Person owner;

    public Contract(long id, LocalDate startDate, LocalDate expirationDate, long number, Person owner) {
        this.id = id;
        this.startDate = startDate;
        this.expirationDate = expirationDate;
        this.number = number;
        this.owner = owner;
    }

    public long getId() {
        return id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public long getNumber() {
        return number;
    }

    public Person getOwner() {
        return owner;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contract contract = (Contract) o;
        return id == contract.id && number == contract.number && startDate.equals(contract.startDate) && expirationDate.equals(contract.expirationDate) && owner.equals(contract.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startDate, expirationDate, number, owner);
    }

    @Override
    public String toString() {
        return "Contract{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", expirationDate=" + expirationDate +
                ", number=" + number +
                ", owner=" + owner +
                '}';
    }
}
