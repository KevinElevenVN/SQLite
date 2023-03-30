package com.example.sqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {

    LayoutInflater inflater;
    TextView textView;
    Context context;

    public MyAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public int getCount() {
        return MainActivity.sinhViens.size();
    }

    @Override
    public Object getItem(int position) {
        return MainActivity.sinhViens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return MainActivity.sinhViens.get(position).get_id();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout
                .item_list, null);
        textView =  view.findViewById(R.id.tvName);
        textView.setText(MainActivity.sinhViens
                .get(position).get_ten());
        textView =  view.findViewById(R.id.tvClass);
        textView.setText(MainActivity.sinhViens
                .get(position).get_lop());
        view.findViewById(R.id.imgDelete)
                .setOnClickListener(view1 -> {
                    MainActivity.database.xoa
                            (MainActivity.sinhViens.get(position));
                    MainActivity.sinhViens.remove(position);
                    notifyDataSetChanged();
                });
        return view;
    }
}
