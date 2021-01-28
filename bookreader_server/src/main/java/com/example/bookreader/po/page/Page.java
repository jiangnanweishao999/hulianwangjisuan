package com.example.bookreader.po.page;

public class Page {
    private int lineLimit;//行数
    private int wordLimit;//每行字数
    private String content;//内容
    private int id;//页号
    private int beginId;

    public int getLineLimit() {
        return lineLimit;
    }

    public void setLineLimit(int lineNumber) {
        this.lineLimit = lineNumber;
    }

    public int getWordLimit() {
        return wordLimit;
    }

    public void setWordLimit(int charInLine) {
        this.wordLimit = charInLine;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBeginId() {
        return beginId;
    }

    public void setBeginId(int beginId) {
        this.beginId = beginId;
    }
}
