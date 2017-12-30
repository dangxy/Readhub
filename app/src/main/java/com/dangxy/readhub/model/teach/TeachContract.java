package com.dangxy.readhub.model.teach;

import com.dangxy.readhub.base.IBasePresenter;
import com.dangxy.readhub.base.IBaseView;
import com.dangxy.readhub.entity.TeachEntity;

/**
 * @author dangxy99
 * @description TeachContract
 */
public interface TeachContract {

    interface ITeachView extends IBaseView {
        void setRefresh(TeachEntity teachEntity);

        void getTeachEntity(TeachEntity teachEntity, boolean isFirst);
    }

    interface ITeachPresenter extends IBasePresenter {
    }
}
