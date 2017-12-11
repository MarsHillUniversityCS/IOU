package cs421.cs.mhu.edu.iou;

import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.util.Log;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by s000192153 on 11/28/17.
 */

public class NotificationMgr {

    public static String notificationIDKey = "notificationIDKey";
    public static String notificationActionKey = "notificationActionKey";
    public static String app_name = "IOU";

    public void sendNotificationWithActions(Activity context, String title, String body, int notificationID){

        //create notification
        Log.d(app_name, "send notification with actions");
        Log.d(app_name, notificationID + "");
        Notification.Builder builder =
                new Notification.Builder(context)
                        .setSmallIcon(android.R.drawable.ic_dialog_info)
                        .setContentTitle(title)
                        .setContentText(body);

        Intent smsIntent = new Intent(context, ActionReceiver.class);
        smsIntent.putExtra(notificationIDKey, notificationID);
        smsIntent.putExtra(notificationActionKey, ActionReceiver.Action1);
        PendingIntent smsPendingIntent = PendingIntent.getBroadcast(context, notificationID , smsIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification.Action smsAction = new Notification.Action.Builder(Icon.createWithResource(context, android.R.drawable.ic_dialog_info), context.getString(R.string.strNotificationAction1), smsPendingIntent).build();
        builder.addAction(smsAction);

        Intent remindIntent = new Intent(context, ActionReceiver.class);
        remindIntent.putExtra(notificationIDKey, notificationID);
        remindIntent.putExtra(notificationActionKey, ActionReceiver.Action2);
        PendingIntent remindPendingIntent = PendingIntent.getBroadcast(context, notificationID, remindIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification.Action remindMeAction = new Notification.Action.Builder(Icon.createWithResource(context, android.R.drawable.ic_dialog_info), context.getString(R.string.strNotificationAction2), remindPendingIntent).build();
        builder.addAction(remindMeAction);

        //"activate" this notification
        NotificationManager notifyMgr =
                (NotificationManager)
                        context.getSystemService(NOTIFICATION_SERVICE);
        notifyMgr.notify(notificationID, builder.build());
    }

    public void sendNotification(Activity context, String title, String body, int notificationID){

        //create
        Log.d(app_name, "send notification without actions");
        Notification.Builder builder =
                new Notification.Builder(context)
                        .setSmallIcon(android.R.drawable.ic_dialog_info)
                        .setContentTitle(title)
                        .setContentText(body);

        //create an intent to go back to MainActivity
        Intent resultIntent = new Intent(context, IOUMain.class);
        resultIntent.putExtra(notificationIDKey, notificationID);
        //allow android to launch this app when clicked
        PendingIntent pendingIntent =
                PendingIntent.getActivity(
                        context,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);

        //"activate" this notification
        NotificationManager notifyMgr =
                (NotificationManager)
                        context.getSystemService(NOTIFICATION_SERVICE);
        notifyMgr.notify(notificationID, builder.build());
    }
}
