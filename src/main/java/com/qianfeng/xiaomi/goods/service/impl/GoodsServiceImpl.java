package com.qianfeng.xiaomi.goods.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qianfeng.xiaomi.goods.pojo.Goods;
import com.qianfeng.xiaomi.goods.service.GoodsService;
import com.qianfeng.xiaomi.mapper.GoodsMapper;
import com.qianfeng.xiaomi.vo.PageBean;
import org.omg.PortableServer.LIFESPAN_POLICY_ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    public PageBean findGoodsByTypeId(Integer typeId,Integer pageNum,Integer pageSize) {
        //开始分页
        PageHelper.startPage(pageNum,pageSize);

        List<Goods> goodsList= goodsMapper.findGoodsByTypeId(typeId);

        //存放数据
        PageInfo<Goods> pageInfo = new PageInfo<>(goodsList);
        PageBean pageBean = new PageBean(pageNum, pageSize, pageInfo.getTotal());
        pageBean.setData(goodsList);

        return pageBean;
    }
    @Override

    public Goods findGoodsById(Integer id) {
        return goodsMapper.findGoodsById(id);
    }

    @Override
    public PageBean searchGoods(Integer pageNum, Integer pageSize, String search) {
        PageHelper.startPage(pageNum, pageSize);
        List<Goods> goods = goodsMapper.searchGoods(search);
        PageInfo<Goods> pageInfo = new PageInfo<>(goods);

        PageBean pageBean = new PageBean(pageNum, pageSize, pageInfo.getTotal());
        pageBean.setData(goods);
        return pageBean;
    }

    @Override
    public PageBean findByPageWhere(Integer pageNum, Integer pageSize, Integer typeid, String s, String pubdate) {
        PageHelper.startPage(pageNum, pageSize);

        List<Goods> list = goodsMapper.findByPageWhere(typeid, s, pubdate);
        PageInfo<Goods> pageInfo = new PageInfo<>(list);
        PageBean pageBean = new PageBean(pageNum, pageSize, pageInfo.getTotal());
        pageBean.setData(list);
        return pageBean;
    }

    @Override
    public void addGoods(Goods goods) {
        goodsMapper.addGoods(goods);
    }
}
