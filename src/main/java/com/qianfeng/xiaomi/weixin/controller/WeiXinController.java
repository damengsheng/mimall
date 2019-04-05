package com.qianfeng.xiaomi.weixin.controller;

import com.alibaba.fastjson.JSON;
import com.qianfeng.xiaomi.order.service.OrderService;
import com.qianfeng.xiaomi.weixin.pojo.WeiXinResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class WeiXinController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/weixincallback")
    public String weixincallback(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("utf-8");
            //接受微信支付回调的参数
            String json=request.getParameter("result");
            //使用Fastjson
            WeiXinResult weixin= JSON.parseObject(json, WeiXinResult.class);

            if(weixin!=null) {
                String orderid=weixin.getResult().getOut_trade_no();
                String money=weixin.getResult().getCash_fee();
                //判断结果
                if(weixin.getResult().getResult_code().equals("SUCCESS")) {
                    if(weixin.getType()==0) {
                        System.out.println("微信返回0.........");
                        request.setAttribute("msg", "您的订单号为:"+orderid+",金额为:"+money+"已经支付成功,等待发货~~");

                    }else if(weixin.getType()==1) {
                        System.out.println("微信返回1........");
                        response.getWriter().write("success");
                    }
                    //处理订单状态
                    orderService.updateStatus(orderid, "2");
                }else {
                    System.out.println("支付失败");
                    request.setAttribute("msg", "支付失败");
                }
            }
            System.out.println("回调执行了..................");
            return "/message";
        } catch (Exception e) {
            e.printStackTrace();
            return "/index";
        }
    }
}
