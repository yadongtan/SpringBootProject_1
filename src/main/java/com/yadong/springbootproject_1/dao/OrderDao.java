package com.yadong.springbootproject_1.dao;

import com.yadong.springbootproject_1.entity.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDao {

    List<Order> getAllOrdersByUid(String uid);
    Order getOrderByOrderId(String orderId);
    int addOrder(Order order);

}
