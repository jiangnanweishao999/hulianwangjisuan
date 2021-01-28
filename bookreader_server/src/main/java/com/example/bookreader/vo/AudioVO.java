package com.example.bookreader.vo;

public class AudioVO {
    String sourcePath;
    String audioName;
    int bookId;
    int audioNo;
    String audioDescription;

    public String getSourcePath(){ return this.sourcePath; }

    public void setSourcePath(String sourcePath) { this.sourcePath = sourcePath; }

    public String getAudioName(){ return this.audioName; }

    public void setAudioName(String audioName) { this.audioName = audioName; }

    public int getBookId() { return bookId; }

    public void setBookId(int bookId) { this.bookId = bookId; }

    public int getAudioNo() { return audioNo; }

    public void setAudioNo(int audioNo) { this.audioNo = audioNo; }

    public String getAudioDescription() { return this.audioDescription; }

    public void setAudioDescription(String audioDescription) { this.audioDescription = audioDescription; }
}
