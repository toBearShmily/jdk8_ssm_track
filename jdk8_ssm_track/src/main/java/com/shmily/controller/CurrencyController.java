package com.shmily.controller;

import com.shmily.common.Response;
import com.shmily.util.Files_Helper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;

/**
 * 通用功能
 * Created by Administrator on 2017/1/10.
 */
@Controller
@RequestMapping("FileUpload")
public class CurrencyController {

    @RequestMapping("toUpload")
    public String toUpload(){
        return "FileUpload";
    }

    /**
     * 采用 fileUpload_multipartFile ， file.transferTo 来保存上传的文件
     *
     * @param req
     * @param multipartFile
     * @return
     */
    @RequestMapping(value = "fileUpload_multipartFile")
    @ResponseBody
    public Response fileUpload_multipartFile(HttpServletRequest req, @RequestParam("file_upload") MultipartFile multipartFile) {
        //调用保存文件的帮助类进行保存文件，并返回文件的相对路径
        String filePath = Files_Helper.fileUpload_transferTo_spring(req, multipartFile, "/file_manager/Upload");
        //return "{'TFMark':'true','Msg':'upload success !','filePath':'" + filePath + "'}";
        Response response = new Response();
        response.success(filePath);
        return response;
    }

    /**
     * 方式二
     * 采用 fileUpload_multipartRequest file.transferTo 来保存上传文件
     * 参数不写 MultipartFile multipartFile 在request请求里面直接转成multipartRequest，从multipartRequest中获取到文件流
     */
    @RequestMapping(value = "fileUpload_multipartRequest")
    @ResponseBody
    public String fileUpload_multipartRequest(HttpServletRequest request) {
        //将request转成MultipartHttpServletRequest
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        //页面控件的文件流，对应页面控件 input file_upload
        MultipartFile multipartFile = multipartRequest.getFile("file_upload");
        //调用保存文件的帮助类进行保存文件，并返回文件的相对路径
        String filePath = Files_Helper.fileUpload_transferTo_spring(request, multipartFile, "/file_manager/Upload");
        return "{'TFMark':'true','Msg':'upload success !','filePath':'" + filePath + "'}";
    }

    /**
     * 方式三
     * 采用 CommonsMultipartResolver file.transferTo 来保存上传文件
     * 自动扫描全部的input表单
     */
    @RequestMapping(value = "fileUpload_CommonsMultipartResolver")
    @ResponseBody
    public String fileUpload_CommonsMultipartResolver(HttpServletRequest request) {
        //将当前上下文初始化给  CommonsMultipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        //检查form中是否有enctype="multipart/form-data"
        if (multipartResolver.isMultipart(request)) {
            //将request变成多部分request
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            //获取multiRequest 中所有的文件名
            Iterator iter = multipartRequest.getFileNames();
            while (iter.hasNext()) {
                //一次遍历所有文件
                MultipartFile multipartFile = multipartRequest.getFile(iter.next().toString());
                //调用保存文件的帮助类进行保存文件，并返回文件的相对路径
                String fileName = Files_Helper.fileUpload_transferTo_spring(request, multipartFile, "/file_manager/Upload");
                System.out.println(fileName);
            }
        }
        return "upload success ! ";
    }

    /**
     * 方式四
     * 通过流的方式上传文件
     */
    @RequestMapping("fileUpload_stream")
    @ResponseBody
    public String upFile(HttpServletRequest request, @RequestParam("file_upload") MultipartFile multipartFile) {
        String filePath = Files_Helper.fileUpload_stream(request, multipartFile, "/file_manager/Upload");
        return "{'TFMark':'true','Msg':'upload success !','filePath':'" + filePath + "'}";
    }

    /**
     * 方式五
     * 采用 fileUpload_ajax ， file.transferTo 来保存上传的文件 异步
     */
    @RequestMapping(value = "fileUpload_ajax", method = RequestMethod.POST)
    public @ResponseBody Response fileUpload_ajax(HttpServletRequest request, @RequestParam("upload_file") MultipartFile multipartFile) {
        Response response = new Response();
        //调用保存文件的帮助类进行保存文件，并返回文件的相对路径
        String filePath = Files_Helper.fileUpload_transferTo_spring(request, multipartFile, "/file_manager/Upload");
        //return "{'TFMark':'true','Msg':'upload success !','filePath':'" + filePath + "'}";
        response.success(filePath);
        return response;
    }

    /**
     * 多文件上传
     * 采用 MultipartFile[] multipartFile 上传文件方法
     */
    @RequestMapping(value = "fileUpload_spring_list")
    @ResponseBody
    public String fileUpload_spring_list(HttpServletRequest request, @RequestParam("file_upload") MultipartFile[] multipartFile) {
        //判断file数组不能为空并且长度大于0
        if (multipartFile != null && multipartFile.length > 0) {
            //循环获取file数组中得文件
            try {
                for (int i = 0; i < multipartFile.length; i++) {
                    MultipartFile file = multipartFile[i];
                    //保存文件
                    String fileName = Files_Helper.fileUpload_transferTo_spring(request, file, "/file_manager/Upload");
                    System.out.println(fileName);
                }
                return "{'TFMark':'true','Msg':'upload success !'}";
            } catch (Exception ee) {
                return "{'TFMark':'false','Msg':'参数传递有误！'}";
            }
        }
        return "{'TFMark':'false','Msg':'参数传递有误！'}";
    }

    /**
     * 文件下载
     *
     * @param response
     */
    @RequestMapping(value = "fileDownload_servlet")
    public void fileDownload_servlet(HttpServletRequest request, HttpServletResponse response) {
        Files_Helper.FilesDownload_stream(request, response, "/file_manager/Download/ChMkJlfI162IeK07AAY_egKaTWoAAU6qQHhDUcABj-S342.jpg");
    }
}
