package com.tj.filedownload.config.filter;

import com.tj.filedownload.config.annotation.Logical;
import com.tj.filedownload.config.annotation.RequiresPermissions;
import com.tj.filedownload.domain.TjSysUser;
import com.tj.filedownload.service.LoginService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

/**
 * @author peng
 * @createDate 2022/7/29 16:23
 */
@Aspect
@Order(3)
@Component
public class PermissionAspect {

    @Autowired
    private LoginService loginUser;

    @Before("@annotation(com.tj.filedownload.config.annotation.RequiresPermissions)")
    public void before(JoinPoint joinPoint) throws Exception {

        Set permissions = loginUser.getUserInfo().getPermissionList();
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        RequiresPermissions a =methodSignature.getMethod().getAnnotation(RequiresPermissions.class);
        String[] perms =a.value();

        if(a.logical() == Logical.AND){
            for(String perm: perms){
                if(!permissions.contains(perm)){
                    throw new Exception("权限不足");
                }
            }
        } else{
            boolean flag = false;
            for (String perm : perms) {
                if (permissions.contains(perm)) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                throw new Exception("权限不足");
            }
        }
    }
}
