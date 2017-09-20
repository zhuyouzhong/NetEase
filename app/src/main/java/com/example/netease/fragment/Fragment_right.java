package com.example.netease.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.netease.R;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

/**
 * Created by 祝文 on 2017/9/15.
 */

public class Fragment_right extends Fragment {

    private View view;
    private ImageView iv_right_touxiang;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_right_item, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
        initOnClick();
    }

    private void initOnClick() {
        iv_right_touxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UMShareAPI share=UMShareAPI.get(getContext());
                share.getPlatformInfo(getActivity(), SHARE_MEDIA.QQ,umAuthListener);
            }
        });
    }

    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //授权开始的回调
        }
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            Toast.makeText(getContext(), "Authorize succeed", Toast.LENGTH_SHORT).show();

            String s=data.get("iconurl");
            Glide.with(getContext()).load(s).into(iv_right_touxiang);
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText( getContext(), "Authorize fail", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(getContext(), "Authorize cancel", Toast.LENGTH_SHORT).show();
        }
    };

    private void initView() {
        iv_right_touxiang = view.findViewById(R.id.iv_right_touxiang);

    }
}
