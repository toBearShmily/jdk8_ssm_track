<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/1/9
  Time: 下午 04:00
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
    <title>Exception Info Page</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">
    <link rel="shortcut icon" href="${ctx}/resources/view/favicon.ico">
    <link href="${ctx}/resources/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="${ctx}/resources/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="${ctx}/resources/css/animate.min.css" rel="stylesheet">
    <link href="${ctx}/resources/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <script src="${ctx}/resources/js/jquery.min.js?v=2.1.4"></script>
    <script src="${ctx}/resources/js/bootstrap.min.js?v=3.3.6"></script>
    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
</head>
<body class="gray-bg">
<div class="middle-box text-center animated fadeInDown">
    <h1>500</h1>
    <h3 class="font-bold">服务器内部错误</h3>

    <div class="error-desc">
        服务器好像出错了...
        <br/>
        <span>请求地址</span>:<p class="path">${response.data.path}</p>
        <br/>
        <span>请求参数</span>:<p class="parms">${response.data.params}</p>
        <br/>
        <span>异常信息</span>:<p class="msg">${response.data.msg}</p>
        <br/>
        您可以返回主页看看
        <br/><a href="../resouces/view/index-2.html" class="btn btn-primary m-t">主页</a>
    </div>
</div>
</body>
</html>
