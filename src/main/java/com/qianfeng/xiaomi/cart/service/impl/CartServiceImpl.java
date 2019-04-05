package com.qianfeng.xiaomi.cart.service.impl;

import com.qianfeng.xiaomi.cart.pojo.CartGoods;
import com.qianfeng.xiaomi.cart.pojo.CartUser;
import com.qianfeng.xiaomi.cart.service.CartService;
import com.qianfeng.xiaomi.goods.pojo.Goods;
import com.qianfeng.xiaomi.mapper.CartMapper;
import com.qianfeng.xiaomi.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public Integer getCartIdByUid(Integer uid) {
        return cartMapper.findCartIdByUid(uid);
    }



    @Override
    public void addCart(Integer id, Integer gId, Integer number) {
       //查询购物车中是否拥有此商品
        CartGoods cartGoods = cartMapper.findCartByIdAndGid(id, gId);

        //没有
        CartGoods cartGoods1 = new CartGoods(id,gId,number);
        if (cartGoods==null) {
            //直接添加

            cartMapper.addCart(cartGoods1);

        }else{
            //有，直接更新

            cartMapper.updateCartByCidAndGid(cartGoods1);
        }
    }

    @Override
    public List<CartGoods> getCart(Integer id) {

        //查询购物车表
        List<CartGoods> cartGoods =  cartMapper.findCartAll(id);

        for (CartGoods cartGood : cartGoods) {
            //根据商品id查询商品的价格
            Goods goods = goodsMapper.findGoodsById(cartGood.getGid());
            //添加商品属性
            cartGood.setGoods(goods);
        }
        return cartGoods;
    }

    @Override
    public void updateCart(Integer id, Integer goodsId, Integer number) {
        cartMapper.updateCartByCidAndGid(new CartGoods(id,goodsId,number));
    }

    @Override
    public void deleteCartByCidAndGid(Integer id, Integer goodsId) {
        cartMapper.deleteCartByCidAndGid(id, goodsId);
    }

    @Override
    public void clearCart() {
        cartMapper.deleteCartAll();
    }

    @Override
    public int addCartUid(CartUser cartUser) {
        cartMapper.addCartUid(cartUser);
        return cartUser.getId();
    }
}
