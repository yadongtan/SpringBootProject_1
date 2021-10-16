package com.yadong.springbootproject_1.service.impl;

import com.yadong.springbootproject_1.dao.CartDao;
import com.yadong.springbootproject_1.entity.Cart;
import com.yadong.springbootproject_1.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartDao cartDao;

    @Override
    public List<Cart> getCartByUid(String uid) {
        return cartDao.getCartByUid(uid);
    }

    @Override
    public int addItemToCart(String itemId, Integer itemCount, String uid, Date addTime) {
        return cartDao.addItemToCart(itemId,itemCount,uid,addTime);
    }

    @Override
    public int deleteItemFromCart(String itemId, String uid) {
        return cartDao.deleteItemFromCart(itemId,uid);
    }

    @Override
    public int deleteAllItemsFromCart(String uid) {
        return cartDao.deleteAllItemsFromCart(uid);
    }

    @Override
    public int updateItemsToCart(String itemId, Integer count, String uid) {
        return cartDao.updateItemsToCart(itemId,count,uid);
    }
}
