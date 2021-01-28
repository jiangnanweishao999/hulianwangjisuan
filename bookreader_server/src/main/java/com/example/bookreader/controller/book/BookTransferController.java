package com.example.bookreader.controller.book;

import com.example.bookreader.bl.book.BookTransferService;
import com.example.bookreader.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController()
@RequestMapping("/api/book")
public class BookTransferController {
    @Autowired
    BookTransferService bookTransferService;

    @GetMapping("/{sourcePath}/{bookName}/addBook")
    public ResponseVO addBook(@PathVariable String sourcePath,@PathVariable String bookName){

        String Path=sourcePath+"\\"+bookName;
        bookTransferService.uploadBook(Path,"weiyihui-ruangong2homework",bookName);
        //bookTransferService.uploadBook(Path,"tianchiwu",bookName);
        return ResponseVO.buildSuccess();
    }

    @PostMapping("{id}/{path}/{filename}/updateImg")
    public ResponseVO updateImg(@PathVariable("id")int id,@PathVariable("path")String path,@PathVariable("filename")String filename){
        String Path=path+"\\"+filename;
        bookTransferService.uploadImg(id,Path,"weiyihui-ruangong2homework",filename);
        return ResponseVO.buildSuccess();
    }

    @GetMapping("getBookList")
    public ResponseVO getBookList(@RequestParam("content")String content){
        return ResponseVO.buildSuccess(bookTransferService.getBookList(content));
    }
    @GetMapping("getGoodBookList")
    public ResponseVO getGoodBookList(){
        return ResponseVO.buildSuccess(bookTransferService.getGoodBookList());
    }

    @GetMapping("getBook")
    public ResponseVO getBook(@RequestParam("id")int id) throws IOException {
        return ResponseVO.buildSuccess(bookTransferService.getBook(id));
    }

    @GetMapping("null")
    public void test(){
        String bookName="悲惨世界.txt";
        String Path="F:"+"\\"+bookName;
        bookTransferService.uploadBook(Path,"weiyihui-ruangong2homework",bookName);
    }

    @GetMapping("picture")
    public void test2(){
        String path="F:";
        String filename="TIM图片20200623213207.jpg";
        String Path=path+"\\"+filename;
        bookTransferService.uploadImg(4,Path,"weiyihui-ruangong2homework",filename);
    }
}
