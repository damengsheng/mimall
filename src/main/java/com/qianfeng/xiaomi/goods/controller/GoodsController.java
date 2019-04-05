package com.qianfeng.xiaomi.goods.controller;


import com.qianfeng.xiaomi.goods.pojo.Goods;
import com.qianfeng.xiaomi.goods.service.GoodsService;
import com.qianfeng.xiaomi.vo.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    /**
     * 获取商品列表分页
     * @param typeId
     * @param pageNum
     * @param pageSize
     * @param model
     * @return
     */
    @RequestMapping("/getgoodslistbytypeid")
    public String getGoodsListByTypeId(Integer typeId, Integer pageNum, Integer pageSize, Model model) {
        pageNum = pageNum == null ? 1 : pageNum;
        pageSize = pageSize == null ? 8 : pageSize;
        PageBean pageBean = goodsService.findGoodsByTypeId(typeId, pageNum, pageSize);
        model.addAttribute("typeId", typeId);
        model.addAttribute("pageBean", pageBean);
        return "/goodsList";
    }

    @RequestMapping("/getgoodsbyid")
    public String getGoodsById(Integer id,Model model) {
        Goods goods = goodsService.findGoodsById(id);
        model.addAttribute("goods", goods);
        return "/goodsDetail";
    }

    @RequestMapping("/searchgoods")
    public String getSearchGoods(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "8") Integer pageSize, String search, Model model) {
        search = "%" + search + "%";
        PageBean pageBean = goodsService.searchGoods(pageNum, pageSize, search);
        model.addAttribute("pageBean", pageBean);
        return "/goodsList";
    }
}
