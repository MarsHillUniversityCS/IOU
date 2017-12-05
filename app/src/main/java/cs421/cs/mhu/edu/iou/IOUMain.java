package cs421.cs.mhu.edu.iou;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class IOUMain extends Activity {

    private NotificationMgr manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ioumain);
        manager = new NotificationMgr();
    }

    public void sendNotification(View v){
        manager.sendNotification(this, "IOU Debt Reminder", "You owe Marty $5", 101);
    }

    public void sendNotificationWithActions(View v){
        manager.sendNotificationWithActions(this, "IOU Debt Reminder", "Marty owes you $20", 102);
    }
}
