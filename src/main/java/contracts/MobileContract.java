package contracts;

import person.Person;

import java.time.LocalDate;
import java.util.Objects;

public class MobileContract extends Contract {
    private int minutes;
    private int sms;
    private int gigabytes;

    /**
     * @param minutes   number of minutes in the tariff
     * @param sms       number of SMS in the tariff
     * @param gigabytes number of gigabytes in the tariff
     **/

    public MobileContract(long id, LocalDate startDate, LocalDate expirationDate, long number, Person owner, int minutes, int sms, int gigabytes) {
        super(id, startDate, expirationDate, number, owner);
        this.minutes = minutes;
        this.sms = sms;
        this.gigabytes = gigabytes;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getSms() {
        return sms;
    }

    public void setSms(int sms) {
        this.sms = sms;
    }

    public int getGigabytes() {
        return gigabytes;
    }

    public void setGigabytes(int gigabytes) {
        this.gigabytes = gigabytes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MobileContract that = (MobileContract) o;
        return minutes == that.minutes && sms == that.sms && gigabytes == that.gigabytes;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), minutes, sms, gigabytes);
    }

    @Override
    public String toString() {
        return "MobileContract{" +
                "id=" + getId() +
                ", startDate=" + getStartDate() +
                ", expirationDate=" + getExpirationDate() +
                ", number=" + getNumber() +
                ", owner=" + getOwner() +
                ", minutes=" + minutes +
                ", sms=" + sms +
                ", gigabits=" + gigabytes +
                '}';
    }
}
