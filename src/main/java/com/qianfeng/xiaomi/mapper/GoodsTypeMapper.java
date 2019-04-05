package com.qianfeng.xiaomi.mapper;

import com.qianfeng.xiaomi.goods.pojo.GoodsType;

import java.util.List;

public interface GoodsTypeMapper {
    List<GoodsType> findGoodsTypeAll();

    List<GoodsType> findAll();

    List<GoodsType> findByLevel(int level);

    void add(GoodsType goodsType);
}
