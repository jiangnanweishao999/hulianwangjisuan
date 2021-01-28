package com.example.bookreader.bl.shelf;

import com.example.bookreader.po.book.Book;
import com.example.bookreader.po.book.Shelf;
import com.example.bookreader.vo.ResponseVO;

import java.util.List;

public interface ShelfService {
    ResponseVO addShelf(int bookId, String openId);
    void deleteShelf(int id);
    List<Book> getAllShelf(String openId);
}
