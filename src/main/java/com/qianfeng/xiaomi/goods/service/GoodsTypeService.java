package com.qianfeng.xiaomi.goods.service;

import com.qianfeng.xiaomi.goods.pojo.GoodsType;
import com.qianfeng.xiaomi.utils.RedisClient;

import java.util.List;

public interface GoodsTypeService {
    List<GoodsType> findGoodsTypeAll(RedisClient redisClient);

    List<GoodsType> findAll();

    List<GoodsType> findByLevel(int level);

    void add(GoodsType goodsType);
}
