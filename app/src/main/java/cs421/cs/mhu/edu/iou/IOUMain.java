package cs421.cs.mhu.edu.iou;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.Random;

public class IOUMain extends Activity {

    private NotificationMgr manager;
    private Random notificationIdGenerator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ioumain);
        manager = new NotificationMgr();
        notificationIdGenerator = new Random();
    }

    public void sendNotification(View v){
        int notificationId = notificationIdGenerator.nextInt();
        Log.d("NotificationTester", notificationId + "");
        manager.sendNotification(this, "IOU Debt Reminder", "You owe Marty $5", notificationId);
    }

    public void sendNotificationWithActions(View v){
        int notificationId = notificationIdGenerator.nextInt();
        Log.d("NotificationTester", notificationId + "");
        manager.sendNotificationWithActions(this, "IOU Debt Reminder", "Marty owes you $20", notificationId);
    }
}
