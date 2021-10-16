package com.yadong.springbootproject_1.dao;

import com.yadong.springbootproject_1.entity.Item;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemDao {

    Item getItemByItemId(String itemId);
    List<Item> getItemsByOwnerId(String ownerId);
    List<Item> getItemsByItemName(String itemName);
    List<Item> getItemsByItemKind(String itemKind);
    List<Item> getAllItems();
    int addItem(Item item);
    int deleteItem(String itemId);
    int updateItem(Item item);


}
