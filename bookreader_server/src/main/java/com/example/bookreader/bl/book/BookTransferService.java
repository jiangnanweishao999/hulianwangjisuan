package com.example.bookreader.bl.book;

import com.aliyun.oss.model.OSSObject;
import com.example.bookreader.po.book.Book;
import com.example.bookreader.vo.ResponseVO;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

/*
* 负责在服务器和oss之间传输文件
* */
public interface BookTransferService {
     void initialize();
     boolean isInitialized();
     ResponseVO uploadBook(String sourcePath, String destBucket, String destFileName) ;
     OSSObject downloadBook(String bookUrl);
     List<Book> getBookList(String content);
     List<Book> getGoodBookList();
     Book getBook(int id) throws IOException;
     ResponseVO uploadImg(int id,String sourcePath,String destBucket,String filename);
     List<String> getBookContent(int id) throws IOException;
}
