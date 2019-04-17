package com.example.apple.yunqiao_weex.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.apple.yunqiao_weex.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 姓名    PanJiangHao
 * 时间    2019/4/17 4:28 PM
 * 描述
 */

public class MyAdapter extends ArrayAdapter<String> {
    List<String> list = new ArrayList<>();
    public MyAdapter(@NonNull Context context, int resource, @NonNull List<String> objects) {
        super(context, resource, objects);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.my_list_view_item,null);
        } else {
            view = convertView;
        }
        TextView textView = view.findViewById(R.id.text_view);
        textView.setText(getItem(position));
        return view;
    }
}
