package com.example.bookreader.data.book;

import com.example.bookreader.po.book.Book;
import com.example.bookreader.vo.Chapter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BookMapper {
    /**
     * 添加新书
     * @param book
     * @return
     */
    void addBook(Book book);

    /**
     * 获取某一章节的信息
     * @param bookId
     * @param chapterNo
     * @return
     */
    Chapter getChapter(@Param("bookId")int bookId,@Param("chapterNo") int chapterNo);

    /**
     * 获取某本书的存储地址
     * @param bookId
     * @return
     */
    String getBookUrl(@Param("bookId")int bookId);

    /**
     * 获取bookId
     * @param bookName
     * @return
     */
    int getBookId(@Param("bookName")String bookName);
    List<String > getChapterList(@Param("id") int id);

    /**
     * 加载页信息
     * @param chapter
     * @return
     */
    void addChapter(Chapter chapter);

    /**
     * 获取图书列表
     * @return
     */
     List<Book> getBookList();
     Book getBook(@Param("id") int id);

    /**
     * 设置章节数
     * @param bookId
     * @param chapters
     */
     void setBookLength(@Param("bookId")int bookId,@Param("chapters")double chapters);

    /**
     * 更新书详细信息
     * @param id
     * @param bookAuthor
     * @param bookDescription
     * @param label
     */
    void updateBook(int id,String bookAuthor,String bookDescription,String label);

    /**
     * 上传图片
     * @param id
     * @param url
     */
    void updateImg(int id,String url);
}
