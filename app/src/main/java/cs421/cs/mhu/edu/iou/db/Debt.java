package cs421.cs.mhu.edu.iou.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.location.Location;

import java.text.DecimalFormat;

/**
 * Created by mgilbert on 11/30/17.
 */

@Entity
public class Debt {

    @Ignore
    static DecimalFormat df2 = new DecimalFormat("#.00");

    @PrimaryKey(autoGenerate = true)
    private long id;

    private String contactID;

    private boolean theyOweMe;

    private String title;

    private String description;

    private double amount;

    private long time;

    private int reminderFrequency;

    private boolean paidInFull;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContactID() {
        return contactID;
    }

    public void setContactID(String contactID) {
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

    public String getAmountString(){
        return "$" + df2.format(getAmount());
    }

    public String toString() {
        String result = "";
        if(theyOweMe){
            result = "Contact id " + getContactID() + " owes me ";
        } else {
            result = "I owe contact id " + getContactID() + " ";
        }

        result += getAmountString() + " for " + getTitle();
        return result;
    }
}
