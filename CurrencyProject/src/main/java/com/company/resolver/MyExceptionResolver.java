package com.company.resolver;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 异常处理
 */
public class MyExceptionResolver implements HandlerExceptionResolver {

	protected Logger logger = Logger.getLogger(this.getClass());

	public ModelAndView resolveException(HttpServletRequest request,HttpServletResponse response, Object handler, Exception ex) {
		logger.error("==============异常开始=============");
		logger.info("=============="+ex.getMessage()+"=============");
		ex.printStackTrace();
		logger.error("==============异常结束=============");
		ModelAndView mv = new ModelAndView("/page/sys_error");
		mv.addObject("exception", ex.toString().replaceAll("\n", "<br/>"));
		return mv;
	}

}
