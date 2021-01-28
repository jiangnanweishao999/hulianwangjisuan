package com.example.bookreader.vo;

public class Chapter {
    private int id;
    private int bookId;//书号
    private int chapterNo;//章号
    private String chapterName;
    private double beginLocation;//开始行
    private double endLocation;//结束行

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getChapterNo() {
        return chapterNo;
    }

    public void setChapterNo(int chapterNo) {
        this.chapterNo = chapterNo;
    }

    public double getBeginLocation() {
        return beginLocation;
    }

    public void setBeginLocation(double beginLocation) {
        this.beginLocation = beginLocation;
    }

    public double getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(double endLocation) {
        this.endLocation = endLocation;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }
}
