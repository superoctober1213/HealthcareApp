package com.decard.mobilesdkexample.ReadHistory;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.decard.mobilesdkexample.R;
import java.util.List;

public class NormalAdapter extends RecyclerView.Adapter<com.decard.mobilesdkexample.ReadHistory.NormalAdapter.VH>{

    private List<ObjectModel> mDatas;
    public NormalAdapter(List<ObjectModel> data) {
        this.mDatas = data;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        ObjectModel model = mDatas.get(position);
//        holder.number.setText(model.number + "");
        holder.title.setText(model.title);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //item 点击事件
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_1, parent, false);
        return new VH(v);
    }

    public static class VH extends RecyclerView.ViewHolder{
        public final TextView title;
//        public final TextView number;
        public VH(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.title);
//            number = (TextView) v.findViewById(R.id.number);
        }
    }
}
