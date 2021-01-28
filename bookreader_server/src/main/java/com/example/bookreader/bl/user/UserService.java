package com.example.bookreader.bl.user;

import com.example.bookreader.po.user.User;
import com.example.bookreader.vo.ResponseVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.InputStream;

public interface UserService {
    ResponseVO register(User user);

    ResponseVO getUserInfo(String openId);

    ResponseVO login(String openId);

    ResponseVO updateUserInfo(User user);

    ResponseVO uploadProfilePhoto(String openId, String destBucket, InputStream in);


}
