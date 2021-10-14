package com.yadong.springbootproject_1;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.sql.DataSource;

@SpringBootApplication
@MapperScan("com.yadong.springbootproject_1.dao")
public class SpringBootProject1Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(SpringBootProject1Application.class, args);
        DataSource dataSource = (DataSource) run.getBean("dataSource");
        System.out.println(dataSource);
    }

}
