package com.dangxy.readhub.model;

import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.dangxy.readhub.R;
import com.dangxy.readhub.base.BaseActivity;
import com.dangxy.readhub.model.web.X5WebView;
import com.jakewharton.rxbinding2.view.RxView;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

/**
 * @author dangxy99
 * @description 详情页面
 * @date 2017/12/30
 */
public class WebViewDetailActivity extends BaseActivity {


    @BindView(R.id.tv_web_title)
    TextView tvWebTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.web_detail)
    X5WebView webDetail;

    @Override
    protected void initView() {
        String url = getIntent().getStringExtra("url");
        RxView.clicks(ivBack).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                finish();
            }
        });
        webDetail.loadUrl(url);
        webDetail.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                String title = view.getTitle();
                if (!TextUtils.isEmpty(title)) {
                    tvWebTitle.setText(title);
                }
            }
        });
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_web_view_detail;
    }

}
