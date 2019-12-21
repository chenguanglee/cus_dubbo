package com.dubbo.protocol.register;

import com.alibaba.fastjson.JSONObject;

import com.dubbo.protocol.framework.Redis;
import com.dubbo.protocol.framework.URL;
import redis.clients.jedis.Jedis;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

/**
 * @author chenguangli
 * @date 2019/12/18 23:37
 */
public class RemoteMapRegister {

    private static Map<String, List<URL>> REGISTER = new HashMap<>();

    public static void registe(String interfaceName, URL url) {
        List<URL> list = Collections.singletonList(url);
        REGISTER.put(interfaceName, list);
        //saveFile();
        registeToRedis(interfaceName, url);
    }

    public static URL getUrlRandom(String interfaceName) {
//        Map<String, List<URL>> registerMap = getUrlFromFile();
//        List<URL> list = registerMap.get(interfaceName);
        List<URL> list = getUrlFromRedis(interfaceName);
        Random random = new Random(1995);
        int n = random.nextInt(list.size());
        return list.get(n);
    }


    public static void registeToRedis(String interfaceName, URL url) {
        Jedis jedis = Redis.getJedis();
        String value = JSONObject.toJSONString(url);
        jedis.sadd(interfaceName, value);
    }

    public static List<URL> getUrlFromRedis(String interfaceName) {
        Jedis jedis = Redis.getJedis();
        Set<String> smembers = jedis.smembers(interfaceName);
        List<URL> list = new ArrayList<>();
        for (String smember : smembers) {
            list.add(JSONObject.parseObject(smember, URL.class));
        }
        return list;
    }

    public static void saveFile() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("/serviceAddress.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(REGISTER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Map<String, List<URL>> getUrlFromFile() {
        try {
            FileInputStream fileInputStream = new FileInputStream("/serviceAddress.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            return (Map<String, List<URL>>) objectInputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
