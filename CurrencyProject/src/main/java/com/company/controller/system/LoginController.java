package com.company.controller.system;

import com.alibaba.fastjson.JSONObject;
import com.company.service.user.userinfo.UserInfoManager;
import com.company.utils.Const;
import com.company.utils.DateUtil;
import com.company.utils.PageData;
import com.company.utils.ResponseContextUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by yzw on 2017/5/23.
 */
@RequestMapping(value = "/login")
@Controller
public class LoginController extends BaseController{

    private Logger logger = Logger.getLogger(this.getClass());

    @Resource(name = "userInfoService")
    private UserInfoManager userInfoService;

    /**
     * 登录验证
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/vrifyLogin.json",method = RequestMethod.POST)
    public @ResponseBody
    ResponseContextUtil verifyLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {

        ResponseContextUtil responseContextUtil = new ResponseContextUtil();

        PageData data = this.getPageData();
        if (data.containsKey("userName")&&data.containsKey("passWord")){
            String userName = data.getString("userName");
            String passWord = data.getString("passWord");

            //登录验证用户名密码
            userInfoService.findVerifyLogin(data);

            //保存用户session信息
            HttpSession session = request.getSession();

            session.setAttribute("userName",userName);
            session.setAttribute("passWord",passWord);

            //更新最后一次登录时间
            String lastLoginTime = DateUtil.getTime();

            logger.info(">>>>>>>>>>>>>>>>>>>>"+lastLoginTime+":"+userName+" 登录系统<<<<<<<<<<<<<<<<<<<<");
            responseContextUtil.setResult(Const.API_RETURN_RESULT_SUCCESS);
            responseContextUtil.setMessage("登录成功！");
            JSONObject obj = new JSONObject();
            obj.put("locationUrl","/login/mainIndex.html");
            responseContextUtil.setData(obj);
        } else {
            responseContextUtil.setResult(Const.API_RETURN_RESULT_FAIL);
            responseContextUtil.setMessage("登录失败，请检查参数！");
        }
        return responseContextUtil;
    }

    /**
     * 退出系统
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/loginOut.html")
    public ModelAndView loginOut(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        if(session!=null){
            String userName = (String) session.getAttribute("userName");
            logger.info(">>>>>>>>>>>>>>>>>>>>"+DateUtil.getTime()+":"+userName+" 退出系统<<<<<<<<<<<<<<<<<<<<");
            session.invalidate();
        }
        return new ModelAndView("redirect:/system/login.html");
    }

    /**
     * 跳转首页
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/mainIndex.html")
    public ModelAndView JUMP_TO_MAIN_INDEX(HttpServletRequest request, HttpServletResponse response) throws Exception {

        return new ModelAndView("page/mainIndex/index");
    }

    /**
     * 桌面
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/myDesktop.html")
    public ModelAndView MY_DESKTOP(HttpServletRequest request, HttpServletResponse response) throws Exception {

        return new ModelAndView("page/mainIndex/empty_page");
    }
}
