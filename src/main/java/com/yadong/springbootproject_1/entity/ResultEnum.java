package com.yadong.springbootproject_1.entity;

public enum ResultEnum {
    SUCCESS(200,"success"),
    ERROR_USERNAME_PASSWORD(200,"用户名或密码错误"),
    NULL_USER(200,"不存在该用户"),
    NULL_USERNAME_PASSWORD(200,"用户名或密码为空"),
    ERROR_ITEMS(301,"商品信息错误"),
    NULL_CART(302,"购物车为空"),
    NO_ITEMS(303,"商品库存不足"),
    TRY_LATER(304,"当前访问人数较多,请稍后再试"),
    EXIST_ITEMS(305,"该商品信息已存在,请先移除原有信息"),
    ERROR_400(400,"参数错误"),
    ERROR_PERMIT(403,"权限禁止访问"),
    NULL_DATA(200,"null");

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    ResultEnum(int code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public static String msg(int code){
        for(ResultEnum m : ResultEnum.values()){
            if(m.getCode() == code){
                return m.getMsg();
            }
        }
        return "unknown error";
    }



}
