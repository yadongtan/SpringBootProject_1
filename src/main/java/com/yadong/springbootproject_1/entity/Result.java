package com.yadong.springbootproject_1.entity;

import com.sun.org.apache.bcel.internal.classfile.Code;

public class Result {

    private int code;   //一个状态码
    private String msg; //附带消息
    private Object data;    //数据
    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(ResultEnum code) {
        this.code = code.getCode();
        this.msg = code.getMsg();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
