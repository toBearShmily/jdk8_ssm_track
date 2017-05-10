package com.shmily.support.weixin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shmily.util.HttpUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by wuxubiao on 2017/5/8.
 */
public class AuthSupport {

    private static Logger log = LoggerFactory.getLogger(AuthSupport.class);

    public static String access_token = "";
    public static String openid = "";

    /**
     * 用户授权
     */
    public static String oAuthCode() throws UnsupportedEncodingException {
        /*PrintWriter out = null;
        try {
            out = resp.getWriter();
        }catch (IOException e){
            log.error("获取输出流失败",e);
        }*/
        String redirect_uri = java.net.URLEncoder.encode("http://wxb.tunnel.qydev.com/ssm/getCode","UTF-8");
        log.info("编码后的回调地址为：{}",redirect_uri);
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + WeiXin.APPID
                + "&redirect_uri=" + redirect_uri
                + "&response_type=code"
                + "&scope=snsapi_userinfo"
                + "&state=STATE#wechat_redirect";

        /*try {
            String result = HttpUtil.get(url);
        } catch (IOException e) {
            log.error("用户授权请求失败，失败原因:{}",e.getMessage(),e);
        }*/
        return url;
    }

    /**
     * 授权后获取code，换取access_token
     * @param resp
     */
    public static void getCode(String code, HttpServletResponse resp){
        String result = "";
        if(null == code){
            log.info("用户未同意授权!!!");
            return ;
        }
        //用户同意授权
        String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + WeiXin.APPID
                + "&secret=" + WeiXin.APPSECRET
                + "&code=" + code
                + "&grant_type=authorization_code";

        try {
            result = HttpUtil.get(requestUrl);
        }catch (IOException e){
            log.error("【getCode】微信接口请求异常，异常原因：{}",e.getMessage(),e);
        }

        JSONObject jsb = String2JsonObject(result);
        if(jsb.getString("errcode") != null){
            log.info("获取access_token失败，原因：{}",jsb.getString("errmsg"));
        }
        AuthSupport.access_token = jsb.getString("access_token");
        AuthSupport.openid = jsb.getString("openid");
    }

    /**
     * 获取用户信息
     */
    public static void weiXinUserInfo(){
        String requestUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=" + AuthSupport.access_token
                + "&openid=" + AuthSupport.openid
                + "&lang=zh_CN";

        String result = "";

        try{
            result = HttpUtil.get(requestUrl);
        } catch(IOException e){
            log.error("【weiXinUserInfo】微信获取用户信息失败{}",e.getMessage(),e);
        }

        //JSONObject jsb = String2JsonObject(result);
        WeiXinUser weiXinUser = JSONObject.parseObject(result,WeiXinUser.class);

        log.info("微信用户结果解析："+JSON.toJSONString(weiXinUser));

    }


    private static JSONObject String2JsonObject(String result){
        JSONObject jsb = null;
        if(StringUtils.isNotBlank(result)){
            jsb = JSON.parseObject(result);
        }else{
            log.info("返回数据为空!!!");
        }
        return jsb;
    }
}
