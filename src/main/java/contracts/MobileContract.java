package contracts;

import person.Person;

import java.time.LocalDate;
import java.util.Objects;

public class MobileContract extends Contract{
    private int minutes;
    private int sms;
    private int gigabits;

    public MobileContract(long id, LocalDate startDate, LocalDate expirationDate, long number, Person owner, int minutes, int sms, int gigabits) {
        super(id, startDate, expirationDate, number, owner);
        this.minutes = minutes;
        this.sms = sms;
        this.gigabits = gigabits;
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

    public int getGigabits() {
        return gigabits;
    }

    public void setGigabits(int gigabits) {
        this.gigabits = gigabits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MobileContract that = (MobileContract) o;
        return minutes == that.minutes && sms == that.sms && gigabits == that.gigabits;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), minutes, sms, gigabits);
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
                ", gigabits=" + gigabits +
                '}';
    }
}
