package com.company.controller;

import com.company.service.TestService;
import com.company.utils.Const;
import com.company.utils.ResponseContextUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yzw on 2017/4/25.
 */
@Controller
@RequestMapping(value = "/Test")
public class TestController {

    protected Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private TestService testService;

    @RequestMapping(value = "returnSuccess.html")
    public ModelAndView returnSuccess() throws Exception{
        ModelAndView model = new ModelAndView();
        model.setViewName("/page/success");
        model.addObject("context", "恭喜，中奖50000万！！");
        return model;
    }

    @RequestMapping(value = "returnString.json",method = RequestMethod.POST)
    @ResponseBody
    public ResponseContextUtil returnString() {
        ResponseContextUtil response = new ResponseContextUtil();
        try {
            Map map = new HashMap();
            List<Object>  objs = testService.queryEntity(map);
            response.setData(objs);
            response.setResult(Const.API_RETURN_RESULT_SUCCESS);
            response.setMessage("查询成功！");
        } catch (Exception e) {
            e.printStackTrace();
            response.setResult(Const.API_RETURN_RESULT_FAIL);
            response.setMessage("查询失败！");
        }
        return response;
    }

}
