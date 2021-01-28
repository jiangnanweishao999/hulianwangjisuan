package com.example.bookreader.data.mark;

import com.example.bookreader.po.mark.Mark;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MarkMapper {
    void addMark(Mark mark);
    List<Mark>getAllMarksByBookId(int bookId,String openId);
    List<Mark>getAllMarksByOpenId(String openId);
    Mark getMark(int id);
    void delteMark(int id);
}
