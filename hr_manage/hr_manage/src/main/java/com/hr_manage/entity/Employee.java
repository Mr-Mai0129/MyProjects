package com.hr_manage.entity;

import org.springframework.stereotype.Component;

import java.sql.Date;


/**
 * 员工对象
 */
@Component
public class Employee {

    /**  员工id*/
    private int id;
    /** 员工姓名*/
    private String name;
    /** 员工性别*/
    private int sex;
    /** 员工状态 在职1，不在0*/
    private String status;
    /** 员工的部门ID */
    private int departmentId;
    /** 员工的岗位ID*/
    private int postId;
    /** 员工电话*/
    private String phone;
    /** 员工年龄*/
    private int age;
    /** 员工住址*/
    private String address;
    /** 员工的入职时间*/
    private Date entryTime;
    /**员工的部门名称 */
    private String departmentName;
    /**员工的岗位名称*/
    private  String postName;
    /**员工简历ID*/
    private  Integer fileId;
    /**员工简历名称*/
    private  String fileName;
    /**员工简历路径*/
    private  String filePath;


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

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
