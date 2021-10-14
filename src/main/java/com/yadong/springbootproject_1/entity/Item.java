package com.yadong.springbootproject_1.entity;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Component;

@Component
@Alias("item")
public class Item {

    private String item_id; //商品id
    private String  item_name;  //商品名称
    private Integer total;  //商品数量
    private Integer purchases;  //已被购买次数
    private Integer price;  //商品价格
    private String kind;    //商品类型
    private String info;    //商品信息
    private String itemImgPath; //商品图片路径

    public Item(){}

    public Item(String item_id, String item_name, Integer total, Integer purchases, Integer price, String kind, String info, String itemImgPath) {
        this.item_id = item_id;
        this.item_name = item_name;
        this.total = total;
        this.purchases = purchases;
        this.price = price;
        this.kind = kind;
        this.info = info;
        this.itemImgPath = itemImgPath;
    }

    @Override
    public String toString() {
        return "Item{" +
                "item_id='" + item_id + '\'' +
                ", item_name='" + item_name + '\'' +
                ", total=" + total +
                ", purchases=" + purchases +
                ", price=" + price +
                ", kind='" + kind + '\'' +
                ", info='" + info + '\'' +
                ", itemImgPath='" + itemImgPath + '\'' +
                '}';
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPurchases() {
        return purchases;
    }

    public void setPurchases(Integer purchases) {
        this.purchases = purchases;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getItemImgPath() {
        return itemImgPath;
    }

    public void setItemImgPath(String itemImgPath) {
        this.itemImgPath = itemImgPath;
    }


}
