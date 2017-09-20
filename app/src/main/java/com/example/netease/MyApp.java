package com.example.netease;

import android.app.Application;

import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by 祝文 on 2017/9/17.
 */

public class MyApp extends Application {
    {
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");

    }
    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

        UMShareAPI.get(this);

    }
}
