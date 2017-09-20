package com.example.netease.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.netease.R;

/**
 * Created by 祝文 on 2017/9/14.
 */

public class Fragment_left extends Fragment {

    private View view;
    private RelativeLayout ll_news;
    private RelativeLayout ll_dingyue;
    private RelativeLayout rl_news_bj;
    private RelativeLayout rl_newsdy_bj;
    private RelativeLayout ll_tupian;
    private RelativeLayout ll_shipin;
    private RelativeLayout ll_gentie;
    private RelativeLayout rl_tupian_bj;
    private RelativeLayout rl_shipin_bj;
    private RelativeLayout rl_gentie_bj;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_left_item, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
        initOnClick();
    }

    private void initOnClick() {
        ll_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rl_news_bj.setVisibility(View.VISIBLE);
                rl_newsdy_bj.setVisibility(View.GONE);
                rl_tupian_bj.setVisibility(View.GONE);
                rl_shipin_bj.setVisibility(View.GONE);
                rl_gentie_bj.setVisibility(View.GONE);
            }
        });
        ll_dingyue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rl_news_bj.setVisibility(View.GONE);
                rl_newsdy_bj.setVisibility(View.VISIBLE);
                rl_tupian_bj.setVisibility(View.GONE);
                rl_shipin_bj.setVisibility(View.GONE);
                rl_gentie_bj.setVisibility(View.GONE);
        }
        });
        ll_tupian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rl_news_bj.setVisibility(View.GONE);
                rl_newsdy_bj.setVisibility(View.GONE);
                rl_tupian_bj.setVisibility(View.VISIBLE);
                rl_shipin_bj.setVisibility(View.GONE);
                rl_gentie_bj.setVisibility(View.GONE);
            }
        });
        ll_shipin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rl_news_bj.setVisibility(View.GONE);
                rl_newsdy_bj.setVisibility(View.GONE);
                rl_tupian_bj.setVisibility(View.GONE);
                rl_shipin_bj.setVisibility(View.VISIBLE);
                rl_gentie_bj.setVisibility(View.GONE);
            }
        });
        ll_gentie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rl_news_bj.setVisibility(View.GONE);
                rl_newsdy_bj.setVisibility(View.GONE);
                rl_tupian_bj.setVisibility(View.GONE);
                rl_shipin_bj.setVisibility(View.GONE);
                rl_gentie_bj.setVisibility(View.VISIBLE);
            }
        });
    }

    private void initView() {
        ll_news = view.findViewById(R.id.ll_news);
        ll_dingyue = view.findViewById(R.id.ll_dingyue);
        ll_tupian = view.findViewById(R.id.ll_tupian);
        ll_shipin = view.findViewById(R.id.ll_shipin);
        ll_gentie = view.findViewById(R.id.ll_gentie);

        rl_news_bj = view.findViewById(R.id.rl_news_bj);
        rl_newsdy_bj = view.findViewById(R.id.rl_newsdy_bj);
        rl_tupian_bj = view.findViewById(R.id.rl_tupian_bj);
        rl_shipin_bj = view.findViewById(R.id.rl_shipin_bj);
        rl_gentie_bj = view.findViewById(R.id.rl_gentie_bj);
    }
}
