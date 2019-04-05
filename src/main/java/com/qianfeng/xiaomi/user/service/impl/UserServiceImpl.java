package com.qianfeng.xiaomi.user.service.impl;

import com.qianfeng.xiaomi.mapper.UserMapper;
import com.qianfeng.xiaomi.user.pojo.User;
import com.qianfeng.xiaomi.user.service.UserService;
import com.qianfeng.xiaomi.utils.ActiveUtils;
import com.qianfeng.xiaomi.utils.EmailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public void addUser(User user) {
        user.setFlag(0);
        user.setRole(1);
        user.setCode(ActiveUtils.createActiveCode());
        userMapper.addUser(user);

        EmailUtils.sendEmail(user);
    }


    @Override
    public int checkUserName(String username) {
        int i = userMapper.findUserByUsername(username);
       // System.out.println(i);
        return i;
    }

    @Override
    public User login(String username, String password) {
        return userMapper.findUserByUsernameAndPassword(username, password,1);
    }

    @Override
    public void updatePassword(User user) {
        int num = userMapper.updatePassword(user);

        if (num == 0) {
            throw new RuntimeException("修改密码失败");
        }

    }

    @Override
    public boolean active(String email, String code) {
        int user = userMapper.active(email, code);
        if(user > 0) {
            return true;
        }
        return false;
    }

    @Override
    public User adminlogin(String username, String password) {
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        User user=userMapper.findUserByUsernameAndPassword(username, password, 2);
        if(user!=null) {
            if(user.getRole()!=0) {
                throw new RuntimeException("没有权限");
            }
        }else {
            throw new RuntimeException("用户名或密码错误");
        }

        return user;
    }

    @Override
    public List<User> findAll(String username, String gender) {
        return userMapper.findAll(username, gender);
    }

    @Override
    public void remove(int id) {
        userMapper.remove(id);
    }
}
