package com.example.netease;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.netease.adapter.MyLixianAdapter;
import com.example.netease.api.ApiUtils;
import com.example.netease.bean.Dao;
import com.example.netease.bean.Lixian;
import com.example.netease.netUtils.NetUtils;
import com.usher.greendao_demo.greendao.gen.DaoDao;
import com.usher.greendao_demo.greendao.gen.DaoMaster;
import com.usher.greendao_demo.greendao.gen.DaoSession;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LixianActivity extends AppCompatActivity {

    private ListView lv;
    private ImageView iv_lixian_fanhui;
    private TextView tv_xiazai;
    private ArrayList<Lixian> list;
    private CheckBox cb;
    private DaoDao daoDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lixian);

        initView();
        initData();
        initOnclick();


    }

    private void initOnclick() {
        iv_lixian_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tv_xiazai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                            NetUtils.viefil(LixianActivity.this, new NetUtils.NetWork() {
                                @Override
                                public void youNetwork() {
                                    for (final Lixian lixian : list) {
                                        if(lixian.zhuangtai)
                                        {
                                        loader(lixian.type);
                                        }
                                    }
                                    Toast.makeText(LixianActivity.this,"下载到数据库", Toast.LENGTH_SHORT).show();
                                    finish();
                                }

                                @Override
                                public void noNetwork() {
                                    Toast.makeText(LixianActivity.this, "网络有误", Toast.LENGTH_SHORT).show();
                                }
                            });




                   // System.out.println(new StringBuffer().append(lixian.zhuangtai+"\n"));


            }
        });
    }


    private void loader(final String type) {


        new Thread(new Runnable() {
            @Override
            public void run() {
                initOkHttp(type);
            }
        }).start();

    }

    private void initOkHttp(String type) {
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder()
                .url(ApiUtils.url+"&type="+type)
                .build();
        try {
            Response response=client.newCall(request).execute();
            if(response.isSuccessful())
            {
                String string = response.body().string();
                // System.out.println(response.body().string());
                daoDao.insert(new Dao(string,type));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void initData() {
        list = new ArrayList<>();
        list.add(new Lixian("社会","shehui",true));
        list.add(new Lixian("国际","guoji",true));
        list.add(new Lixian("国内","guonei",true));
        list.add(new Lixian("娱乐","yule",true));
        list.add(new Lixian("体育","tiyu",true));
        list.add(new Lixian("科技","keji",true));
        list.add(new Lixian("头条","toutiao",true));
        list.add(new Lixian("军事","junshi",true));
        list.add(new Lixian("财经","caijing",true));
        list.add(new Lixian("时尚","shishang",true));

        //初始化数据库相关的
        DaoMaster.DevOpenHelper helper=new DaoMaster.DevOpenHelper(LixianActivity.this,"shuju-db",null);
        SQLiteDatabase sd = helper.getWritableDatabase();
        DaoMaster master=new DaoMaster(sd);
        DaoSession session=master.newSession();
        daoDao = session.getDaoDao();

        MyLixianAdapter ma=new MyLixianAdapter(list,LixianActivity.this);
        lv.setAdapter(ma);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Lixian lixian = list.get(i);

                cb=view.findViewById(R.id.cb);
                if(cb.isChecked())
                {
                    cb.setChecked(false);
                    lixian.zhuangtai=false;
                }
                else
                {
                    cb.setChecked(true);
                    lixian.zhuangtai=true;
                }
                list.set(i,lixian);
            }
        });
    }

    private void initView() {
        lv = (ListView) findViewById(R.id.lv);
        iv_lixian_fanhui = (ImageView) findViewById(R.id.iv_lixian_fanhui);
        tv_xiazai = (TextView) findViewById(R.id.tv_xiazai);

    }
}
