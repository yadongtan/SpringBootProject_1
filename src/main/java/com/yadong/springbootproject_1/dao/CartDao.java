package com.yadong.springbootproject_1.dao;

import com.yadong.springbootproject_1.entity.Cart;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface CartDao {

    List<Cart> getCartByUid(String uid);
    int addItemToCart(String itemId, Integer itemCount, String uid, Date addTime);
    int deleteItemFromCart(String itemId,String uid);
    int deleteAllItemsFromCart(String uid);
    int updateItemsToCart(String itemId,Integer itemCount,String uid);

}
