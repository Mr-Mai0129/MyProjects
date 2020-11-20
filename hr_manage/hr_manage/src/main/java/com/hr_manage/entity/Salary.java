package com.hr_manage.entity;


import java.sql.Date;

/**
 * 薪酬对象
 */
public class Salary {

    /** id */
    private  int id;
    /** 员工ID */
    private  int eid;
    /** 工资 */
    private double salary;
    /** 时间 */
    private Date date;
    /** 状态（是否已经发放） */
    private  int status;

    /**员工姓名 */
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
       this.name=name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
