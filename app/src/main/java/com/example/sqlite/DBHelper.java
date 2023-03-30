package com.example.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static final String TEN_DATABASE = "QuanLySinhVien";
    public static final String TEN_BANG_SINHVIEN = "SinhVien";
    public static final String COT_ID="_id";
    public static final String COT_TEN="_ten";
    public static final String COT_LOP="_lop";

    private static final String TAO_BANG_SINHVIEN = ""
            + "create table " + TEN_BANG_SINHVIEN + " ( "
            + COT_ID + " integer primary key autoincrement ,"
            + COT_TEN + " text not null, "
            + COT_LOP + " text not null );";

    public DBHelper(@NonNull Context context) {
        super(context, TEN_DATABASE, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TAO_BANG_SINHVIEN);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
