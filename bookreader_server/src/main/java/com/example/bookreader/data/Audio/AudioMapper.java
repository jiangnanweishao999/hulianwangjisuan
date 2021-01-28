package com.example.bookreader.data.Audio;

import com.example.bookreader.po.Audio.Audio;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AudioMapper {
    void addAudio(Audio audio);

    List<Audio> allAudio();
}
