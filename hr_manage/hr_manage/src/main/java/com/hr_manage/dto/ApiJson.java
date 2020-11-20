package com.hr_manage.dto;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
public class ApiJson<T> implements Serializable {

    private int id;   //添加操作返回的id
    private int code;  //判断操作是否成功
    private boolean success;
    private String message;   //信息
    private  boolean exist;  //判断数据库中唯一性约束
    private List<T> list;          //判断的数据
    private int total;       //查询得到的总数
    private List<Integer> ids;   //添加返回的ids集合
    private  String token;      //登录成功后返回的token

    public ApiJson(int id, int code, boolean success, String message, boolean exist, List<T> list, int total, List<Integer> ids, String token) {
        this.id = id;
        this.code = code;
        this.success = success;
        this.message = message;
        this.exist = exist;
        this.list = list;
        this.total = total;
        this.ids = ids;
        this.token = token;
    }

    public ApiJson() {
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

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public boolean isExist() {
        return exist;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }





}

