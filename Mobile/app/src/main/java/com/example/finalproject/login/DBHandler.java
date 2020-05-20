package com.example.finalproject.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class DBHandler {
    static ModeDBHelper modeDBHelper;
    static SQLiteDatabase db;
    Context context;

    public DBHandler(Context context) {
        this.context = context;
        modeDBHelper = new ModeDBHelper(context);
        db = modeDBHelper.getWritableDatabase();
    }
    public void insert(int mode){
        ContentValues contentValues = new ContentValues();
        contentValues.put("mode",mode);
        db.insert("mode",null,contentValues);
    }
    public Cursor select(){
        Cursor cursor = db.query("mode",null,null,null,
                null,null,null);
        return cursor;
    }
    public Cursor selectMode(){
        Cursor cursor = db.query("mode",new String[]{"mode"},null,null,
                null,null,null);
        return cursor;
    }
    /*public void update(){
        Cursor sel = select();
        sel.getInt(0);
    }*/
}
