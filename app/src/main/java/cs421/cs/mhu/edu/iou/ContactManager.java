package cs421.cs.mhu.edu.iou;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import cs421.cs.mhu.edu.iou.AddDebtActivity;
import cs421.cs.mhu.edu.iou.R;

/**
 * Created by brandonwatkins on 11/12/17.
 */

public class ContactManager {

    //Context applicationContext = AddDebtActivity.getContextOfApplication();

    public static String getNameUsingContactId(Context applicationContext, String contactId) {

        String cContactIdString = ContactsContract.Contacts._ID;
        Uri cCONTACT_CONTENT_URI = ContactsContract.Contacts.CONTENT_URI;
        String cDisplayNameColumn = ContactsContract.Contacts.DISPLAY_NAME;

        String name = "";

        String selection = cContactIdString + " = ? ";
        String[] selectionArgs = new String[]{String.valueOf(contactId)};

        Cursor cursor = applicationContext.getContentResolver().query(cCONTACT_CONTENT_URI, null, selection, selectionArgs, null);
        if ((cursor != null) && (cursor.getCount() > 0)) {
            Log.d("AddNewContact", "Cursor count: " + cursor.getCount());
            cursor.moveToFirst();
            while ((cursor != null) && (cursor.isAfterLast() == false)) {
                if (cursor.getColumnIndex(cContactIdString) >= 0) {
                    if (contactId.equals(cursor.getString(cursor.getColumnIndex(cContactIdString)))) {
                        name = cursor.getString(cursor.getColumnIndex(cDisplayNameColumn));
                        break;
                    }
                }
                cursor.moveToNext();
            }
        }
        if (cursor != null) {
            cursor.close();
        }

        return name;
        //lblNameID.setText(name);

    }

   /* public int getNumberUsingContactId(String contactId) {

        String cContactIdString = ContactsContract.Contacts._ID;
        Uri cCONTACT_CONTENT_URI = ContactsContract.Contacts.CONTENT_URI;
        String cDisplayNumberColumn = ContactsContract.CommonDataKinds.Phone.NUMBER;

        int number;

        String selection = cContactIdString + " = ? ";
        String[] selectionArgs = new String[]{String.valueOf(contactId)};

        Cursor cursor = applicationContext.getContentResolver().query(cCONTACT_CONTENT_URI, null, selection, selectionArgs, null);
        if ((cursor != null) && (cursor.getCount() > 0)) {
            Log.d("AddNewContact", "Cursor count: " + cursor.getCount());
            cursor.moveToFirst();
            while ((cursor != null) && (cursor.isAfterLast() == false)) {
                if (cursor.getColumnIndex(cContactIdString) >= 0) {
                    if (contactId.equals(cursor.getString(cursor.getColumnIndex(cContactIdString)))) {
                        number = Integer.parseInt(cursor.getString(cursor.getColumnIndex(cDisplayNumberColumn)));
                        break;
                    }
                }
                cursor.moveToNext();
            }
        }
        if (cursor != null) {
            cursor.close();
        }

        return number;
    }*/

}