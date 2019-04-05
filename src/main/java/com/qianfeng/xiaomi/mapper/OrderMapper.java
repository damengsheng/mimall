package com.qianfeng.xiaomi.mapper;

import com.qianfeng.xiaomi.order.pojo.Order;
import com.qianfeng.xiaomi.order.pojo.OrderDetail;
import com.qianfeng.xiaomi.order.pojo.Order_Address;
import com.qianfeng.xiaomi.vo.OrderVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {
    void addOrder(Order order);

    void addOrderAndAddress(Order_Address order_address);

    void addOrderDetail(OrderDetail orderDetail);

    List<Order> findOrderByUid(Integer uid);

    int findAidByOid(String oid);

    Order findOrderByOid(String id);

    List<OrderDetail> findOrderDetailByOid(String oid);

    List<OrderVo> findOrderByPageWhere(@Param("username") String username, @Param("status") String status);

    void updateStatus(@Param("oid") String oid, @Param("status") String status);
}
