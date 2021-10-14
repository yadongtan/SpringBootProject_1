package com.yadong.springbootproject_1.dao;

import com.yadong.springbootproject_1.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {


    User getUserByUsername(String username);
}
