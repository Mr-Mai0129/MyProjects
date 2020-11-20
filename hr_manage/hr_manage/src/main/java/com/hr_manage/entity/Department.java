package com.hr_manage.entity;

/**
 * 部门对象
 */
public class Department {

    /** 部门id */
    private int id;
    /** 部门名称 */
    private String name;
    /** 部门职务 */
    private String position;

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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
