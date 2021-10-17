package com.yadong.springbootproject_1.controller;

import com.yadong.springbootproject_1.entity.Item;
import com.yadong.springbootproject_1.entity.Kill;
import com.yadong.springbootproject_1.entity.Result;
import com.yadong.springbootproject_1.entity.ResultEnum;
import com.yadong.springbootproject_1.service.ItemService;
import com.yadong.springbootproject_1.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/kill")
public class KillController {

    private static final String KILL_ITEMS_HASH_NAME = "killItems";
    @Autowired
    ItemService itemService;

    @Autowired
    RedisUtil redisUtil;

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
        Long r = redisUtil.sSet("killItemsIds", itemId);
        if(r == 0){
            result.setCode(ResultEnum.EXIST_ITEMS);
            return result;
        }
        item.setItemTotal(item.getItemTotal() - kill.getItemCount());
        int re = itemService.updateItem(item);       //减去库存
        if (re == 0) {    //乐观锁,修改不起作用,结束,重来
            //移除添加到redis中的商品id
            redisUtil.setRemove("killItemsIds",itemId);
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
        result.setCode(ResultEnum.SUCCESS);
        result.setData(kill);
        return result;
    }
}
