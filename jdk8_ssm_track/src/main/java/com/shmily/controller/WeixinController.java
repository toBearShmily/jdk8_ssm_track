package com.shmily.controller;

import com.shmily.support.weixin.AuthSupport;
import com.shmily.support.weixin.SecurityKit;
import com.shmily.support.weixin.TextMessage;
import com.shmily.support.weixin.WeiXin;
import com.shmily.util.weiXin.MessageUtil;
import org.dom4j.DocumentException;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/25.
 */

@Controller
public class WeixinController {
    private Logger log = LoggerFactory.getLogger(WeixinController.class);
    /**
     * 开发者通过检验signature对请求进行校验（下面有校验方式）。
     * 若确认此次GET请求来自微信服务器，请原样返回echostr参数内容，则接入生效，成为开发者成功，否则接入失败。
     * @param req
     * @param resp
     */
    @RequestMapping(value = "init", method = RequestMethod.GET)
    public void init(HttpServletRequest req , HttpServletResponse resp){
        log.info("验证服务器成功！！！");
        PrintWriter out = null;
        try{
            out = resp.getWriter();
        }catch(IOException e){
            log.error("获取输出流失败哦！！！");
        }

        /*signature	微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
        timestamp	时间戳
        nonce	随机数
        echostr 	随机字符串(供返回的)*/
        String signature = req.getParameter("signature");
        String timestamp = req.getParameter("timestamp");
        String nonce = req.getParameter("nonce");
        String echostr = req.getParameter("echostr");

        //将token、timestamp、nonce三个参数进行字典序排序
        String[] array = {timestamp,nonce, WeiXin.TOKEN};
        Arrays.sort(array);
        // 将三个参数字符串拼接成一个字符串
        StringBuffer sb = new StringBuffer();
        for(String arr : array){
            sb.append(arr);
        }
        //进行sha1加密
        String security = SecurityKit.sha1(sb.toString());
        if(signature.equals(security)){
            out.println(echostr);
        }
    }

    @RequestMapping(value = "init" , method = RequestMethod.POST)
    public void initEntrance(HttpServletRequest req , HttpServletResponse resp){
        try {
            req.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = null;
        try {
            out = resp.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String ,String> map = null;
        try {
            map = MessageUtil.xmlToMap(req);
            String ToUserName = map.get("ToUserName");
            String FromUserName = map.get("FromUserName");
            String MsgType = map.get("MsgType");
            String Content = map.get("Content");

            TextMessage textMessage = null;
            if("text".equals(MsgType)){
                textMessage = MessageUtil.messageResult(FromUserName,ToUserName,Content);
                if (Content.equals("1")) {
                    textMessage.setContent("你发的是1!!!");
                }else if (Content.equals("2")) {
                    textMessage.setContent("你发的是2!!!");
                }
            }else if("event".equals(MsgType)){
                if(map.get("Event").equals("subscribe")){
                    //关注事件
                    textMessage = MessageUtil.messageResult(FromUserName,ToUserName,Content);
                    textMessage.setContent("欢迎关注我的公众号~");
                }
            }
            String message = MessageUtil.textMessageToXml(textMessage);
            log.info("result message : {}",message);
            out.print(message);
            out.flush();
        } catch (DocumentException e) {
            log.error("接收消息异常，异常原因：{}",e.getMessage(),e);
        } finally {
            out.close();
        }

    }

    @RequestMapping("auth")
    public void auth(HttpServletResponse resp){
        try {
            String url = AuthSupport.oAuthCode();
            resp.sendRedirect(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("getCode")
    public void getCode(HttpServletRequest req, HttpServletResponse resp){
        String code = req.getParameter("code");
        AuthSupport.getCode(code, resp);

        AuthSupport.weiXinUserInfo();
    }

}
