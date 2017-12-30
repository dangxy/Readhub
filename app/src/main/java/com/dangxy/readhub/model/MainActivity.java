package com.dangxy.readhub.model;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.dangxy.readhub.R;
import com.dangxy.readhub.base.BaseActivity;
import com.jakewharton.rxbinding2.view.RxView;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

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
    @BindView(R.id.iv_setting)
    ImageView ivSetting;

    @Override
    protected void initView() {

        ReadhubAdapter readhubAdapter = new ReadhubAdapter(getSupportFragmentManager());
        vpReadHubList.setOffscreenPageLimit(3);
        vpReadHubList.setAdapter(readhubAdapter);
        tlReadHubList.setupWithViewPager(vpReadHubList);
        tlReadHubList.setTabMode(TabLayout.MODE_FIXED);
        tlReadHubList.setTabsFromPagerAdapter(readhubAdapter);
        RxView.clicks(ivSetting).subscribe(new Consumer<Object>() {

            @Override
            public void accept(Object o) throws Exception {
                Intent intent = new Intent(mContext, SettingActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_main;
    }


}
