package com.tj.filedownload.service;

import com.alibaba.fastjson.JSONObject;
import com.tj.filedownload.common.CommonUtil;
import com.tj.filedownload.common.LocalCacheUtil;
import com.tj.filedownload.common.RedisUtils;
import com.tj.filedownload.common.StringTools;
import com.tj.filedownload.dto.UserInfo;
import com.tj.filedownload.mapper.TjSysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

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
    @Autowired
    LocalCacheUtil<UserInfo> localCacheUtil;

    public String generateToken(String username){
        String token = UUID.randomUUID().toString().replace("-", "").substring(0,20);
        redis.hashSet("username",token, username);
        localCacheUtil.add("token", userMapper.getUserInfo(username));
        return token;
    }

    public UserInfo saveLoginInfo(String token){
        UserInfo info = userMapper.getUserInfo(redis.hashGet("username", token).toString());
        if(redis.hasKey(info.getUser())){
            return (UserInfo) redis.get(info.getUser());
        }
        redis.set(info.getUser(), info);
        return info;
    }

    public UserInfo getUserInfo(){
        return localCacheUtil.get("token");
    }

    public JSONObject logout() {
        redis.hashDelete("username", localCacheUtil.get("token"));
        localCacheUtil.delete("token");
        return CommonUtil.successJson();
    }
}
