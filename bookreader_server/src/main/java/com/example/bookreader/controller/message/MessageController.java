package com.example.bookreader.controller.message;

import com.example.bookreader.bl.message.MessageService;
import com.example.bookreader.po.message.Message;
import com.example.bookreader.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController()
@RequestMapping("/api/book")
public class MessageController {
    @Autowired
    MessageService messageService;
    @PostMapping("addReadRecord")
    public ResponseVO addMessage(@RequestBody Message message,@RequestParam("openId")String openId){
        message.setOpenId(openId);
        return messageService.addMessage(message);
    }
    @GetMapping("{id}/deleteMessage")
    public ResponseVO deleteMessage(@PathVariable("id")int id){
        return messageService.deleteMessage(id);
    }
    @GetMapping("{openId}/deleteAllMessages")
    public ResponseVO deleteAllMessages(@PathVariable("openId")String openId){
        return messageService.deleteAllMessages(openId);
    }
    @GetMapping("{openId}/getAllMessages")
    public ResponseVO getMessages(@PathVariable("openId")String openId){
        return messageService.getAllMessages(openId);
    }
    @GetMapping("getRecord")
    public ResponseVO getRecord(@RequestParam("openId")String openId,@RequestParam("date")String date) throws ParseException {
        return messageService.getRecord(openId,date);
    }
}
