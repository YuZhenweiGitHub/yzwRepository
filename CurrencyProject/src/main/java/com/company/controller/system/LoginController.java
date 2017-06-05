package com.company.controller.system;

import com.alibaba.druid.sql.visitor.functions.If;
import com.alibaba.fastjson.JSONObject;
import com.company.service.user.userinfo.UserInfoManager;
import com.company.utils.*;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

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

            //验证标识
            Boolean loginFlag = false;

            //获取用户信息
            List<PageData> userInfos = userInfoService.findUserInfoByUserName(data);

            PageData userInfo = new PageData();
            if (userInfos != null && userInfos.size() > 0) {
                userInfo = userInfos.get(0);

                String userStatus = userInfo.getString("USER_STATUS_");
                //验证用户状态
                if (userStatus.equals("0")) {
                    String pwd = new SimpleHash("SHA-1",userName,passWord).toString();

                    if (pwd.equals(userInfo.getString("PASS_WORD_"))){
                        loginFlag = true;
                    }
                } else {
                    //用户状态异常返回对应消息提示
                    return this.checkUserStatus(userStatus);
                }
            }

            if (loginFlag) {
                //最近一次登录时间
                String lastLoginTime = DateUtil.getTime();
                //验证成功，保存session信息
                Session session = Jurisdiction.getSession();
                //获取真实登录IP
                String LAST_LOGIN_IP_ = InternetProtocol.getRemoteAddr(request);
                userInfo.put("LAST_LOGIN_IP_",LAST_LOGIN_IP_);
                userInfo.put("LAST_LOGIN_TIME_",lastLoginTime);
                //更新
                userInfoService.edit(userInfo);
                session.setAttribute("userInfo",userInfo);

                //shiro加入身份验证
                Subject subject = SecurityUtils.getSubject();
                UsernamePasswordToken token = new UsernamePasswordToken(userName,passWord);

                subject.login(token);

                logger.info(">>>>>>>>>>>>>>>>>>>>"+lastLoginTime+":"+userName+" 登录系统<<<<<<<<<<<<<<<<<<<<");
                responseContextUtil.setResult(Const.API_RETURN_RESULT_SUCCESS);
                responseContextUtil.setMessage("登录成功！");
                JSONObject obj = new JSONObject();
                obj.put("locationUrl","/login/mainIndex.html");
                responseContextUtil.setData(obj);
            }else{
                responseContextUtil.setResult(Const.API_RETURN_RESULT_FAIL);
                responseContextUtil.setMessage("登录失败：请检查用户名或密码！");
            }
        } else {
            responseContextUtil.setResult(Const.API_RETURN_RESULT_FAIL);
            responseContextUtil.setMessage("登录失败：请检查参数！");
        }
        return responseContextUtil;
    }

    /**
     * 返回用户状态
     * @param userStatus
     * @return
     */
    private ResponseContextUtil checkUserStatus(String userStatus) {
        ResponseContextUtil responseContextUtil = new ResponseContextUtil();
        String message = "";
        switch (userStatus) {
            case "1" :
                message = "登录失败：用户已冻结，如需激活请联系管理员！";
                break;
            case "2" :
                message = "登录失败：用户状态异常，请联系管理员！";
                break;
            case "3" :
                message = "登录失败：用户状态异常，请联系管理员！";
                break;
            default :
                message = "登录失败：用户状态异常，请联系管理员！";
                break;
        }
        responseContextUtil.setResult(Const.API_RETURN_RESULT_FAIL);
        responseContextUtil.setMessage(message);
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

        Session session = Jurisdiction.getSession();
        if(session!=null){
            PageData userInfo = (PageData) session.getAttribute("userInfo");
            String userName = userInfo.getString("userName");
            logger.info(">>>>>>>>>>>>>>>>>>>>"+DateUtil.getTime()+":"+userName+" 退出系统<<<<<<<<<<<<<<<<<<<<");
            session.removeAttribute("userInfo");
            //shiro销毁
            Subject subject = SecurityUtils.getSubject();
            subject.logout();
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

    /**
     * 用户注册
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/register.json")
    public @ResponseBody ResponseContextUtil register(HttpServletRequest request, HttpServletResponse response) {

        ResponseContextUtil responseContextUtil = new ResponseContextUtil();

        try {
            PageData userInfo = new PageData();
            userInfo.put("UU_ID_", UuidUtil.get32UUID());
            userInfo.put("REGISTER_TIME_", DateUtil.getTime());
            //获取真实登录IP
            String LAST_LOGIN_IP_ = InternetProtocol.getRemoteAddr(request);
            userInfo.put("LAST_LOGIN_IP_", LAST_LOGIN_IP_);
            userInfo.put("DELTE_FLAG_", Const.IS_DELETE);
            userInfo.put("USER_STATUS_", "0");

            userInfoService.save(userInfo);

            responseContextUtil.setResult(Const.API_RETURN_RESULT_SUCCESS);
            responseContextUtil.setMessage("注册成功！");
            JSONObject obj = new JSONObject();
            obj.put("locationUrl","/system/login.html");
            responseContextUtil.setData(obj);
        }catch (Exception e){
            responseContextUtil.setResult(Const.API_RETURN_RESULT_FAIL);
            responseContextUtil.setMessage("注册失败！");
        }
        return responseContextUtil;
    }
}
