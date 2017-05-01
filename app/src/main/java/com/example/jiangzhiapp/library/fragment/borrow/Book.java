package com.example.jiangzhiapp.library.fragment.borrow;

/**
 * Created by 浅墨留痕 on 2017/4/16.
 */
public class Book {

    private int imageId;
    private String bookName;
    private String author;
    private int days;

    public Book(int imageId, String bookName, String author) {
        this.imageId = imageId;
        this.bookName = bookName;
        this.author = author;
    }

    public Book(int imageId, String bookName, String author, int days) {
        this.imageId = imageId;
        this.bookName = bookName;
        this.author = author;
        this.days = days;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }
}
