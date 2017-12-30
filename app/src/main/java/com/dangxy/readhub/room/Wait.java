package com.dangxy.readhub.room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * @author dangxueyi
 * @description
 * @date 2017/12/30
 */

@Entity(tableName = "Wait")
public class Wait {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "wid")
    private String wid;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "summary")
    private String summary;
    @ColumnInfo(name = "url")
    private String url;

    public String getWid() {
        return wid;
    }

    public void setWid(String wid) {
        this.wid = wid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Wait(String wid, String title, String summary, String url) {
        this.wid = wid;
        this.title = title;
        this.summary = summary;
        this.url = url;
    }
}
