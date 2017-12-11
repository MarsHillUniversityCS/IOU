package cs421.cs.mhu.edu.iou.db.tasks;

import android.os.AsyncTask;

import cs421.cs.mhu.edu.iou.db.Debt;
import cs421.cs.mhu.edu.iou.db.IOUDb;

/**
 * Created by marty on 12/5/17.
 */

public class AddDebtTask extends AsyncTask<Debt, Void, Void> {
    private IOUDb ioudb;

    public AddDebtTask(IOUDb ioudb) {
        this.ioudb = ioudb;
    }

    @Override
    protected Void doInBackground(Debt... debts) {
        ioudb.iouDao().addDebt(debts[0]);
        return null;
    }
}
