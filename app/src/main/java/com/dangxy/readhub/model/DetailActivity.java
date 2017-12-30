package com.dangxy.readhub.model;

import android.support.v7.widget.CardView;
import android.widget.ImageView;
import android.widget.TextView;

import com.dangxy.readhub.R;
import com.dangxy.readhub.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author dangxy99
 * @description 详情页面
 * @date 2017/12/30
 */
public class DetailActivity extends BaseActivity {

    @BindView(R.id.tv_detail_title)
    TextView tvDetailTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.summary)
    TextView summaryTitle;
    @BindView(R.id.cardView)
    CardView cardView;
    private String summary;
    private String title;

    @Override
    protected void initView() {
        title = getIntent().getStringExtra("title");
        summary = getIntent().getStringExtra("summary");
        tvDetailTitle.setText(title);
        summaryTitle.setText(summary);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_detail;
    }


    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
