package com.company.interceptor;

import com.company.utils.PropertiesUtil;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by yzw on 2017/5/22.
 */
public class LoginHandlerInterceptor extends HandlerInterceptorAdapter {

    private String redirectUrl = "/system/login.html";
    private String sessionKey = "userName";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String url = PropertiesUtil.getProperty("redirectUrl");
        String key = PropertiesUtil.getProperty("sessionKey");
        redirectUrl = url == null ? redirectUrl : url;
        sessionKey = key == null ? sessionKey : key;

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse rep = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        if (session == null || session.getAttribute(sessionKey) == null) {
            //如果判断是 AJAX 请求,直接设置为session超时
            if (req.getHeader("x-requested-with") != null && req.getHeader("x-requested-with").equals("XMLHttpRequest")) {
                rep.setHeader("sessionstatus", "timeout");
                return false;
            } else {
                System.out.print("=============" + req.getContextPath());
                rep.sendRedirect(redirectUrl);
                return false;
            }
        }
        return true;
    }
}
