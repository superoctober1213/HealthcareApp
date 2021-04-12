package com.decard.mobilesdkexample.ReadHistory;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.decard.mobilesdkexample.LoginRegister.DBOpenHelper;
import com.decard.mobilesdkexample.LoginRegister.User;
import com.decard.mobilesdkexample.R;

import java.sql.DatabaseMetaData;
import java.util.ArrayList;

public class Activity1 extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DBReadHelper dbReadHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_id_history);
        recyclerView = findViewById(R.id.rv);
        dbReadHelper = new DBReadHelper(this);
        ArrayList<IdInfo> data = dbReadHelper.getAllData();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        HistoryAdapter historyAdapter = new HistoryAdapter(data);
        recyclerView.setAdapter(historyAdapter);

    }
}

interface OnStartDragListener{
    void startDrag(RecyclerView.ViewHolder holder);
}
