package com.shmily.util;

import redis.clients.jedis.Jedis;

/**
 * Created by Administrator on 2017/3/22.
 */
public enum redis_common {
    REDIS;

    private Jedis jedis=null;

    private redis_common(){

    }

    public Jedis getInstance(){
        return jedis;
    }

}
