package com.dangxy.readhub.model.wait;

import com.dangxy.readhub.base.IBasePresenter;
import com.dangxy.readhub.base.IBaseView;
import com.dangxy.readhub.room.Wait;

import java.util.List;

/**
 * @author dangxy99
 * @description WaitContract
 */
public interface WaitContract {

    interface IWaitView extends IBaseView {


        void setRefresh(List<Wait> wait);

        void getTopicEntity(List<Wait> wait);
    }

    interface IWaitPresenter extends IBasePresenter {
    }
}
