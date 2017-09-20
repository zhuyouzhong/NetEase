package view;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tablagout_zdy_henghua.R;

import java.util.ArrayList;

import Mypageradapter.MyPagerAdapter;
import bean.News;

/**
 * Created by 祝文 on 2017/8/31.
 */

public class HorizonScollTabhost extends LinearLayout {
    private ArrayList<News> list;
    private ArrayList<Fragment> fragments;
    private Context context;
    private LinearLayout ll;
    private ViewPager vp;
    private ArrayList<TextView> tv_list;
    private HorizontalScrollView horiscrollview;
    private MyPagerAdapter myPagerAdapter;

    public HorizonScollTabhost(Context context) {
        this(context,null);
    }

    public HorizonScollTabhost(Context context, @Nullable AttributeSet attrs) {
       this(context,attrs,0);
    }

    public HorizonScollTabhost(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
        initView();
    }

    private void initView() {
        tv_list = new ArrayList<>();
        View view = LayoutInflater.from(context).inflate(R.layout.horizonscolltabhost, this, true);
        horiscrollview = view.findViewById(R.id.horiscrollview);
        ll = view.findViewById(R.id.ll);
        vp = view.findViewById(R.id.vp);
    }

    public void diaply(ArrayList<News> list,ArrayList<Fragment> fragments)
    {
        this.list=list;
        this.fragments=fragments;

        draw();
    }

    /**
     * 横滑和ViewPage
     */
    private void draw() {
        drawHorizon();
        drawViewPager();
    }

    /**
     * 横滑
     */
    private void drawHorizon() {

        tv_list.clear();
        ll.removeAllViews();
        for (int i = 0; i <list.size() ; i++) {

            String s = list.get(i).getName();
            final TextView tv_name= (TextView) LayoutInflater.from(context).inflate(R.layout.top_item,null);
            tv_name.setText(s);
            final int finalI = i;
            tv_name.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    vp.setCurrentItem(finalI);
                    //文字水平居中
                    moveItemTextView(tv_name);
                }
            });
            ll.addView(tv_name);
            tv_list.add(tv_name);
            tv_list.get(0).setTextColor(Color.RED);
        }
    }

    /**
     * 水平居中
     * @param tv_name
     */
    private void moveItemTextView(TextView tv_name) {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int screenWidth = dm.widthPixels;
        int[] locations = new int[2];
        tv_name.getLocationInWindow(locations);
        int rbWidth = tv_name.getWidth();
        horiscrollview.smoothScrollBy((locations[0] + rbWidth / 2 - screenWidth / 2),
                0);
    }

    /**
     * Viewpager
     */
    private void drawViewPager() {

        if(myPagerAdapter==null)
        {
            myPagerAdapter = new MyPagerAdapter(((FragmentActivity)context).getSupportFragmentManager(),fragments,context);
            vp.setAdapter(myPagerAdapter);
        }
        else
        {
            myPagerAdapter.notifyDataSetChanged();
        }

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                if(tv_list!=null&&tv_list.size()>0)
                {
                    for (int i = 0; i <tv_list.size() ; i++) {
                        if(i==position)
                        {
                            tv_list.get(i).setTextColor(Color.RED);
                        }
                        else
                        {
                            tv_list.get(i).setTextColor(Color.BLACK);
                        }
                    }
                }

                moveItemTextView(tv_list.get(position));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}
