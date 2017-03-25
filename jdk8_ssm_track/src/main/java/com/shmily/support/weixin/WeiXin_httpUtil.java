package com.shmily.support.weixin;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import jdk.nashorn.internal.runtime.JSONFunctions;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by Administrator on 2017/3/25.
 */
public class WeiXin_httpUtil {

    public static String getAccessToken(String url){
        String context = null;
        try {
            //获得client
            CloseableHttpClient client = HttpClients.createDefault();

            //通过get方式请求链接
            HttpGet get = new HttpGet(url);

            //执行请求
            CloseableHttpResponse resp = client.execute(get);

            //判断返回状态
            int status = resp.getStatusLine().getStatusCode();
            if( status >= 200 && status < 300){
                //entity为请求链接返回内容
                HttpEntity entity = resp.getEntity();
                //内容转换为字符串
                context = EntityUtils.toString(entity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(StringUtils.isNotBlank(context)){
            //根据返回结果转换为javaBean
            AccessToken accessToken = JSON.toJavaObject( JSON.parseObject(context),AccessToken.class);
            System.out.println("获取的access_Token为："+accessToken.getAccess_token());
            return accessToken.getAccess_token();
        }
        return "";
    }

}
