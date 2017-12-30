package com.dangxy.readhub.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.reactivex.Flowable;

/**
 * @author dangxueyi
 * @description
 * @date 2017/12/30
 */

@Dao
public interface WaitDao {
    @Query("SELECT DISTINCT  * FROM Wait order by id DESC ")
    Flowable <List<Wait>> findAll();

    @Insert
      void addWait(Wait wait);

    @Delete
    void deleteWait(Wait wait);

    @Update
    void updateWait(Wait wait);

    @Query("select * FROM Wait WHERE wid = :id")
     Flowable<Wait> getWaitById(String id);
}
