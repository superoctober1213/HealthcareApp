package com.decard.mobilesdkexample.ReadHistory;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

public class NormalAdapterWrapper extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    enum ITEM_TYPE{
        HEADER,
        FOOTER,
        NORMAL
    }

    private com.decard.mobilesdkexample.ReadHistory.NormalAdapter mAdapter;
    private View mHeaderView;
    private View mFooterView;

    public NormalAdapterWrapper(com.decard.mobilesdkexample.ReadHistory.NormalAdapter adapter){
        mAdapter = adapter;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return ITEM_TYPE.HEADER.ordinal();
        } else if(position == mAdapter.getItemCount() + 1){
            return ITEM_TYPE.FOOTER.ordinal();
        } else{
            return ITEM_TYPE.NORMAL.ordinal();
        }
    }

    @Override
    public int getItemCount() {
        return mAdapter.getItemCount() + 2;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(position == 0){
            return;
        } else if(position == mAdapter.getItemCount() + 1){
            return;
        } else{
            mAdapter.onBindViewHolder(((com.decard.mobilesdkexample.ReadHistory.NormalAdapter.VH)holder), position - 1);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == ITEM_TYPE.HEADER.ordinal()){
            return new RecyclerView.ViewHolder(mHeaderView) {};
        } else if(viewType == ITEM_TYPE.FOOTER.ordinal()){
            return new RecyclerView.ViewHolder(mFooterView) {};
        } else{
            return mAdapter.onCreateViewHolder(parent,viewType);
        }
    }

    public void addHeaderView(View view){
        this.mHeaderView = view;
    }
    public void addFooterView(View view){
        this.mFooterView = view;
    }
}
