package com.crawler.Mapper;

import com.crawler.Entity.Friend;
import org.apache.ibatis.annotations.Param;

import javax.ws.rs.QueryParam;
import java.util.List;

public interface UserMapper {


    Boolean register(@QueryParam("userName")String userName,@QueryParam("password")String password);

    List<Friend> getFriend(@Param("userId")Long userId);

}
