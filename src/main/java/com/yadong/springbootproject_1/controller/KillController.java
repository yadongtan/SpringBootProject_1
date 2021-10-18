package com.yadong.springbootproject_1.controller;

import com.yadong.springbootproject_1.entity.*;
import com.yadong.springbootproject_1.service.ItemService;
import com.yadong.springbootproject_1.service.OrderService;
import com.yadong.springbootproject_1.util.RedisUtil;
import com.yadong.springbootproject_1.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/kill")
public class KillController {

    public static final String KILL_ITEMS_HASH_NAME = "killItems";
    public static final String KILL_ITEMSIDS_SET_NAME = "killItemsIds";

    @Autowired
    ItemService itemService;

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    OrderService orderService;

    @PostMapping
    @Transactional
    //将商品添加到秒杀系统中,但不开始秒杀,即killAble的值为false
    public Result addItemToKill(@RequestBody Kill kill){
        Result result = new Result();
        if(kill.getItemPrice() <= 0 || kill.getItemCount()<=0){
            result.setCode(ResultEnum.ERROR_400);
            return result;
        }
        String itemId = kill.getItemId();
        Item item = itemService.getItemByItemId(itemId);
        if(item == null || item.getItemTotal() < kill.getItemCount()){
            result.setCode(ResultEnum.NO_ITEMS);
            return result;
        }
        //判断商品是不是已经在秒杀,是的话直接返回,不予修改原有秒杀数据
        Long r = redisUtil.sSet(KILL_ITEMSIDS_SET_NAME, itemId);
        if(r == 0){
            result.setCode(ResultEnum.EXIST_ITEMS);
            return result;
        }
        item.setItemTotal(item.getItemTotal() - kill.getItemCount());
        int re = itemService.updateItem(item);       //减去库存
        if (re == 0) {    //乐观锁,修改不起作用,结束,重来
            //移除添加到redis中的商品id
            redisUtil.setRemove(KILL_ITEMSIDS_SET_NAME,itemId);
            result.setCode(ResultEnum.TRY_LATER);
            return result;
        }
        /*
          秒杀商品数据正确,可以添加到Redis缓存中做准备
          killItemsIds-> itemId1,itemId2,itemId3....
          killItems ->
                        itemCount:itemId1 :xxx
                        itemPrice:itemId1 :xxx
                        killAble:itemId1 :xxx
         */
        //上面已经将商品itemId添加到了killItemsIds集合中,接下来添加到killItems中
        kill.setSetTime(System.currentTimeMillis()/1000);
        if(kill.getKillTime() != null && kill.getKillTime() > 0){
            redisUtil.hset(KILL_ITEMS_HASH_NAME,kill.getKillTimeKey(),kill.getKillTime());
        }else{
            redisUtil.hset(KILL_ITEMS_HASH_NAME, kill.getKillTimeKey(), 1, 1000 * 60 * 60 * 24);    //默认一天后开始秒杀
        }
        redisUtil.hset(KILL_ITEMS_HASH_NAME,kill.getKillSetTimeKey(),kill.getSetTime());
        redisUtil.hset(KILL_ITEMS_HASH_NAME,kill.getItemCountKey(),kill.getItemCount());
        redisUtil.hset(KILL_ITEMS_HASH_NAME,kill.getItemPriceKey(),kill.getItemPrice());
        redisUtil.hset(KILL_ITEMS_HASH_NAME, kill.getKillAbleKey(), false);
        kill.setKillAble(false);
        result.setCode(ResultEnum.SUCCESS);
        result.setData(kill);
        return result;
    }

    //返回可以秒杀的项目
    @GetMapping("/killable")
    public Result getKillAbleItems() {
        Set<Object> objects = redisUtil.sGet(KILL_ITEMSIDS_SET_NAME);
        List<Kill> killList = new ArrayList<>();
        for (Object i : objects) {
            Kill kill = new Kill();
            kill.setItemId((String) i); //拿到未加入秒杀区的itemId,去获取时间,判断是否开始秒杀
            Integer killTime = (Integer) redisUtil.hget(KILL_ITEMS_HASH_NAME, kill.getKillTimeKey());
            Integer setTime = (Integer) redisUtil.hget(KILL_ITEMS_HASH_NAME, kill.getKillSetTimeKey());
            if (System.currentTimeMillis() / 1000 >= setTime + killTime) {  //要秒杀的商品,要加入秒杀区了
                redisUtil.hset(KILL_ITEMS_HASH_NAME, kill.getKillAbleKey(), true);
                kill.setKillAble(true);
                killList.add(kill);
            }
        }
        Result result = new Result();
        result.setCode(ResultEnum.SUCCESS);
        result.setData(killList);
        return result;
    }

    //返回单一可秒杀商品信息
    @GetMapping("/{itemId}")
    public Result getKillItem(@PathVariable String itemId) {
        Result result = new Result();
        if (!StringUtils.notNullString(itemId)) {
            result.setCode(ResultEnum.ERROR_400);
            return result;
        }
        Kill kill = new Kill();
        kill.setItemId(itemId);
        if (!redisUtil.sGet(KILL_ITEMSIDS_SET_NAME).contains(itemId)){
            result.setCode(ResultEnum.ERROR_400);
            return result;
        }else{
            kill.setItemCount((Integer) redisUtil.hget(KILL_ITEMS_HASH_NAME, kill.getItemCountKey()));
            kill.setItemPrice((Double) redisUtil.hget(KILL_ITEMS_HASH_NAME, kill.getItemPriceKey()));
            kill.setSetTime(Integer.toUnsignedLong((Integer) redisUtil.hget(KILL_ITEMS_HASH_NAME, kill.getKillSetTimeKey())));
            kill.setKillTime(Integer.toUnsignedLong((Integer) redisUtil.hget(KILL_ITEMS_HASH_NAME, kill.getKillTimeKey())));
            kill.setKillAble((Boolean) redisUtil.hget(KILL_ITEMS_HASH_NAME,kill.getKillAbleKey()));
        }
        result.setCode(ResultEnum.SUCCESS);
        result.setData(kill);
        return result;
    }

    //秒杀，秒杀到了就下订单
    //开启Redis的watch
    @PostMapping("/order/{itemId}")
    @Transactional
    public Result makeOrder(HttpSession session, @PathVariable("itemId")String itemId){
        Result result = getKillItem(itemId);
        Kill kill = new Kill();
        kill.setItemId(itemId);
        Integer count = (Integer) redisUtil.hget(KILL_ITEMS_HASH_NAME, kill.getItemCountKey());
//        if(count <= 0){
//            redisUtil.hset(KILL_ITEMS_HASH_NAME,kill.getKillAbleKey(),false);
//            result.setCode(ResultEnum.NO_ITEMS);
//            return result;
//        }
        Kill kill_1 = (Kill) getKillItem(itemId).getData();
        if(kill_1 == null || kill_1.getItemCount() <= 0 || ((kill_1.getKillTime() + kill_1.getSetTime() )>= System.currentTimeMillis()/1000)){
            result.setCode(ResultEnum.ERROR_400);
            return result;
        }else{
            kill_1.setKillAble(true);
            redisUtil.hset(KILL_ITEMS_HASH_NAME,kill_1.getKillAbleKey(),true);
        }
        Boolean killAble = (Boolean) redisUtil.hget(KILL_ITEMS_HASH_NAME, kill_1.getKillAbleKey());
        if(killAble){
            List<Object> re = redisUtil.killItem(kill_1);
            if(re == null || re.isEmpty()){ //失败
                result.setCode(ResultEnum.ERROR_400);
                return result;
            }//秒杀成功
            else{
                Order order = new Order();
                String uid = (String) session.getAttribute("uid");
                String orderId = uid
                        + System.currentTimeMillis()
                        + StringUtils.getRandomNum(5);
                System.out.println("orderId = "+orderId);
                order.setOrderId(orderId);
                //2.设置uid
                order.setUid(uid);
                //3.设置item_id
                order.setItemId(itemId);
                //4.设置count
                order.setCounts(1);
                //5.设置single_price
                order.setSinglePrice((Double) redisUtil.hget(KILL_ITEMS_HASH_NAME, kill_1.getItemPriceKey()));
                order.setOrderTime(new Date(System.currentTimeMillis()));
                order.setTotalPrice(order.getSinglePrice());
                int re1 = orderService.addOrder(order);
                if(re1 == 0){
                    redisUtil.hincr(KILL_ITEMS_HASH_NAME, kill_1.getItemCountKey(), 1);
                    result.setCode(ResultEnum.ERROR_400);
                    return result;
                }
                result.setCode(ResultEnum.SUCCESS);
                result.setData(order);
                return result;
            }
        }else{
            result.setCode(ResultEnum.ERROR_400);
            return result;
        }
    }

}
