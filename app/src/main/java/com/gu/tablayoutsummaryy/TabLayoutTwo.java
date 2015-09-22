package com.gu.tablayoutsummaryy;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.MenuItem;

import com.astuetz.PagerSlidingTabStrip;
import com.gu.tablayoutsummaryy.fragment.FragmentA;
import com.gu.tablayoutsummaryy.fragment.FragmentB;
import com.gu.tablayoutsummaryy.fragment.FragmentC;
import com.gu.tablayoutsummaryy.fragment.FragmentD;

import java.util.ArrayList;
import java.util.List;

/**
 * github大神的开源库，material风格设计，配置简单方便，布局清晰，易于维护
 */
public class TabLayoutTwo extends AppCompatActivity {
    private List<String> labList = new ArrayList<>();
    private List<Fragment> fragments = new ArrayList<>();
    private PagerSlidingTabStrip tabs;
    private ViewPager pager;
    private MyPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tablayout_two);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initViews();
        initDatas();
    }

    private void initViews() {
        tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        pager = (ViewPager) findViewById(R.id.pager);
    }

    private void initDatas() {
        //可以尝试将下面的注释代码取消注释，运行看一下当有很多个tab时候，会是一个怎样的效果
        labList.add("LAB-A");
        labList.add("LAB-B");
        labList.add("LAB-C");
        labList.add("LAB-D");
//        labList.add("LAB-A");
//        labList.add("LAB-B");
//        labList.add("LAB-C");
//        labList.add("LAB-D");
        fragments.add(new FragmentA());
        fragments.add(new FragmentB());
        fragments.add(new FragmentC());
        fragments.add(new FragmentD());
//        fragments.add(new FragmentA());
//        fragments.add(new FragmentB());
//        fragments.add(new FragmentC());
//        fragments.add(new FragmentD());

        //如果setShouldExpand设置为true，那么每一个tab会一样的宽，以均分屏幕宽度
        //这个在这边设置没有效果，貌似必须在xml文件中进行配置：app:pstsShouldExpand="true"
        //tabs.setShouldExpand(true);
        adapter = new MyPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        tabs.setViewPager(pager);
        // 设置每个page之间的间距
        int pageMargin =
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics());
        pager.setPageMargin(pageMargin);
        // 设置一开始初始化时，展现哪个页面
        pager.setCurrentItem(0);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * FragmentPagerAdapter 用于配置ViewPager展示Fragment
     */
    public class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return labList.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }
    }
}
