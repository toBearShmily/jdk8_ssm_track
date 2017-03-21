<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/1/12
  Time: 下午 04:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    /*添加访问静态文件--wxb*/
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
    response.setHeader("P3P", "CP=CAO PSA OUR");
    request.setAttribute("ctx", basePath);
%>
<html>
<head>
    <title>FileUpload</title>

    <script src="${ctx}/resources/js/jquery.min.js?v=2.1.4"></script>
    <script src="${ctx}/resources/js/ajaxfileupload.js"></script>
</head>
<body>
<h3>采用 fileUpload_multipartFile ， file.transferTo 来保存上传的文件</h3>
<form name="form1" action="${ctx}/FileUpload/fileUpload_multipartFile" method="post" enctype="multipart/form-data">
    <input type="file" name="file_upload">
    <input type="submit" value="upload"/>
</form>
<hr>

<h3>采用 fileUpload_multipartRequest ， file.transferTo 来保存上传的文件</h3>
<form name="form2" action="${ctx}/FileUpload/fileUpload_multipartRequest" method="post" enctype="multipart/form-data">
    <input type="file" name="file_upload">
    <input type="submit" value="upload"/>
</form>
<hr>

<h3>采用 CommonsMultipartResolver ， file.transferTo 来保存上传的文件</h3>
<form name="form3" action="${ctx}/FileUpload/fileUpload_CommonsMultipartResolver" method="post" enctype="multipart/form-data">
    <input type="file" name="file_upload">
    <input type="submit" value="upload"/>
</form>

<h3>通过流的方式上传文件</h3>
<form name="form4" action="${ctx}/FileUpload/fileUpload_stream" method="post" enctype="multipart/form-data">
    <input type="file" name="file_upload">
    <input type="submit" value="upload"/>
</form>
<hr>

<h3>通过ajax插件 ajaxfileupload.js 来异步上传文件</h3>
<form name="form5" action="/" method="post" enctype="multipart/form-data">
    <input type="file" id="file_AjaxFile" name="file_AjaxFile">
    <input type="button" value="upload" onclick="fileUploadAjax()"/><span id="sp_AjaxFile"></span><br><br>
    上传进度：<span id="sp_fileUploadProgress">0%</span>
</form>

<script type="text/javascript">
    function fileUploadAjax() {
        if ($("#file_AjaxFile").val().length > 0) {
            progressInterval = setInterval(getProgress, 500);
            $.ajaxFileUpload({
                url: '${ctx}/FileUpload/fileUpload_ajax', //用于文件上传的服务器端请求地址
                type: "post",
                secureuri: false, //一般设置为false
                fileElementId: 'file_AjaxFile', //文件上传空间的id属性  <input type="file" id="file1" name="file" />
                dataType: 'application/json', //返回值类型 一般设置为json
                success: function (data)  //服务器成功响应处理函数
                {
                    var jsonObject = eval('(' + data + ')');
                    $("#sp_AjaxFile").html(" Upload Success ！ filePath:" + jsonObject.filePath);
                },
                error: function (data, status, e)//服务器响应失败处理函数
                {
                    alert(e);
                }
            });//end ajaxfile
        }
        else {
            alert("请选择文件!");
        }
    }
    var progressInterval = null;
    var i = 0;
    var getProgress = function () {
        $.get("${ctx}/FileUpload/fileUploadprogress",
                {},
                function (data) {
                    $("#sp_fileUploadProgress").html(i++ + data);
                    if (data == 100 || i == 100)
                        clearInterval(progressInterval);
                }
        );
    }
</script>
<hr>

<h3>多文件上传 采用 MultipartFile[] multipartFile 上传文件方法</h3>
<form name="form5" action="${ctx}/FileUpload/fileUpload_spring_list" method="post" enctype="multipart/form-data">
    <input type="file" name="file_upload">
    <input type="file" name="file_upload">
    <input type="file" name="file_upload">
    <input type="submit" value="upload"/>
</form>
<hr>
<h3>通过 a 标签的方式进行文件下载</h3><br>
<a href="${ctx}/file_manager/Download/ChMkJlfI162IeK07AAY_egKaTWoAAU6qQHhDUcABj-S342.jpg">通过 a 标签下载图片</a>
<hr>
<h3>通过 Response 文件流的方式下载文件</h3>
<a href="${ctx}/FileUpload/fileDownload_servlet">通过 文件流 的方式下载文件 mst.txt</a>
</body>
</html>
