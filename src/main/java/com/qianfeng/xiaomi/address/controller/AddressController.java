package com.qianfeng.xiaomi.address.controller;

import com.qianfeng.xiaomi.address.pojo.Address;
import com.qianfeng.xiaomi.address.service.AddressService;
import com.qianfeng.xiaomi.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    /**
     * 获取地址
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/getaddress")
    public String getAddress(HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("user");

        if (user==null) {
            model.addAttribute("msg", "请登录");
            return "redirect:/login.jsp";
        }

        List<Address> addresses = addressService.findAddressAll(user.getId());
        model.addAttribute("addList", addresses);
        return "/self_info";
    }

    /**
     * 设为默认地址
     * @param id
     * @return
     */
    @RequestMapping("/defaultaddress")
    public String defaultAddress(Integer id) {
        addressService.defaultAddress(id);
        return "forward:getaddress.action";
    }

    /**
     * 删除地址
     * @param id
     * @return
     */
    @RequestMapping("/deleteaddress")
    public String deleteAddressById(Integer id) {
        addressService.deleteAddressById(id);
        return "forward:getaddress.action";
    }

    /**
     * 添加地址
     * @param address
     * @param request
     * @param response
     * @param model
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/addaddress")
    public String addAddress(Address address, HttpServletRequest request, HttpServletResponse response, Model model) throws UnsupportedEncodingException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        if (address.getUid() == 0) {
            model.addAttribute("msg", "请登录");
            return "redirect:/login.jsp";
        }

        System.out.println(address);
        addressService.addAddress(address);
        return "forward:getaddress.action";
    }

    @RequestMapping("/updateaddress")
    public String updateAddress(Address address) {
        addressService.updateAddress(address);
        return "forward:getaddress.action";
    }

}
