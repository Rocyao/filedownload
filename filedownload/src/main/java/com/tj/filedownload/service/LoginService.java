package com.tj.filedownload.service;

import com.tj.filedownload.common.RedisUtils;
import com.tj.filedownload.common.StringTools;
import com.tj.filedownload.dto.UserInfo;
import com.tj.filedownload.mapper.TjSysUserMapper;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author peng
 * @createDate 2022/7/29 16:38
 */
@Service
public class LoginService {

    @Autowired
    RedisUtils redis;
    @Autowired
    TjSysUserMapper userMapper;


    public void saveLoginInfo(String user){
        MDC.put("user",user);
        redis.hashSet("userLogin", user, userMapper.getUserInfo(user));
    }

    public UserInfo getUserInfo(){
        String user = MDC.get("user");
        return getUserInfoFromCache(user);
    }
    public UserInfo getUserInfoFromCache(String user){
        if(StringTools.isNullOrEmpty(user)){
            return null;
        }
        UserInfo info = (UserInfo)redis.hashGet("userLogin", user);
        if(info == null){
            return null;
        }
        return info;
    }

    public void clearCache(){
        MDC.remove("user");
    }
}
