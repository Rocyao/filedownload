package com.tj.filedownload.dto;

import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * @author peng
 * @createDate 2022/7/29 17:33
 */
@Data
public class UserInfo {
    private int userId;
    private String user;
    private String phone;
    private String file_ext;
    private String remarks;
    private List<Integer> roleIds;
    private Set<String> permissionList;

}
