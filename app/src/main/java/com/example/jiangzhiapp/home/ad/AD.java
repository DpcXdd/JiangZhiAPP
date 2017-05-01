package com.example.jiangzhiapp.home.ad;

/**
 * Created by 浅墨留痕 on 2017/3/1.
 */
public class AD {
    private int imageId;
    private String data;

    public AD(int imageId, String data) {
        this.imageId = imageId;
        this.data = data;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
