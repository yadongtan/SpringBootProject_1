package com.yadong.springbootproject_1.service;

import com.yadong.springbootproject_1.entity.Item;

import java.util.List;

public interface ItemService {

    Item getItemByItemId(String itemId);
    List<Item> getItemsByOwnerId(String ownerId);
    List<Item> getItemsByItemName(String itemName);
    List<Item> getItemsByItemKind(String itemKind);
    int addItem(Item item);
    int deleteItem(String itemId);
    int updateItem(Item item);

}
