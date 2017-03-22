package redis_test.string;

import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.Jedis;
import redis_test.strategyAndFactory.factory.dataType;

import java.util.List;
import java.util.PrimitiveIterator;

/**
 * 参数类型为string
 * Created by Administrator on 2017/3/22.
 */
public class typeForString extends dataType {
    private Jedis jedis;
    public typeForString(Jedis jedis){
        this.jedis = jedis;
    }
    /**
     * 存储string类型数据
     * @param key
     * @param value
     * @return
     */
    public boolean set(String key , String value){
        String result = jedis.set(key,value);
        return result.equalsIgnoreCase("OK")? true : false;
    }

    /**
     * 根据key获取value
     * @param key
     * @return
     */
    public String get(String key){
        return jedis.get(key);
    }

    /**
     * 修改当前key的值
     * @param key
     * @param value
     * @return
     */
    public boolean setValue(String key,String value){
        String result = jedis.getSet(key,value);
        System.out.println("返还的原值为："+result);
        return StringUtils.isNotBlank(result)? true : false;
    }

    /**
     * 返回字符串长度
     * @param key
     * @return
     */
    public long length(String key){
        long result = jedis.strlen(key);
        return result>=0 ? result : -1;
    }

    /**
     * 设置value，并设置过期时间
     * @param key
     * @param seconds
     * @param value
     * @return
     */
    public boolean setAndTime(String key ,int seconds ,String value){
        String result = jedis.setex(key,seconds,value);
        return result.equalsIgnoreCase("OK")? true : false;
    }

    /**
     * 判断存入的值是否存在，存在则不做操作返回false，反之true
     * @param key
     * @param value
     * @return
     */
    public boolean setExist(String key ,String value){
        //已存在则返回0.不存在返回1并添加值
        long result = jedis.setnx(key , value);
        return result==1 ? true : false;
    }

    /**
     * 根据keys返回values
     * @param keys
     * @return
     */
    public List<String> getValues(String[] keys){
        List<String> list = jedis.mget(keys);
        return list;
    }


    public Jedis getJedis() {
        return jedis;
    }

    public void setJedis(Jedis jedis) {
        this.jedis = jedis;
    }
}
