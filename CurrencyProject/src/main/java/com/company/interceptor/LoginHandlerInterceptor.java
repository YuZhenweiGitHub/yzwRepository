package com.company.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.company.utils.Const;
import com.company.utils.PropertiesUtil;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yzw on 2017/5/22.
 */
public class LoginHandlerInterceptor extends HandlerInterceptorAdapter {

    private String redirectUrl = "/system/login.html";
    private String sessionKey = Const.SESSION_USER;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        response.addHeader("Access-Control-Allow-Origin","*");
        String url = PropertiesUtil.getProperty("redirectUrl");
        String key = PropertiesUtil.getProperty("sessionKey");
        String requestUri = request.getRequestURI();

        if(requestUri.matches(Const.NO_INTERCEPTOR_PATH)){
            return true;
        }
        redirectUrl = url == null ? redirectUrl : url;
        sessionKey = key == null ? sessionKey : key;
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse rep = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        if (session == null || session.getAttribute(sessionKey) == null) {
            //如果判断是 AJAX 请求,直接设置为session超时
            if (isAjax(request)) {
                rep.setContentType("text/html; charset=GBK");
                PrintWriter out = response.getWriter();
                JSONObject jo = new JSONObject();
                jo.put("result","timeout");
                jo.put("message","Session失效，请重新登录!");
                out.println(jo);
                out.flush();
                out.close();
                return false;
            } else {
                System.out.print("=============" + req.getContextPath());
                rep.sendRedirect(redirectUrl);
                return false;
            }
        }
        return true;
    }

    // 是否为ajax请求
    private boolean isAjax(ServletRequest request){
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        return "XMLHttpRequest".equalsIgnoreCase(httpServletRequest.getHeader("X-Requested-With"));
    }
}
