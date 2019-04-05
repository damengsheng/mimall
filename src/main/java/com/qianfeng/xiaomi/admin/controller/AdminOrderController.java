package com.qianfeng.xiaomi.admin.controller;

import com.qianfeng.xiaomi.order.service.OrderService;
import com.qianfeng.xiaomi.vo.OrderVo;
import com.qianfeng.xiaomi.vo.PageBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminOrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/getAllOrder")
    public String getAllOrder(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "8") Integer pageSize, String username, String status, Model model) {

        try {
            PageBean<OrderVo> pageBean=orderService.findOrderByPageWhere(pageNum,pageSize,username,status);

            model.addAttribute("pageBean", pageBean);
            model.addAttribute("username", username);
            model.addAttribute("status", status);
            return "/admin/showAllOrder";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/admin/admin.jsp";
        }
    }

    @RequestMapping("/changeOrder")
    public String changeOrder(String oid) {
        orderService.updateStatus(oid, "3");
        return "redirect:/admin/getAllOrder.action";
    }


}
