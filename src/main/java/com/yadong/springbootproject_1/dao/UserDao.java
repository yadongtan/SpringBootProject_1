package com.yadong.springbootproject_1.dao;

import com.yadong.springbootproject_1.entity.User;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {


    User getUserByUsername(String username);

    User getUserByUid(String uid);

}
