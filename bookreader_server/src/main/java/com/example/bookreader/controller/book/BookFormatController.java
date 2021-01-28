package com.example.bookreader.controller.book;

import com.example.bookreader.bl.book.BookFormatService;
import com.example.bookreader.data.book.BookMapper;

import com.example.bookreader.po.book.Book;
import com.example.bookreader.po.page.Page;
import com.example.bookreader.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping("/api/book")
public class BookFormatController {
    @Autowired
    BookFormatService bookFormatService;
    @Autowired
    BookMapper bookMapper;


    //测试
    @GetMapping("/getChapterAsPages")
    public ResponseVO getChapterAsPages(@RequestParam("book")int book,@RequestParam("chapter")int chapter,@RequestParam("line")int line,@RequestParam("word")int word){
  // @PostMapping("/{book}/{chapter}/{line}/{word}/getChapterAsPages")
 //  public ResponseVO getChapterAsPage(@PathVariable("book")int book, @PathVariable("chapter")int chapter, @PathVariable("line")int line, @PathVariable("word")int word){

       String chapterContent="";
       try{
          chapterContent=bookFormatService.chaptering(book,chapter);
       }
       catch(Exception e){

       }
        List<Page> list=bookFormatService.paging(chapterContent,line,word,1);
        System.out.println(list.get(0).getContent());

      return ResponseVO.buildSuccess(list);


    }

    @PostMapping("{id}/{bookAuthor}/{bookDescription}/{label}/updateBook")
    public ResponseVO updateBook(@PathVariable("id")int id,@PathVariable("bookAuthor") String bookAuthor,@PathVariable("bookDescription") String bookDescription,@PathVariable("label")String label){
        bookFormatService.updateBook(id,bookAuthor,bookDescription,label);
        return ResponseVO.buildSuccess();
    }

    @GetMapping("no")
    public void tests(){
        String bookAuthor="这是作者";
        String bookDescroption="这是简介";
        String label="恐怖";
        List<Book> books=bookMapper.getBookList();
        for(int i=0;i<books.size();i++){
            bookMapper.updateBook(books.get(i).getId(),bookAuthor,bookDescroption,label);
        }
    }
}
