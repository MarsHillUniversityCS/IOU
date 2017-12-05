package cs421.cs.mhu.edu.iou;

import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.NotificationManager;
import android.content.Intent;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by s000192153 on 11/28/17.
 */

public class NotificationMgr {

    public static String notificationKey = "notificationKey";

    public void sendNotificationWithActions(Activity context, String title, String body, int notificationID){

        //create notification
        Notification.Builder builder =
                new Notification.Builder(context)
                        .setSmallIcon(android.R.drawable.ic_dialog_info)
                        .setContentTitle(title)
                        .setContentText(body);

        //create an intent to go back to MainActivity
        Intent resultIntent = new Intent(context, IOUMain.class);
        resultIntent.putExtra(notificationKey, notificationID);
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

        //add Send SMS option to the notification
        Intent iAction1 = new Intent(context, NotificationActions.class);
        iAction1.putExtra(notificationKey, notificationID);
        iAction1.setAction(NotificationActions.Action1);
        PendingIntent piAction1 = PendingIntent.getService(context, 0, iAction1, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.addAction(android.R.drawable.sym_action_chat, "Send SMS", piAction1);

        //add dismiss option to the notification
        Intent iAction2 = new Intent(context, NotificationActions.class);
        iAction2.putExtra(notificationKey, notificationID);
        iAction2.setAction(NotificationActions.Action2);
        PendingIntent piDismissAction = PendingIntent.getService(context, 0, iAction2, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.addAction(android.R.drawable.sym_action_chat, "Remind me later", piDismissAction);

        //"activate" this notification
        NotificationManager notifyMgr =
                (NotificationManager)
                        context.getSystemService(NOTIFICATION_SERVICE);
        notifyMgr.notify(notificationID, builder.build());
    }

    public void sendNotification(Activity context, String title, String body, int notificationID){

        //create notification
        Notification.Builder builder =
                new Notification.Builder(context)
                        .setSmallIcon(android.R.drawable.ic_dialog_info)
                        .setContentTitle(title)
                        .setContentText(body);

        //create an intent to go back to MainActivity
        Intent resultIntent = new Intent(context, IOUMain.class);
        resultIntent.putExtra(notificationKey, notificationID);
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
