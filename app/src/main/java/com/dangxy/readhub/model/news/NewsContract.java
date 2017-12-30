package com.dangxy.readhub.model.news;

import com.dangxy.readhub.base.IBasePresenter;
import com.dangxy.readhub.base.IBaseView;
import com.dangxy.readhub.entity.NewEntity;

/**
 * @author dangxy99
 * @description TeachContract
 */
public interface NewsContract {

    interface INewsView extends IBaseView {
        void setRefresh(NewEntity newEntity);

        void getNewsEntity(NewEntity newEntity, boolean isFirst);
    }

    interface INewsPresenter extends IBasePresenter {
    }
}
