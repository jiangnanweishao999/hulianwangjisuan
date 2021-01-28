package com.example.bookreader.controller.shelf;

import com.example.bookreader.bl.shelf.ShelfService;
import com.example.bookreader.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/shelf")
public class ShelfController {
    @Autowired
    ShelfService shelfService;

    @PostMapping("addShelf")
    public ResponseVO addShelf(@RequestParam("bookId")int bookId,@RequestParam("openId")String openId){

        return shelfService.addShelf(bookId,openId);
    }

    @GetMapping("deleteShelf")
    public ResponseVO deleteShelf(@RequestParam("bookId")int id){
        shelfService.deleteShelf(id);
        return ResponseVO.buildSuccess(true);
    }

    @GetMapping("getShelfBooks")
    public ResponseVO getAllShelf(@RequestParam("openId")String openId){
        return ResponseVO.buildSuccess(shelfService.getAllShelf(openId));
    }
}
