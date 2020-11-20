package com.hr_manage.entity;

/**
 * 岗位对象
 */
public class Post {

    /** 岗位id */
    private int id;
    /** 岗位名称 */
    private String name;
    /** 岗位介绍内容 */
    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
