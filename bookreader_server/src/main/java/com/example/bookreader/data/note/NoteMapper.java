package com.example.bookreader.data.note;


import com.example.bookreader.po.note.Note;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface NoteMapper {
    int addNote(Note note);
    List<Note> getAllNotes(@Param("openId")String openId);
    int deleteNote(@Param("id")Integer id);
    int updateNote(@Param("noteContent")String noteContent,@Param("createDate")String createDate,@Param("id")Integer id);
}
