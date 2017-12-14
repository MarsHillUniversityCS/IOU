package cs421.cs.mhu.edu.iou.listdebts;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cs421.cs.mhu.edu.iou.adddebt.AddDebtActivity;
import cs421.cs.mhu.edu.iou.addpayment.AddPayment;
import cs421.cs.mhu.edu.iou.db.Debt;

/**
 * Created by marty on 12/14/17.
 */

public class ListTheirDebtsFragment extends ListDebtsFragment {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState){

        View v = super.onCreateView(inflater, container, savedInstanceState);

        debtListViewModel = ViewModelProviders.of(this).get(DebtListViewModel.class);

        debtListViewModel.getTheirDebtList().observe(this,
                new Observer<List<Debt>>(){
                    @Override
                    public void onChanged(@Nullable List<Debt> debts){
                        recyclerViewAdapter.addItems(debts);
                    }

                });

        addDebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mView.getContext(), AddDebtActivity.class);
                //add an extra flag
                i.putExtra(ListDebtsFragment.WHO_OWES_WHOM, ListDebtsFragment.THEY_OWE);
                startActivity(i);
            }
        });

        addPayment.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent i = new Intent(mView.getContext(), AddPayment.class);
                //add an extra flag
                i.putExtra(ListDebtsFragment.WHO_OWES_WHOM, ListDebtsFragment.THEY_OWE);
                startActivity(i);
            }
        });
        return v;
    }
}
