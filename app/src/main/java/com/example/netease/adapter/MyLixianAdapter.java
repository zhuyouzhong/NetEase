package com.example.netease.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.netease.R;
import com.example.netease.bean.Lixian;

import java.util.ArrayList;

/**
 * Created by 祝文 on 2017/9/16.
 */

public class MyLixianAdapter extends BaseAdapter {
    private ArrayList<Lixian> list;
    private Context context;

    public MyLixianAdapter(ArrayList<Lixian> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view=LayoutInflater.from(context).inflate(R.layout.lixian_item,null);
        TextView tv_name=view.findViewById(R.id.tv_name);
        CheckBox cb= view.findViewById(R.id.cb);
        tv_name.setText(list.get(i).name);
        cb.setChecked(true);
        return view;
    }
}
