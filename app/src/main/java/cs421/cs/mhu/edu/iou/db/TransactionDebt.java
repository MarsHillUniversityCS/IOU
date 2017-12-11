package cs421.cs.mhu.edu.iou.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by mgilbert on 11/30/17.
 */
@Entity(
        foreignKeys = {
                @ForeignKey(
                        entity = Debt.class,
                        parentColumns = "id",
                        childColumns = "debtID",
                        onDelete = CASCADE),
                @ForeignKey(
                        entity = Transaction.class,
                        parentColumns = "id",
                        childColumns = "transactionID",
                        onDelete = CASCADE)
        }
)

public class TransactionDebt {
    @PrimaryKey(autoGenerate = true)
    private long id;

    private long transactionID;
    private long debtID;
    private double amount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(long transactionID) {
        this.transactionID = transactionID;
    }

    public long getDebtID() {
        return debtID;
    }

    public void setDebtID(long debtID) {
        this.debtID = debtID;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
