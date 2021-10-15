package com.yadong.springbootproject_1.controller;

import com.yadong.springbootproject_1.entity.Result;
import com.yadong.springbootproject_1.entity.ResultEnum;
import com.yadong.springbootproject_1.entity.User;
import com.yadong.springbootproject_1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RequestMapping("/api/user")
@RestController
public class UserController{

    @Autowired
    UserService userService;

    @PostMapping("/check")
    public Result check(HttpSession session, @RequestParam("username") String username,
                        @RequestParam("password") String password) {
        Result result = new Result();
        if("".equals(username) || username == null || "".equals(password) || password == null){
            result.setCode(ResultEnum.NULL_USERNAME_PASSWORD);
            return result;
        }
        User user = userService.getUserByUsername(username);

        if(user == null){
            result.setCode(ResultEnum.NULL_USER);
            return result;
        }

        if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
            session.setAttribute("uid",user.getUid());
            session.setAttribute("username", user.getUsername());
            session.setAttribute("admin", user.isAdmin());
            result.setCode(ResultEnum.SUCCESS);
            result.setData(true);
        } else {
            result.setCode(ResultEnum.ERROR_USERNAME_PASSWORD);
        }
        return result;
    }

    @GetMapping
    public Result getUserInfo(HttpSession session){
        Result result = new Result();
        User user = userService.getUserByUid((String)session.getAttribute("uid"));
        System.out.println(user);
        if (user == null) {
            result.setCode(ResultEnum.NULL_USER);
        }else{
            result.setCode(ResultEnum.SUCCESS);
            result.setData(user);
        }
        return result;
    }

}


