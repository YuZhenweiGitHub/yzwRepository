<%--
  Created by IntelliJ IDEA.
  User: yzw
  Date: 2017/5/17
  Time: 17:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<script>
    //jquery.LoadImage.js
    window.onload = function() {
        setTimeout(function() {
            // XHR to request a JS and a CSS
            var xhr = new XMLHttpRequest();
            xhr.open('GET', '');
            xhr.send('');
            xhr = new XMLHttpRequest();
            xhr.open('GET', '');
            xhr.send('');
            // preload image
            new Image().src = '';
        }, 1000);
    };
</script>
</body>
</html>
