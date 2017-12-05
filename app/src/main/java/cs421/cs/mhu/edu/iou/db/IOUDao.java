package cs421.cs.mhu.edu.iou.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;
import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by marty on 12/1/17.
 */

@Dao
public interface IOUDao {

    @Query("SELECT * FROM Debt")
    LiveData<List<Debt>> getAllDebts();

    @Insert (onConflict = REPLACE)
    void addDebt(Debt debt);

    @Update
    void updateDebt(Debt debt);

    @Query ("SELECT * FROM Debt WHERE id = :id")
    Debt getDebtById(long id);

    @Delete
    void deleteDebt(Debt debt);

}
