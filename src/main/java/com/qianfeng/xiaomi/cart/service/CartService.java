package com.qianfeng.xiaomi.cart.service;

import com.qianfeng.xiaomi.cart.pojo.CartGoods;
import com.qianfeng.xiaomi.cart.pojo.CartUser;

import java.util.List;

public interface CartService {

    Integer getCartIdByUid(Integer id);

   // CartGoods findCartByIdAndGid(Integer id, Integer goodsId);

    void addCart(Integer id, Integer goodsId, Integer number);

    List<CartGoods> getCart(Integer id);

    void updateCart(Integer id, Integer goodsId, Integer number);

    void deleteCartByCidAndGid(Integer id, Integer goodsId);

    void clearCart();

    int addCartUid(CartUser cartUser);
}
