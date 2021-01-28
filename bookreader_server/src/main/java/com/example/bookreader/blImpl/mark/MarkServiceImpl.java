package com.example.bookreader.blImpl.mark;

import com.example.bookreader.bl.mark.MarkService;
import com.example.bookreader.data.mark.MarkMapper;
import com.example.bookreader.po.mark.Mark;
import com.example.bookreader.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarkServiceImpl implements MarkService {

    @Autowired
    MarkMapper markMapper;

    public void addMark(int bookId,int chapterId,String openId,String content){
        Mark mark=new Mark();
        mark.setBookId(bookId);
        mark.setChapterId(chapterId);
        mark.setOpenId(openId);
        double offset=content.length();
        for(int i=0;i<content.length();i++){
            if(content.charAt(i)=='\n'||content.charAt(i)==' '){
                offset--;
            }
        }
        mark.setOffset(offset);
        markMapper.addMark(mark);
    }

    @Override
    public List<Mark> getAllMarksByBookId(int bookId,String openId){
        return markMapper.getAllMarksByBookId(bookId,openId);
    }

    @Override
    public List<Mark> getAllMarksByOpenId(String openId){
        return markMapper.getAllMarksByOpenId(openId);
    }

    @Override
    public Mark getMark(int id){
        return markMapper.getMark(id);
    }

    @Override
    public ResponseVO deleteMark(int id){
        markMapper.delteMark(id);
        return ResponseVO.buildSuccess(true);
    }
}
