package com.example.bookreader.blImpl.message;

import com.example.bookreader.bl.message.MessageService;
import com.example.bookreader.data.message.MessageMapper;
import com.example.bookreader.po.message.Message;
import com.example.bookreader.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    MessageMapper messageMapper;
    @Override
    public ResponseVO addMessage(Message message){
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String Date = sf.format(date);
        message.setDate(Date);
        return ResponseVO.buildSuccess(messageMapper.createNewMessage(message));
    }

    @Override
    public ResponseVO deleteMessage(Integer id){
        return ResponseVO.buildSuccess(messageMapper.deleteMessage(id));
    }

    @Override
    public ResponseVO deleteAllMessages(String openId){
        return ResponseVO.buildSuccess(messageMapper.deleteAllMessages(openId));
    }

    @Override
    public ResponseVO getAllMessages(String openId){
        return ResponseVO.buildSuccess(messageMapper.getAllMessages(openId));
    }

    @Override
    public ResponseVO getRecord(String openId,String date) throws ParseException {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        List<Message> target=messageMapper.getAllMessages(openId);
        List<Message> result=new ArrayList<>();
        for (Message message : target) {
            if (message.getDate().substring(0, 10).equals(date)&&!message.getAction().equals("listen book")) {
                result.add(message);
            }
        }

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<Date> dates=new ArrayList<>();
        for (Message message : result) {
            dates.add(sf.parse(message.getDate()));
        }
        List<Long> length=new ArrayList<>();
        for(int i=0;i<dates.size();i=i+2){
            length.add(dates.get(i+1).getTime()-dates.get(i).getTime());
            System.out.println(length);
        }
        List<String> answer=new ArrayList<>();
        long max=0;
        long min=0;
        long sum=0;
        for (Long aLong : length) {
            if (max <= aLong) {
                max = aLong;
            }
            if(min>=aLong){
                min=aLong;
            }
            sum+=aLong;
            System.out.println(sum);
        }
        max=max% nd % nh / nm;
        min=min% nd % nh / nm;
        sum=sum% nd % nh / nm;
        answer.add("最长阅读时间："+max+"分钟");
        answer.add("最短阅读时间："+min+"分钟");
        answer.add("总计阅读时间："+sum+"分钟");
        List<Integer>books=new ArrayList<>();
        for (Message message : result) {
            if (!books.contains(message.getBookId())) {
                books.add(message.getBookId());
            }
        }
        answer.add("共读了："+books.size()+"本书");
        StringBuilder end= new StringBuilder();
        for (String s : answer) {
            end.append(s);
            end.append("\n");
        }
        return ResponseVO.buildSuccess(end);
    }

}
