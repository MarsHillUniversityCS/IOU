package cs421.cs.mhu.edu.iou;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.provider.ContactsContract;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import cs421.cs.mhu.edu.iou.db.Debt;
import cs421.cs.mhu.edu.iou.db.IOUDb;
import cs421.cs.mhu.edu.iou.db.tasks.AddDebtTask;
import cs421.cs.mhu.edu.iou.listdebts.ViewDebtListActivity;

public class AddDebtActivity extends Activity {

    private static final String TAG = AddDebtActivity.class.getSimpleName();
    private static final int REQUEST_CODE_PICK_CONTACTS = 1;
    private Uri uriContact;
    private String contactID;
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 2;

    private Button selectContactBtn;
    private Button selectDateBtn;
    private Button selectTimeBtn;
    private Switch whoOwesBtn;

    private TextView whoOwesLbl;
    private TextView amountLbl;
    private TextView titleLbl;
    private TextView dateLbl;
    private TextView timeLbl;
    private TextView dateSelectedLbl;
    private TextView timeSelectedLbl;

    private EditText selectedContactName;
    private EditText selectedContactNumber;

    private EditText amount;
    private EditText title;
    private EditText time;
    private EditText description;

    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    int mDay;
    int mMonth;
    int mYear;
    int mHour;
    int mMinute;
    int mAmPm;

    String AMPM;

    Boolean theyOweMe = true;

    public static Context contextOfApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_debt);

        contextOfApplication = getApplicationContext();

        requestPermissions();

        final Calendar c = Calendar.getInstance();

        mDay    = c.get(Calendar.DAY_OF_MONTH);
        mMonth  = c.get(Calendar.MONTH);
        mYear   = c.get(Calendar.YEAR);
        mHour   = c.get(Calendar.HOUR);
        mMinute = c.get(Calendar.MINUTE);
        mAmPm   = c.get(Calendar.AM_PM);


        whoOwesLbl          = (TextView) findViewById(R.id.whoOwesLbl);
        amountLbl           = (TextView) findViewById(R.id.amountLbl);
        titleLbl            = (TextView) findViewById(R.id.titleLbl);
        dateLbl             = (TextView) findViewById(R.id.dateLbl);
        timeLbl             = (TextView) findViewById(R.id.timeLbl);
        dateSelectedLbl     = (TextView) findViewById(R.id.dateSelectedLbl);
        timeSelectedLbl     = (TextView) findViewById(R.id.timeSelectedLbl);

        selectedContactName     = (EditText) findViewById(R.id.selectedContactName);
        selectedContactNumber   = (EditText) findViewById(R.id.selectedContactNumber);

        amount        = (EditText) findViewById(R.id.amountEditText);
        title         = (EditText) findViewById(R.id.titleEditText);
        description   = (EditText) findViewById(R.id.descriptionEditText);

        selectContactBtn = (Button) findViewById(R.id.selectContactBtn);
        selectDateBtn    = (Button) findViewById(R.id.selectDateBtn);
        selectTimeBtn    = (Button) findViewById(R.id.selectTimeBtn);
        whoOwesBtn       = (Switch) findViewById(R.id.whoOwesSwitch);


        dateSelectedLbl.setText(mDay + "/" + (mMonth + 1) + "/" + mYear);

        //Determines AM or PM for display
        if(mAmPm == 0) {
            AMPM = "AM";
        } else if (mAmPm == 1) {
            AMPM = "PM";
        }

        timeSelectedLbl.setText(mHour + ":" + mMinute + " " + AMPM);

       whoOwesBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton cb, boolean on){
                if(on) {
                    //Do something when Switch button is on/checked
                    whoOwesLbl.setText("I owe");
                    theyOweMe = false;
                }
                else {
                    //Do something when Switch is off/unchecked
                    whoOwesLbl.setText("They owe");
                    theyOweMe = true;
                }
            }
        });
    }

    public static Context getContextOfApplication()
    {
        return contextOfApplication;
    }

    public void requestPermissions() {

        // Here, thisActivity is the current activity
        if (this.checkSelfPermission(
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (this.shouldShowRequestPermissionRationale(
                    Manifest.permission.READ_CONTACTS)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.
                this.requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
    }

    /**
     * Date picker that gets the date selected by the user and stores the data
     *
     * @param v
     */
    public void selectDate(View v) {

        datePickerDialog = new DatePickerDialog(AddDebtActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        dateSelectedLbl.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                        mDay    = dayOfMonth;
                        mMonth  = monthOfYear;
                        mYear   = year;
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    /**
     * Time picker that gets the time selected by the user and stores the data
     *
     * @param v
     */
    public void selectTime(View v) {

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        timeSelectedLbl.setText(hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }

    public void saveBtn(View v){

        Calendar calendar = Calendar.getInstance();
        calendar.set(mYear, mMonth, mDay,
                mHour, mMinute, 0);

        long selectedTime = calendar.getTimeInMillis();

        Debt d = new Debt();
        String value = amount.getText().toString();
        d.setAmount(Double.parseDouble(value));

        String desc = description.getText().toString();
        d.setDescription(desc);

        String debtTitle = title.getText().toString();
        d.setTitle(debtTitle);

        d.setReminderFrequency(864000);
        d.setTheyOweMe(theyOweMe);
        d.setTime(selectedTime);
        d.setContactID(Integer.parseInt(contactID));
        IOUDb database = IOUDb.getDatabase(this);
        //database.iouDao().addDebt(d);
        new AddDebtTask(database).execute(d);

        finish();
    }

    public void onClickSelectContact(View btnSelectContact) {

        // using native contacts selection
        // Intent.ACTION_PICK = Pick an item from the data, returning what was selected.
        startActivityForResult(new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI), REQUEST_CODE_PICK_CONTACTS);

        Log.d("AddNewContact", "Contacts: " + ContactsContract.Contacts.CONTENT_URI);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_PICK_CONTACTS && resultCode == RESULT_OK) {
            Log.d(TAG, "Response: " + data.toString());
            uriContact = data.getData();

            Log.d("AddNewContact", "URI: " + uriContact.toString());

            retrieveContactName();
            retrieveContactNumber();
            //retrieveContactPhoto();

        }
    }

    private void retrieveContactNumber() {

        String contactNumber = null;

        // getting contacts ID
        Cursor cursorID = getContentResolver().query(uriContact,
                new String[]{ContactsContract.Contacts._ID},
                null, null, null);

        if (cursorID.moveToFirst()) {

            contactID = cursorID.getString(cursorID.getColumnIndex(ContactsContract.Contacts._ID));
        }

        cursorID.close();

        Log.d(TAG, "Contact ID: " + contactID);

        // Using the contact ID now we will get contact phone number
        Cursor cursorPhone = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER},

                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ? AND " +
                        ContactsContract.CommonDataKinds.Phone.TYPE + " = " +
                        ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE,

                new String[]{contactID},
                null);

        if (cursorPhone.moveToFirst()) {
            contactNumber = cursorPhone.getString(cursorPhone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
        }

        cursorPhone.close();

        Log.d(TAG, "Contact Phone Number: " + contactNumber);

        selectedContactNumber.setText(contactNumber);
    }

    private void retrieveContactName() {

        String contactName = null;

        // querying contact data store
        Cursor cursor = getContentResolver().query(uriContact, null, null, null, null);

        if (cursor.moveToFirst()) {

            // DISPLAY_NAME = The display name for the contact.
            // HAS_PHONE_NUMBER =   An indicator of whether this contact has at least one phone number.

            contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
        }

        cursor.close();

        Log.d(TAG, "Contact Name: " + contactName);

        selectedContactName.setText(contactName);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Log.d("AddNewContact", "Permission Granted");
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    Log.d("AddNewContact", "Permission Denied");
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    public void addNewContact(View v) {
        // Creates a new Intent to insert a contact
        Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
        // Sets the MIME type to match the Contacts Provider
        intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);

        /* Assumes EditText fields in your UI contain an email address
         * and a phone number.
         *
         */
        selectedContactName = (EditText) findViewById(R.id.selectedContactName);
        selectedContactNumber = (EditText) findViewById(R.id.selectedContactNumber);

        /*
         * Inserts new data into the Intent. This data is passed to the
         * contacts app's Insert screen
         */
        // Inserts an email address
        intent.putExtra(ContactsContract.Intents.Insert.NAME, selectedContactName.getText())
        /*
         * In this example, sets the email type to be a work email.
         * You can set other email types as necessary.
         */
                .putExtra(ContactsContract.Intents.Insert.EMAIL_TYPE, ContactsContract.CommonDataKinds.Email.TYPE_WORK)
                // Inserts a phone number
                .putExtra(ContactsContract.Intents.Insert.PHONE, selectedContactNumber.getText())
        /*
         * In this example, sets the phone type to be a work phone.
         * You can set other phone types as necessary.
         */
                .putExtra(ContactsContract.Intents.Insert.PHONE_TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_WORK);

        /* Sends the Intent*/
        startActivity(intent);
    }

    public void getNameUsingContactId(String contactId) {

        String cContactIdString = ContactsContract.Contacts._ID;
        Uri cCONTACT_CONTENT_URI = ContactsContract.Contacts.CONTENT_URI;
        String cDisplayNameColumn = ContactsContract.Contacts.DISPLAY_NAME;

        String name = "";

        String selection = cContactIdString + " = ? ";
        String[] selectionArgs = new String[]{String.valueOf(contactId)};

        Cursor cursor = getContentResolver().query(cCONTACT_CONTENT_URI, null, selection, selectionArgs, null);
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

        //lblNameID.setText(name);

    }

}

