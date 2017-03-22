package redis_test.redisUtil;

import redis.clients.jedis.Jedis;

/**
 * Created by Administrator on 2017/3/20.
 */
public class jedis_connection_redis {

    public static void main(String[] args) {
        ss();
    }
    public static void ss(){
        //连接redis服务器
        Jedis jedis = new Jedis("60.205.217.220",6379);
        //权限认证
        jedis.auth("wxb123");

        String result = jedis.get("1");
        System.out.println("返回值为："+result);

        jedis.append("1"," 666");
        System.out.println("追加后："+jedis.get("1"));
        jedis.close();

    }

}
