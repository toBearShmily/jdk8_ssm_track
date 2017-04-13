package com.shmily.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shmily.common.Constants;
import com.shmily.common.Response;
import com.shmily.common.ValidateJson;
import com.shmily.model.User;
import com.shmily.service.UserService;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Enumeration;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/28.
 */
@RestController
public class UserController {
    private static Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "toLogin" ,method = RequestMethod.GET)
    public ModelAndView toLogin(@RequestParam("test") String test){
        System.out.println("接收参数："+test);
        ModelAndView mv = new ModelAndView("login");
        
        return mv;
    }

    @RequestMapping(value="/toUser/{id}",method = RequestMethod.GET)
    public Response toUser(@PathVariable("id") int id) throws Exception {
        Response response = new Response();
        User user =  userService.getById(id);
        //list.stream().collect(Collectors.toList()).forEach(System.out::println);
        response.success(user);
        return response;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Response save(@Valid User user ,BindingResult bindingResult,HttpServletRequest req){
        Response response = new Response();
        System.out.println(req.getParameter("nickName"));
        Map<String,String> map = ValidateJson.getJsonString(bindingResult);
        if(null != map){//校验参数失败
            response.failure(map);
            log.error("参数校验失败,失败信息："+map.get("info"));
        }else{
            int result = userService.saveUser(user);
            String result_66 = "";
            if(result >= 0){
                result_66 = "注册成功！！！";
                log.info(JSON.toJSONString(response.success(result_66)));
                response.success(result_66);
            }else{
                result_66 = "注册失败！！！";
                response.success(result_66);
                log.info("注册失败，保存数据库异常!!!");
            }
        }
        return response;
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    private Response login(@RequestParam("nickName") String nickName,@RequestParam("pad") String psd){
        HttpServletRequest request = null;
        Enumeration e = request.getAttributeNames();

       /* for(;;e.hasMoreElements()){
            System.out.println(e.nextElement());
        }*/
        Response response = new Response();
        boolean isCommont = false;
        if(StringUtils.isNotBlank(nickName) && StringUtils.isNotBlank(psd)){
            int result = userService.selectByNameAndPsd(nickName,psd);
            if(result >= 1)
                isCommont=true;
            response.success(isCommont);
        }else{
            response.failure("参数验证不通过！！！");
        }
        return response;
    }
}

