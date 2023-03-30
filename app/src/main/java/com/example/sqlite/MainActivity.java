package com.example.sqlite;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    public static ListView listView;
    public static ArrayList<SinhVien> sinhViens;
    public static MyDatabase database;
    private EditText editTextTen, editTextLop;
    private Button btnTen,btnLop;

    private String ten, lop;
    private static long id = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextTen = findViewById(R.id.edtTen);
        editTextLop = findViewById(R.id.edtLop);
        btnTen=findViewById(R.id.btnThem);
        btnLop=findViewById(R.id.btnSua);
    }

    @SuppressLint("Range")
    public void capNhatDuLieu() {
        if (sinhViens == null) {
            sinhViens = new ArrayList<>();
        } else {
            sinhViens.removeAll(sinhViens);
        }
        Cursor cursor = database.layTatCaDuLieu();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                SinhVien sinhVien = new SinhVien();
                sinhVien.set_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex(
                        DBHelper.COT_ID))));
                sinhVien.set_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex(
                        DBHelper.COT_TEN))));
                sinhVien.set_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex(
                        DBHelper.COT_LOP))));
                sinhViens.add(sinhVien);
            }
        }

        if (sinhViens != null) {
            listView.setAdapter(new MyAdapter(getApplicationContext()));
        }

        listView.setOnItemClickListener((adapterView, view, postition, id) -> {
            editTextTen.setText(sinhViens.get(postition).get_ten());
            editTextLop.setText(sinhViens.get(postition).get_lop());
            MainActivity.id = id;
        });
    }

    public SinhVien layDuLieuNguoiDung() {
        ten = editTextTen.getText().toString();
        lop = editTextLop.getText().toString();
        if (ten.trim().length() == 0  || lop.trim().length() == 0) {
            return null;
        }
        SinhVien sinhVien = new SinhVien();
        sinhVien.set_id(id);
        sinhVien.set_ten(ten);
        sinhVien.set_lop(lop);
        return sinhVien;
    }

    public void them(View view) {
        SinhVien sinhVien1 = layDuLieuNguoiDung();
        if (sinhVien1 != null) {
            if (database.them(sinhVien1) != -1) {
                sinhViens.add(sinhVien1);
                capNhatDuLieu();
                listView.invalidateViews();
                editTextTen.setText(null);
                editTextLop.setText(null);
                id = -1;
            }
        }
    }

    public void sua(View view) {
        SinhVien sinhVien1 = layDuLieuNguoiDung();
        if (sinhVien1 != null && id != -1) {
            database.sua(sinhVien1);
            capNhatDuLieu();
            listView.invalidateViews();
            editTextTen.setText(null);
            editTextLop.setText(null);
            id = -1;
        }

    }
}