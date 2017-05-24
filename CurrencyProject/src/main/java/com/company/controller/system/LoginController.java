package com.company.controller.system;

import com.alibaba.fastjson.JSONObject;
import com.company.service.SysUserService;
import com.company.utils.Const;
import com.company.utils.ResponseContextUtil;
import com.sun.swing.internal.plaf.synth.resources.synth_sv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by yzw on 2017/5/23.
 */
@RequestMapping(value = "/login")
@Controller
public class LoginController {

    private Logger logger = Logger.getLogger(this.getClass().getName());

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

        String username = request.getParameter("userName");
        String password = request.getParameter("passWord");
        ResponseContextUtil responseContextUtil = new ResponseContextUtil();
        Map map = new HashMap();

        //sysUserService.findVerifyLogin(map);

        HttpSession session = request.getSession();

        session.setAttribute("userName",username);
        session.setAttribute("passWord",password);

        responseContextUtil.setResult(Const.API_RETURN_RESULT_SUCCESS);
        responseContextUtil.setMessage("登录成功！");
        JSONObject data = new JSONObject();
        data.put("locationUrl","/login/mainIndex.html");
        responseContextUtil.setData(data);
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
