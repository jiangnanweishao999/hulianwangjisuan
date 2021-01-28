package com.example.bookreader.data.book;

import com.example.bookreader.po.book.Shelf;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ShelfMapper {
    void addShelf(Shelf shelf);
    void deleteShelf(int id);
    List<Shelf> getAllShelf(String openId);
}
