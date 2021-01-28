package com.example.bookreader.po.message;

public class Message {
    private int id;
    private String openId;
    private String action;
    private int bookId;
    private int chapter;
    private int page;
    private String date;
    public void setId(int id) {
        this.id = id;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setChapter(int chapter) {
        this.chapter = chapter;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getId() {
        return id;
    }

    public String getOpenId() {
        return openId;
    }

    public String getAction() {
        return action;
    }

    public int getBookId() {
        return bookId;
    }

    public int getChapter() {
        return chapter;
    }

    public int getPage() {
        return page;
    }

    public String getDate() {
        return date;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }


}
