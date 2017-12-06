package cs421.cs.mhu.edu.iou.db.tasks;

import android.os.AsyncTask;

import cs421.cs.mhu.edu.iou.db.Debt;
import cs421.cs.mhu.edu.iou.db.IOUDb;

/**
 * Created by marty on 12/5/17.
 */

public class DeleteDebtTask extends AsyncTask<Debt, Void, Void> {
    private IOUDb ioudb;

    public DeleteDebtTask(IOUDb ioudb) {
        this.ioudb = ioudb;
    }

    @Override
    protected Void doInBackground(final Debt... debts) {
        ioudb.iouDao().deleteDebt(debts[0]);
        return null;
    }

}
