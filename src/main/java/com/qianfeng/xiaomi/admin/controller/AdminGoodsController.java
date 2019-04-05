package com.qianfeng.xiaomi.admin.controller;

import com.qianfeng.xiaomi.goods.pojo.Goods;
import com.qianfeng.xiaomi.goods.service.GoodsService;
import com.qianfeng.xiaomi.utils.FastDfsUtils;
import com.qianfeng.xiaomi.utils.TextUtils;
import com.qianfeng.xiaomi.vo.PageBean;
import org.csource.common.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminGoodsController {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private FastDfsUtils fastDfsUtils;

    @RequestMapping("/getGoodsList")
    public String getGoodsList(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "8") Integer pageSize, @RequestParam(defaultValue = "1") Integer typeid, String name, String pubdate, Model model) {

        //PageBean<Goods> pageBean=goodsService.findByPage(pageNum, pageSize, typeid); "select * from tb_goods "+where
        PageBean<Goods> pageBean = goodsService.findByPageWhere(pageNum, pageSize, typeid, name, pubdate);
        System.out.println(pageBean.getData().size());
        model.addAttribute("pageBean", pageBean);
        model.addAttribute("typeid", typeid);
        model.addAttribute("name", name);
        model.addAttribute("pubdate", pubdate);
        return "/admin/showGoods";
    }

    @RequestMapping("/addGoods")
    public String addGoods(Goods goods, MultipartFile file) {
        try {
            String filename = file.getOriginalFilename();
            String ext = filename.substring(filename.lastIndexOf(".") + 1);
            String path = fastDfsUtils.fileUpLoad(file.getBytes(), ext);
            String picture = "http://119.23.184.152/" + path.replaceFirst("/","");
            System.out.println(picture);
            goods.setPicture(picture);
            goodsService.addGoods(goods);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/admin/getGoodsList.action";
    }
}
