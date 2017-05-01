package com.example.jiangzhiapp.teach.teachBean;

/**
 * Created by JHO on 2017-04-26.
 */

public class Teaching {
    public static final String ALL_TERM = "所有学期";
    public static final String TERM1 = "大一(2015-2016) 第1学期";
    public static final String TERM2 = "大一(2015-2016) 第2学期";
    public static final String TERM3 = "大二(2016-2017) 第1学期";
    public static final String TERM4 = "大二(2016-2017) 第2学期";
    public static final String TERM5 = "大三(2017-2018) 第1学期";
    public static final String TERM6 = "大三(2017-2018) 第2学期";

    private String name;
    private float price;
    private String date;
    private String term;

    public Teaching(String name, float price, String date, String term) {
        this.name = name;
        this.price = price;
        this.date = date;
        this.term = term;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

}
