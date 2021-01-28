package com.example.bookreader.bl.message;

import com.example.bookreader.po.message.Message;
import com.example.bookreader.vo.ResponseVO;

import java.text.ParseException;

public interface MessageService {
    ResponseVO addMessage(Message message);

    ResponseVO deleteMessage(Integer id);

    ResponseVO deleteAllMessages(String openId);

    ResponseVO getAllMessages(String openId);

    ResponseVO getRecord(String openId,String date) throws ParseException;
}
