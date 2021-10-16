package com.yadong.springbootproject_1.service;

import com.yadong.springbootproject_1.entity.Cart;

import java.sql.Date;
import java.util.List;

public interface CartService {

    List<Cart> getCartByUid(String uid);
    int addItemToCart(String itemId, Integer itemCount, String uid, Date addTime);
    int deleteItemFromCart(String itemId,String uid);
    int deleteAllItemsFromCart(String uid);
    int updateItemsToCart(String itemId,Integer count,String uid);

}
