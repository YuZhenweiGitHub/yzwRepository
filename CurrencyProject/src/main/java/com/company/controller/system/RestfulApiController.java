package com.company.controller.system;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.company.entity.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by YZW on 2017/10/10.
 */
@Controller
@RequestMapping(value="/event")
public class RestfulApiController {

    /*@RequestMapping(value="/order/{orderKey}",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public @ResponseBody ResponseEntity<?> getString(@PathVariable("orderKey") String orderKey){
	    // 查询结果obj
        return new ResponseEntity<Object>(obj,HttpStatus.OK.value());
    }

    @RequestMapping(value = "/updateEmp", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<?> updateEmployee(HttpServletRequest request) {
        JSONObject params = new JSONObject();
        try{
            params = JSON.parseObject(request.getParameter("jsonParams"));
        } catch (JSONException e) {
            return new ResponseEntity<String>("参数格式错误，请检查是否为标准json格式.", HttpStatus.BAD_REQUEST.value());
        }
        if(params.containsKey("token")) {
            return new ResponseEntity<String>("token not found.", HttpStatus.NOT_FOUND.value());
        }
        // 检查token
        ResponseEntity<String> checkResult = checkToken(params.getString("token"));
        if(checkResult.getStatusCode()!=HttpStatus.OK.value()) {
            return checkResult;
        }

        return new ResponseEntity<String>("更新成功", HttpStatus.OK.value());
    }*/

    @ExceptionHandler
    public @ResponseBody ResponseEntity<String> exceptionHandler(Exception e){
        e.printStackTrace();
        return new ResponseEntity<String>("An internal error occurred while processing your request.", HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}
