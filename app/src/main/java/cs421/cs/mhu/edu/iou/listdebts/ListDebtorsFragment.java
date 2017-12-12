package cs421.cs.mhu.edu.iou.listdebts;

import android.app.Activity;
import android.app.AlertDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cs421.cs.mhu.edu.iou.R;
import cs421.cs.mhu.edu.iou.db.Debt;

/**
 * Created by mgilbert on 12/12/17.
 */

public class ListDebtorsFragment extends Fragment implements
        View.OnLongClickListener,
        View.OnClickListener {

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
    FloatingActionButton addButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_view_debtors, container, false);


        recyclerView = v.findViewById(R.id.recyclerView);
        recyclerViewAdapter = new RecyclerViewAdapter(new ArrayList<Debt>(),
                this,
                this);
        mLayoutManager = new LinearLayoutManager(v.getContext());
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setAdapter(recyclerViewAdapter);

        addButton = v.findViewById(R.id.fab);

        debtListViewModel = ViewModelProviders.of(this).get(DebtListViewModel.class);

        debtListViewModel.getDebtList().observe(this,
                new Observer<List<Debt>>(){
                    @Override
                    public void onChanged(@Nullable List<Debt> debts){
                        recyclerViewAdapter.addItems(debts);
                    }

                });

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


        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }

    @Override public boolean onLongClick(View view) { final Debt d = (Debt) view.getTag();
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());

        builder.setTitle("Delete Debt");
        builder.setMessage("Are you sure you'd like to delete this debt?");
        builder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Snackbar deleteAndUndo = Snackbar.make(
                                ListDebtorsFragment.this.getActivity().findViewById(R.id.fab),
                                "Deleting debt",
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

        Snackbar.make(this.getActivity().findViewById(R.id.fab), d.toString(),
                Snackbar.LENGTH_SHORT)
                .show();
    }



}
