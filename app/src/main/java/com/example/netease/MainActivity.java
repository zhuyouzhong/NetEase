package com.example.netease;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.netease.adapter.MyPagerAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager vp;
    private ArrayList<Integer> list;
    private TextView tv_jinru;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = getSharedPreferences("msg", MODE_PRIVATE);
        boolean zhi = sp.getBoolean("zhi", false);
        if(zhi==false)
        {
            initView();
            initOnClick();
            initData();
            setData();
        }
        else
        {
            Intent in=new Intent(MainActivity.this,HomeActivity.class);
            startActivity(in);
            finish();
        }
    }

    private void initOnClick() {
        tv_jinru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(MainActivity.this,HomeActivity.class);
                startActivity(in);
                finish();
                sp.edit().putBoolean("zhi",true).commit();
            }
        });
    }

    private void setData() {
        MyPagerAdapter ma=new MyPagerAdapter(list,MainActivity.this);
        vp.setAdapter(ma);

        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position==4)
                {
                    tv_jinru.setVisibility(View.VISIBLE);
                }
                else
                {
                    tv_jinru.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initData() {
        list = new ArrayList<>();
        list.add(R.drawable.a);
        list.add(R.drawable.b);
        list.add(R.drawable.c);
        list.add(R.drawable.d);
        list.add(R.drawable.e);
    }

    private void initView() {
        vp = (ViewPager) findViewById(R.id.vp);
        tv_jinru = (TextView) findViewById(R.id.tv_jinru);
    }
}
