package com.example.netease.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.example.netease.R;

import java.util.ArrayList;

/**
 * Created by 祝文 on 2017/9/14.
 */

public class MyPagerAdapter extends PagerAdapter {
    private ArrayList<Integer> list;
    private Context context;

    public MyPagerAdapter(ArrayList<Integer> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.pager_item, null);
        ImageView iv_pager=view.findViewById(R.id.iv_pager);

        Glide.with(context).load(list.get(position)).into(iv_pager);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
