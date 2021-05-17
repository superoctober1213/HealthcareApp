package com.decard.mobilesdkexample.BaseClass;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.ArrayList;

public class DBReadHelper extends SQLiteOpenHelper {

    private SQLiteDatabase db;

    public DBReadHelper(Context context){
        super(context, "db_test", null,1);
        db = getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS info(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "gender TEXT," +
                "birth TEXT," +
                "idNum TEXT," +
                "address TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS info");
        onCreate(sqLiteDatabase);
    }

    public void add(String name, String gender, String birth, String idNum, String address){
        db.execSQL("INSERT INTO info(name,gender,birth,idNum,address) VALUES(?,?,?,?,?)", new Object[]{name,gender,birth,idNum,address});
    }

//    public void delete(){
//        db.delete("info", "birth = 19961005", null);
//    }


    public ArrayList<IdInfo> getAllData(){
        ArrayList<IdInfo> list = new ArrayList<>();
        Cursor cursor = db.query("info", null,null,null,null,null, null);
        while (cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String gender = cursor.getString(cursor.getColumnIndex("gender"));
            String birth = cursor.getString(cursor.getColumnIndex("birth"));
            String idNum = cursor.getString(cursor.getColumnIndex("idNum"));
            String address = cursor.getString(cursor.getColumnIndex("address"));
            list.add(new IdInfo(name,gender,birth,idNum,address));
        }
        return list;
    }
}
