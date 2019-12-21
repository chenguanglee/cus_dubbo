package com.dubbo.protocol.framework;

import redis.clients.jedis.Jedis;

/**
 * @author chenguangli
 * @date 2019/12/21 11:42
 */
public class Redis {

    private static Jedis jedis;

    static {
        jedis = new Jedis("localhost", 6379);

    }

    public static Jedis getJedis() {
        return jedis;
    }

    public static void main(String[] args) {
        Jedis jedis = getJedis();
        System.out.println(jedis.ping());

    }
}
