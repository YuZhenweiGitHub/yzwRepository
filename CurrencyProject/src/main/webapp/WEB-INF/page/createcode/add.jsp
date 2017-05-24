<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>">

<script src="static/js/jquery-1.7.2.js"></script>

</head>
<body>
<form action="createCode/add.do" method="post" name="loginForm" id="loginForm">
		上级包名：<input name="packageName" id="packageName" value="" type="text">说明：（package com.fh.controller.<font color="red">system</font>;）中system名称<br>
		类名称：<input name="objectName" id="objectName" value="" type="text">说明：（<font color="red">SysMenu</font>Controller）中SysMenu<br>
		数据库表名全称：<input name="tablename" id="tablename" value="" type="text">说明：itsm_sys_user<br>
		数据库表前缀：<input name="tabletop" id="tabletop" value="" type="text">说明：itsm_sys_user中的itsm<br>
		类说明：<input name="TITLE" id="TITLE" value="" type="text">说明：（类说明文字）<br>
		
		<input onclick="severCheck();" value="生成" type="button">
</form>	
<script>
function severCheck(){
	if(check()){
		
		$("#loginForm").submit();
	}
}


//客户端校验
function check() {

	if ($("#packageName").val() == "") {

		alert("模块名称不得为空!");
		$("#packageName").focus();
		return false;
	} 

	if ($("#objectName").val() == "") {

		alert("类名称不得为空!");
		$("#objectName").focus();
		return false;
	}

	if ($("#tablename").val() == "") {

		alert("数据库表名全称不得为空!");
		$("#tablename").focus();
		return false;
	}
	
	if ($("#tabletop").val() == "") {

		alert("数据库表名前缀不得为空!");
		$("#tabletop").focus();
		return false;
	}

	if ($("#TITLE").val() == "") {

		alert("类说明不得为空!");
		$("#TITLE").focus();
		return false;
	}

	return true;
}

</script>			
</body>
</html>