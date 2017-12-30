package com.dangxy.readhub.model.news;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.dangxy.readhub.Api.RetrofitReadhub;
import com.dangxy.readhub.Api.RxReadhubService;
import com.dangxy.readhub.ReadhubApplication;
import com.dangxy.readhub.entity.NewEntity;
import com.dangxy.readhub.model.teach.TeachContract;
import com.dangxy.readhub.utils.Constant;
import com.dangxy.readhub.utils.LoadMoreDelegate;
import com.dangxy.readhub.utils.TimeUtils;

import java.util.concurrent.atomic.AtomicInteger;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author dangxueyi
 * @description
 * @date 2017/12/30
 */

public class NewsPresenter implements TeachContract.ITeachPresenter, SwipeRefreshLayout.OnRefreshListener, LoadMoreDelegate.LoadMoreSubject {
    private final RxReadhubService readhubService;
    private NewsContract.INewsView iNewsView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private boolean isRefresh;
    private RecyclerView mRecyclerView;
    private LoadMoreDelegate loadMoreDelegate;
    private AtomicInteger loadingCount;
    private String lastCursor = "";
    private boolean isFirst;

    public NewsPresenter(NewsContract.INewsView iNewsView) {
        this.iNewsView = iNewsView;
        readhubService = new RetrofitReadhub().newInstance(ReadhubApplication.getInstance()).create(RxReadhubService.class);
    }

    public void setRefresh(SwipeRefreshLayout swipeRefreshLayout, RecyclerView recyclerView) {
        this.mSwipeRefreshLayout = swipeRefreshLayout;
        this.mRecyclerView = recyclerView;
        mSwipeRefreshLayout.setOnRefreshListener(this);
        loadMoreDelegate = new LoadMoreDelegate(this);
        loadMoreDelegate.attach(mRecyclerView);
        loadingCount = new AtomicInteger(0);
    }

    @Override
    public void getData() {
        if(TextUtils.isEmpty(lastCursor)){
            isFirst=true;
        }else{
            isFirst=false;
        }
        readhubService.listNews(lastCursor, Constant.pageSize)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        notifyLoadingStarted();
                        if (!isRefresh && !isLoading()) {
                            iNewsView.showLoading();
                        }
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<NewEntity>() {
                    @Override
                    public void accept(NewEntity newEntity) throws Exception {
                        notifyLoadingFinished();
                        lastCursor = "" + TimeUtils.getTimeStampByReahubDateString(newEntity.getData().get(newEntity.getData().size() - 1).getPublishDate());
                        if (isRefresh) {
                            mSwipeRefreshLayout.setRefreshing(false);
                            isRefresh = false;
                            iNewsView.setRefresh(newEntity);
                        } else {
                            iNewsView.hideLoading();
                            iNewsView.getNewsEntity(newEntity, isFirst);

                        }

                    }
                });


    }

    @Override
    public boolean isLoading() {
        return loadingCount.get() > 0;
    }

    @Override
    public void onLoadMore() {
        getData();
    }

    public void notifyLoadingStarted() {

        loadingCount.getAndIncrement();
    }


    public void notifyLoadingFinished() {

        loadingCount.decrementAndGet();
    }

    @Override
    public void onRefresh() {
        lastCursor = "";
        isRefresh = true;
        getData();
    }
}
