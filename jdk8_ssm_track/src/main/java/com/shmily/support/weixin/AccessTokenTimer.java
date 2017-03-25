package com.shmily.support.weixin;

import com.shmily.util.redis_common;
import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.Jedis;

/**
 * 方法一：access_token过期时间为7200秒，，获取并定时刷新
 * 方法二：存入redis，设置数据过期时间
 * 此处采用方法二暂时
 * Created by Administrator on 2017/3/25.
 */
public class AccessTokenTimer {
    private Jedis jedis = redis_common.REDIS.getInstance();

    public String  getAccessToken(){
        String Access_Token = null;

        if(StringUtils.isNotBlank(jedis.get("AccessToken"))){
            Access_Token = jedis.get("AccessToken");
        }else{
            Access_Token = WeiXin_httpUtil.getAccessToken(WeiXin.ACCESS_URL);
            jedis.setex("AccessToken",7000,Access_Token);
        }
        return Access_Token;
    }
}
