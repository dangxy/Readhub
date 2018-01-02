package com.dangxy.readhub.model.news;


import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.dangxy.readhub.R;
import com.dangxy.readhub.ReadhubApplication;
import com.dangxy.readhub.base.BaseLazyFragment;
import com.dangxy.readhub.entity.NewEntity;
import com.dangxy.readhub.model.DetailActivity;
import com.dangxy.readhub.room.Wait;
import com.dangxy.readhub.room.WaitDataBase;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * @description
 * @author  dangxy99
 * @date   2017/12/30
 */
public class NewFragment extends BaseLazyFragment implements NewsContract.INewsView, NewshListAdapter.DetailClickListener {


    @BindView(R.id.rv_news)
    RecyclerView rvNews;
    @BindView(R.id.srl_news)
    SwipeRefreshLayout srlNews;
    Unbinder unbinder;
    private NewsPresenter newsPresenter;
    private NewshListAdapter newsListAdapter;
    private Wait wait;

    public NewFragment() {
    }




    @Override
    protected void loadData() {
        newsPresenter = new NewsPresenter(this);
        newsPresenter.getData();
        newsPresenter.setRefresh(srlNews, rvNews);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_new;
    }
    @Override
    protected void initViews() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ReadhubApplication.getInstance());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvNews.setLayoutManager(linearLayoutManager);
    }


    @Override
    public void setRefresh(NewEntity newEntity) {
        newsListAdapter.refresh(newEntity.getData());
    }

    @Override
    public void getNewsEntity(NewEntity newEntity, boolean isFirst) {
        if(isFirst){
            newsListAdapter = new NewshListAdapter(newEntity.getData());
            rvNews.setAdapter(newsListAdapter);
        }else {
            newsListAdapter.addAll(newEntity.getData());
        }
        newsListAdapter.setOnDetailClickListener(this);

    }


    @Override
    public void onDetailClickListener(String title, String summary, String url) {
        Intent intent = new Intent(mContext, DetailActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("title", title);
        intent.putExtra("summary", summary);
        startActivity(intent);
    }
    @Override
    public void onWaitClickListener(final ImageView imageView, String id, String title, String summary, String url) {
        wait = new Wait(id, title, summary, url);
        new Thread(new Runnable() {
            @Override
            public void run() {
                WaitDataBase.getDatabase().waitDao().addWait(wait);
            }
        }).start();
        Snackbar.make(imageView, "已添加到稍后再看", Snackbar.LENGTH_SHORT).setAction("知道了", new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        }).show();

    }
}
