package com.dangxy.readhub.model.topic;

import com.dangxy.readhub.base.IBasePresenter;
import com.dangxy.readhub.base.IBaseView;
import com.dangxy.readhub.entity.TopicEntity;

/**
 * @author dangxy99
 * @description TeachContract
 */
public interface TopicContract {

    interface ITopicView extends IBaseView {
        void setRefresh(TopicEntity teachEntity);

        void getTopicEntity(TopicEntity teachEntity, boolean isFirst);
    }

    interface ITopicPresenter extends IBasePresenter {
    }
}
