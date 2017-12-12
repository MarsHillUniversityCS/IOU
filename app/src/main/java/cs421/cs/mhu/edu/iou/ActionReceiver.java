package cs421.cs.mhu.edu.iou;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import java.util.Timer;

/**
 * Created by s000192153 on 12/11/17.
 */

public class ActionReceiver extends BroadcastReceiver {

    SMSUtils utils;
    Timer timer;
    public static final String Action1 = "Send SMS";
    public static final String Action2 = "Remind me later";

    public void onReceive(Context context, Intent intent){
        Log.d(NotificationMgr.app_name, "got broadcast");

        String action = intent.getStringExtra(NotificationMgr.notificationActionKey);
        int notificationId = intent.getIntExtra(NotificationMgr.notificationIDKey, 0);
        int contactID = intent.getIntExtra(NotificationMgr.notificationContactID, 0);
        if (Action1.equals(action)) {
            performAction1(contactID);
            Log.d(NotificationMgr.app_name,notificationId + "");
        } else if (Action2.equals(action)){
            performAction2(contactID);
            Log.d(NotificationMgr.app_name,notificationId + "");
        } else {
            throw new IllegalArgumentException("Unsupported action: " + action);
        }
        NotificationManager mgr = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mgr.cancel(notificationId);

        Intent intent1 = new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
        context.sendBroadcast(intent1);
    }

    public void performAction1(int contactID){
        String phoneNumber = "7548649023";
        Double amount = 32.50;
        String textMessage = "Hey, you still owe me " + amount + " please pay me back.";

        utils.sendText(phoneNumber, textMessage);
        // TODO: 11/28/17 insert sms sending code
    }

    public void performAction2(int contactID) {
        Log.d(NotificationMgr.app_name, "Remind later action");
        // TODO: 11/30/17 insert remind me later code
    }
}
