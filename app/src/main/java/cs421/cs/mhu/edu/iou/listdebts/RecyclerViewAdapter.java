package cs421.cs.mhu.edu.iou.listdebts;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cs421.cs.mhu.edu.iou.util.ContactManager;
import cs421.cs.mhu.edu.iou.R;
import cs421.cs.mhu.edu.iou.db.Debt;

/**
 * This class handles the list view of all of the debts
 * Created by marty on 12/1/17.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private List<Debt> debtList;
    private View.OnLongClickListener longClickListener;
    private View.OnClickListener clickListener;
    private Context mContext;

    public void setContext(Context c){
        mContext = c;
    }

    RecyclerViewAdapter(List<Debt> debtList,
                               View.OnLongClickListener longClickListener,
                               View.OnClickListener clickListener) {
        this.debtList = debtList;
        this.longClickListener = longClickListener;
        this.clickListener = clickListener;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.debt_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {
        Debt debt = debtList.get(position);
        holder.titleTextView.setText(debt.getTitle());
        //BRANDON - this is where we take debt.getContactId() and retrieve a First/Last name
        //holder.nameTextView.setText(debt.getPersonName());

        //placeholder until Brandon works his magic
        holder.nameTextView.setText(ContactManager.getNameUsingContactId(
                mContext, debt.getContactID()+""));


        holder.amountTextView.setText(debt.getAmountString());
        holder.itemView.setTag(debt);
        holder.itemView.setOnLongClickListener(longClickListener);
        holder.itemView.setOnClickListener(clickListener);
    }

    @Override
    public int getItemCount() {
        return debtList.size();
    }

    void addItems(List<Debt> debtList) {
        this.debtList = debtList;
        notifyDataSetChanged();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private TextView nameTextView;
        private TextView amountTextView;

        RecyclerViewHolder(View view) {
            super(view);
            titleTextView   =     view.findViewById(R.id.titleTextView);
            nameTextView    =      view.findViewById(R.id.nameTextView);
            amountTextView  =    view.findViewById(R.id.amountTextView);
        }
    }

    /*public String getPersonName(long contactId){
        //AddDebtActivity.
    }*/
}
