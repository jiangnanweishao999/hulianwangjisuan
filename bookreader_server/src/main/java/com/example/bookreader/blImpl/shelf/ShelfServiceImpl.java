package com.example.bookreader.blImpl.shelf;

import com.example.bookreader.bl.shelf.ShelfService;
import com.example.bookreader.data.book.BookMapper;
import com.example.bookreader.data.book.ShelfMapper;
import com.example.bookreader.po.book.Book;
import com.example.bookreader.po.book.Shelf;
import com.example.bookreader.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShelfServiceImpl implements ShelfService {
    @Autowired
    ShelfMapper shelfMapper;
    @Autowired
    BookMapper bookMapper;

    @Override
    public ResponseVO addShelf(int bookId, String openId) {
        List<Shelf> shelves=shelfMapper.getAllShelf(openId);
        for (Shelf value : shelves) {
            if (value.getBookId() == bookId) {
                return ResponseVO.buildFailure("已有此书");
            }
        }
        Shelf shelf=new Shelf();
        shelf.setBookId(bookId);
        shelf.setOpenId(openId);
        shelfMapper.addShelf(shelf);
        return ResponseVO.buildSuccess();
    }

    @Override
    public void deleteShelf(int id) {
        shelfMapper.deleteShelf(id);
    }

    @Override
    public List<Book> getAllShelf(String openId) {
        List<Shelf> shelves=shelfMapper.getAllShelf(openId);
        List<Book> result=new ArrayList<>();
        for (Shelf shelf : shelves) {
            result.add(bookMapper.getBook(shelf.getBookId()));
        }
        return result;
    }
}
