package com.example.bookreader.controller.note;

import com.example.bookreader.bl.note.NoteService;
import com.example.bookreader.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/note")
public class NoteController {
    @Autowired
    NoteService noteService;
    @GetMapping("getNoteList")
    ResponseVO getNoteList(@RequestParam("openId")String openId){

        return noteService.getAllNotes(openId);
    }
    @GetMapping("updateNote")
    ResponseVO updateNote(@RequestParam("id")Integer id,@RequestParam("content")String noteContent){
        return noteService.updateNote(id,noteContent);
    }

    @GetMapping("deleteNote")
    ResponseVO  deleteNote(@RequestParam("id")Integer id){
        return noteService.deleteNote(id);
    }

    @PostMapping("addNote")
    ResponseVO addNote(@RequestParam("openId")String openId,@RequestParam("title")String title,@RequestParam("noteContent") String noteContent){
        return noteService.addNote(openId,title,noteContent);
    }

}
