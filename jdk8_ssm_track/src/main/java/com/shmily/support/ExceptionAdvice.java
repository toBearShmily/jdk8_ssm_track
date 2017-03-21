package com.shmily.support;

import com.shmily.common.Response;
import com.shmily.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ValidationException;
import java.io.IOException;
import java.net.ConnectException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 使用AOP技术，编写一个全局的异常处理切面类，用它来统一处理所有的异常行为
 * @ResponseBody注解表示返回值可序列化为JSON字符串
 * Created by Administrator on 2017/1/6.
 */
@ControllerAdvice
public class ExceptionAdvice {
    private Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);
    private String error_page = "error";
    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ModelAndView handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        logger.error("参数解析失败", e);
        Response response = new Response().failure("could_not_read_json");
        ModelAndView mv = new ModelAndView("error_page");
        mv.addObject("response",response);
        return mv;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public Response handleValidationException(ValidationException e) {
        logger.error("参数验证失败", e);
        return new Response().failure("validation-参数验证失败");
    }

    /**
     * 405 - Method Not Allowed
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ModelAndView handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        logger.error("不支持当前请求方法", e);
        Response response = new Response().failure("request_method_not_supported");
        ModelAndView mv = new ModelAndView("error_page");
        mv.addObject("response",response);
        return mv;
    }

    /**
     * 415 - Unsupported Media Type
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseBody
    public Response handleHttpMediaTypeNotSupportedException(Exception e) {
        logger.error("不支持当前媒体类型", e);
        return new Response().failure("content_type_not_supported");
    }

    /**
     * 加入响应状态码后返回的json数据包括状态和其他信息，自己本身传递到前台的数据需要在ResponseText中获取
     * 500 - Internal Server Error
     */
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    //@ResponseBody
    public ModelAndView handleException(HttpServletRequest req, Exception e) {
        ModelAndView mv = new ModelAndView(error_page);
        Map<String,Object> map = new HashMap<>();
        map.put("tip", "此错误说明调用接口失败，失败原因见msg，如果msg为空，联系后台");
        map.put("msg", e.getMessage());
        map.put("path", req.getRequestURI());
        map.put("params", req.getParameterMap());
        map.put("status", "0");
        logger.error("服务运行异常", e);
        Response response = new Response();
        response.failure(map);
        mv.addObject("response",response);
        logger.info("返回参数：",response);
        return mv;
    }

    /*
     * 记录Ajax异常日志，并将错误Ajax错误信息传递(回写)给前台展示,
     * 前台的jQuery的Ajax请求error中，可以打印错误提示信息   --  data.responseText   : 这里即是后台传递的错误提示
     * eg:
     * $.ajax({
     *           type : 'get',
     *          dataType : "json",
     *          url : ctx + '/test/test',
     *          accept:"application/json",
     *          success : function(data) {
     *              console.log(data);
     *          },
     *          error : function(data, errorThrown) {
     *              console.log(data);
     *              alert("error" + data.responseText);
     *          }
     *      });
     */
    /*@ExceptionHandler(AjaxException.class)
    public void operateExpAjax(AjaxException ex, HttpServletResponse response) throws IOException {
        logger.error(ex.getMessage(), ex);
        logger.info("************* ------ 异常信息已记录（" + DateUtils.getDateTime(new Date())+ "） ------- ***********");
        //将Ajax异常信息回写到前台，用于页面的提示
        response.getWriter().write("sorry,Ajax请求出错！！！");
    }*/


    @ExceptionHandler(ConnectException.class)
    @ResponseBody
    public Response operateExpNetException(ConnectException ex, HttpServletResponse response) throws IOException {
        logger.error(ex.getMessage(), ex);
        logger.info("************* ------ 异常信息已记录（" + DateUtils.getDateTime(new Date())+ "） ------- ***********");
        //将Ajax异常信息回写到前台，用于页面的提示
        return new Response().failure("sorry,网络连接出错！！！");
    }
}
