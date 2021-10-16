package com.yadong.springbootproject_1.service;

import com.yadong.springbootproject_1.entity.Order;

import java.util.List;

public interface OrderService {

    List<Order> getAllOrdersByUid(String uid);
    Order getOrderByOrderId(String orderId);
    int addOrder(Order order);

}
