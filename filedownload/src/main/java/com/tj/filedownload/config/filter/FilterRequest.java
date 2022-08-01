package com.tj.filedownload.config.filter;

import com.tj.filedownload.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author peng
 * @createDate 2022/8/1 9:55
 */
@Service
public class FilterRequest extends OncePerRequestFilter implements Filter {
    @Autowired
    LoginService loginService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String user = request.getParameter("user");
        loginService.saveLoginInfo(user);
        request = new ContentCachingRequestWrapper(request);
        filterChain.doFilter(request, response);
    }
}