package com.hr_manage.controller;

import com.hr_manage.dto.ApiJson;
import com.hr_manage.entity.Department;
import com.hr_manage.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;


/**
 * 部门信息接口Controller类
 */
@RestController
@RequestMapping("/api/department")
public class DepartmentController {


    @Autowired
    private IDepartmentService departmentService;


    @Autowired
    private ApiJson apiJson;


    /**
     * 根据条件查询部门信息
     *
     * @return
     */
    @RequestMapping("/select.do")
    public ApiJson findByCondition(Integer id, String name, Integer index) {
        ApiJson<Department> departmentApiJson = new ApiJson<>();
        Integer startIndex = null;
        if (index != null) {
            startIndex = (index - 1) * 10;
        }
        List<Department> departments = departmentService.findByCondition(id, name, startIndex);
        if (departments != null) {
            int total = departmentService.selectTotalByCondition(id, name, index);
            departmentApiJson.setCode(1);
            departmentApiJson.setList(departments);
            departmentApiJson.setTotal(total);
            departmentApiJson.setMessage("查询成功");
        }
        return departmentApiJson;
    }


    /**
     * 检查部门名称是否存在
     *
     * @param name
     * @return
     */
    @RequestMapping("/check_department_name.do")
    public ApiJson checkDepartmentName(String name) {
        if (name == null) {
            apiJson.setCode(0);
            apiJson.setMessage("部门名称为空");
            return apiJson;
        }
        int total = departmentService.selectTotalByName(name);
        if (total > 0) {
            apiJson.setCode(1);
            apiJson.setExist(true);
            apiJson.setMessage("部门名称已经存在");
            return apiJson;
        }
        apiJson.setCode(1);
        apiJson.setExist(false);
        apiJson.setMessage("部门名称不存在");
        return apiJson;
    }

    /**
     * 添加部门信息
     *
     * @param department
     * @return
     */
    @RequestMapping("/add.do")
    public ApiJson add(Department department) {
        if (department == null) {
            apiJson.setCode(0);
            apiJson.setMessage("部门信息为空");
            return apiJson;
        }
        int row = departmentService.add(department);
        if (row == 0) {
            apiJson.setCode(0);
            return apiJson;
        }
        apiJson.setCode(1);
        apiJson.setId(department.getId());
        apiJson.setMessage("添加成功");
        return apiJson;
    }

    /**
     * 删除部门信息
     *
     * @param id
     * @return
     */
    @RequestMapping("/delete.do")
    public ApiJson remove(Integer id) {
        if (id == null) {
            apiJson.setCode(0);
            apiJson.setMessage("id不存在");
        }
        try {
            departmentService.deleteById(id);
        }catch (DataIntegrityViolationException e){
            e.printStackTrace();
            apiJson.setCode(0);
            apiJson.setMessage("有其他表对其引用,无法删除");
            return apiJson;
        }
        apiJson.setCode(1);
        apiJson.setMessage("删除成功");
        return apiJson;
    }

    /**
     * 修改部门信息
     *
     * @param id
     * @param department 需要修改的部门信息
     * @return
     */
    @RequestMapping("/update.do")
    public ApiJson update(Integer id, Department department) {
        if (id == null) {
            apiJson.setCode(0);
            apiJson.setMessage("id不存在");
            return apiJson;
        }try {
            departmentService.update(department);
        }catch (DataIntegrityViolationException e){
            e.printStackTrace();
            apiJson.setCode(0);
            apiJson.setMessage("有其他表对其引用,无法修改");
            return apiJson;
        }
        apiJson.setCode(1);
        apiJson.setMessage("修改成功");
        return apiJson;
    }


}
