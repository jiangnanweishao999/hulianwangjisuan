package com.example.bookreader.blImpl.note;

import com.example.bookreader.bl.note.NoteService;
import com.example.bookreader.data.note.NoteMapper;
import com.example.bookreader.po.note.Note;
import com.example.bookreader.vo.ResponseVO;
import com.google.gson.internal.$Gson$Types;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class NoteServiceImpl implements NoteService {
    @Autowired
    NoteMapper noteMapper;
    @Override
    public ResponseVO getAllNotes(String openId){
        return ResponseVO.buildSuccess(noteMapper.getAllNotes(openId));
    }
    @Override
    public ResponseVO updateNote(Integer id,String noteContent){
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        String createDate = sf.format(date);
        return ResponseVO.buildSuccess(noteMapper.updateNote(noteContent,createDate,id));
    }

    @Override
    public ResponseVO deleteNote(Integer id){
        return ResponseVO.buildSuccess(noteMapper.deleteNote(id));
    }

    @Override
    public ResponseVO addNote(String openId,String title,String text){
        Note note=new Note();
        note.setOpenId(openId);
        note.setTitle(title);
        note.setNoteContent(text);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        String createDate = sf.format(date);
        note.setCreateDate(createDate);
        return ResponseVO.buildSuccess(noteMapper.addNote(note));
    }
}
