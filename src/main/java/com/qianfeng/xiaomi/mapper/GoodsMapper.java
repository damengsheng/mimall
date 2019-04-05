package com.qianfeng.xiaomi.mapper;

import com.qianfeng.xiaomi.goods.pojo.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsMapper {
    List<Goods> findGoodsByTypeId(Integer typeId);

    Goods findGoodsById(Integer id);

    List<Goods> searchGoods(String search);

    List<Goods> findByPageWhere(@Param("typeId") Integer typeid,@Param("name") String s,@Param("pubdate") String pubdate);

    void addGoods(Goods goods);
}
