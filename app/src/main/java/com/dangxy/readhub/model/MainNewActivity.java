package com.dangxy.readhub.model;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.widget.Toast;

import com.dangxy.readhub.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author dangxy99
 * @description 描述
 * @date 2018/1/5
 */
public class MainNewActivity extends AppCompatActivity {


    @BindView(R.id.tl_read_hub_list)
    TabLayout tlReadHubList;
    @BindView(R.id.vp_read_hub_list)
    ViewPager vpReadHubList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_new);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        ReadhubAdapter readhubAdapter = new ReadhubAdapter(getSupportFragmentManager());
        vpReadHubList.setOffscreenPageLimit(3);
        vpReadHubList.setAdapter(readhubAdapter);
        tlReadHubList.setupWithViewPager(vpReadHubList);
        tlReadHubList.setTabMode(TabLayout.MODE_FIXED);
        tlReadHubList.setTabsFromPagerAdapter(readhubAdapter);
    }
    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(this, "再点击一次，退出Readhub", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;

        }
        return super.onKeyDown(keyCode, event);
    }
}
