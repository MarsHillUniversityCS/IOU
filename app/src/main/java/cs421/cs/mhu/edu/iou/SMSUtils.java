package cs421.cs.mhu.edu.iou;


import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.telephony.SmsManager;
import android.widget.Toast;

/**
 * Created by drewgarber1 on 11/28/17.
 */

public class SMSUtils {


    private String phoneNumber = "800000000";
    private String textMessage = "text message";

    Activity context;

    public SMSUtils(Activity context) {
        this.context = context;
    }

    public void sendText(String phoneNumber, String textMessage) {
        if (context.checkSelfPermission(Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            getPermissionToReadSMS(context);

        } else {

            SmsManager text = SmsManager.getDefault();
            text.sendTextMessage(phoneNumber, null, textMessage, null, null);
            Toast.makeText(context, "Message sent!", Toast.LENGTH_SHORT).show();
        }
    }

    private static final int READ_SMS_PERMISSIONS_REQUEST = 1;

    public void getPermissionToReadSMS(Activity context) {
        if (context.checkSelfPermission(Manifest.permission.READ_SMS)

                != PackageManager.PERMISSION_GRANTED) {

            if (context.shouldShowRequestPermissionRationale(
                    Manifest.permission.READ_SMS)) {
                Toast.makeText(context, "Please allow permission!", Toast.LENGTH_SHORT).show();
            }
            context.requestPermissions(new String[]{Manifest.permission.READ_SMS},
                    READ_SMS_PERMISSIONS_REQUEST);
        }
    }

    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[],
                                           int[] grantResults) {
        // Make sure it's our original READ_CONTACTS request
        if (requestCode == READ_SMS_PERMISSIONS_REQUEST) {
            if (grantResults.length == 1 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(context, "Read SMS permission granted", Toast.LENGTH_SHORT).show();
                //refreshSmsInbox();
            } else {
                Toast.makeText(context, "Read SMS permission denied", Toast.LENGTH_SHORT).show();
            }

        } else {
            context.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
