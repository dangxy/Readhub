package com.dangxy.readhub.base;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dangxy.readhub.R;
import com.dangxy.readhub.utils.MLog;
import com.dangxy.readhub.utils.NetUtil;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.support.RxFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author dangxy99
 * @description Fragment基类
 * @date 2017/12/23
 */
public abstract class BaseLazyFragment extends RxFragment implements IBaseView, EmptyLayout.OnRetryListener {
    @Nullable
    @BindView(R.id.empty_layout)
    EmptyLayout mEmptyLayout;
    private boolean mIsInited;
    private boolean mIsPrepared;
    private View mRootView;
    protected Context mContext;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(attachLayoutRes(), container, false);
        mIsPrepared = true;
        ButterKnife.bind(this, mRootView);
        mContext = getActivity();
        initViews();
        lazyLoad();
        return mRootView;
    }

    public void lazyLoad() {
        if (getUserVisibleHint() && mIsPrepared && !mIsInited) {
            if (checkNetwork() == -1) {
                if (mEmptyLayout != null) {
                    mEmptyLayout.setEmptyStatus(EmptyLayout.STATUS_NO_NET);
                    mEmptyLayout.setRetryListener(this);
                }

            } else {
                loadData();
                mIsInited = true;
                MLog.d("DANG", "第一次加载");
            }

        } else {
            MLog.d("DANG", "不在加载");
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            lazyLoad();
        }
    }

    /**
     * 加载数据
     */
    protected abstract void loadData();


    /**
     * 绑定布局文件
     *
     * @return 布局文件ID
     */
    protected abstract int attachLayoutRes();


    /**
     * 初始化视图控件
     */
    protected abstract void initViews();

    @Override
    public void showLoading() {
        if (mEmptyLayout != null) {
            mEmptyLayout.setEmptyStatus(EmptyLayout.STATUS_LOADING);
        }
    }

    @Override
    public void hideLoading() {
        if (mEmptyLayout != null) {
            mEmptyLayout.hide();
        }
    }

    @Override
    public void showNetError(EmptyLayout.OnRetryListener onRetryListener) {
        if (mEmptyLayout != null) {
            mEmptyLayout.setEmptyStatus(EmptyLayout.STATUS_NO_NET);
            mEmptyLayout.setRetryListener(this);
        }
    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return this.<T>bindToLifecycle();
    }

    private int checkNetwork() {
        return NetUtil.getNetWorkState(mContext);

    }

    @Override
    public void onRetry() {
        if (checkNetwork() == -1) {
            Toast.makeText(mContext, "请检查网络", Toast.LENGTH_SHORT).show();
        } else {
            loadData();

        }
    }
}
