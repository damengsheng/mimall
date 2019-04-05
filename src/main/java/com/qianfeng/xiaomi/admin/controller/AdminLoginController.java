package com.qianfeng.xiaomi.admin.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.qianfeng.xiaomi.user.pojo.User;
import com.qianfeng.xiaomi.user.service.UserService;
import com.qianfeng.xiaomi.utils.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminLoginController {

    @Autowired
    private UserService userService;

    @RequestMapping("/adminLogin")
    public String adminLogin(String username, String password, Model model, HttpServletRequest request) {
        if(TextUtils.empty(username)) {
            model.addAttribute("msg", "用户名不能为空");
            return "/admin/login";
        }
        if(TextUtils.empty(password)) {
            model.addAttribute("msg", "密码不能为空");
            return "/admin/login";
        }

        try {
            User admin=userService.adminlogin(username, password);
            request.getSession().setAttribute("admin", admin);
            return "redirect:/admin/admin.jsp";

        }catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
        }
        return "/admin/login";
    }

    @RequestMapping("/getUserList")
    public void getUserList(String username, String gender, HttpServletResponse response) {
        System.out.println(gender);
        List<User> list = userService.findAll(username, gender);
        String json= JSON.toJSONString(list, SerializerFeature.WriteMapNullValue);
        try {
            response.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/deleteUser")
    public void deleteUser(String id) {
        if(TextUtils.empty(id)) {

            return;
        }

        int _id=Integer.parseInt(id);

        userService.remove(_id);
    }

}
