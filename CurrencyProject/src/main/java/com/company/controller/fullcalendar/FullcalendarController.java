package com.company.controller.fullcalendar;

import com.company.controller.system.BaseController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by YZW on 2017/11/28.
 */
@Controller
@RequestMapping(value = "/fullcalendar")
public class FullcalendarController extends BaseController {

    private Logger logger = Logger.getLogger(this.getClass());

    @RequestMapping(value = "schedulePlan.html")
    public ModelAndView JUMP_TO_SCHEDULE(HttpServletRequest request, HttpServletResponse response){

        logger.info(">>>>>>>>>>>>>>>>>>>>跳转在线排班页面<<<<<<<<<<<<<<<<<<<<");

        return new ModelAndView("page/fullcalendar/locales");
    }
}
