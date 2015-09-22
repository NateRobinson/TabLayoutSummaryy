package com.gu.tablayoutsummaryy;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.gu.tablayoutsummaryy.fragment.FragmentA;
import com.gu.tablayoutsummaryy.fragment.FragmentB;
import com.gu.tablayoutsummaryy.fragment.FragmentC;
import com.gu.tablayoutsummaryy.fragment.FragmentD;
import com.gu.tablayoutsummaryy.fragment.FragmentE;
import com.gu.tablayoutsummaryy.fragment.FragmentF;
import com.gu.tablayoutsummaryy.view.SyncHorizontalScrollView;

import java.util.ArrayList;
import java.util.List;

/**
 * 参考的网上的写法，以前我有几个项目就用的这种实现方式，实现代码详细，便于我们理解实现细节，并且个性化配置简单方便
 */
public class TabLayoutOne extends AppCompatActivity {

    private RelativeLayout rl_nav;
    private SyncHorizontalScrollView mHsv;
    private RadioGroup rg_nav_content;
    private LinearLayout iv_nav_indicator;
    private ImageView iv_nav_indicator_line;
    private ImageView iv_nav_left;
    private ImageView iv_nav_right;
    private List<String> labList = new ArrayList<>();
    private LayoutInflater mInflater;
    private int indicatorWidth;
    private ViewPager mViewPager;
    private List<Fragment> fragments = new ArrayList<>();
    private int currentPager = 0;
    private TabFragmentPagerAdapter mAdapter;
    private int currentIndicatorLeft = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tablayout_one);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //动态的添加tablayout的标签数据，真实项目中可以从服务器获取这些值
        labList.add("LAB-A");
        labList.add("LAB-B");
        labList.add("LAB-C");
        labList.add("LAB-D");
        labList.add("LAB-E");
        labList.add("LAB-F");
        //动态的添加Fragment到List中
        fragments.add(new FragmentA());
        fragments.add(new FragmentB());
        fragments.add(new FragmentC());
        fragments.add(new FragmentD());
        fragments.add(new FragmentE());
        fragments.add(new FragmentF());
        initViews();
    }

    /**
     * 初始化View
     */
    private void initViews() {
        rl_nav = (RelativeLayout) findViewById(R.id.rl_nav);
        mHsv = (SyncHorizontalScrollView) findViewById(R.id.mHsv);
        rg_nav_content = (RadioGroup) findViewById(R.id.rg_nav_content);
        iv_nav_indicator = (LinearLayout) findViewById(R.id.iv_nav_indicator);
        iv_nav_indicator_line = (ImageView) findViewById(R.id.iv_nav_indicator_line);
        iv_nav_left = (ImageView) findViewById(R.id.iv_nav_left);
        iv_nav_right = (ImageView) findViewById(R.id.iv_nav_right);
        mViewPager = (ViewPager) findViewById(R.id.mViewPager);
        initView();
        setListener();
    }

    /**
     * 初始化控件属性
     */
    private void initView() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        // 每个tab的宽度
        indicatorWidth = (dm.widthPixels - 39) / 4;
        // 滑块容器布局的相关属性设置
        ViewGroup.LayoutParams cursor_Params = iv_nav_indicator.getLayoutParams();
        // 初始化滑块容器布局的宽与每个tab的宽度一致
        cursor_Params.width = indicatorWidth;
        // 初始化滑块容器布局的高度
        cursor_Params.height = (int)
                (2 * getResources().getDisplayMetrics().density + 0.5f);
        iv_nav_indicator.setLayoutParams(cursor_Params);
        // 滑块布局中的滑块相关属性
        ViewGroup.LayoutParams cursor_Params_line = iv_nav_indicator_line.getLayoutParams();
        // 滑块的宽度为滑块容器布局的一半
        cursor_Params_line.width = indicatorWidth / 2;
        cursor_Params_line.height = (int)
                (2 * getResources().getDisplayMetrics().density + 0.5f);
        iv_nav_indicator_line.setLayoutParams(cursor_Params_line);
        //初始化SyncHorizontalScrollView
        mHsv.setSomeParam(rl_nav, iv_nav_left, iv_nav_right, this);
        // 获取布局填充器
        mInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        initNavigationHSV();
        mAdapter = new TabFragmentPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(currentPager);
    }

    /**
     * 设置监听
     */
    private void setListener() {
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                if (rg_nav_content != null && rg_nav_content.getChildCount() > position) {
                    //动态设置position对象的RadioButton为选中的状态
                    (rg_nav_content.getChildAt(position)).performClick();
                    currentPager = position;
                }

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });

        rg_nav_content.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (rg_nav_content.getChildAt(checkedId) != null) {
                    TranslateAnimation animation =
                            new TranslateAnimation(currentIndicatorLeft,
                                    (rg_nav_content.getChildAt(checkedId)).getLeft(), 0f, 0f);
                    animation.setInterpolator(new LinearInterpolator());
                    animation.setDuration(100);
                    animation.setFillAfter(true);
                    for (int i = 0; i < rg_nav_content.getChildCount(); i++) {
                        ((RadioButton) rg_nav_content.getChildAt(i)).setTextColor(Color.parseColor("#666666"));
                    }
                    ((RadioButton) rg_nav_content.getChildAt(checkedId)).setTextColor(Color.parseColor("#e72d85"));
                    // 执行位移动画
                    iv_nav_indicator.startAnimation(animation);
                    mViewPager.setCurrentItem(checkedId); // ViewPager
                    // 跟随一起 切换
                    // 记录当前 下标的距最左侧的 距离
                    currentIndicatorLeft = (rg_nav_content.getChildAt(checkedId)).getLeft();
                    mHsv.smoothScrollTo((checkedId > 1 ? (rg_nav_content.getChildAt(checkedId)).getLeft()
                            : 0) - (rg_nav_content.getChildAt(2)).getLeft(), 0);
                }
            }
        });
    }

    /**
     * 初始化头部tab，此处用RadioButton
     */
    private void initNavigationHSV() {
        rg_nav_content.removeAllViews();
        if (null == labList) {
            return;
        }
        //根据lablist动态生成RadioButton
        for (int i = 0; i < labList.size(); i++) {
            RadioButton rb = (RadioButton) mInflater.inflate(R.layout.nav_radiogroup_item, null);
            rb.setId(i);
            rb.setText(labList.get(i));
            rb.setBackgroundColor(Color.parseColor("#ffffff"));
            rb.setLayoutParams(new ViewGroup.LayoutParams(indicatorWidth, ViewGroup.LayoutParams.MATCH_PARENT));
            rb.setTextSize(18);
            if (i == currentPager) {
                rb.setTextColor(Color.parseColor("#e72d85"));
            } else {
                rb.setTextColor(Color.parseColor("#666666"));
            }
            if (currentPager == i) {
                rb.setChecked(true);
            }
            rg_nav_content.addView(rb);
        }

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

    class TabFragmentPagerAdapter extends FragmentPagerAdapter {

        public TabFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int arg0) {
            return fragments.get(arg0);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

    }
}
