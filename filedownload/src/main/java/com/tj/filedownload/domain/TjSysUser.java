package com.tj.filedownload.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *  @author peng
 *  @createDate 2022/7/29 9:45
 */
/**
    * 用户表
    */
@ApiModel(value="com-tj-filedownload-domain-TjSysUser")
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TjSysUser {
    /**
    * 用户ID
    */
    @ApiModelProperty(value="用户ID")
    private Long userId;

    /**
    * 用户名
    */
    @ApiModelProperty(value="用户名")
    private String user;

    /**
    * 手机号
    */
    @ApiModelProperty(value="手机号")
    private String phone;

    /**
    * 文件位置
    */
    @ApiModelProperty(value="文件位置")
    private String fileExt;

    /**
    * 备注
    */
    @ApiModelProperty(value="备注")
    private String remarks;
}