package com.yadong.springbootproject_1.entity;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
@Alias("order")
public class Order {

    private String orderId;    //订单号
    private String uid; //外键(用户uid)
    private String itemId; //外键(商品itemId)
    private Integer counts;  //数量
    private Double singlePrice;    //单价
    private Double totalPrice; //总价
    private Date orderTime; //下单时间

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer count) {
        this.counts = count;
    }

    public Double getSinglePrice() {
        return singlePrice;
    }

    public void setSinglePrice(Double singlePrice) {
        this.singlePrice = singlePrice;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Order(){}

    public Order(String orderId, String uid, String itemId, Integer counts, Double singlePrice, Double totalPrice, Date orderTime) {
        this.orderId = orderId;
        this.uid = uid;
        this.itemId = itemId;
        this.counts = counts;
        this.singlePrice = singlePrice;
        this.totalPrice = totalPrice;
        this.orderTime = orderTime;
    }
}
