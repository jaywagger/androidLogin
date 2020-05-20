package com.example.finalproject.login;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ModeDBHelper extends SQLiteOpenHelper {
    public static  final int DB_VERSION = 1;

    public ModeDBHelper(Context context){
        super(context,"mode.db",null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("db","db생성");

        String sql = "create table if not exists mode("
                    +"idx integer primary key autoincrement,"
                    +"mode integer)";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("dbtest","데이터베이스의 스키마 변경");
        switch (oldVersion){
            case 1:
                Log.d("db","버전 업그레이드");
                break;
            case 2:
                Log.d("db","버전 업그레이드");
                break;
        }
    }
}
