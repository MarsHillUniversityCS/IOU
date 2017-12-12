package cs421.cs.mhu.edu.iou.listdebts;

import android.app.AlertDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cs421.cs.mhu.edu.iou.R;
import cs421.cs.mhu.edu.iou.db.Debt;

public class ViewDebtListActivity extends AppCompatActivity implements View.OnLongClickListener, View.OnClickListener {

    // MJG - I looked at these sites for ref on RecylerView stuff
    // https://www.sitepoint.com/mastering-complex-lists-with-the-android-recyclerview/
    // https://android.jlelse.eu/android-architecture-components-room-livedata-and-viewmodel-fca5da39e26b

    private final String[] debtTitles = {
            "Hamilton Tickets",
            "McDonalds",
            "Back massage",
            "Main St. Burrito",
            "Stackhouse",
            "Fuel",
            "Groceries",
            "Parking Ticket"
    };

    Debt lastDeletedDebt;

    DebtListViewModel debtListViewModel;
    RecyclerViewAdapter recyclerViewAdapter;
    RecyclerView recyclerView;
    LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_debt_list);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerViewAdapter = new RecyclerViewAdapter(new ArrayList<Debt>(),
                this,
                this);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setAdapter(recyclerViewAdapter);

        //RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        RecyclerView.ItemAnimator itemAnimator = recyclerView.getItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(10);
        recyclerView.setItemAnimator(itemAnimator);

        debtListViewModel = ViewModelProviders.of(this).get(DebtListViewModel.class);

        debtListViewModel.getDebtList().observe(ViewDebtListActivity.this,
                new Observer<List<Debt>>(){
            @Override
            public void onChanged(@Nullable List<Debt> debts){
                recyclerViewAdapter.addItems(debts);
            }

        });

        FloatingActionButton addButton = findViewById(R.id.fab);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //BRANDON - this would launch your 'AddDebtActivity', or whatever.
                Debt d = new Debt();
                double amount = Math.random() * 100;
                d.setAmount(amount);
                d.setDescription("Tickets to a broadway show");

                String title = debtTitles[(int)(Math.random() * debtTitles.length)];
                d.setTitle(title);

                d.setPaidInFull(false);
                d.setReminderFrequency(864000);
                d.setTheyOweMe(true);
                d.setTime(System.currentTimeMillis());
                d.setContactID(1);
                debtListViewModel.addDebt(d);
            }
        });


    }

    @Override
    public boolean onLongClick(View view) {
        final Debt d = (Debt) view.getTag();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Delete Debt");
        builder.setMessage("Are you sure you'd like to delete this debt?");
        builder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Snackbar deleteAndUndo = Snackbar.make(findViewById(R.id.fab), "Deleting debt",
                                Snackbar.LENGTH_SHORT);
                        deleteAndUndo.setAction(R.string.undo_string, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                debtListViewModel.addDebt(lastDeletedDebt);
                            }
                        });
                        deleteAndUndo.show();
                        lastDeletedDebt = d;
                        debtListViewModel.deleteDebt(d);
                    }
                });
        builder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //do nothing...
                    }
                });
                builder.show();
        return true;
    }

    @Override
    public void onClick(View view) {
        Debt d = (Debt) view.getTag();

        //Toast.makeText(this, d.toString(), Toast.LENGTH_LONG).show();

        Snackbar.make(findViewById(R.id.fab), d.toString(),
                Snackbar.LENGTH_SHORT)
                .show();
    }

}
