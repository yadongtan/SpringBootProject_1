package com.yadong.springbootproject_1.entity;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
@Alias("order")
public class Order {

    private String order_id;    //订单号
    private String userId; //外键(用户uid)
    private String itemId; //外键(商品itemId)
    private Integer count;  //数量
    private Integer singlePrice;    //单价
    private Integer totalPrice; //总价
    private Date orderTime; //下单时间

    public Order(){}

    public Order(String order_id, String userId, String itemId, Integer count, Integer singlePrice, Integer totalPrice, Date orderTime) {
        this.order_id = order_id;
        this.userId = userId;
        this.itemId = itemId;
        this.count = count;
        this.singlePrice = singlePrice;
        this.totalPrice = totalPrice;
        this.orderTime = orderTime;
    }




    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getSinglePrice() {
        return singlePrice;
    }

    public void setSinglePrice(Integer singlePrice) {
        this.singlePrice = singlePrice;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }


}
