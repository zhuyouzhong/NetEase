package com.example.netease;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.andy.library.ChannelActivity;
import com.andy.library.ChannelBean;
import com.bumptech.glide.Glide;
import com.example.netease.fragment.Fragment_caijing;
import com.example.netease.fragment.Fragment_guoji;
import com.example.netease.fragment.Fragment_guonei;
import com.example.netease.fragment.Fragment_junshi;
import com.example.netease.fragment.Fragment_keji;
import com.example.netease.fragment.Fragment_left;
import com.example.netease.fragment.Fragment_right;
//import com.example.netease.fragment.Fragment_shouye;
import com.example.netease.fragment.Fragment_shehui;
import com.example.netease.fragment.Fragment_shishang;
import com.example.netease.fragment.Fragment_tiyu;
import com.example.netease.fragment.Fragment_top;
import com.example.netease.fragment.Fragment_wode;
import com.example.netease.fragment.Fragment_yule;
import com.kson.slidingmenu.SlidingMenu;
import com.umeng.socialize.UMShareAPI;
import com.yzq.zxinglibrary.Consants;
import com.yzq.zxinglibrary.android.CaptureActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import bean.News;
import view.HorizonScollTabhost;

public class HomeActivity extends AppCompatActivity {

    private LinearLayout ll_shouye;
    private LinearLayout ll_wode;
    //private Fragment_shouye shouye;
    private Fragment_wode wode;
    private ImageView iv_shouye;
    private ImageView iv_wode;
    private TextView tv_shouye;
    private TextView tv_wode;
    private ImageView iv_caidan;
    private SlidingMenu menu;
    private SharedPreferences sp;
    //private String jsorstr;
    private DrawerLayout dl;
    private LinearLayout ll_left;
    private ImageView iv_zuo;
    private ImageView iv_sadian;
    private ImageView iv_touxiang;
    private LinearLayout ll_right;
    private LinearLayout ll_tianqi;
    private LinearLayout ll_lixian;
    private LinearLayout ll_yejian;
    private LinearLayout ll_sousuo;
    private LinearLayout ll_saoyisao;
    private LinearLayout ll_shezhi;
    private PopupWindow popupWindow;




    private HorizonScollTabhost tabhost;
    private ImageView iv_xjt;
    private String jsorstr;
    private ArrayList<ChannelBean> beanlist;
    private ArrayList<News> list;
    private ArrayList<Fragment> fragments;
    private SharedPreferences msg;
    private LinearLayout ll_wode2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        sp = getSharedPreferences("setting", Context.MODE_PRIVATE);
        initView();
        //添加过来的
        initData();
        initOnClick();
        initSlidingMenu();
       /* shouye = new Fragment_shouye();
        wode = new Fragment_wode();
*/
         //getSupportFragmentManager().beginTransaction().replace(R.id.fl_home, shouye).commit();
        /*getSupportFragmentManager().beginTransaction().add(R.id.fl_home,shouye).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.fl_home,wode).commit();
        getSupportFragmentManager().beginTransaction().show(shouye).commit();
        getSupportFragmentManager().beginTransaction().hide(wode).commit();
*/
         iv_shouye.setImageResource(R.drawable.shouyehong);
         tv_shouye.setTextColor(getResources().getColor(R.color.ziti));


    }

    private void initData() {
        list = new ArrayList<>();
        fragments = new ArrayList<>();
        msg = getSharedPreferences("msg", Context.MODE_PRIVATE);
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

    private void initSlidingMenu() {
      /*  menu = new SlidingMenu(HomeActivity.this);
        menu.setMenu(R.layout.fragment_left);*/
        //menu.setMenu(R.layout.fragment_shouye);
       getSupportFragmentManager().beginTransaction().replace(R.id.fl_left,new Fragment_left()).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_right,new Fragment_right()).commit();
      /*  menu.setMode(SlidingMenu.LEFT);
        menu.setTouchModeAbove(SlidingMenu.LEFT);
        menu.setBehindOffsetRes(R.dimen.jianju);
        menu.attachToActivity(HomeActivity.this,SlidingMenu.SLIDING_CONTENT);*/
    }

    private void initOnClick() {
        ll_shouye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //getSupportFragmentManager().beginTransaction().replace(R.id.fl_home,shouye).commit();
              /*  getSupportFragmentManager().beginTransaction().show(shouye).commit();
                getSupportFragmentManager().beginTransaction().hide(wode).commit();*/

                tabhost.setVisibility(View.VISIBLE);
                ll_wode2.setVisibility(View.GONE);
                iv_shouye.setImageResource(R.drawable.shouyehong);
                tv_shouye.setTextColor(getResources().getColor(R.color.ziti));

                iv_wode.setImageResource(R.drawable.wodebai);
                tv_wode.setTextColor(Color.GRAY);
            }
        });
        ll_wode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // getSupportFragmentManager().beginTransaction().replace(R.id.fl_home,wode).commit();
                /*getSupportFragmentManager().beginTransaction().show(wode).commit();
                getSupportFragmentManager().beginTransaction().hide(shouye).commit();*/
                tabhost.setVisibility(View.GONE);
                ll_wode2.setVisibility(View.VISIBLE);
                iv_shouye.setImageResource(R.drawable.shouyebai);
                tv_shouye.setTextColor(Color.GRAY);

                iv_wode.setImageResource(R.drawable.wodehong);
                tv_wode.setTextColor(getResources().getColor(R.color.ziti));
            }
        });
        iv_caidan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dl.openDrawer(ll_left);
                iv_zuo.setVisibility(View.VISIBLE);
                iv_caidan.setVisibility(View.GONE);
            }
        });
        iv_zuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // dl.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                dl.closeDrawer(ll_left);
                iv_zuo.setVisibility(View.GONE);
                iv_caidan.setVisibility(View.VISIBLE);
            }
        });
        iv_sadian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopWindow();
            }
        });

        iv_touxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dl.isDrawerOpen(ll_right))
                {
                    dl.closeDrawer(ll_right);
                    iv_zuo.setVisibility(View.GONE);
                    iv_caidan.setVisibility(View.VISIBLE);
                }
                else
                {
                    dl.openDrawer(ll_right);
                    iv_zuo.setVisibility(View.VISIBLE);
                    iv_caidan.setVisibility(View.GONE);
                }


            }
        });

            
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
                    ChannelActivity.startChannelActivity(HomeActivity.this, beanlist);
                }
                else
                {
                    ChannelActivity.startChannelActivity(HomeActivity.this,jsorstr);
                }
                msg.edit().putBoolean("zhi",false).commit();
            }
        });

    }

    private void showPopWindow() {
        View view = LayoutInflater.from(HomeActivity.this).inflate(R.layout.popwindow_item, null);
        ll_tianqi = view. findViewById(R.id.ll_tianqi);
        ll_lixian = view. findViewById(R.id.ll_lixian);
        ll_yejian = view. findViewById(R.id.ll_yejian);
        ll_sousuo = view.  findViewById(R.id.ll_sousuo);
        ll_saoyisao = view.  findViewById(R.id.ll_saoyisao);
        ll_shezhi = view.  findViewById(R.id.ll_shezhi);


        //View view1 = LayoutInflater.from(HomeActivity.this).inflate(R.layout.activity_home, null);
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT,true);
        //点击空白处消失popWindow
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        //位于父控件的位置
        //popupWindow.showAtLocation(view1,Gravity.TOP,80,55);
        popupWindow.showAsDropDown(iv_sadian);


        ll_tianqi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this, "天气", Toast.LENGTH_SHORT).show();
                popupWindow.dismiss();
            }
        });
        ll_lixian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this, "离线", Toast.LENGTH_SHORT).show();
                Intent in=new Intent(HomeActivity.this,LixianActivity.class);
                 startActivity(in);
                popupWindow.dismiss();
            }
        });
        ll_yejian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this, "夜间", Toast.LENGTH_SHORT).show();
                int current = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
                if(current==Configuration.UI_MODE_NIGHT_YES)
                {
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);

                }
                else
                {
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);

                }
                popupWindow.dismiss();
            }
        });
        ll_sousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this, "搜索", Toast.LENGTH_SHORT).show();

                popupWindow.dismiss();
            }
        });
        ll_saoyisao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this, "扫一扫", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(HomeActivity.this,
                        CaptureActivity.class);
                startActivityForResult(intent, 0);

                popupWindow.dismiss();
            }
        });
        ll_shezhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent in=new Intent(HomeActivity.this,ShezhiActivity.class);
                startActivity(in);
                Toast.makeText(HomeActivity.this, "设置", Toast.LENGTH_SHORT).show();
                popupWindow.dismiss();
            }
        });


    }


    private void initView() {
        ll_shouye = (LinearLayout) findViewById(R.id.ll_shouye);
        ll_wode = (LinearLayout) findViewById(R.id.ll_wode);
        iv_shouye = (ImageView) findViewById(R.id.iv_shouye);
        iv_wode = (ImageView) findViewById(R.id.iv_wode);
        tv_shouye = (TextView) findViewById(R.id.tv_shouye);
        tv_wode = (TextView) findViewById(R.id.tv_wode);
        dl = (DrawerLayout) findViewById(R.id.dl);
        ll_left = (LinearLayout) findViewById(R.id.ll_left);
       iv_caidan = (ImageView) findViewById(R.id.iv_caidan);
        iv_zuo = (ImageView) findViewById(R.id.iv_zuo);
        iv_sadian = (ImageView) findViewById(R.id.iv_sadian);
        iv_touxiang = (ImageView) findViewById(R.id.iv_touxiang);
        ll_right = (LinearLayout) findViewById(R.id.ll_right);




        tabhost = (HorizonScollTabhost) findViewById(R.id.tabhost);
        iv_xjt = (ImageView) findViewById(R.id.iv_xjt);
        ll_wode2 = (LinearLayout) findViewById(R.id.ll_wode2);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
        if(resultCode==101)
        {
            jsorstr = data.getStringExtra(ChannelActivity.RESULT_JSON_KEY);
            sp.edit().putString("set_setting",jsorstr).commit();
             recreate();
        }
        // 扫描二维码/条码回传
        if (requestCode == 0 && resultCode == RESULT_OK) {
            if (data != null) {
                String content = data.getStringExtra(Consants.CODED_CONTENT);
            }
        }

    }
}
