package com.tj.filedownload.controller;

import com.tj.filedownload.common.CommonUtils;
import com.tj.filedownload.config.annotation.RequiresPermissions;
import com.tj.filedownload.service.LoginService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author peng
 * @createDate 2022/7/29 10:31
 */
@RestController
@RequestMapping("/file")
public class FileDownload {

    @Value("${file.srcPath}")
    public String srcPath;

    @Autowired
    LoginService loginService;

    @RequiresPermissions("role:download")
    @GetMapping("/download/{targetName}")
    public void downloadExcelFile(@PathVariable("targetName") String targetName, HttpServletResponse response){
        try {
            CommonUtils.downloadFile("13453404272.xlsx" ,srcPath, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/login/{user}")
    public void login(@PathVariable("user") String user){

    }

    @GetMapping("/logout")
    public void logout(){
        loginService.clearCache();
    }
}
