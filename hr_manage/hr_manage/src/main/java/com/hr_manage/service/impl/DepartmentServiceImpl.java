package com.hr_manage.service.impl;

import com.hr_manage.mapper.IDepartmentMapper;
import com.hr_manage.entity.Department;
import com.hr_manage.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 部门信息Service层实现类
 */
@Service
public class DepartmentServiceImpl implements IDepartmentService {

    @Autowired
    private IDepartmentMapper departmentMapper;


    @Override
    public List<Department> findByCondition(Integer id, String name, Integer startIndex) {
        return departmentMapper.findByCondition(id, name, startIndex);
    }

    @Override
    public int selectTotalByCondition(Integer id, String name, Integer startIndex) {
        return departmentMapper.selectTotalByCondition(id, name, startIndex);
    }

    @Override
    public int add(Department department) {
        return departmentMapper.add(department);
    }

    @Override
    public int deleteById(Integer id) {
        return departmentMapper.deleteById(id);
    }

    @Override
    public int update(Department department) {
        return departmentMapper.update(department);
    }

    @Override
    public int selectTotalByName(String name) {
        return departmentMapper.selectTotalByName(name);
    }
}
