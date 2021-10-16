package com.yadong.springbootproject_1.service.impl;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.yadong.springbootproject_1.dao.ItemDao;
import com.yadong.springbootproject_1.entity.Item;
import com.yadong.springbootproject_1.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemDao itemDao;

    @Override
    public Item getItemByItemId(String itemId) {
        return itemDao.getItemByItemId(itemId);
    }


    @Override
    public List<Item> getItemsByOwnerId(String ownerId) {
        return itemDao.getItemsByOwnerId(ownerId);
    }

    @Override
    public List<Item> getItemsByItemName(String itemName) {
        return itemDao.getItemsByItemName(itemName);
    }

    @Override
    public List<Item> getItemsByItemKind(String itemKind) {
        return itemDao.getItemsByItemKind(itemKind);
    }

    @Override
    public int addItem(Item item) {
        return itemDao.addItem(item);
    }

    @Override
    public int deleteItem(String itemId) {
        return itemDao.deleteItem(itemId);
    }

    @Override
    public int updateItem(Item item) {
        return itemDao.updateItem(item);
    }

    @Override
    public List<Item> getAllItems() {
        return itemDao.getAllItems();
    }

}
