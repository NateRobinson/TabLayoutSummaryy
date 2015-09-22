package com.gu.tablayoutsummaryy;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.gu.tablayoutsummaryy.fragment.FragmentA;
import com.gu.tablayoutsummaryy.fragment.FragmentB;
import com.gu.tablayoutsummaryy.fragment.FragmentC;
import com.gu.tablayoutsummaryy.fragment.FragmentD;

import java.util.ArrayList;
import java.util.List;

/**
 * android.support.design.widget.TabLayout 是谷歌自己退出的tablayout实现方法，更加美观易用，并且向下兼容
 * 是开发中最优的选择
 */
public class TabLayoutThree extends AppCompatActivity {

    private SimpleFragmentPagerAdapter pagerAdapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private List<Fragment> fragments = new ArrayList<>();
    private List<String> labList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tablayout_three);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initViews();
        initDatas();
    }

    private void initViews() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
    }

    private void initDatas() {
        labList.add("LAB-A");
        labList.add("LAB-B");
        labList.add("LAB-C");
        labList.add("LAB-D");
        fragments.add(new FragmentA());
        fragments.add(new FragmentB());
        fragments.add(new FragmentC());
        fragments.add(new FragmentD());
        pagerAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        //MODE_FIXED模式会使及格tab均分屏幕的宽度
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
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

    class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

        public SimpleFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return labList.get(position);
        }

    }

}
