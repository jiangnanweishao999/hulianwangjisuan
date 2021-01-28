package com.example.bookreader.data.user;

import com.example.bookreader.po.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {
    int createNewAccount(User user);

    User getAccountByOpenId(@Param("openId") String openId);

    int updateAccount(@Param("openId") String openId,@Param("userName") String userName, @Param("phoneNumber") String phoneNumber,@Param("signature")String signature);

    int uploadProfilePhoto(@Param("openId")String openId,@Param("url")String url);

}
