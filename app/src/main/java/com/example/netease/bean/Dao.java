package com.example.netease.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by 祝文 on 2017/9/17.
 */
@Entity
public class Dao {

    private String json;
    private String type;

    @Generated(hash = 384647155)
    public Dao() {
    }

    @Generated(hash = 1394986815)
    public Dao(String json, String type) {
        this.json = json;
        this.type = type;
    }

    public String getJson() {
        return json;
    }

    public String getType() {
        return type;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Dao{" +
                "json='" + json + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
    /* @Generated(hash = 384647155)
    public Dao() {
    }
    @Generated(hash = 1492406052)
    public Dao(String json) {
        this.json = json;
    }
    public String getJson() {
        return json;
    }



    public void setJson(String json) {
        this.json = json;
    }

    @Override
    public String toString() {
        return "Dao{" +

                ", json='" + json + '\'' +
                '}';
    }*/
}
