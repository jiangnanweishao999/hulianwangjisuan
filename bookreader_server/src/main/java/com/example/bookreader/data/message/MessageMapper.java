package com.example.bookreader.data.message;

import com.example.bookreader.po.message.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MessageMapper {
    int createNewMessage(Message message);

    List<Message> getAllUserMessages();

    int deleteMessage(@Param("id")Integer id);

    int deleteAllMessages(@Param("openId")String openId);

    List<Message> getAllMessages(@Param("openId")String openId);
}
