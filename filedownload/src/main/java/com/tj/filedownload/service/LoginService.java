package com.tj.filedownload.service;

import com.alibaba.fastjson.JSONObject;
import com.github.benmanes.caffeine.cache.Cache;
import com.tj.filedownload.common.CommonUtil;
import com.tj.filedownload.common.LocalCacheUtil;
import com.tj.filedownload.common.RedisUtils;
import com.tj.filedownload.common.StringTools;
import com.tj.filedownload.config.CaffeineConfig;
import com.tj.filedownload.dto.UserInfo;
import com.tj.filedownload.mapper.TjSysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
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
    LocalCacheUtil<String> util;

    public void saveLoginInfo(String user){
        redis.hashSet("userLogin", user, userMapper.getUserInfo(user));
    }

    public String generateInfo(String username) {
        String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 20);
        redis.set("username", username, 2 * 60);
        util.add(username, uuid);
        return uuid;
    }

    public UserInfo getUserInfo(){
        return getUserInfoFromCache(redis.get("username").toString());
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

    public JSONObject logout(String user) {
        redis.delete(user);
        util.delete(user);
        return CommonUtil.successJson();
    }
}
