package com.yadong.springbootproject_1.service.impl;


import com.yadong.springbootproject_1.dao.OrderDao;
import com.yadong.springbootproject_1.entity.Order;
import com.yadong.springbootproject_1.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDao orderDao;

    @Override
    public List<Order> getAllOrdersByUid(String uid) {
        return orderDao.getAllOrdersByUid(uid);
    }

    @Override
    public Order getOrderByOrderId(String orderId) {
        return orderDao.getOrderByOrderId(orderId);
    }

    @Override
    public int addOrder(Order order) {
        return orderDao.addOrder(order);
    }


}
