package cs421.cs.mhu.edu.iou.listdebts;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import cs421.cs.mhu.edu.iou.db.Debt;
import cs421.cs.mhu.edu.iou.db.IOUDb;
import cs421.cs.mhu.edu.iou.db.tasks.AddDebtTask;
import cs421.cs.mhu.edu.iou.db.tasks.DeleteDebtTask;

/**
 * ViewModel for the DebtList
 * Created by marty on 12/1/17.
 */

public class DebtListViewModel extends AndroidViewModel {
    /*
    * If you need a ViewModel tutorial to understand why it's useful/necessary, check out
    * https://medium.com/google-developers/viewmodels-a-simple-example-ed5ac416317e
    */


    private final LiveData<List<Debt>> debtList;

    private IOUDb database;

    public DebtListViewModel(Application application) {
        super(application);

        database = IOUDb.getDatabase(application);

        debtList = database.iouDao().getAllDebts();
    }

    LiveData<List<Debt>> getDebtList() {
        return debtList;
    }

    void deleteDebt(Debt d) {
        new DeleteDebtTask(database).execute(d);
    }

    void addDebt(Debt d) {
        new AddDebtTask(database).execute(d);
    }
}
