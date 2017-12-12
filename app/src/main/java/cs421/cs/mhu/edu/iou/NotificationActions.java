package cs421.cs.mhu.edu.iou;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by s000192153 on 11/16/17.
 * @deprecated - isn't this class Deprecated, Josh?
 */

public class NotificationActions extends IntentService {
    public static final String Action1 = "Send SMS";
    public static final String Action2 = "Remind me later";

    public NotificationActions(){
        super("this");
    }

    public NotificationActions(String name) {
        super(name);
    }

    public void onHandleIntent(Intent intent){
        final String action = intent.getAction();
        Log.d("NotificationTester", "got to action");
        int notificationID = 0;
        intent.getIntExtra(NotificationMgr.notificationIDKey, notificationID);
        if (Action1.equals(action)) {
            Log.d("NotificationTester", "Send SMS action");
            //NotificationMgr.cancel(notificationID);
            // TODO: 11/28/17 insert sms sending code
        } else if (Action2.equals(action)){
            Log.d("NotificationTester", "Remind later action");
            // TODO: 11/30/17 insert remind me later code
        } else {
            throw new IllegalArgumentException("Unsupported action: " + action);
        }
    }
}