package com.company.resolver;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.company.utils.ResponseContextUtil;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 异常处理
 */
public class MyExceptionResolver implements HandlerExceptionResolver {

	protected Logger logger = Logger.getLogger(this.getClass());

	private final String errMsg = "系统出现异常，请联系管理员！";

	public ModelAndView resolveException(HttpServletRequest request,HttpServletResponse response, Object handler, Exception ex) {
		logger.error("==============异常开始=============");
		logger.info("=============="+ex.getMessage()+"=============");
		ex.printStackTrace();

		if (!(request.getHeader("accept").contains("application/json")  || (request.getHeader("X-Requested-With")!= null && request
				.getHeader("X-Requested-With").contains("XMLHttpRequest") ))) {
			// 普通请求
			ModelAndView mv = new ModelAndView("/page/sys_error");
			mv.addObject("exception", errMsg);
			logger.error("==============异常结束=============");
			return mv;
		} else {
			//ajax请求
			try {
				response.setCharacterEncoding("utf-8");
				response.setContentType("text/html; charset=utf-8");
				PrintWriter writer = response.getWriter();
				ResponseContextUtil res = new  ResponseContextUtil();
				res.setResult(-1);
				res.setMessage(errMsg);
				writer.write(JSON.toJSONString(res));
				logger.error("==============异常结束=============");
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
	}

}
