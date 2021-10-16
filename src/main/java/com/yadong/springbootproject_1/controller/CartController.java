package com.yadong.springbootproject_1.controller;


import com.sun.org.apache.xpath.internal.operations.Or;
import com.yadong.springbootproject_1.entity.*;
import com.yadong.springbootproject_1.service.CartService;
import com.yadong.springbootproject_1.service.ItemService;
import com.yadong.springbootproject_1.service.OrderService;
import com.yadong.springbootproject_1.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@RequestMapping("/api/cart")
@RestController
public class CartController {

    @Autowired
    CartService cartService;

    @Autowired
    OrderService orderService;

    @Autowired
    ItemService itemService;

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

    //结算购物车生成订单
    @GetMapping("/submit")
    @Transactional(rollbackFor = Exception.class)
    public Result submitCartAndMakeOrder(HttpSession session){
        Result result = new Result();
        //1.获取要结算购物车的用户信息
        String uid = (String) session.getAttribute("uid");
        if(!StringUtils.notNullString(uid)){
            result.setCode(ResultEnum.ERROR_400);
            return result;
        }
        //2.获取该用户的购物车
        List<Cart> cartList = cartService.getCartByUid(uid);
        if(cartList == null || cartList.size() == 0){
            result.setCode(ResultEnum.NULL_CART);
            return result;
        }
        //3.生成订单,开启事务,使用乐观锁
        Order order = new Order();
        for (Cart cart : cartList) {
            //1.设置订单号
            Long currentTime = new Long(System.currentTimeMillis());
            String ct = currentTime.toString();
            String orderId = uid
                    + ct
                    + StringUtils.getRandomNum(5);
            System.out.println("orderId = "+orderId);
            order.setOrderId(orderId);
            //2.设置uid
            order.setUid(uid);
            //3.设置item_id
            order.setItemId(cart.getItemId());
            //4.设置count
            order.setCounts(cart.getItemCount());
            //5.设置single_price
            Item item = itemService.getItemByItemId(cart.getItemId());
            //商品库存不够的情况,放弃这个订单,生成其他订单
            if(item.getItemTotal() < cart.getItemCount()){
                result.setCode(ResultEnum.NO_ITEMS);
                return result;
            }else{//够就更新库存,继续生成订单
                item.setItemTotal(item.getItemTotal() - order.getCounts());
                int re = itemService.updateItem(item);
                if(re == 0){    //乐观锁,re为0,说明被修改,version不匹配
                    result.setCode(ResultEnum.ERROR_400);
                    return result;
                }//继续往下执行说明数据没有问题
            }
            order.setSinglePrice(item.getItemPrice());
            //6.设置total_price
            order.setTotalPrice(item.getItemPrice()*cart.getItemCount());
            //7.设置order_time
            order.setOrderTime(new Date(System.currentTimeMillis()));
            int re = orderService.addOrder(order);
            if(re == 0){
                result.setCode(ResultEnum.ERROR_400);
                return result;
            }
            cartService.deleteItemFromCart(item.getItemId(), uid);
        }
        //4.返回结果
        result.setCode(ResultEnum.SUCCESS);
        result.setData(true);
        return result;

    }

}
