package com.yadong.springbootproject_1.entity;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Alias("cart")
@Component
public class Cart {

    private String itemId;
    private String uid;
    private Integer itemCount = 1;
    private Date addTime;



    @Override
    public String toString() {
        return "Cart{" +
                "item_id='" + itemId + '\'' +
                ", uid='" + uid + '\'' +
                ", item_count='" + itemCount + '\'' +
                ", add_time='" + addTime + '\'' +
                '}';
    }

    public Cart(){}

    public Cart(String itemId, String uid, Integer itemCount, Date addTime) {
        this.itemId = itemId;
        this.uid = uid;
        this.itemCount = itemCount;
        this.addTime = addTime;
    }



    public String getItemId() {
        return itemId;
    }

    public void setItemId(String item_id) {
        this.itemId = item_id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Integer getItemCount() {
        return itemCount;
    }

    public void setItemCount(Integer item_count) {
        this.itemCount = item_count;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }




}
