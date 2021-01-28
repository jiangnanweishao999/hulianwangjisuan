package com.example.bookreader.bl.book;

import com.example.bookreader.vo.Chapter;
import com.example.bookreader.po.page.Page;

import java.io.File;
import java.io.IOException;
import java.util.List;

/*
* 负责将原文件进行格式化
* */
public interface BookFormatService {
    List<Page> paging(String content,int lineLimit,int wordLimit,double offset);//分页
    String chaptering(int bookId,int ChapterNo) throws IOException;//分章
    double getBookChapters(String sourcePath,int bookId);
    void updateBook(int id,String bookAuthor,String bookDescription,String label);
}
