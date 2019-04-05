package com.qianfeng.xiaomi.order.controller;

import com.qianfeng.xiaomi.address.pojo.Address;
import com.qianfeng.xiaomi.address.service.AddressService;
import com.qianfeng.xiaomi.cart.pojo.CartGoods;
import com.qianfeng.xiaomi.cart.service.CartService;
import com.qianfeng.xiaomi.goods.service.GoodsService;
import com.qianfeng.xiaomi.order.pojo.Order;
import com.qianfeng.xiaomi.order.pojo.OrderDetail;
import com.qianfeng.xiaomi.order.service.OrderService;
import com.qianfeng.xiaomi.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private CartService cartService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/getorderview")
    public String getOrderView(HttpServletRequest request,Model model) {
        User user = (User) request.getSession().getAttribute("user");

        if (user==null) {
            model.addAttribute("msg", "请登录");
            return "redirect:/login.jsp";
        }
        int id = cartService.getCartIdByUid(user.getId());
        List<CartGoods> cart = cartService.getCart(id);
        List<Address> addressList = addressService.findAddressByUid(user.getId());
        model.addAttribute("cart", cart);
        model.addAttribute("addList", addressList);
        return "/order";
    }

    /**
     * 添加订单
     * @param money
     * @param aid
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/addorder")
    public String addOrder(double money, Integer aid,HttpServletRequest request,Model model) {
        User user = (User) request.getSession().getAttribute("user");

        if (user==null) {
            model.addAttribute("msg", "请登录");
            return "redirect:/login.jsp";
        }

        Order order = orderService.addOrder(money, aid, user.getId());
        model.addAttribute("order", order);
        return "/orderSuccess";
    }

    /**
     * 获取订单列表
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/getorderlist")
    public String getOrderList(HttpServletRequest request,Model model) {
        User user = (User) request.getSession().getAttribute("user");

        if (user==null) {
            model.addAttribute("msg", "请登录");
            return "redirect:/login.jsp";
        }
        List<Order> orderList =  orderService.findOrderByUid(user.getId());

        model.addAttribute("orderList", orderList);
        return "/orderList";
    }

    /**
     * 获取订单详情
     * @param oid
     * @return
     */
    @RequestMapping("/getorderdetail")
    public String getOrderDetail(String oid,Model model) {
        //查询订单
        Order order = orderService.getOrderByOid(oid);
        //查询地址id
        int aid=  orderService.findAidByOid(oid);
        //查询地址
        Address address = addressService.findaddressByAid(aid);
        //查询商品详情
        List<OrderDetail> orderDetails = orderService.findOrderDetailByOid(oid);
        //查询商品
       // Goods goods = goodsService.findGoodsById(pid);

        model.addAttribute("order", order);
        model.addAttribute("address", address);
        model.addAttribute("orderDetail", orderDetails);
       // model.addAttribute("goods", goods);

        return "/orderDetail";
    }
}
