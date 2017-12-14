package cs421.cs.mhu.edu.iou.addpayment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import cs421.cs.mhu.edu.iou.R;
import cs421.cs.mhu.edu.iou.listdebts.ListDebtsFragment;

public class AddPayment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payment);

        //MJG - set the value if this activity is called
        //with WHO_OWES_WHOM set to I_OWE, since the default
        //is THEY_OWE
        String whoOwes = this.getIntent().getStringExtra(ListDebtsFragment.WHO_OWES_WHOM);
        if (whoOwes != null && whoOwes.equals(ListDebtsFragment.I_OWE)){
            //this should trigger code that
            //changes the lbl value - MJG
            //i.e., whoOwesBtn.setChecked(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_payment, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.confirm:
                //add the function to perform here
                return(true);
        }
        return(super.onOptionsItemSelected(item));
    }
}
