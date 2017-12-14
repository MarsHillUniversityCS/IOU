package cs421.cs.mhu.edu.iou.adddebt;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
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

import cs421.cs.mhu.edu.iou.R;
import cs421.cs.mhu.edu.iou.db.Debt;
import cs421.cs.mhu.edu.iou.db.IOUDb;
import cs421.cs.mhu.edu.iou.db.tasks.AddDebtTask;
import cs421.cs.mhu.edu.iou.listdebts.ListDebtsFragment;

public class AddDebtActivity extends AppCompatActivity {

    private static final String TAG = AddDebtActivity.class.getSimpleName();
    private static final int REQUEST_CODE_PICK_CONTACTS = 1;
    private Uri uriContact;
    private String contactID;
    //private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 2;

    Button selectContactBtn;
    Button selectDateBtn;
    Button selectTimeBtn;
    Switch whoOwesBtn;

    TextView whoOwesLbl;
    TextView amountLbl;
    TextView titleLbl;
    TextView dateLbl;
    TextView timeLbl;
    TextView dateSelectedLbl;
    TextView timeSelectedLbl;

    EditText selectedContactName;
    EditText selectedContactNumber;

    EditText amount;
    EditText title;
    EditText time;
    EditText description;

    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;
    int mDay;
    int mMonth;
    int mYear;
    int mHour;
    int mMinute;
    int mAmPm;

    String AMPM;

    Boolean theyOweMe = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_debt);



        //MJG - This is done when the app starts; no need to do it here.
        //requestPermissions();

        final Calendar c = Calendar.getInstance();

        mDay = c.get(Calendar.DAY_OF_MONTH);
        mMonth = c.get(Calendar.MONTH);
        mYear = c.get(Calendar.YEAR);
        mHour = c.get(Calendar.HOUR);
        mMinute = c.get(Calendar.MINUTE);
        mAmPm = c.get(Calendar.AM_PM);


        whoOwesLbl = findViewById(R.id.whoOwesLbl);
        amountLbl = findViewById(R.id.amountLbl);
        titleLbl = findViewById(R.id.titleLbl);
        dateLbl = findViewById(R.id.dateLbl);
        timeLbl = findViewById(R.id.timeLbl);
        dateSelectedLbl = findViewById(R.id.dateSelectedLbl);
        timeSelectedLbl = findViewById(R.id.timeSelectedLbl);

        selectedContactName = findViewById(R.id.selectedContactName);
        selectedContactNumber = findViewById(R.id.selectedContactNumber);

        amount = findViewById(R.id.amountEditText);
        title = findViewById(R.id.titleEditText);
        description = findViewById(R.id.descriptionEditText);

        selectContactBtn = findViewById(R.id.selectContactBtn);
        selectDateBtn = findViewById(R.id.selectDateBtn);
        selectTimeBtn = findViewById(R.id.selectTimeBtn);
        whoOwesBtn = findViewById(R.id.whoOwesSwitch);


        dateSelectedLbl.setText(mDay + "/" + (mMonth + 1) + "/" + mYear);

        //Determines AM or PM for display
        if (mAmPm == 0) {
            AMPM = "AM";
        } else if (mAmPm == 1) {
            AMPM = "PM";
        }

        timeSelectedLbl.setText(mHour + ":" + mMinute + " " + AMPM);


        whoOwesBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton cb, boolean on) {
                if (on) {
                    //Do something when Switch button is on/checked
                    whoOwesLbl.setText(R.string.i_owe);
                    theyOweMe = false;
                } else {
                    //Do something when Switch is off/unchecked
                    whoOwesLbl.setText(R.string.they_owe);
                    theyOweMe = true;
                }
            }
        });

        //MJG - set the value if this activity is called
        //with WHO_OWES_WHOM set to I_OWE, since the default
        //is THEY_OWE
        String whoOwes = this.getIntent().getStringExtra(ListDebtsFragment.WHO_OWES_WHOM);
        if (whoOwes != null && whoOwes.equals(ListDebtsFragment.I_OWE)){
            //this should trigger Brandon's code that
            //changes the lbl value - MJG
            whoOwesBtn.setChecked(true);
        }
    }

    /*
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
    */

    /**
     * Date picker that gets the date selected by the user and stores the data
     *
     * @param v - the view
     */
    public void selectDate(View v) {

        datePickerDialog = new DatePickerDialog(AddDebtActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        dateSelectedLbl.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                        mDay = dayOfMonth;
                        mMonth = monthOfYear;
                        mYear = year;
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    /**
     * Time picker that gets the time selected by the user and stores the data
     *
     * @param v - The View
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

    public void saveBtn(View v) {

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
        d.setContactID(contactID);
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

        if (cursorID == null) return;
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

        if (cursorPhone == null) return;
        if (cursorPhone.moveToFirst()) {
            contactNumber = cursorPhone.getString(cursorPhone.getColumnIndex(
                    ContactsContract.CommonDataKinds.Phone.NUMBER));
        }

        cursorPhone.close();

        Log.d(TAG, "Contact Phone Number: " + contactNumber);

        selectedContactNumber.setText(contactNumber);
    }

    private void retrieveContactName() {

        String contactName = null;

        // querying contact data store
        Cursor cursor = getContentResolver().query(uriContact, null, null, null, null);
        if (cursor == null) return;
        if (cursor.moveToFirst()) {

            // DISPLAY_NAME = The display name for the contact.
            // HAS_PHONE_NUMBER =   An indicator of whether this contact has at least one phone number.

            contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
        }

        cursor.close();

        Log.d(TAG, "Contact Name: " + contactName);

        selectedContactName.setText(contactName);
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
        selectedContactName = findViewById(R.id.selectedContactName);
        selectedContactNumber = findViewById(R.id.selectedContactNumber);

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

    //XXX - Isn't this somewhere else? Like ContactManger? *Shouldn't* it be there, if it's not?
    //-MJG
    /*
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
            while (!cursor.isAfterLast()) {
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
    */

}

