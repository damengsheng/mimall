package com.qianfeng.xiaomi.mapper;

import com.qianfeng.xiaomi.user.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    void addUser(User user);

    int findUserByUsername(String username);

    User findUserByUsernameAndPassword(@Param("username") String username, @Param("password") String password,@Param("flag") int flag);

    int updatePassword(User user);

    int active(@Param("email") String email,@Param("code") String code);

    List<User> findAll(@Param("username") String username,@Param("gender") String gender);

    void remove(int id);
}
