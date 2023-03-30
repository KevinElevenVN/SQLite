package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MyDatabase {
    SQLiteDatabase database;
    DBHelper helper;

    public MyDatabase(Context context){
        helper = new DBHelper(context);
        database = helper.getWritableDatabase();
    }

    public Cursor layTatCaDuLieu(){
        String[] cot = {DBHelper.COT_ID,
                DBHelper.COT_TEN,
                DBHelper.COT_LOP};
        Cursor cursor = null;
        cursor = database.query(DBHelper.
                TEN_BANG_SINHVIEN,cot,null,null,null,null,
                DBHelper.COT_ID + " DESC");
        return cursor;
    }

    public long them(SinhVien sinhVien) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.COT_TEN,sinhVien.get_ten());
        values.put(DBHelper.COT_LOP,sinhVien.get_lop());

        return database.insert(DBHelper.
                TEN_BANG_SINHVIEN,null,values);
    }

    public long xoa(SinhVien sinhVien) {
        return database.delete(DBHelper.TEN_BANG_SINHVIEN,
                DBHelper.COT_TEN + " = " + "" + sinhVien.get_ten() + "'", null);
    }

    public long sua(SinhVien sinhVien){
        ContentValues values = new ContentValues();
        values.put(DBHelper.COT_TEN,sinhVien.get_ten());
        values.put(DBHelper.COT_LOP,sinhVien.get_lop());

        return database.update(DBHelper
                .TEN_BANG_SINHVIEN, values,
                DBHelper.COT_ID + " = "
                + sinhVien.get_id(),null);
    }
}
