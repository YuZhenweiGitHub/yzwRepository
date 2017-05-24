package com.company.controller.system;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.logging.Logger;

/**
 * Created by yzw on 2017/5/8.
 */
@Controller
@RequestMapping(value = "/system")
public class SystemCommonController {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    /**
     * 跳转登录页
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "login.html")
    public ModelAndView JUMP_TO_LOGIN(HttpServletRequest request, HttpServletResponse response){

        logger.info(">>>>>>>>>>>>>>>>>>>>跳转登录页面<<<<<<<<<<<<<<<<<<<<");

        return new ModelAndView("/page/system/login");
    }

    /**
     * 跳转注册页
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "register.html")
    public ModelAndView JUMP_TO_REGISTER(HttpServletRequest request, HttpServletResponse response){

        logger.info(">>>>>>>>>>>>>>>>>>>>跳转注册页面<<<<<<<<<<<<<<<<<<<<");

        return new ModelAndView("/page/system/register");
    }
}
