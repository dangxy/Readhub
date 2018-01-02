package com.dangxy.readhub.model;

import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dangxy.readhub.R;
import com.dangxy.readhub.base.BaseActivity;
import com.dangxy.readhub.utils.FileUtils;
import com.dangxy.readhub.utils.MLog;
import com.jakewharton.rxbinding2.view.RxView;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

/**
 * @author dangxy99
 * @description 设置页面
 * @date 2017/12/30
 */
public class SettingActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.rl_clean)
    RelativeLayout rlClean;
    @BindView(R.id.tv_total)
    TextView tvTotal;

    @Override
    protected void initView() {
        tvTotal.setText(getCacheSize());
        RxView.clicks(ivBack).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                finish();
            }
        });
        RxView.clicks(rlClean).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {

                boolean flag = FileUtils.deleteFolderFile(SettingActivity.this.getCacheDir().getAbsolutePath(), true);
                if (flag) {
                    Toast.makeText(mContext, "清理成功", Toast.LENGTH_SHORT).show();
                    tvTotal.setText(getCacheSize());
                }


            }
        });
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_setting;
    }

    public String getCacheSize() {
        try {
            return FileUtils.getFormatSize(FileUtils.getFolderSize(SettingActivity.this.getCacheDir()));
        } catch (Exception e) {
            e.printStackTrace();
            MLog.e("SET_GLIDE", "获取失败");
            return "获取失败";
        }
    }


}
