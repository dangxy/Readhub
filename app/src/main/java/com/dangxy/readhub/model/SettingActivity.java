package com.dangxy.readhub.model;

import android.widget.ImageView;

import com.dangxy.readhub.R;
import com.dangxy.readhub.base.BaseActivity;
import com.jakewharton.rxbinding2.view.RxView;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

/**
 * @description  设置页面
 * @author  dangxy99
 * @date   2017/12/30
 */
public class SettingActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @Override
    protected void initView() {
        RxView.clicks(ivBack).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                finish();
            }
        });
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_setting;
    }
}
