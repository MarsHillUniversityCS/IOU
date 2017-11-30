package cs421.cs.mhu.edu.iou;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by mgilbert on 11/30/17.
 */
@Entity (
  tableName="transactions",
  foreignKeys=@ForeignKey(
    entity=Debt.class,
    parentColumns="id",
    childColumns="debtID",
    onDelete=CASCADE))
public class Transaction {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private long contactID;

    private long debtID;

    private boolean toMe;

    private double amount;

    private String memo;

}
