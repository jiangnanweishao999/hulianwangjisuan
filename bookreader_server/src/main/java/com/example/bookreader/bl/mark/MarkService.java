package com.example.bookreader.bl.mark;

import com.example.bookreader.po.mark.Mark;
import com.example.bookreader.vo.ResponseVO;

import java.util.List;

public interface MarkService {
    void addMark(int bookId,int chapterId,String openId,String content);
    List<Mark> getAllMarksByBookId(int bookId,String openId);
    List<Mark> getAllMarksByOpenId(String openId);
    Mark getMark(int id);
    ResponseVO deleteMark(int id);
}
