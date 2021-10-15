package com.yadong.springbootproject_1.service;

import com.yadong.springbootproject_1.dao.UserDao;
import com.yadong.springbootproject_1.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface UserService {

    public User getUserByUsername(String username);

    public User getUserByUid(String uid);
}
