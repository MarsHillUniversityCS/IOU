package cs421.cs.mhu.edu.iou;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import cs421.cs.mhu.edu.iou.listdebts.ViewDebtListActivity;

public class IOUMain extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ioumain);
    }

    public void launchList(View v){
        Intent i = new Intent(this, ViewDebtListActivity.class);

        startActivity(i);
    }

    public void goToAddPayment(View v){
        Intent i = new Intent(this, AddPayment.class);
        startActivity(i);
    }
}
