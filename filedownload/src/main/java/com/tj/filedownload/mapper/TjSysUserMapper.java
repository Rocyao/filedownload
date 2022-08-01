package com.tj.filedownload.mapper;

import com.tj.filedownload.dto.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author peng
 * @createDate 2022/7/29 9:45
 */
@Mapper
public interface TjSysUserMapper {


    UserInfo getUserInfo(String user);

}