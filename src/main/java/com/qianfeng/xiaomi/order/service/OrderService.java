package com.qianfeng.xiaomi.order.service;

import com.qianfeng.xiaomi.order.pojo.Order;
import com.qianfeng.xiaomi.order.pojo.OrderDetail;
import com.qianfeng.xiaomi.vo.OrderVo;
import com.qianfeng.xiaomi.vo.PageBean;

import java.util.List;

public interface OrderService {
    Order addOrder(double money, Integer aid, Integer uid);

    List<Order> findOrderByUid(Integer id);

    Order getOrderByOid(String oid);

    int findAidByOid(String oid);

    List<OrderDetail> findOrderDetailByOid(String oid);

    PageBean<OrderVo> findOrderByPageWhere(Integer pageNum, Integer pageSize, String username, String status);

    void updateStatus(String oid, String s);
}
