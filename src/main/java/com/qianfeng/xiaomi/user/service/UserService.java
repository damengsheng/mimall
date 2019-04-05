package com.qianfeng.xiaomi.user.service;

import com.qianfeng.xiaomi.user.pojo.User;

import java.util.List;

public interface UserService {
    void addUser(User user);

    int checkUserName(String username);

    User login(String username, String password);

    void updatePassword(User user);

    boolean active(String email, String code);

    User adminlogin(String username, String password);

    List<User> findAll(String username, String gender);

    void remove(int id);
}
