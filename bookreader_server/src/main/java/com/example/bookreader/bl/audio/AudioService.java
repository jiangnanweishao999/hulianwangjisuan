package com.example.bookreader.bl.audio;


import com.example.bookreader.po.Audio.Audio;
import com.example.bookreader.vo.AudioVO;
import com.example.bookreader.vo.ResponseVO;

import java.util.List;

public interface AudioService {
    void initialize();

    ResponseVO uploadAudio(AudioVO audioVO,String destBucket);

    List<Audio> getAllAudio();
}
