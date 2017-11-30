package cs421.cs.mhu.edu.iou;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.location.Location;

/**
 * Created by mgilbert on 11/30/17.
 */

@Entity
public class Debt {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private long contactID;

    private boolean theyOweMe;

    private String title;

    private String description;

    private double amount;

    private long time;

    private Location location;

    private int reminderFrequency;

    private boolean paidInFull;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getContactID() {
        return contactID;
    }

    public void setContactID(long contactID) {
        this.contactID = contactID;
    }

    public boolean isTheyOweMe() {
        return theyOweMe;
    }

    public void setTheyOweMe(boolean theyOweMe) {
        this.theyOweMe = theyOweMe;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getReminderFrequency() {
        return reminderFrequency;
    }

    public void setReminderFrequency(int reminderFrequency) {
        this.reminderFrequency = reminderFrequency;
    }

    public boolean isPaidInFull() {
        return paidInFull;
    }

    public void setPaidInFull(boolean paidInFull) {
        this.paidInFull = paidInFull;
    }
}
