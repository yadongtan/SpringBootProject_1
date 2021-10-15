package com.yadong.springbootproject_1.entity;

import com.yadong.springbootproject_1.util.StringUtils;
import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Component;

@Alias("item")
@Component
public class Item {

    private String ownerId; //拥有者id
    private String itemId; //商品id
    private String  itemName;  //商品名称
    private Integer itemTotal;  //商品数量
    private Integer itemPurchases = 0;  //已被购买次数
    private Integer itemPrice;  //商品价格
    private String itemKind;    //商品类型
    private String itemInfo = "该商品无简介";    //商品信息
    private String itemImgPath = "img/item/item_img_default.png"; //商品图片路径

    @Override
    public String toString() {
        return "Item{" +
                "ownerId='" + ownerId + '\'' +
                ", itemId='" + itemId + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemTotal=" + itemTotal +
                ", itemPurchases=" + itemPurchases +
                ", itemPrice=" + itemPrice +
                ", itemKind='" + itemKind + '\'' +
                ", itemInfo='" + itemInfo + '\'' +
                ", itemImgPath='" + itemImgPath + '\'' +
                '}';
    }


    public Item(String ownerId, String itemId, String itemName, Integer itemTotal, Integer itemPurchases, Integer itemPrice, String itemKind, String itemInfo, String itemImgPath) {
        this.ownerId = ownerId;
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemTotal = itemTotal;
        this.itemPurchases = itemPurchases;
        this.itemPrice = itemPrice;
        this.itemKind = itemKind;
        this.itemInfo = itemInfo;
        this.itemImgPath = itemImgPath;
    }



    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getItemTotal() {
        return itemTotal;
    }

    public void setItemTotal(Integer itemTotal) {
        this.itemTotal = itemTotal;
    }

    public Integer getItemPurchases() {
        return itemPurchases;
    }

    public void setItemPurchases(Integer itemPurchases) {
        this.itemPurchases = itemPurchases;
    }

    public Integer getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Integer itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemKind() {
        return itemKind;
    }

    public void setItemKind(String itemKind) {
        this.itemKind = itemKind;
    }

    public String getItemInfo() {
        return itemInfo;
    }

    public void setItemInfo(String itemInfo) {
        this.itemInfo = itemInfo;
    }

    public String getItemImgPath() {
        return itemImgPath;
    }

    public void setItemImgPath(String itemImgPath) {
        this.itemImgPath = itemImgPath;
    }

    public Item(){}

    public static boolean canWrite(Item item){
        return StringUtils.noNullStringInList(
                item.ownerId,item.itemName,item.itemPrice.toString(),item.itemKind
        );
    }
}
