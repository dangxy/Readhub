package com.dangxy.readhub.entity;


import com.dangxy.readhub.utils.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dangxueyi
 * @description
 * @date 2017/12/29
 */

public class TopicEntity {

    private List<Topic> data;
    private int pageSize;
    private int totalItems;
    private int totalPages;

    public List<Topic> getData() {
        return data;
    }

    public void setData(List<Topic> data) {
        this.data = data;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
    public class Topic {
        private String id;
        private String title;
        private String summary;
        private ArrayList<News> newsArray;
        private ArrayList<News> weiboArray;
        private ArrayList<News> wechatArray;
        private ArrayList<News> relatedTopicArray;
        private String publishUserId;
        private String order;
        private String publishDate;
        private String createdAt;
        private String updatedAt;

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public void setNewsArray(ArrayList<News> newsArray) {
            this.newsArray = newsArray;
        }

        public ArrayList<News> getWeiboArray() {
            return weiboArray;
        }

        public void setWeiboArray(ArrayList<News> weiboArray) {
            this.weiboArray = weiboArray;
        }

        public ArrayList<News> getWechatArray() {
            return wechatArray;
        }

        public void setWechatArray(ArrayList<News> wechatArray) {
            this.wechatArray = wechatArray;
        }

        public ArrayList<News> getRelatedTopicArray() {
            return relatedTopicArray;
        }

        public void setRelatedTopicArray(ArrayList<News> relatedTopicArray) {
            this.relatedTopicArray = relatedTopicArray;
        }

        public String getPublishUserId() {
            return publishUserId;
        }

        public void setPublishUserId(String publishUserId) {
            this.publishUserId = publishUserId;
        }

        public void setOrder(String order) {
            this.order = order;
        }

        public String getPublishDate() {
            return publishDate;
        }

        public void setPublishDate(String publishDate) {
            this.publishDate = publishDate;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getId() {
            return TextUtils.getSafeString(id);
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return TextUtils.getSafeString(title);
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSummary() {
            return TextUtils.getSafeString(summary);
        }

        public ArrayList<News> getNewsArray() {
            return newsArray;
        }

        public String getOrder() {
            return TextUtils.getSafeString(order);
        }

    }
    public class News {
        private String id;
        private String url;
        private String title;
        private String groupId;
        private String siteName;
        private String mobileUrl;
        private String authorName;
        private String duplicateId;
        private String publishDate;

        public String getId() {
            return TextUtils.getSafeString(id);
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUrl() {
            return TextUtils.getSafeString(url);
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getTitle() {
            return TextUtils.getSafeString(title);
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSiteName() {
            return TextUtils.getSafeString(siteName);
        }
    }
}
