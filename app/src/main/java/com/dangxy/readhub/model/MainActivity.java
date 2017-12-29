package com.dangxy.readhub.model;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.dangxy.readhub.R;
import com.dangxy.readhub.base.BaseActivity;

import butterknife.BindView;

/**
 * @author dangxy99
 * @description 描述
 * @date 2017/12/30
 */
public class MainActivity extends BaseActivity {


    @BindView(R.id.tl_read_hub_list)
    TabLayout tlReadHubList;
    @BindView(R.id.vp_read_hub_list)
    ViewPager vpReadHubList;

    @Override
    protected void initView() {

        ReadhubAdapter readhubAdapter = new ReadhubAdapter(getSupportFragmentManager());
        vpReadHubList.setOffscreenPageLimit(3);
        vpReadHubList.setAdapter(readhubAdapter);
        tlReadHubList.setupWithViewPager(vpReadHubList);
        tlReadHubList.setTabMode(TabLayout.MODE_FIXED);
        tlReadHubList.setTabsFromPagerAdapter(readhubAdapter);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_main;
    }

}
