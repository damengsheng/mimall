package com.qianfeng.xiaomi.goods.controller;

import com.qianfeng.xiaomi.goods.pojo.GoodsType;
import com.qianfeng.xiaomi.goods.service.GoodsTypeService;
import com.qianfeng.xiaomi.utils.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/goodstype")
public class GoodsTypeController {

    @Autowired
    private GoodsTypeService goodsTypeService;
    @Autowired
    private RedisClient jedisClient;

    @RequestMapping("/getgoodstypelist")
    @ResponseBody
    public List<GoodsType> getGoodsTypeList() {
        List<GoodsType> goodsType = goodsTypeService.findGoodsTypeAll(jedisClient);
        return goodsType;
    }



}
