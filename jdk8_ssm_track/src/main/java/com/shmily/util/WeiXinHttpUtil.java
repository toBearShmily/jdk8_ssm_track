package com.shmily.util;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by wuxubiao on 2017/5/8.
 */
public class WeiXinHttpUtil {

    public static String sendGet(String url){
        String result = "";

        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpGet post = new HttpGet(url);

        try {
            CloseableHttpResponse response = httpClient.execute(post);

            HttpEntity httpEntity = response.getEntity();

            result = EntityUtils.toString(httpEntity);

            System.out.println("返回结果:"+JSON.toJSONString(result));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
