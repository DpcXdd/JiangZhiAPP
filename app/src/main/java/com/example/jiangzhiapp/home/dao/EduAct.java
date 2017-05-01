package com.example.jiangzhiapp.home.dao;

/**
 * Created by JHO on 2017-04-20.
 */

public class EduAct {
    private int ImgId;
    private String title;
    private String dtaile;
    private String url;

    public EduAct (int imgId, String title, String dtaile, String url) {
        ImgId = imgId;
        this.title = title;
        this.dtaile = dtaile;
        this.url = url;
    }

    public int getImgId() {
        return ImgId;
    }

    public void setImgId(int imgId) {
        ImgId = imgId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDtaile() {
        return dtaile;
    }

    public void setDtaile(String dtaile) {
        this.dtaile = dtaile;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
