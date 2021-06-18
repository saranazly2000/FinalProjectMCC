package com.example.project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class DatesAdapter extends RecyclerView.Adapter<DatesAdapter.ViewHolder>  {
    private List<Dates> dateList;
    private OnCompleteListener<QuerySnapshot> context;

    public DatesAdapter(List<Dates> dateList, OnCompleteListener<QuerySnapshot> context) {
        this.dateList = dateList;
        this.context =  context;
    }

    @NonNull
    @Override
    public DatesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.date_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Dates date =dateList.get(position);
        holder.date.setText(date.getDate());
        holder.event.setText(date.getEvent());

    }

    @Override
    public int getItemCount() {
        return dateList.size();
    }
    public  class ViewHolder extends RecyclerView.ViewHolder{
        public TextView date;
        public TextView event;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date = (TextView) itemView.findViewById(R.id.dateTV);
            event = (TextView) itemView.findViewById(R.id.eventTV);


        }


    }


}