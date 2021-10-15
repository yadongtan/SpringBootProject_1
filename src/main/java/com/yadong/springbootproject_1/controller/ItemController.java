package com.yadong.springbootproject_1.controller;

import com.yadong.springbootproject_1.entity.Item;
import com.yadong.springbootproject_1.entity.Result;
import com.yadong.springbootproject_1.entity.ResultEnum;
import com.yadong.springbootproject_1.service.ItemService;
import com.yadong.springbootproject_1.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("/api/item")
@Controller
public class ItemController {

    @Autowired
    ItemService itemService;

    @GetMapping
    @ResponseBody
    public Result getAllItems(HttpSession session) {
        Result result = new Result();
        String uid = (String) session.getAttribute("uid");
        List<Item> itemList = itemService.getItemsByOwnerId(uid);
        if(itemList != null) {
            result.setCode(ResultEnum.SUCCESS);
            result.setData(itemList);
        }else{
            result.setCode(ResultEnum.ERROR_ITEMS);
        }
        return result;
    }

    @PostMapping
    @ResponseBody
    public Result addItem(HttpSession session, Item item){
        Result result = new Result();
        if(item == null){
            result.setCode(ResultEnum.ERROR_ITEMS);
        }else
        {
            item.setOwnerId((String) session.getAttribute("uid"));
            int re = itemService.addItem(item);
            if(re != 1){
                result.setCode(ResultEnum.ERROR_ITEMS);
            }else{
                result.setCode(ResultEnum.SUCCESS);
                result.setData(re);
            }
        }
        return result;
    }

    @DeleteMapping("/{itemId}")
    @ResponseBody
    public Result delete(@PathVariable("itemId")String itemId){
        Result result = new Result();
        if(StringUtils.notNullString(itemId)) {
            int re = itemService.deleteItem(itemId);
            if(re == 1){
                result.setCode(ResultEnum.SUCCESS);
                result.setData(re);
            }else{
                result.setCode(ResultEnum.ERROR_ITEMS);
            }
        }else {
            result.setCode(ResultEnum.ERROR_ITEMS);
        }
        System.out.println("delete!");
        return result;
    }

    @PutMapping("/{itemId}")
    @ResponseBody
    public Result update(HttpSession session,@RequestBody Item item){
        Result result = new Result();
        if(item == null){
            result.setCode(ResultEnum.ERROR_ITEMS);
        }else
        {
            item.setOwnerId((String) session.getAttribute("uid"));
            int re = itemService.updateItem(item);
            if(re != 1){
                result.setCode(ResultEnum.ERROR_ITEMS);
            }else{
                result.setCode(ResultEnum.SUCCESS);
                result.setData(re);
            }
        }
        return result;
    }

}
