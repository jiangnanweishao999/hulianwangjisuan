package com.example.bookreader.controller.user;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSS;
import com.example.bookreader.bl.user.UserService;
import com.example.bookreader.po.user.User;
import com.example.bookreader.vo.ResponseVO;
import org.apache.commons.codec.binary.Base64InputStream;
import org.apache.commons.io.FileUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Base64;
import java.util.HashMap;

@RestController()
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;
    @GetMapping("register")
    public ResponseVO register(@RequestBody User user){
        return userService.register(user);
    }
    @GetMapping("{openId}/getUserInfo")
    public ResponseVO getUserInfo(@PathVariable("openId")String openId){
        return userService.getUserInfo(openId);
    }
    @GetMapping("getOpenId")
    public ResponseVO login(@RequestParam("name")String openId){
        return userService.login(openId);
    }
    @GetMapping("updateUserInfo")/*user信息里需要包含openId*/
    public ResponseVO updateUserInfo(@RequestBody User user){
        return userService.updateUserInfo(user);
    }

    @PostMapping("updateProfilePhoto")
    public ResponseVO uploadProfilePhoto(
            @RequestParam("openId")String openId, @RequestParam("url") String url) {
        System.out.println(url);
        Base64.Decoder decoder=Base64.getDecoder();
        byte[] result=decoder.decode(url);
        InputStream inputStream= new ByteArrayInputStream(result);
        return ResponseVO.buildSuccess(userService.uploadProfilePhoto(openId,"weiyihui-ruangong2homework",inputStream));

    }





}
