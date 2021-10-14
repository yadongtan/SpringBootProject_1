# SpringBootProject_1
第一个SpringBoot项目

 src/main/resources目录下:
  static 用于存放静态资源,如css和js等,比如JQuery可以放在static/js/JQuery.js
  template 用于存放静态网页,如index.html 可以放在 template/index.html
  application.yml 或者application.properties 是配置文件,用于数据库或者Redis等等相关配置
 
1.接口形式以/api开头,如或者某个用户信息,可以以/api/user/{id}模板:
获取id为2020的用户,则应提交ajax请求/api/user/2020

2.所有接口返回数据形式为json,返回一个Result对象,包含状态码code(200,404),请求结果信息msg,和data数据体
如,返回的是一个用户信息,则这个json信息为{"code":200,"msg":"success","data":{"user":{"id":2020,"username":"xxxx","userAge":24}}}
如,返回的是一个列表的用户信息,则data部分为数组,如:{"code":200,"msg":"success",...}//待会再写

