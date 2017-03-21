<%@page pageEncoding="utf-8" %>
<%
    /*添加访问静态文件--wxb*/
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
    response.setHeader("P3P", "CP=CAO PSA OUR");
    request.setAttribute("ctx", basePath);
%>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>H+ 后台主题UI框架 - 404 页面</title>
    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">
    <%--<link rel="shortcut icon" href="favicon.ico"> <link href="css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">--%>
    <%--<link href="css/animate.min.css" rel="stylesheet">--%>
    <%--<link href="css/style.min862f.css?v=4.1.0" rel="stylesheet">--%>
    <script src="//cdn.bootcss.com/jquery/2.0.2/jquery.js"></script>
    <script>
        $(function () {
            $.ajax({
                url: '${ctx}/toUser/1',
                type: "GET",
                dataType: "text",
                success: function (data) {
                    console.log("dqwdqd:" + data);
                    var context = "";
                    context += JSON.stringify(data);
                    $('.two').html(context)
                    $('.two').show();
                    $('.sub').show();
                },
                error: function (dat) {
                    console.log(dat);
                    var errorText = JSON.parse(dat);
                    alert("系统异常" + errorText.data.msg);
                }
            })

            $('#sub').click(function () {
                $('fom').submit();
            })
        });

    </script>
</head>
<body>
<h2>Hello World!</h2>
<input type="text" value="1" class="aa"/>
<div class="two" style="width: 200px;height: 200px;background-color: #6fd1bd; display: none;"></div>
<div class="sub" style="width: 200px;height: 200px;background-color: #6fd1bd; display: none;">
    <form name="fom" id="fom" method="post" action="${ctx}/save">
        <input name="nickName" id="nickName" type="text" value=""/>
        <input name="password" id="password" type="text" value=""/>
        <input name="roleId" id="roleId" type="text" value=""/>
        <input name="sex" id="sex" type="text" value=""/>
        <input type="submit" id="sub"/>
    </form>
</div>
<div><a href="">文件操作!!!</a></div>
</body>
</html>
