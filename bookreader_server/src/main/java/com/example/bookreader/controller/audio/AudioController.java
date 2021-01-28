package com.example.bookreader.controller.audio;

import com.example.bookreader.bl.audio.AudioService;
import com.example.bookreader.data.Audio.AudioMapper;
import com.example.bookreader.data.book.BookMapper;
import com.example.bookreader.vo.AudioVO;
import com.example.bookreader.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;

@RestController()
@RequestMapping("/api/audio")
public class AudioController {
    @Autowired
    AudioService audioService;

    @PostMapping("/addAudio")
    public ResponseVO addAudio(@RequestBody AudioVO audioVO){
        //System.out.println(audioVO.getAudioName());
        return ResponseVO.buildSuccess(audioService.uploadAudio(audioVO,"tianchiwu"));
    }

    @PostMapping("/getAllAudio")
    public ResponseVO allAudio(){
        return ResponseVO.buildSuccess(audioService.getAllAudio());
    }

}
