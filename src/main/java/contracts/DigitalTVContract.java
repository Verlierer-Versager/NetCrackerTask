package contracts;

import person.Person;

import java.time.LocalDate;
import java.util.Arrays;

public class DigitalTVContract extends Contract{
    private String[] channelPackage;

    public DigitalTVContract(long id, LocalDate startDate, LocalDate expirationDate, long number, Person owner, String[] channelPackage) {
        super(id, startDate, expirationDate, number, owner);
        this.channelPackage = channelPackage;
    }

    public String[] getChannelPackage() {
        return channelPackage;
    }

    public void setChannelPackage(String[] channelPackage) {
        this.channelPackage = channelPackage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DigitalTVContract that = (DigitalTVContract) o;
        return Arrays.equals(channelPackage, that.channelPackage);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Arrays.hashCode(channelPackage);
        return result;
    }

    @Override
    public String toString() {
        return "DigitalTVContract{" +
                "id=" + getId() +
                ", startDate=" + getStartDate() +
                ", expirationDate=" + getExpirationDate() +
                ", number=" + getNumber() +
                ", owner=" + getOwner() +
                ", channelPackage=" + Arrays.toString(channelPackage) +
                '}';
    }
}
