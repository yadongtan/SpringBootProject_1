package com.yadong.springbootproject_1.entity;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Component;

@Component
@Alias("kill")
public class Kill {

    String itemId;  //外键(商品ItemId)
    Integer itemCount;//商品数量
    Double itemPrice;   //秒杀价格
    Long setTime; //这个秒杀商品加入系统的时间
    Long killTime;   //倒计时,以秒为单位
    boolean killAble = false;


    public Kill(String itemId, Integer itemCount, Double itemPrice, Long setTime, Long killTime, boolean killAble) {
        this.itemId = itemId;
        this.itemCount = itemCount;
        this.itemPrice = itemPrice;
        this.setTime = setTime;
        this.killTime = killTime;
        this.killAble = killAble;
    }

    public boolean isKillAble() {
        return killAble;
    }

    public void setKillAble(boolean killAble) {
        this.killAble = killAble;
    }

    public Kill(String itemId, Integer itemCount, Double itemPrice, Long setTime, Long killTime) {
        this.itemId = itemId;
        this.itemCount = itemCount;
        this.itemPrice = itemPrice;
        this.setTime = setTime;
        this.killTime = killTime;
    }

    public Long getSetTime() {
        return setTime;
    }

    public void setSetTime(Long setTime) {
        this.setTime = setTime;
    }


    public Long getKillTime() {
        return killTime;
    }

    public void setKillTime(Long killTime) {
        this.killTime = killTime;
    }

    public static final String KILL_ITEM_ID = ":itemId";
    public static final String KILL_ITEM_COUNT = ":itemCount";
    public static final String KILL_ITEM_PRICE = ":itemPrice";
    public static final String KILL_ABLE = ":killAble";
    public static final String KILL_SET_TIME = ":killSetTime";
    public static final String KILL_TIME = ":killTime";

    public String getKillSetTimeKey(){
        return itemId + KILL_SET_TIME;
    }
    public String getItemIdKey(){
        return  itemId + KILL_ITEM_ID;
    }
    public String getKillTimeKey(){
        return itemId + KILL_TIME;
    }
    public String getItemCountKey(){
        return  itemId + KILL_ITEM_COUNT;
    }

    public String getItemPriceKey(){
        return itemId + KILL_ITEM_PRICE ;
    }

    public String getKillAbleKey(){
        return itemId + KILL_ABLE ;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Integer getItemCount() {
        return itemCount;
    }

    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }

    public Double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
    }
    public Kill(){}

}
