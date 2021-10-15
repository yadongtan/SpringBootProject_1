package com.yadong.springbootproject_1.service.impl;

import com.yadong.springbootproject_1.dao.UserDao;
import com.yadong.springbootproject_1.entity.User;
import com.yadong.springbootproject_1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    @Override
    public User getUserByUid(String uid) {
        return userDao.getUserByUid(uid);
    }
}
