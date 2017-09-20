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
 * Created by 祝文 on 2017/9/15.
 */

public class TupianPageAdapter extends PagerAdapter {
    private ArrayList<String> tu_list;
    private Context context;

    public TupianPageAdapter(ArrayList<String> tu_list, Context context) {
        this.tu_list = tu_list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.tupian, null);
        ImageView iv_vp_tu=view.findViewById(R.id.iv_vp_tu);
        Glide.with(context).load(tu_list.get(position%tu_list.size()).toString()).into(iv_vp_tu);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
