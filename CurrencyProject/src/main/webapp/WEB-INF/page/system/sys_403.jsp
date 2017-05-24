<%--
  Created by IntelliJ IDEA.
  User: yzw
  Date: 2017/4/27
  Time: 17:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <title>403</title>
</head>
<body>
<div>
    <h4 class="smaller">检查一下可能性:</h4>
    <ul>
        <li>检查请求链接是不是有误</li>
        <li>检查处理类代码是不是有误</li>
        <li>检查环境配置是不是有误</li>
        <li>检查处理类视图映射路径</li>
    </ul>
</div>
</body>
</html>
