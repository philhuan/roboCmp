package com.hjwblog.robo_cmp.bean;

import java.io.Serializable;

public class JSONResult<T> implements Serializable {
    //Serializable将对象的状态保存在存储媒体中以便可以在以后重新创建出完全相同的副本
    public static final int SUCCESS=0;
    public static final int ERROR=1;
    public static final int OTHER=2;

    private int code;
    private String message = "";
    private T data;
    private String pass="";

    public JSONResult(){
        code = SUCCESS;
    }
    //为了方便，重载n个构造器
    public JSONResult(int code, String message, T data) {
        super();
        this.code = code;
        this.message = message;
        this.data = data;
    }
    public JSONResult(int code, String error){
        this(code,error,null);
    }
    public JSONResult(int code, T data){
        this(code,"",data);
    }
    public JSONResult(String error){
        this(ERROR,error,null);
    }

    public JSONResult(T data){
        this(SUCCESS,"",data);
    }
    public JSONResult(int code){
        this(code,"",null);
    }
    public JSONResult(Throwable e){
        this(ERROR,e.getMessage(),null);
    }
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
    public static int getSuccess() {
        return SUCCESS;
    }
    public static int getError() {
        return ERROR;
    }

    @Override
    public String toString() {
        return "JsonResult [code=" + code + ", message=" + message + ", pass=" + pass + ", data=" + data + "]";
    }
}

