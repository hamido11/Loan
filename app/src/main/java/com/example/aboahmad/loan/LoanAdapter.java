package com.example.aboahmad.loan;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class LoanAdapter extends  RecyclerView.Adapter<LoanAdapter.LoanViewHolder> {
   private onBorrowerClickListener mlistener;
   private List<Borrower> borrowerList=new ArrayList<>();
    @Override
    public LoanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.loan_item,parent
        ,false);
        LoanViewHolder viewHolder=new LoanViewHolder(v);
     return viewHolder;
    }

    @Override
    public void onBindViewHolder(LoanViewHolder holder, int position) {
    Borrower curentBorrower=borrowerList.get(position);
     holder.tv_name.setText(curentBorrower.getName());
     holder.tv_description.setText(curentBorrower.getLoan_description());
    }

    @Override
    public int getItemCount() {
        return borrowerList.size();
    }
    public void setBorrowerList(List<Borrower> borrowers){
        this.borrowerList=borrowers;
        notifyDataSetChanged();
    }

    public void setOnBorrowerClockListener(onBorrowerClickListener listener){
        this.mlistener=listener;
    }
    public Borrower getBorrowerAt(int position){
        return borrowerList.get(position);
    }


    public  class LoanViewHolder extends RecyclerView.ViewHolder{
     TextView tv_name;
     TextView tv_description;

        public LoanViewHolder(View itemView) {
            super(itemView);
            tv_name=itemView.findViewById(R.id.text_name);
            tv_description=itemView.findViewById(R.id.text_description);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position=getAdapterPosition();
                    if(mlistener != null && position != RecyclerView.NO_POSITION){
                        Borrower borrower=borrowerList.get(position);
                        mlistener.onBorrowerClick(borrower);
                    }
                }
            });
        }
    }
    public interface onBorrowerClickListener{
        void onBorrowerClick(Borrower borrower);
    }
}
