package com.qianfeng.xiaomi.mapper;

import com.qianfeng.xiaomi.cart.pojo.CartGoods;
import com.qianfeng.xiaomi.cart.pojo.CartUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartMapper {

    Integer findCartIdByUid(Integer uid);

    CartGoods findCartByIdAndGid(@Param("cid") Integer cid, @Param("gid") Integer gid);

    void addCart(CartGoods cartGoods);

    void updateCartByCidAndGid(CartGoods cartGoods);

    List<CartGoods> findCartAll(Integer cid);

    void deleteCartByCidAndGid(@Param("cid") Integer cid, @Param("gid") Integer gid);

    void deleteCartAll();

    Integer addCartUid(CartUser cartUser);
}
