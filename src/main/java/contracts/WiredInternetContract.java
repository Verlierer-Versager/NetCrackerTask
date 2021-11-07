package contracts;

import person.Person;

import java.time.LocalDate;
import java.util.Objects;

public class WiredInternetContract extends Contract {
    private int connectionSpeed;

    /**
     * @param connectionSpeed connection speed according to the tariff
     **/

    public WiredInternetContract(long id, LocalDate startDate, LocalDate expirationDate, long number, Person owner, int connectionSpeed) {
        super(id, startDate, expirationDate, number, owner);
        this.connectionSpeed = connectionSpeed;
    }

    public int getConnectionSpeed() {
        return connectionSpeed;
    }

    public void setConnectionSpeed(int connectionSpeed) {
        this.connectionSpeed = connectionSpeed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        WiredInternetContract that = (WiredInternetContract) o;
        return connectionSpeed == that.connectionSpeed;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), connectionSpeed);
    }

    @Override
    public String toString() {
        return "WiredInternetContract{" +
                "id=" + getId() +
                ", startDate=" + getStartDate() +
                ", expirationDate=" + getExpirationDate() +
                ", number=" + getNumber() +
                ", owner=" + getOwner() +
                ", connectionSpeed=" + connectionSpeed +
                '}';
    }
}
