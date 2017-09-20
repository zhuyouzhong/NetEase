package com.example.netease.bean;

/**
 * Created by 祝文 on 2017/9/14.
 */

public class Shuju {
    private String title;
    private String date;
    private String author_name;
    private String url;
    private String thumbnail_pic_s;

    @Override
    public String toString() {
        return "Shuju{" +
                "title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", author_name='" + author_name + '\'' +
                ", url='" + url + '\'' +
                ", thumbnail_pic_s='" + thumbnail_pic_s + '\'' +
                '}';
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setThumbnail_pic_s(String thumbnail_pic_s) {
        this.thumbnail_pic_s = thumbnail_pic_s;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public String getUrl() {
        return url;
    }

    public String getThumbnail_pic_s() {
        return thumbnail_pic_s;
    }

    public Shuju() {
    }

    public Shuju(String title, String date, String author_name, String url, String thumbnail_pic_s) {
        this.title = title;
        this.date = date;
        this.author_name = author_name;
        this.url = url;
        this.thumbnail_pic_s = thumbnail_pic_s;
    }
}
