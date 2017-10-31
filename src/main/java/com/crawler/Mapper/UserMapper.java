package com.crawler.Mapper;

import com.crawler.Entity.Friend;
import com.crawler.Entity.UserInfo;
import org.apache.ibatis.annotations.Param;

import javax.ws.rs.QueryParam;
import java.util.List;

public interface UserMapper {



    List<Friend> getFriend(@Param("userId")Long userId);


    UserInfo checkUserName(@Param("username")String username);

}
