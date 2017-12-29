package com.dangxy.readhub.Api;

import com.dangxy.readhub.entity.NewEntity;
import com.dangxy.readhub.entity.TeachEntity;
import com.dangxy.readhub.entity.TopicEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author dangxueyi
 * @description
 * @date 2017/12/13
 */

public interface RxReadhubService {
    /**
     * 获取新闻信息
     * @param lastCursor
     * @param pageSize
     * @return
     */
    @GET("news")
    Observable<NewEntity> listNews(@Query("lastCursor") String lastCursor, @Query("pageSize") int pageSize);
    /**
     * 获取热门话题
     * @param lastCursor
     * @param pageSize
     * @return
     */
    @GET("topic")
    Observable<TopicEntity> listTopicNews(@Query("lastCursor") String lastCursor, @Query("pageSize") int pageSize);
    /**
     * 获取开发者头条
     * @param lastCursor
     * @param pageSize
     * @return
     */
    @GET("technews")
    Observable<TeachEntity> listTechNews(@Query("lastCursor") String lastCursor, @Query("pageSize") int pageSize);
}
