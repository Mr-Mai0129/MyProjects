package com.hr_manage.entity;

/**
 * 文件对象
 */
public class File {

    /** id */
    private int id;
    /** 文件名 */
    private  String  name;
    /** 文件路径 */
    private  String  path;

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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
