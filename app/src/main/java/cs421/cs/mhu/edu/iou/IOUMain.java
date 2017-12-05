package cs421.cs.mhu.edu.iou;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class IOUMain extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ioumain);
    }

    /**
     * Button that takes you to the ViewEditActivity
     * @param v
     */
    public void addDebtBtn(View v) {
        Intent i = new Intent(this, AddDebtActivity.class);
        startActivity(i);
    }
}
