package com.dangxy.readhub.model.wait;


import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dangxy.readhub.R;
import com.dangxy.readhub.ReadhubApplication;
import com.dangxy.readhub.base.BaseLazyFragment;
import com.dangxy.readhub.model.DetailActivity;
import com.dangxy.readhub.room.Wait;

import java.util.List;

import butterknife.BindView;

/**
 * @author dangxy99
 * @description 描述
 * @date 2017/12/30
 */
public class WaitFragment extends BaseLazyFragment implements WaitContract.IWaitView, WaitListAdapter.DetailClickListener {


    @BindView(R.id.rv_wait)
    RecyclerView rvWait;
    @BindView(R.id.srl_wait)
    SwipeRefreshLayout srlWait;
    private WaitPresenter waitPresenter;
    private WaitListAdapter waitListAdapter;

    public WaitFragment() {
    }


    @Override
    protected void loadData() {
         waitPresenter = new WaitPresenter(this);
         waitPresenter.getData();
         waitPresenter.setRefresh(srlWait,rvWait);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_wait;
    }

    @Override
    protected void initViews() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ReadhubApplication.getInstance());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvWait.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void setRefresh(List<Wait> wait) {
        waitListAdapter.refresh(wait);
    }

    @Override
    public void getTopicEntity(List<Wait> wait) {
       waitListAdapter = new WaitListAdapter(wait);
       rvWait.setAdapter(waitListAdapter);
       waitListAdapter.setOnDetailClickListener(this);
    }

    @Override
    public void onDetailClickListener(String title, String summary, String url) {
        Intent intent = new Intent(mContext, DetailActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("title", title);
        intent.putExtra("summary", summary);
        startActivity(intent);
    }

}
