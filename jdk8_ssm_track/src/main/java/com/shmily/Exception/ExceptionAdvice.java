package com.shmily.Exception;

import com.shmily.common.Response;
import com.shmily.common.StateEnum;
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
        Response response = new Response().failure(StateEnum.JOSN_ERROR);
        ModelAndView mv = new ModelAndView("error_page");
        mv.addObject("response",response);
        return mv;
    }

    /**
     * 加入响应状态码后返回的json数据包括状态和其他信息，自己本身传递到前台的数据需要在ResponseText中获取
     * 500 - Internal Server Error
     */
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
        response.failure(StateEnum.ERROR,map);
        mv.addObject("response",response);
        logger.info("返回参数：",response);
        return mv;
    }

    @ExceptionHandler(BizExceotion.class)
    @ResponseBody
    public Response bizException(BizExceotion biz){

        return new Response();
    }
}
