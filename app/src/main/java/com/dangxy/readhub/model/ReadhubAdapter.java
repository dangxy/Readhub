package com.dangxy.readhub.model;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.dangxy.readhub.model.news.NewFragment;
import com.dangxy.readhub.model.teach.TeachFragment;
import com.dangxy.readhub.model.topic.TopicFragment;
import com.dangxy.readhub.model.wait.WaitFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dangxueyi
 * @description
 * @date 2017/12/30
 */

public class ReadhubAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private List<String> fragmentTitles;
    private List<String> titleList;

    public ReadhubAdapter(FragmentManager fm) {
        super(fm);
        fragments = new ArrayList<>();
        fragmentTitles = new ArrayList<>();
        titleList = new ArrayList<>();
        titleList.add("热门话题");
        titleList.add("科技动态");
        titleList.add("开发者咨询");
        titleList.add("稍等在看");
        initData();
    }

    private void initData() {
        for (String s : titleList) {
            fragmentTitles.add(s);
        }
        TopicFragment topicFragment = new TopicFragment();
        NewFragment newFragment = new NewFragment();
        TeachFragment teachFragment = new TeachFragment();
        WaitFragment waitFragment = new WaitFragment();
        fragments.add(topicFragment);
        fragments.add(newFragment);
        fragments.add(teachFragment);
        fragments.add(waitFragment);

    }

    @Override
    public Fragment getItem(int position) {
        if (fragments.size() > 0) {
            return fragments.get(position);
        } else {
            return null;
        }
    }

    @Override
    public int getCount() {
        return fragmentTitles.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (fragmentTitles.size() > 0) {
            return fragmentTitles.get(position);
        } else {
            return null;
        }
    }
}
