package com.qianfeng.xiaomi.order.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qianfeng.xiaomi.address.pojo.Address;
import com.qianfeng.xiaomi.address.service.AddressService;
import com.qianfeng.xiaomi.cart.pojo.CartGoods;
import com.qianfeng.xiaomi.cart.service.CartService;
import com.qianfeng.xiaomi.goods.service.GoodsService;
import com.qianfeng.xiaomi.mapper.OrderMapper;
import com.qianfeng.xiaomi.order.pojo.Order;
import com.qianfeng.xiaomi.order.pojo.OrderDetail;
import com.qianfeng.xiaomi.order.pojo.Order_Address;
import com.qianfeng.xiaomi.order.service.OrderService;
import com.qianfeng.xiaomi.utils.OrderIdUtil;
import com.qianfeng.xiaomi.vo.OrderVo;
import com.qianfeng.xiaomi.vo.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private CartService cartService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private GoodsService goodsService;

    @Override
    public Order addOrder(double money, Integer aid, Integer uid) {
        String  oid = OrderIdUtil.getOrderId();
        Order order = new Order(oid, uid, money, "1", new Date());
        //添加订单表
        orderMapper.addOrder(order);
        //添加订单与地址表
        orderMapper.addOrderAndAddress(new Order_Address(oid,aid));
        //添加订单详情表
        //查询用户的购物车
        int cid = cartService.getCartIdByUid(uid);
        //查询购物车中的数据
        List<CartGoods> cart = cartService.getCart(cid);
        //添加订单详情
        for (CartGoods cartGoods : cart) {
            OrderDetail orderDetail = new OrderDetail(0, oid, cartGoods.getGid(), cartGoods.getNum(), cartGoods.getMoney());
            orderMapper.addOrderDetail(orderDetail);
        }
        //清空购物车
        cartService.clearCart();
        return order;
    }

    /**
     * 获取订单列表
     * @param id
     * @return
     */
    @Override
    public List<Order> findOrderByUid(Integer id) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        //根据用户id查询订单
        List<Order> orderList =  orderMapper.findOrderByUid(id);
        //查询地址id
        for (Order order : orderList) {



            String oid = order.getId();

        //    System.out.println(oid);

            int aid = orderMapper.findAidByOid(oid);
            //根据aid查询地址
            Address address =  addressService.findaddressByAid(aid);
            //传值
            order.setAddress(address.getDetail());
        }
        return orderList;
    }

    @Override
    public Order getOrderByOid(String oid) {
        return orderMapper.findOrderByOid(oid);
    }

    @Override
    public int findAidByOid(String oid) {
        return orderMapper.findAidByOid(oid);
    }

    @Override
    public List<OrderDetail> findOrderDetailByOid(String oid) {
        List<OrderDetail> orderDetails = orderMapper.findOrderDetailByOid(oid);
//        for (OrderDetail orderDetail : orderDetails) {
//            Goods goods = goodsService.findGoodsById(orderDetail.getPid());
//            orderDetail.setGoods(goods);
//        }
        return orderDetails;
    }

    @Override
    public PageBean<OrderVo> findOrderByPageWhere(Integer pageNum, Integer pageSize, String username, String status) {
        PageHelper.startPage(pageNum, pageSize);
        List<OrderVo> list = orderMapper.findOrderByPageWhere(username, status);
        PageInfo<OrderVo> pageInfo = new PageInfo<>(list);
        PageBean<OrderVo> pageBean=new PageBean<>(pageNum, pageSize, pageInfo.getTotal());
        pageBean.setData(list);
        return pageBean;
    }

    @Override
    public void updateStatus(String oid, String s) {
        orderMapper.updateStatus(oid,s);
    }
}
