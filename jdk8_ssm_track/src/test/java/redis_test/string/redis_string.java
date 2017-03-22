package redis_test.string;

import com.alibaba.fastjson.JSON;
import redis.clients.jedis.Jedis;
import redis_test.redisUtil.redis_common;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * redis存储string类型数据
 * Created by Administrator on 2017/3/22.
 */
public class redis_string {

    public static void main(String[] args) {
        new redis_string().test();
    }


    public void test(){

        Jedis jedis = redis_common.REDIS.getInstance();
       /* String aa = jedis.set("2","xmh");
        System.out.println(aa);
        jedis.set("3","wzn");
        jedis.set("4","100");
        String bb = jedis.getSet("3","00");
        System.out.println(bb+"  bb-----------"+jedis.get("3"));


        System.out.println(jedis.get("1")+"---------"+jedis.get("2")+"-----------"+jedis.get("3"));

        long result = jedis.decr("4");
        System.out.println("decr操作："+result+"------------------------"+jedis.decr("5")+"-------"+jedis.incr("5"));*/
       /* String result = jedis.setex("5",100,"outTime");
        System.out.println(result);*/
        /*long aa = jedis.setnx("4","new");
        System.out.println(aa+"-------------------"+jedis.get("4"));*/
        /*String[] array = new String[]{"1","2","3","4","7"};
        List<String> list = jedis.mget(array);
        for (String s : list){
            System.out.println(s);
        }*/
        /*long aa = jedis.del("4");
        System.out.println(aa+"------------"+jedis.get("4"));*/

        /*jedis.hset("686","94","666");
        System.out.println(jedis.hget("686","94"));*/

        /*Map<String ,String> map = jedis.hgetAll("wxb");
        System.out.println(JSON.toJSONString(map));
        for (Map.Entry<String,String> entry : map.entrySet()){
            System.out.println(entry.getKey()+"="+entry.getValue());
        }*/
        /*Iterator iterator = map.keySet().iterator();
        while (iterator.hasNext()){
            String result = (String)iterator.next();
            System.out.println(result+"="+map.get(result));
        }*/

        //Set<String> set = jedis.keys("*");
       /* for(int i = 0; i<10 ; i++){
            System.out.println(jedis.randomKey());
        }*/

        //System.out.println(set);

        String result = jedis.type("3");
        System.out.println(result);
    }
}
