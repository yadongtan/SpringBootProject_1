package com.yadong.springbootproject_1;

import com.yadong.springbootproject_1.controller.KillController;
import com.yadong.springbootproject_1.entity.Kill;
import com.yadong.springbootproject_1.entity.Result;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@MapperScan("com.yadong.springbootproject_1.dao")
public class SpringBootProject1Application {

    @Autowired
    KillController killController;

    public static void main(String[] args){
        ConfigurableApplicationContext run = SpringApplication.run(SpringBootProject1Application.class, args);
//
//        Kill killItem1 = new Kill();
//        killItem1.setItemCount(20);
//        killItem1.setItemPrice(9.9);
//        killItem1.setItemId("1000000000");
//        killItem1.setKillTime(2L);
//
//        KillController killController = (KillController) run.getBean("killController");
//        Result result = killController.addItemToKill(killItem1);
//        System.out.println(result);
//        Result result2 = killController.addItemToKill(killItem1);
//        System.out.println(result2);

    }
}
