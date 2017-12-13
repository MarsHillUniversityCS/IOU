package cs421.cs.mhu.edu.iou.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by mgilbert on 11/30/17.
 */
@Entity (
  foreignKeys= {@ForeignKey(
    entity=Debt.class,
    parentColumns="id",
    childColumns="debtID",
    onDelete = CASCADE)},
     indices = { @Index("id"), @Index("debtID") }
)
public class Transaction {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private String contactID;

    private long debtID;

    private boolean toMe;

    private double amount;

    private String memo;

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

    public long getDebtID() {
        return debtID;
    }

    public void setDebtID(long debtID) {
        this.debtID = debtID;
    }

    public boolean isToMe() {
        return toMe;
    }

    public void setToMe(boolean toMe) {
        this.toMe = toMe;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }


}
