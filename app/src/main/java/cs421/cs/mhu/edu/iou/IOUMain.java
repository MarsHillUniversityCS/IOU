package cs421.cs.mhu.edu.iou;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class IOUMain extends Activity {

    public SMSUtils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ioumain);
    }

    public void sendText(View v){
        utils = new SMSUtils(this);
        String phoneNumber = "";
        String textMessage = "";

        EditText number = findViewById(R.id.editTextNumber);
        TextView message = findViewById(R.id.editTextMessage);

        phoneNumber = number.getText().toString();
        textMessage = message.getText().toString();

        utils.sendText(phoneNumber, textMessage);
    }
}
