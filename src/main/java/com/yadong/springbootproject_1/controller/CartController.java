package com.yadong.springbootproject_1.controller;


import com.yadong.springbootproject_1.entity.Cart;
import com.yadong.springbootproject_1.entity.Result;
import com.yadong.springbootproject_1.entity.ResultEnum;
import com.yadong.springbootproject_1.service.CartService;
import com.yadong.springbootproject_1.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;

@RequestMapping("/api/cart")
@RestController
public class CartController {

    @Autowired
    CartService cartService;

    //获取购物车信息
    @GetMapping
    public Result getCart(HttpSession session) {
        Result result = new Result();
        String uid = (String) session.getAttribute("uid");
        if (StringUtils.notNullString(uid)) {
            List<Cart> cartList = cartService.getCartByUid(uid);
            if(cartList != null){
                result.setCode(ResultEnum.SUCCESS);
                result.setData(cartList);
            }else{
                result.setCode(ResultEnum.NULL_DATA);
            }
        } else {
            result.setCode(ResultEnum.ERROR_400);
        }
        return result;
    }

    //修改购物车商品
    @PutMapping("/{itemId}")
    public Result updateCart(@PathVariable("itemId") String itemId, @RequestBody Cart cart){
        Result result = new Result();
        if (StringUtils.notNullString(itemId)) {
            int re = cartService.updateItemsToCart(cart.getItemId(), cart.getItemCount(), cart.getUid());
            if(re != 1){
                result.setCode(ResultEnum.ERROR_400);
            }else{
                result.setCode(ResultEnum.SUCCESS);
                result.setData(true);
            }
        } else {
            result.setCode(ResultEnum.ERROR_400);
        }
        return result;
    }

    //删除购物车商品
    @DeleteMapping("/{itemId}")
    public Result deleteItemFromCart(HttpSession session,@PathVariable("itemId")String itemId){
        Result result = new Result();
        String uid = (String) session.getAttribute("uid");
        if(StringUtils.noNullStringInList(uid,itemId)){
            int re = cartService.deleteItemFromCart(itemId,uid);
            if(re != 1){
                result.setCode(ResultEnum.ERROR_400);
            }else{
                result.setCode(ResultEnum.SUCCESS);
                result.setData(true);
            }
        }else{
            result.setCode(ResultEnum.ERROR_400);
        }
        return result;
    }

    //删除全部购物车商品
    @DeleteMapping
    public Result deleteAllItemsFromCart(HttpSession session){
        String uid = (String) session.getAttribute("uid");
        Result result = new Result();
        if(StringUtils.notNullString(uid)){
            int re = cartService.deleteAllItemsFromCart(uid);
            if(re != 1){
                result.setCode(ResultEnum.ERROR_400);
            }else{
                result.setCode(ResultEnum.SUCCESS);
                result.setData(true);
            }
        }else{
            result.setCode(ResultEnum.ERROR_400);
        }
        return result;

    }

    //添加购物车商品
    @PostMapping
    public Result addItemToCart(Cart cart){
        Result result = new Result();
        if(StringUtils.noNullStringInList(cart.getUid(),cart.getUid(),cart.getItemCount().toString())){
            Date date = new Date(System.currentTimeMillis());
            cart.setAddTime(date);
            int re = cartService.addItemToCart(cart.getItemId(), cart.getItemCount(), cart.getUid(), date);
        }else{
            result.setCode(ResultEnum.ERROR_400);
        }
        return result;
    }


}
