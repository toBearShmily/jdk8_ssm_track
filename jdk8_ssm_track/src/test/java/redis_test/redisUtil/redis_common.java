package redis_test.redisUtil;

import redis.clients.jedis.Jedis;

/**
 * Created by Administrator on 2017/3/22.
 */
public enum  redis_common {
    REDIS;

    private Jedis jedis=null;

    private redis_common(){
        //连接redis服务器
        jedis = new Jedis("60.205.217.220",6379);
        //权限认证
        jedis.auth("wxb123");
    }

    public Jedis getInstance(){
        return jedis;
    }

}
