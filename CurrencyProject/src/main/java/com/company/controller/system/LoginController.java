package com.company.controller.system;

import com.alibaba.fastjson.JSONObject;
import com.company.service.SysUserService;
import com.company.utils.Const;
import com.company.utils.PageData;
import com.company.utils.ResponseContextUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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

    @Autowired
    private SysUserService sysUserService;

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
            String username = data.getString("userName");
            String password = data.getString("passWord");

            //sysUserService.findVerifyLogin(data);

            HttpSession session = request.getSession();

            session.setAttribute("userName",username);
            session.setAttribute("passWord",password);

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
     * 跳转首页
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/mainIndex.html")
    public ModelAndView JUMP_TO_MAIN_INDEX(HttpServletRequest request, HttpServletResponse response) throws Exception {

        return new ModelAndView("/page/mainIndex/index");
    }
}
