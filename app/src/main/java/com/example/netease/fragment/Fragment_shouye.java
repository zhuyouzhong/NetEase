package com.example.netease.fragment;

/*
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;


import com.andy.library.ChannelActivity;
import com.andy.library.ChannelBean;
import com.example.netease.R;
import com.kson.slidingmenu.SlidingMenu;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import bean.News;
import view.HorizonScollTabhost;

*/
/**
 * Created by 祝文 on 2017/9/14.
 *//*


public class Fragment_shouye extends Fragment{

    private View view;
    private HorizonScollTabhost tabhost;
    private ImageView iv_xjt;
    private String jsorstr;
    private SharedPreferences sp;
    private ArrayList<ChannelBean> beanlist;
    private SharedPreferences msg;
    private ArrayList<News> list;
    private ArrayList<Fragment> fragments;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_shouye, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        sp = getContext().getSharedPreferences("setting", Context.MODE_PRIVATE);

        initView();
        initOnClick();
        initData();
    }

  */
/*  @Override
    public void onResume() {
        super.onResume();
        initView();
        initOnClick();
        initData();
    }*//*


    private void initData() {
        list = new ArrayList<>();
        fragments = new ArrayList<>();
        msg = getContext().getSharedPreferences("msg", Context.MODE_PRIVATE);
        boolean zhi = msg.getBoolean("zhi", false);
        //Toast.makeText(getContext(), ""+zhi, Toast.LENGTH_SHORT).show();
        if(zhi)
        {
            //Toast.makeText(getContext(), "dsfghj" , Toast.LENGTH_SHORT).show();
            list.add(new News("社会",true));
            list.add(new News("国内",true));
            list.add(new News("国际",true));
            list.add(new News("娱乐",true));
            list.add(new News("体育",true));
            list.add(new News("科技",true));
            list.add(new News("头条",true));
            list.add(new News("军事",true));
            list.add(new News("财经",true));
            list.add(new News("时尚",true));

            fragments.add(new Fragment_shehui());
            fragments.add(new Fragment_guonei());
            fragments.add(new Fragment_guoji());
            fragments.add(new Fragment_yule());
            fragments.add(new Fragment_tiyu());
            fragments.add(new Fragment_keji());
            fragments.add(new Fragment_top());
            fragments.add(new Fragment_junshi());
            fragments.add(new Fragment_caijing());
            fragments.add(new Fragment_shishang());

            tabhost.diaply(list, fragments);
        }

        jsorstr=sp.getString("set_setting",null);
        if(jsorstr!=null)
        {
            try {
                JSONArray jsonArray=new JSONArray(jsorstr);
                list.clear();
                for (int i = 0; i <jsonArray.length() ; i++) {
                    JSONObject jb= (JSONObject) jsonArray.get(i);
                    String name = jb.getString("name");
                    boolean isSelect = jb.getBoolean("isSelect");
                    if(isSelect==true)
                    {
                        list.add(new News(name,isSelect));
                        if(name.equals("社会"))
                        {
                            fragments.add(new Fragment_shehui());
                        }
                        else if(name.equals("国内"))
                        {
                            fragments.add(new Fragment_guonei());
                        }
                        else if(name.equals("国际"))
                        {
                            fragments.add(new Fragment_guoji());
                        }
                        else if(name.equals("娱乐"))
                        {
                            fragments.add(new Fragment_yule());
                        }
                        else if(name.equals("体育"))
                        {
                            fragments.add(new Fragment_tiyu());
                        }
                        else if(name.equals("科技"))
                        {
                            fragments.add(new Fragment_keji());
                        }
                        else if(name.equals("头条"))
                        {
                            fragments.add(new Fragment_top());
                        }
                        else if(name.equals("军事"))
                        {
                            fragments.add(new Fragment_junshi());
                        }
                        else if(name.equals("财经"))
                        {
                            fragments.add(new Fragment_caijing());
                        }
                        else if(name.equals("时尚"))
                        {
                            fragments.add(new Fragment_shishang());
                        }
                    }
                }
                tabhost.diaply(list,fragments);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private void initOnClick() {


        iv_xjt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jsorstr=sp.getString("set_setting",null);
                beanlist = new ArrayList<ChannelBean>();
                if(jsorstr==null)
                {
                    beanlist.add(new ChannelBean("社会",true));
                    beanlist.add(new ChannelBean("国内",true));
                    beanlist.add(new ChannelBean("国际",true));
                    beanlist.add(new ChannelBean("娱乐",true));
                    beanlist.add(new ChannelBean("体育",true));
                    beanlist.add(new ChannelBean("科技",true));
                    beanlist.add(new ChannelBean("头条",true));
                    beanlist.add(new ChannelBean("军事",true));
                    beanlist.add(new ChannelBean("财经",true));
                    beanlist.add(new ChannelBean("时尚",true));
                    ChannelActivity.startChannelActivity((AppCompatActivity) getActivity(), beanlist);
                }
                else
                {
                    ChannelActivity.startChannelActivity((AppCompatActivity) getActivity(),jsorstr);
                }
                msg.edit().putBoolean("zhi",false).commit();
            }
        });
    }

    private void initView() {
        tabhost = view.findViewById(R.id.tabhost);
        iv_xjt = view.findViewById(R.id.iv_xjt);
    }


}
*/
