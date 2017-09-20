package com.example.netease.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.netease.R;
import com.example.netease.XiangqingActivity;
import com.example.netease.adapter.MyAdapter;
import com.example.netease.adapter.TupianPageAdapter;
import com.example.netease.api.ApiUtils;
import com.example.netease.bean.Bean;
import com.example.netease.bean.Shuju;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import view.xlistview.XListView;

/**
 * Created by 祝文 on 2017/9/14.
 */

public class Fragment_junshi extends Fragment implements XListView.IXListViewListener{

    private View view;

    private XListView xlv;
    private ArrayList<Shuju> list;
    private MyAdapter ma;
    private OkHttpClient client;
    private ViewPager vp_top;
    private String tu1="http://01.imgmini.eastday.com/mobile/20170915/20170915103459_fed6d90d8d3503e5eb9649bda0834ad7_4_mwpm_03200403.jpg";
    private String tu2="http://01.imgmini.eastday.com/mobile/20170915/20170915140430_8ba58922372793d5196f1b309b067bd1_7_mwpm_03200403.jpg";
    private String tu3="http://01.imgmini.eastday.com/mobile/20170915/20170915103459_fed6d90d8d3503e5eb9649bda0834ad7_1_mwpm_03200403.jpg";
    private ArrayList<String> tu_list;
    private Handler hd=new Handler();
    private int count=0;
    private LinearLayout ll_dian;
    private ArrayList<ImageView> iv_list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.fragmentzong, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        iv_list = new ArrayList<>();
        client = new OkHttpClient();
        initView();
        initData();
        jiadian();

        new Thread(new Runnable() {
            @Override
            public void run() {
                initRequest();
            }
        }).start();

    }

    private void jiadian() {
        for (int i = 0; i <tu_list.size() ; i++) {
            ImageView iv_dian=new ImageView(getContext());

            if(i==0)
            {
                iv_dian.setImageResource(R.drawable.hong);
            }
            else
            {
                iv_dian.setImageResource(R.drawable.heise);
            }
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(10,10);
            params.setMargins(5,0,5,0);
            ll_dian.addView(iv_dian,params);
            iv_list.add(iv_dian);
        }


    }

    private void initRequest() {

        Request request=new Request.Builder()
                .url(ApiUtils.url+"&type=junshi")
                .build();
        try {
            Response response = client.newCall(request).execute();
            if(response.isSuccessful())
            {
                jiexi(response.body().string());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initData() {

        list = new ArrayList<>();

        xlv.setPullLoadEnable(true);
        xlv.setXListViewListener(this);

        initTopPagerAdapter();


    }

    private void initTopPagerAdapter() {
        View view1 = View.inflate(getContext(), R.layout.vp_top_item, null);
        ll_dian = view1.findViewById(R.id.ll_dian);
        vp_top=view1.findViewById(R.id.vp_top);
        xlv.addHeaderView(view1);
        tu_list = new ArrayList<>();
        tu_list.add(tu1);
        tu_list.add(tu2);
        tu_list.add(tu3);
        TupianPageAdapter tp=new TupianPageAdapter(tu_list,getContext());
        vp_top.setAdapter(tp);
        vp_top.setCurrentItem(1000000000);

        vp_top.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i <tu_list.size() ; i++) {
                    if(i==position%tu_list.size())
                    {
                        iv_list.get(i).setImageResource(R.drawable.hong);
                    }
                    else
                    {
                        iv_list.get(i).setImageResource(R.drawable.heise);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //自动轮播
       /* Runnable r=new Runnable() {
            @Override
            public void run() {
                count++;
                vp_top.setCurrentItem(count);
                hd.postDelayed(this,1000);
            }
        };
        hd.postDelayed(r,1000);*/
    }

    private void initView() {
        xlv = view.findViewById(R.id.xlv);

    }




    private void jiexi(String string) {

        Gson gson=new Gson();
        Bean bean=gson.fromJson(string,Bean.class);
        Bean.ResultBean result = bean.getResult();
        List<Bean.ResultBean.DataBean> data = result.getData();
        for (int i = 0; i < data.size(); i++) {
            Bean.ResultBean.DataBean dataBean = data.get(i);
            String title = dataBean.getTitle();
            String date = dataBean.getDate();
            String author_name = dataBean.getAuthor_name();
            String url = dataBean.getUrl();
            String thumbnail_pic_s = dataBean.getThumbnail_pic_s();

            list.add(new Shuju(title,date,author_name,url,thumbnail_pic_s));
        }
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                setData();
            }
        });
    }

    private void setData() {
        if(ma==null)
        {
            ma = new MyAdapter(list,getContext());
            xlv.setAdapter(ma);
        }
        else
        {
            ma.notifyDataSetChanged();
        }

        xlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent in=new Intent(getContext(), XiangqingActivity.class);
                in.putExtra("url",list.get(i-2).getUrl());
                getContext().startActivity(in);
            }
        });
        xlv.stopLoadMore();
        xlv.stopRefresh();
    }

    @Override
    public void onRefresh() {

        list.clear();
        new Thread(new Runnable() {
            @Override
            public void run() {
                initRequest();
            }
        }).start();
    }

    @Override
    public void onLoadMore() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                initRequest();
            }
        }).start();
    }
}
