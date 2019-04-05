package com.qianfeng.test;

import com.qianfeng.xiaomi.user.service.UserService;
import com.qianfeng.xiaomi.user.service.impl.UserServiceImpl;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

public class TestMain {


    @Test
    public void test1() {
        UserService userService = new UserServiceImpl();
        int name = userService.checkUserName("张三");
        System.out.println(name);
    }

    @Test
    public void test2() {
        String s = "nishiyigeshazi";
        int chars = 3;
        int n = (s.length() + chars - 1) / chars;
        System.out.println(s.length());
        System.out.println(n);
    }

    @Test
    public void test3() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssSSS");
        String orderId = sdf.format(new Date());
        System.out.println(orderId);
        //  return orderId;
    }

    @Test
    public void test4() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(100);
        jedisPoolConfig.setMinIdle(10);
        jedisPoolConfig.setMaxTotal(1024);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "www.damengsheng.cn", 6379, 3000, "123456");
        Jedis jedis = jedisPool.getResource();
        Set<String> keys = jedis.keys("*");
        System.out.println(keys.size());

    }

    @Test
    public void test6() {
        int a = 1;
        Integer integer = new Integer(1);
        System.out.println(a == integer);
    }
}
