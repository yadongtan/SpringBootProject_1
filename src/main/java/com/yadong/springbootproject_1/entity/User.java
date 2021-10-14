package com.yadong.springbootproject_1.entity;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
@Alias("user")
public class User {

    private String uid; //用户uid
    private String username;    //用户名
    private String password;    //用户密码
    private String phone_num;   //用户手机号
    private Date birth; // 用户生日
    private String img_path;    //用户头像图片
    private boolean admin;  //是否管理员权限
    private String note;    //用户备注

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone_num='" + phone_num + '\'' +
                ", birth=" + birth +
                ", img_path='" + img_path + '\'' +
                ", admin=" + admin +
                ", note='" + note + '\'' +
                '}';
    }

    public User(){}

    public User(String uid, String username, String password, String phone_num, Date birth, String img_path, boolean admin, String note) {
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.phone_num = phone_num;
        this.birth = birth;
        this.img_path = img_path;
        this.admin = admin;
        this.note = note;
    }



    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getImg_path() {
        return img_path;
    }

    public void setImg_path(String img_path) {
        this.img_path = img_path;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


}