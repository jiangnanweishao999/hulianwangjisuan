package com.example.bookreader.blImpl.audio;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.example.bookreader.bl.audio.AudioService;
import com.example.bookreader.data.Audio.AudioMapper;
import com.example.bookreader.po.Audio.Audio;
import com.example.bookreader.vo.AudioVO;
import com.example.bookreader.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.List;

@Service
public class AudioServiceImpl implements AudioService {
    @Autowired
    AudioMapper audioMapper;

    private OSS oss=null;

    @Override
    public void initialize(){
        String endpoint="oss-cn-shanghai.aliyuncs.com";
        String accessKeyId="LTAI4GCTpsxuJBMD2CoNbXGv";
        String accessKeySecret="0K75e8beplHBYDGBgYsqQZtxwqh8A6";
        oss= new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }

    @Override
    public ResponseVO uploadAudio(AudioVO audioVO,String destBucket){
        Audio audio=new Audio();
        audio.setAudioDescription(audioVO.getAudioDescription());
        audio.setAudioName(audioVO.getAudioName());
        audio.setBookId(audioVO.getBookId());
        audio.setAudioNo(audioVO.getAudioNo());
        initialize();
        String Path=audioVO.getSourcePath()+"\\"+audioVO.getAudioName();
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(Path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("找不到文件");
        }
        oss.putObject(destBucket, audioVO.getAudioName(), inputStream);
        Date expiration=new Date(new Date().getTime() +3600 *1000);
        URL url=oss.generatePresignedUrl(destBucket,audioVO.getAudioName(),expiration);
        //System.out.println(url.toString());
        audio.setRadioUrl(url.toString());
        oss.shutdown();
        audioMapper.addAudio(audio);
        return ResponseVO.buildSuccess();
    }

    @Override
    public List<Audio> getAllAudio(){
        return audioMapper.allAudio();
    }
}
