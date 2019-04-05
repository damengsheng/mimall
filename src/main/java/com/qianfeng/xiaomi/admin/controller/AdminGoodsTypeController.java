package com.qianfeng.xiaomi.admin.controller;

import com.alibaba.fastjson.JSON;
import com.qianfeng.xiaomi.goods.pojo.GoodsType;
import com.qianfeng.xiaomi.goods.service.GoodsTypeService;
import com.qianfeng.xiaomi.utils.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminGoodsTypeController {

    @Autowired
    private GoodsTypeService goodsTypeService;

    @RequestMapping("/getGoodsType")
    public String getGoodType(Model model) {
        List<GoodsType> goodsTypeAll = goodsTypeService.findAll();
        model.addAttribute("goodsTypeList", goodsTypeAll);
        return "/admin/showGoodsType";
    }

    @RequestMapping("/addGoodsType")
    public String addGoodsType(Integer goodsTypeLevel1, Integer goodsTypeLevel2, String typename) {

        if(TextUtils.empty(typename)) {
            return "redirect:/admin/getGoodsType.action";
        }

        System.out.println(goodsTypeLevel1+"...."+goodsTypeLevel2+"...."+typename);

        GoodsType goodsType=new GoodsType();
        goodsType.setName(typename);

        if(goodsTypeLevel1!=-1) {//添加二级类别
            goodsType.setParent(goodsTypeLevel1);
            goodsType.setLevel(2);
        }
        if(goodsTypeLevel2!=-1) {//添加三级类别
            goodsType.setParent(goodsTypeLevel2);
            goodsType.setLevel(3);
        }
        if(goodsTypeLevel1==-1&&goodsTypeLevel2==-1) {//添加一级
            goodsType.setParent(0);
            goodsType.setLevel(1);
        }

        goodsTypeService.add(goodsType);

        return "redirect:/admin/getGoodsType.action";
    }

    @RequestMapping("/getGoodsTypeJson")
    public void getGoodsTypeJson(String level, Model model, HttpServletResponse response) {
        try {
        int _level=0;
        if(!TextUtils.empty(level)) {
            _level=Integer.parseInt(level);
        }

        List<GoodsType> list=null;
        if(_level==0) {
            list=goodsTypeService.findAll();
        }else {
            list=goodsTypeService.findByLevel(_level);
        }
        String json= JSON.toJSONString(list);
        response.setContentType("application/json;charset=utf-8");

            response.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
