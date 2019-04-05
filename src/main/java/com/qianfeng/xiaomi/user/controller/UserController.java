package com.qianfeng.xiaomi.user.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.qianfeng.xiaomi.user.pojo.User;
import com.qianfeng.xiaomi.user.service.UserService;
import com.qianfeng.xiaomi.utils.Base64Utils;
import com.qianfeng.xiaomi.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private Producer producer;

    /**
     * 用户注册
     * @param user
     * @param repassword
     * @param model
     * @return
     */
    @RequestMapping("/register")
    public String register(User user, String repassword,Model model) {

        //判断密码是否为空
        if (!user.getPassword().equalsIgnoreCase(repassword)) {
            model.addAttribute("msg", "两次密码不一致");
            return "/register";
        }

        //判断用户是否存在
        int i = userService.checkUserName(user.getUsername());
        if (i==0) {
            user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
            userService.addUser(user);
            return "/registerSuccess";
        }
        model.addAttribute("msg", "用户已存在");
        return "/register";
    }

    /**
     * 验证用户名是否重复
     * @param username
     * @param request
     * @return
     */
    @RequestMapping("/checkusername")
    @ResponseBody
    public String checkUserName(String username, HttpServletRequest request) {
        System.out.println(request.getCharacterEncoding());
        System.out.println(username);
        int i = userService.checkUserName(username);
        System.out.println(i);
        return i + "";
    }

    /**
     * 登录
     * @param username
     * @param password
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/login")
    public String login(String username, String password, Model model,HttpServletRequest request) {
        //判断密码是否为空
        if (StringUtil.isEmpty(password)) {
            model.addAttribute("msg", "密码不能为空");
            return "/login";
        }

        User user = userService.login(username, DigestUtils.md5DigestAsHex(password.getBytes()));
        if (user != null) {
            request.getSession().setAttribute("user",user);
            return "/index";
        }
        model.addAttribute("msg", "用户名或密码错误");
        return "/login";
    }

    /**
     * 修改密码
     * @param password
     * @param newpassword
     * @param repassword
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/updatepassword")
    public String updatePassword(String password, String newpassword, String repassword, Model model, HttpServletRequest request) {
        // 验证密码不能为空
        if (StringUtil.isEmpty(password)) {
            model.addAttribute("msg", "密码不能为空");
            return "forward:/self_center.jsp";
        }

        if (StringUtil.isEmpty(newpassword)) {
            model.addAttribute("msg", "新密码不能为空");
            return "forward:/self_center.jsp";
        }

        if (!newpassword.equals(repassword)) {
            model.addAttribute("msg", "两次密码不一致");
            return "forward:/self_center.jsp";
        }

        User user = (User) request.getSession().getAttribute("user");

        if (user==null) {
            model.addAttribute("msg", "请登录");
            return "redirect:/login.jsp";
        }

        User userinfo = userService.login(user.getUsername(), DigestUtils.md5DigestAsHex(password.getBytes()));
        if (userinfo == null) {
            model.addAttribute("msg", "密码不正确");
            return "forward:/self_center.jsp";
        }
        userinfo.setPassword(DigestUtils.md5DigestAsHex(newpassword.getBytes()));

        try {
            userService.updatePassword(userinfo);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("msg", "修改密码失败");
            return "forward:/self_center.jsp";
        }

        return "redirect:/login.jsp";
    }

    /**
     * 验证码
     * @param req
     * @param rsp
     */
    @RequestMapping("/code")
    public void code(HttpServletRequest req, HttpServletResponse rsp) {
        HttpSession session = req.getSession();
        String code = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        System.out.println("验证码: " + code);

        rsp.setDateHeader("Expires", 0);
        rsp.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        rsp.addHeader("Cache-Control", "post-check=0, pre-check=0");
        rsp.setHeader("Pragma", "no-cache");
        rsp.setContentType("image/jpeg");

        String capText = producer.createText();
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);

        BufferedImage image = producer.createImage(capText);
        ServletOutputStream out = null;
        try {
            out = rsp.getOutputStream();
            ImageIO.write(image, "jpg", out);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @RequestMapping("/checkcode")
    @ResponseBody
    public String checkCode(String code, HttpServletRequest request) {
        //从session中取出servlet生成的验证码text值
        String expected = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        System.out.println(expected);
        //获取用户页面输入的验证码
        boolean bool = code != null && expected.equalsIgnoreCase(code);
        if (bool) {
            return "0";
        }
        return "1";
    }

    /**
     * 激活
     * @param e
     * @param c
     * @param model
     * @return
     */
    @RequestMapping("/activate")
    public String activate(String e, String c, Model model) {

        String email = Base64Utils.decode(e);
        String code = Base64Utils.decode(c);

        boolean bool = userService.active(email,code);
        if(bool) {
            model.addAttribute("msg", "激活成功");
        } else {
            model.addAttribute("msg", "激活失败");
        }

        return "forward:/message.jsp";
    }


}
