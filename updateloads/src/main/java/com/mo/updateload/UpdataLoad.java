package com.mo.updateload;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AlertDialog;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;


import java.io.File;

/**
 * Created by Mo on 2017/9/16.
 */

public class UpdataLoad {

    private ProgressDialog dialog;
    public Context context;
    public String url;


    public  void download(Context context, final String url) {
        this.context=context;
        this.url=url;

        AlertDialog.Builder dialogTips= new AlertDialog.Builder(context);
        dialogTips.setTitle("提示");
        dialogTips.setMessage("检测到有新的版本，是否更新？");
        dialogTips.setNegativeButton("取消", null);
        dialogTips.setPositiveButton("更新", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                startDownload(url);
            }
        });
        dialogTips.show();

    }
    private void startDownload(String url){
        //定义保存的文件地址为根目录
        File path = new File(Environment.getExternalStorageDirectory(),
                "版本更新"+ ".apk");
        httpDownLoad(path.getPath(),url);

    }

    private void httpDownLoad(String path, String url) {
        HttpUtils http = new HttpUtils();
        http.download(url, path, true, true, new RequestCallBack<File>() {
            @Override
            public void onStart() {
                super.onStart();
                dialog=new ProgressDialog(context);
                dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                dialog.setMessage("更新应用");
                dialog.setMax(100);
                dialog.show();
            }

            @Override
            public void onLoading(long total, long current, boolean isUploading) {
                int index = (int) (current * 100 / total);
                System.out.println(index+"    ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                dialog.setProgress(index);
            }
            @Override
            public void onSuccess(ResponseInfo<File> responseInfo) {
                //获取到安装包后，调用系统的android安装apk界面进行安装 这是固定格式
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(
                        Uri.fromFile(new File(responseInfo.result.getPath())),
                        "application/vnd.android.package-archive");
                context.startActivity(intent);
                dialog.dismiss();

            }

            @Override
            public void onFailure(HttpException error, String msg) {

            }
        });
    }
}
