<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/1/5
  Time: 下午 09:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    /*添加访问静态文件--wxb*/
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
    response.setHeader("P3P", "CP=CAO PSA OUR");
    request.setAttribute("ctx", basePath);
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div>${allErrors}</div>
</body>
</html>
