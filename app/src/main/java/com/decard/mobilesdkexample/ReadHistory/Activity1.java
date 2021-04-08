package com.decard.mobilesdkexample.ReadHistory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.decard.mobilesdkexample.R;

import java.util.ArrayList;


/**
 * Demo1：RecyclerView的基本使用。
 * - 为RecyclerView添加OnItemClickListener接口。
 * - ItemDecoration的范例：DividerItemDecoration。
 * - 为RecyclerView实现Headerview和Footerview。
 */
public class Activity1 extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ArrayList<com.decard.mobilesdkexample.ReadHistory.ObjectModel> mData;
    private NormalAdapterWrapper mAdapter;
    private com.decard.mobilesdkexample.ReadHistory.NormalAdapter mNoHeaderAdapter;
    private com.decard.mobilesdkexample.ReadHistory.DividerItemDecoration mDecoration;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_id_history);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv);
        mDecoration = new com.decard.mobilesdkexample.ReadHistory.DividerItemDecoration(this, com.decard.mobilesdkexample.ReadHistory.DividerItemDecoration.VERTICAL_LIST);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(mDecoration);
        /*
        CustomAdapter adapter = new CustomAdapter(initData());
        adapter.setOnClickListener(new CustomAdapter.OnClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(Activity1.this,"position=" + position, Toast.LENGTH_SHORT).show();
            }
        });
        */

       // mNoHeaderAdapter = new com.decard.mobilesdkexample.ReadHistory.NormalAdapter(mData = initData());
        mAdapter = new NormalAdapterWrapper(mNoHeaderAdapter);
        View headerView = LayoutInflater.from(this).inflate(R.layout.item_header, mRecyclerView, false);
        View footerView = LayoutInflater.from(this).inflate(R.layout.item_footer, mRecyclerView, false);
        mAdapter.addFooterView(footerView);
        mAdapter.addHeaderView(headerView);
        mRecyclerView.setAdapter(mAdapter);
    }

//    public ArrayList<com.decard.mobilesdkexample.ReadHistory.ObjectModel> initData(){
//        ArrayList<com.decard.mobilesdkexample.ReadHistory.ObjectModel> models = new ArrayList<>();
//        String[] titles = getResources().getStringArray(R.array.title_array);
//        for(int i=0;i<titles.length;i++){
//            com.decard.mobilesdkexample.ReadHistory.ObjectModel model = new com.decard.mobilesdkexample.ReadHistory.ObjectModel();
//            model.number = i + 1;
//            model.title = titles[i];
//            models.add(model);
//        }
//        return models;
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.item_add:
                com.decard.mobilesdkexample.ReadHistory.ObjectModel obj = new  com.decard.mobilesdkexample.ReadHistory.ObjectModel();
                obj.number = 0;
                obj.title = "Insert";
                mData.add(0,obj);
                mAdapter.notifyItemInserted(1);
                break;
            case R.id.item_delete:
                mData.remove(0);
                mAdapter.notifyItemRemoved(1);
                break;
//            case R.id.item_change_divider:
//                mDecoration.setDividerDrawable(getResources().getDrawable(R.drawable.divider));
//                mAdapter.notifyDataSetChanged();
//                break;
            case R.id.item_hlistview:
                mRecyclerView.removeItemDecoration(mDecoration);
                mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
                mRecyclerView.setLayoutManager(mLayoutManager);
                mDecoration = new  com.decard.mobilesdkexample.ReadHistory.DividerItemDecoration(this,  com.decard.mobilesdkexample.ReadHistory.DividerItemDecoration.HORIZONTAL_LIST);
                mRecyclerView.addItemDecoration(mDecoration);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
