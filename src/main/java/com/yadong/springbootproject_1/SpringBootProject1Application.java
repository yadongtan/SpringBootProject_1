package com.yadong.springbootproject_1;

import com.alibaba.druid.pool.DruidDataSource;
import com.yadong.springbootproject_1.dao.CartDao;
import com.yadong.springbootproject_1.dao.ItemDao;
import com.yadong.springbootproject_1.entity.Cart;
import com.yadong.springbootproject_1.entity.User;
import com.yadong.springbootproject_1.service.UserService;
import com.yadong.springbootproject_1.util.RedisUtil;
import com.yadong.springbootproject_1.util.StringUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.sql.DataSource;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@MapperScan("com.yadong.springbootproject_1.dao")
public class SpringBootProject1Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(SpringBootProject1Application.class, args);
        RedisUtil redisUtil = (RedisUtil) run.getBean("redisUtil");

    }

}
