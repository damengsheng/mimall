package com.qianfeng.xiaomi.cart.controller;

import com.qianfeng.xiaomi.cart.pojo.CartGoods;
import com.qianfeng.xiaomi.cart.pojo.CartUser;
import com.qianfeng.xiaomi.cart.service.CartService;
import com.qianfeng.xiaomi.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    public User isUser(HttpServletRequest request,Model model) {
        //判断是否登录
        User user = (User) request.getSession().getAttribute("user");
        return user;
    }

    /**
     * 添加购物车
     * @param goodsId
     * @param number
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/addcart")
    public String addCart(Integer goodsId,Integer number, HttpServletRequest request, Model model) {

        User user = isUser(request,model);

        if (user==null) {
            model.addAttribute("msg", "请登录");
            return "redirect:/login.jsp";
        }
        //获取用户的购物车
        Integer id  = cartService.getCartIdByUid(user.getId());
        if (id == null) {
            id = cartService.addCartUid(new CartUser(0, user.getId()));
        }
        //添加购物车
        cartService.addCart(id, goodsId,number);
        //CartGoods cartGoods = cartService.findCartByIdAndGid(id, goodsId);

        return "/cartSuccess";
    }

    /**
     * 获得购物车
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/getcart")
    public String getCart(Model model,HttpServletRequest request) {
        User user = isUser(request,model);

        if (user==null) {
            model.addAttribute("msg", "请登录");
            return "redirect:/login.jsp";
        }
        //获取用户的购物车
        Integer id  = cartService.getCartIdByUid(user.getId());

        List<CartGoods> cartGoods = null;
        if (id != null) {
            cartGoods =  cartService.getCart(id);
        }

        model.addAttribute("cart", cartGoods);
        return "/cart";
    }

    /**
     * 购物车操作
     * @param goodsId
     * @param number
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/addcartajax")
    @ResponseBody
    public String addCartAjax(Integer goodsId,Integer number, HttpServletRequest request, Model model) {
        User user = isUser(request,model);

        if (user==null) {
            model.addAttribute("msg", "请登录");
            return "redirect:/login.jsp";
        }

        System.out.println("成功执行此方法：==========》"+goodsId + "====" + number);

        int id  = cartService.getCartIdByUid(user.getId());

        if (number==0) {
            cartService.deleteCartByCidAndGid(id, goodsId);
        } else  {
            cartService.updateCart(id, goodsId, number);
        }

//        return "forward:getcart.action";
        return "";
    }

    /**
     * 清空购物车
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/clearcart")
    @ResponseBody
    public String clearCart(HttpServletRequest request, Model model){
        User user = isUser(request,model);

        if (user==null) {
            model.addAttribute("msg", "请登录");
            return "redirect:/login.jsp";
        }

        cartService.clearCart();

        return null;
    }
}


