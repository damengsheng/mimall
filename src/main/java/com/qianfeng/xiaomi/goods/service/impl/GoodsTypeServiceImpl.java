package com.qianfeng.xiaomi.goods.service.impl;

import com.alibaba.fastjson.JSON;
import com.qianfeng.xiaomi.goods.pojo.GoodsType;
import com.qianfeng.xiaomi.goods.service.GoodsTypeService;
import com.qianfeng.xiaomi.mapper.GoodsTypeMapper;
import com.qianfeng.xiaomi.utils.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.List;

@Service
public class GoodsTypeServiceImpl implements GoodsTypeService {

    @Autowired
    private GoodsTypeMapper goodsTypeMapper;

    @Override
    public List<GoodsType> findGoodsTypeAll(RedisClient jedisClient) {
        Jedis jedis = jedisClient.getJedis();
        String goodstype = jedisClient.get("goodstype", jedis);
        if (goodstype != null) {
            System.out.println("走redis");
            List<GoodsType> list = JSON.parseObject(goodstype, List.class);
            System.out.println(list.get(1));
            return list;
        } else {
            System.out.println("走数据库");
            List<GoodsType> goodsTypeAll = goodsTypeMapper.findGoodsTypeAll();
            String json = JSON.toJSONString(goodsTypeAll);
            jedisClient.set("goodstype", json, jedis);
            return goodsTypeAll;
        }
    }

    @Override
    public List<GoodsType> findAll() {
        return goodsTypeMapper.findAll();
    }

    @Override
    public List<GoodsType> findByLevel(int level) {
        return goodsTypeMapper.findByLevel(level);
    }

    @Override
    public void add(GoodsType goodsType) {
        goodsTypeMapper.add(goodsType);
    }

}
