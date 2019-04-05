package com.qianfeng.xiaomi.goods.service;

import com.qianfeng.xiaomi.goods.pojo.Goods;
import com.qianfeng.xiaomi.vo.PageBean;

import java.util.List;

public interface GoodsService {

    PageBean findGoodsByTypeId(Integer typeId, Integer pageNum, Integer pageSize);

    Goods findGoodsById(Integer id);

    PageBean searchGoods(Integer pageSize, Integer pageNum, String search);

    PageBean findByPageWhere(Integer pageNum, Integer pageSize, Integer typeid, String s, String pubdate);

    void addGoods(Goods goods);
}
