package com.crawler.Mapper;

import com.crawler.Entity.UserInfo;
import org.apache.ibatis.annotations.Param;

import javax.ws.rs.QueryParam;

public interface LoginMapper {
    UserInfo loginIn(@Param("userName") String userName, @Param("password")String password);


}
