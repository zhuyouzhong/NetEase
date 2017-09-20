package com.example.netease.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.netease.R;
import com.example.netease.bean.Shuju;

import java.util.ArrayList;

/**
 * Created by 祝文 on 2017/9/14.
 */

public class MyAdapter extends BaseAdapter {
    private ArrayList<Shuju> list;
    private Context context;
    private final int a=0;
    private final int b=1;
    private final int count=2;


    public MyAdapter(ArrayList<Shuju> list, Context context) {
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
    public int getViewTypeCount() {


        return count;
    }

    @Override
    public int getItemViewType(int position) {

        if(position%2==0)
        {
            return a;
        }
        else
        {
            return b;
        }

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        int type = getItemViewType(i);
        Viewholder1 holder1=null;
        Viewholder2 holder2=null;

        if(view==null)
        {
            switch (type)
            {
                case a:
                    holder1=new Viewholder1();
                    view= LayoutInflater.from(context).inflate(R.layout.item1,null);
                    holder1.tv_title=view.findViewById(R.id.tv_title);
                    holder1.iv_tupian=view.findViewById(R.id.iv_tupian);
                    holder1.tv_data=view.findViewById(R.id.tv_data);
                    holder1.tv_author_name=view.findViewById(R.id.tv_author_name);
                    view.setTag(holder1);
                    break;
                case b:
                    holder2=new Viewholder2();
                    view= LayoutInflater.from(context).inflate(R.layout.item2,null);
                    holder2.tv_title=view.findViewById(R.id.tv_title);
                    holder2.iv_tupian=view.findViewById(R.id.iv_tupian);
                    holder2.tv_data=view.findViewById(R.id.tv_data);
                    holder2.tv_author_name=view.findViewById(R.id.tv_author_name);
                    view.setTag(holder2);
                    break;
            }
        }
        else
        {
            switch (type)
            {
                case a:
                    holder1= (Viewholder1) view.getTag();
                    break;
                case b:
                    holder2= (Viewholder2) view.getTag();
                    break;
            }

        }
        switch (type)
        {

            case a:
                SharedPreferences tuzhi = context.getSharedPreferences("tu", Context.MODE_PRIVATE);
                holder1.tv_title.setText(list.get(i).getTitle());
                boolean tuzhi1 = tuzhi.getBoolean("tuzhi", false);
                if(tuzhi1==true)
                {
                    Glide.with(context).load(list.get(i).getThumbnail_pic_s()).into(holder1.iv_tupian);

                }
                holder1.tv_data.setText(list.get(i).getDate());
                holder1.tv_author_name.setText(list.get(i).getAuthor_name());
                break;
            case  b:
                SharedPreferences tuzhi2 = context.getSharedPreferences("tu", Context.MODE_PRIVATE);
                boolean tuzhi3 = tuzhi2.getBoolean("tuzhi", false);
                holder2.tv_title.setText(list.get(i).getTitle());
                if( tuzhi3==true)
                {
                    Glide.with(context).load(list.get(i).getThumbnail_pic_s()).into(holder2.iv_tupian);

                }
                holder2.tv_data.setText(list.get(i).getDate());
                holder2.tv_author_name.setText(list.get(i).getAuthor_name());
                break;
        }
        return view;
    }
    public class Viewholder1
    {
        public TextView tv_title,tv_data,tv_author_name;
        public ImageView iv_tupian;
    }
    public class Viewholder2
    {
        public TextView tv_title,tv_data,tv_author_name;
        public ImageView iv_tupian;
    }
}
