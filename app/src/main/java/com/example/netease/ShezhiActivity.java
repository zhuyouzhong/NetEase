package com.example.netease;

import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.liu.asus.clearcun.CliearUyils;
import com.mo.updateload.UpdataLoad;

import cn.jpush.android.api.JPushInterface;

public class ShezhiActivity extends AppCompatActivity {

    private ImageView iv_shezhi_jiantou;
    private RelativeLayout rl_qingli;
    private CliearUyils cliearUyils;
    private TextView tv_qingli_kb;
    private RelativeLayout rl_gengxin;
    private RelativeLayout rl_tuisong;
    private Switch sw_tuisong;
    private SharedPreferences sp;
    private Switch sw_nowifexiazais;
    private SharedPreferences tu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shezhi);

        sp = getSharedPreferences("img", MODE_PRIVATE);
        tu = getSharedPreferences("tu", MODE_PRIVATE);
        initView();
        initOnclick();
        boolean zhi = sp.getBoolean("zhi", false);
        sw_tuisong.setChecked(zhi);

        boolean tuzhi = tu.getBoolean("tuzhi", false);
        sw_nowifexiazais.setChecked(tuzhi);
    }

    private void initOnclick() {
        iv_shezhi_jiantou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        rl_qingli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String s = cliearUyils.getdqSize(ShezhiActivity.this);
                    cliearUyils.clearAllCache(ShezhiActivity.this);
                    tv_qingli_kb.setText(s);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        rl_gengxin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UpdataLoad updataLoad=new UpdataLoad();
                updataLoad.download(ShezhiActivity.this,"http://www.wandoujia.com/apps/com.dianping.v1/download");
            }
        });

        sw_tuisong.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(b)
                    {
                        JPushInterface.resumePush(ShezhiActivity.this);
                    }
                    else
                    {
                        JPushInterface.stopPush(ShezhiActivity.this);
                    }
                sp.edit().putBoolean("zhi",b).commit();
            }
        });
        sw_nowifexiazais.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {


                    tu.edit().putBoolean("tuzhi",b).commit();

            }
        });
    }

    private void initView() {
        cliearUyils = new CliearUyils();
        iv_shezhi_jiantou = (ImageView) findViewById(R.id.iv_shezhi_jiantou);
        rl_qingli = (RelativeLayout) findViewById(R.id.rl_qingli);
        tv_qingli_kb = (TextView) findViewById(R.id.tv_qingli_kb);

        rl_gengxin = (RelativeLayout) findViewById(R.id.rl_gengxin);
        sw_tuisong = (Switch) findViewById(R.id.sw_tuisong);
        sw_nowifexiazais = (Switch) findViewById(R.id.sw_nowifexiazais);

    }

    @Override
    protected void onResume() {
        super.onResume();
        String s = null;
        try {
            s = cliearUyils.getdqSize(ShezhiActivity.this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        cliearUyils.clearAllCache(ShezhiActivity.this);
        tv_qingli_kb.setText(s);
    }
}
