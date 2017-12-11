package cs421.cs.mhu.edu.iou;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by s000192153 on 12/11/17.
 */

public class ActionReceiver extends BroadcastReceiver {
    public static final String Action1 = "Send SMS";
    public static final String Action2 = "Remind me later";

    public void onReceive(Context context, Intent intent){
        Log.d("NotificationTester", "got broadcast");

        String action = intent.getStringExtra(NotificationMgr.notificationKey2);
        int notificationId = intent.getIntExtra(NotificationMgr.notificationKey, 0);
        if (Action1.equals(action)) {
            performAction1();
            Log.d("NotificationTester",notificationId + "");
        } else if (Action2.equals(action)){
            performAction2();
            Log.d("NotificationTester",notificationId + "");
        } else {
            throw new IllegalArgumentException("Unsupported action: " + action);
        }
        NotificationManager mgr = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mgr.cancel(notificationId);

        Intent intent1 = new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
        context.sendBroadcast(intent1);
    }

    public void performAction1(){
        Log.d("NotificationTester", "Send SMS action");
        // TODO: 11/28/17 insert sms sending code
    }

    public void performAction2() {
        Log.d("NotificationTester", "Remind later action");
        // TODO: 11/30/17 insert remind me later code
    }
}
