package com.example.netease;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMWeb;

public class XiangqingActivity extends BaseActivity {

    private WebView wv;
    private ImageView iv_xq_zuo;
    private ImageView iv_xq_fenxiang;
    private ImageView iv_xq_fenxiang1;
    private LinearLayout ll_fenxiang;
    private String url;
    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiangqing);

        initView();
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        wv.getSettings().setJavaScriptEnabled(true);
        wv.loadUrl(url);
    }

    private void initView() {
        wv = (WebView) findViewById(R.id.wv);
        iv_xq_zuo = (ImageView) findViewById(R.id.iv_xq_zuo);
        iv_xq_fenxiang1 = (ImageView) findViewById(R.id.iv_xq_fenxiang);
        iv_xq_zuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        iv_xq_fenxiang1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View view1 = View.inflate(XiangqingActivity.this, R.layout.xq_pop, null);
                ll_fenxiang = view1.findViewById(R.id.ll_fenxiang);

                popupWindow = new PopupWindow(view1, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,true);
                popupWindow.setFocusable(true);
                popupWindow.setOutsideTouchable(true);
                popupWindow.setBackgroundDrawable(new BitmapDrawable());
                popupWindow.showAsDropDown(view);
                ll_fenxiang.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Toast.makeText(XiangqingActivity.this, "雕文", Toast.LENGTH_SHORT).show();
                        UMWeb ub=new UMWeb(url);
                        ub.setTitle("This is news title");
                        new ShareAction(XiangqingActivity.this)
                                .setPlatform(SHARE_MEDIA.QQ)
                                .withMedia(ub)
                                .setCallback(umShareListener)
                                .share();
                        popupWindow.dismiss();
                    }
                });
            }
        });

    }
     private  UMShareListener umShareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(XiangqingActivity.this,"成功了",Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(XiangqingActivity.this,"失败"+t.getMessage(),Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(XiangqingActivity.this,"取消了",Toast.LENGTH_LONG).show();

        }
    };


}
