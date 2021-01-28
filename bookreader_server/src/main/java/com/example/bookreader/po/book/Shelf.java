package com.example.bookreader.po.book;

public class Shelf {
    private int id;
    private int bookId;
    private String openId;

    public String getOpenId() {
        return openId;
    }

    public int getBookId() {
        return bookId;
    }

    public int getId() {
        return id;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setId(int id) {
        this.id = id;
    }
}
