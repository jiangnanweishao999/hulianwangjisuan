package com.example.bookreader.bl.note;

import com.example.bookreader.vo.ResponseVO;

public interface NoteService {
    ResponseVO getAllNotes(String openId);

    ResponseVO updateNote(Integer id,String noteContent);

    ResponseVO deleteNote(Integer id);

    ResponseVO addNote(String openId,String title,String text);
}
