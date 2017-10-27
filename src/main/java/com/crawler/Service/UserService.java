package com.crawler.Service;

import com.crawler.Entity.Friend;
import com.crawler.Entity.UserInfo;
import com.crawler.Mapper.LoginMapper;
import com.crawler.Mapper.UserMapper;
import com.util.dao.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class UserService {

    @Autowired
    private BaseDao baseDao;

    @Autowired
    private LoginMapper loginMapper;


    @Autowired
    private UserMapper userMapper;

    public String register(String userName,String password){
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(userName);
        userInfo.setPassword(password);
       baseDao.persist(userInfo);
        return userName;
    }


    public UserInfo  loginIn(String userName,String password){
        UserInfo userInfo=loginMapper.loginIn(userName,password);
        if (nonNull(userInfo)) {
            return userInfo;
        }else {
            return null;
        }
    }



    public List<UserInfo> getFriend(Long userId){
        List<Friend> ls= userMapper.getFriend(userId);
        List<UserInfo> userInfos= baseDao.findByIds(UserInfo.class,ls.stream().map(t ->t.getFriendId()).collect(Collectors.toList()));
        return userInfos;
    }

}
