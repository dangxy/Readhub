package com.dangxy.readhub.model.wait;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.dangxy.readhub.Api.RetrofitReadhub;
import com.dangxy.readhub.Api.RxReadhubService;
import com.dangxy.readhub.ReadhubApplication;
import com.dangxy.readhub.room.Wait;
import com.dangxy.readhub.room.WaitDataBase;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author dangxueyi
 * @description
 * @date 2017/12/30
 */

public class WaitPresenter implements WaitContract.IWaitPresenter, SwipeRefreshLayout.OnRefreshListener {
    private WaitContract.IWaitView iTopicView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private boolean isRefresh;
    private RecyclerView mRecyclerView;

    public WaitPresenter(WaitContract.IWaitView iTopicView) {
        this.iTopicView = iTopicView;
    }

    public void setRefresh(SwipeRefreshLayout swipeRefreshLayout, RecyclerView recyclerView) {
        this.mSwipeRefreshLayout = swipeRefreshLayout;
        this.mRecyclerView = recyclerView;
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void getData() {
        WaitDataBase.getDatabase().waitDao().findAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Wait>>() {
                    @Override
                    public void accept(List<Wait> waitList) throws Exception {
                        if (isRefresh) {
                            mSwipeRefreshLayout.setRefreshing(false);
                            isRefresh = false;
                            iTopicView.setRefresh(waitList);
                        } else {
                            iTopicView.hideLoading();
                            iTopicView.getTopicEntity(waitList);

                        }

                    }
                });
    }

    @Override
    public void onRefresh() {
        isRefresh = true;
        getData();
    }
}
