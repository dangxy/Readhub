package com.dangxy.readhub.model.news;

/**
 * @author dangxueyi
 * @description
 * @date 2017/12/30
 */

public class WaitEntity {
    private String id;
    private String title;
    private String summary;
    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
