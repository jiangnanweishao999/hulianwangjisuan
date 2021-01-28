package com.example.bookreader.blImpl.user;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.example.bookreader.bl.book.BookTransferService;
import com.example.bookreader.bl.user.UserService;
import com.example.bookreader.data.user.UserMapper;
import com.example.bookreader.po.user.User;
import com.example.bookreader.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    private OSS oss=null;
    @Override
    public ResponseVO register(User user){
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date(System.currentTimeMillis());
        String registerDate = sf.format(date);
        user.setRegisterDate(registerDate);
        return ResponseVO.buildSuccess(userMapper.createNewAccount(user));
    }

    @Override
    public ResponseVO getUserInfo(String openId){
        return ResponseVO.buildSuccess(userMapper.getAccountByOpenId(openId));
    }

    @Override
    public ResponseVO login(String openId){
        if(userMapper.getAccountByOpenId(openId)!=null){
            return ResponseVO.buildSuccess(getUserInfo(openId));
        }
        else{
            User user=new User();
            user.setOpenId(openId);
            user.setUserName(openId);
            register(user);
            return ResponseVO.buildSuccess(getUserInfo(openId));
        }
    }

    @Override
    public ResponseVO updateUserInfo(User user){
        return ResponseVO.buildSuccess(userMapper.updateAccount(user.getOpenId(),user.getUserName(),user.getPhoneNumber(),user.getSignature()));
    }

    @Override
    public ResponseVO uploadProfilePhoto(String openId, String destBucket, InputStream in)  {
        initialize();
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String fileName = simpleDateFormat.format(date)+".png";
        oss.putObject(destBucket, fileName, in);
        Date expiration = new Date(new Date().getTime() + 3600L * 1000 * 24 * 365 * 10);
        String url = oss.generatePresignedUrl(destBucket, fileName, expiration).toString();
        oss.shutdown();
        return ResponseVO.buildSuccess(userMapper.uploadProfilePhoto(openId,url));
    }




    private void initialize(){
        // String endpoint= OSSconfig.endpoint;
        String endpoint="oss-cn-shanghai.aliyuncs.com";
        //String accessKeyId=OSSconfig.accessKeyId;
        String accessKeyId="LTAI4GHKVzMMMQYYHrspbMFF";
        //String accessKeyId="LTAI4GCTpsxuJBMD2CoNbXGv";
        //String accessKeySecret=OSSconfig.accessKeySecret;
        String accessKeySecret="DNoaGbNwZfnehzS2vWVtHmYHLbu2IB";
        //String accessKeySecret="0K75e8beplHBYDGBgYsqQZtxwqh8A6";
        oss= new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }
}
