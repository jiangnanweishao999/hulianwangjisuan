package com.example.bookreader.controller.mark;
import com.example.bookreader.bl.book.BookFormatService;
import com.example.bookreader.bl.mark.MarkService;
import com.example.bookreader.po.mark.Mark;
import com.example.bookreader.po.page.Page;
import com.example.bookreader.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;

@RestController()
@RequestMapping("/api/mark")
public class MarkController {
    @Autowired
    MarkService markService;

    @Autowired
    BookFormatService bookFormatService;

    @GetMapping("addMark")
    public ResponseVO addMark(@RequestParam("bookId") int bookId,@RequestParam("chapterId") int chapterId,@RequestParam("openId") String openId,@RequestParam("content") String content){
        markService.addMark(bookId,chapterId,openId,content);
        return ResponseVO.buildSuccess(true);
    }

    @GetMapping("getAllMarksByBookId")
    public ResponseVO getAllMarksByBookId(@RequestParam("bookId")int bookId,@RequestParam("openId")String openId){
        return ResponseVO.buildSuccess(markService.getAllMarksByBookId(bookId,openId));
    }

    @GetMapping("getAllMarksByOpenId")
    public ResponseVO getAllMarksByOpenId(@RequestParam("openId")String openId){
        return ResponseVO.buildSuccess(markService.getAllMarksByOpenId(openId));
    }

    @GetMapping("getChapterByMark")
    public ResponseVO getChapterByMark(@RequestParam("markId")int id,@RequestParam("line")int line,@RequestParam("word")int word){
        Mark mark=markService.getMark(id);
        int book=mark.getBookId();
        int chapter=mark.getChapterId();
        double offset=mark.getOffset();
        String chapterContent="";
        try{
            chapterContent=bookFormatService.chaptering(book,chapter);
        }
        catch(Exception e){

        }
        List<Page> list=bookFormatService.paging(chapterContent,line,word,offset);
        System.out.println(list.get(0).getContent());
        return ResponseVO.buildSuccess(list);
    }

    @GetMapping("deleteMark")
    public ResponseVO deleteMark(@RequestParam("markId")int id){
        markService.deleteMark(id);
        return ResponseVO.buildSuccess(true);
    }
}
