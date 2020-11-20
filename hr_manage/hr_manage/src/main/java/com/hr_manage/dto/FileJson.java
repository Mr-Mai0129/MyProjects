package com.hr_manage.dto;

import org.springframework.stereotype.Component;

/**
 * 文件API返回集
 */
@Component
public class FileJson {

    private int id;   //添加操作返回的id
    private int code;  //判断操作是否成功
    private boolean success;
    private String message;   //信息
    private  String  name;  //文件名
    private  String path;   //文件路径

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
