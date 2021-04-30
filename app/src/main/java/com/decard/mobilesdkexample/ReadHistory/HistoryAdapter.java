package com.decard.mobilesdkexample.ReadHistory;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.decard.mobilesdkexample.R;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private ArrayList<IdInfo> list;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView gender;
        TextView birth;
        TextView num;
        TextView address;

        public ViewHolder(View view){
            super(view);
            name = view.findViewById(R.id.info_name);
            gender = view.findViewById(R.id.info_gender);
            birth = view.findViewById(R.id.info_birth);
            num = view.findViewById(R.id.info_num);
            address = view.findViewById(R.id.info_address);

        }
    }

    public HistoryAdapter(ArrayList<IdInfo> readHistoryList){
        list = readHistoryList;

    }


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        IdInfo idInfo = list.get(position);
        holder.name.setText(idInfo.getName());
        holder.gender.setText(idInfo.getGender());
        holder.birth.setText(idInfo.getBirth());
        holder.num.setText(idInfo.getIdNum());
        holder.address.setText(idInfo.getAddress());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
