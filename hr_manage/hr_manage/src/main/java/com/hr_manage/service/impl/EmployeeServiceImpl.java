package com.hr_manage.service.impl;

import com.hr_manage.mapper.IEmployeeMapper;
import com.hr_manage.entity.Employee;
import com.hr_manage.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 员工信息的Service实现类
 */
@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private IEmployeeMapper employeeMapper;


    @Override
    public int add(Employee employee) {
        return employeeMapper.add(employee);
    }

    @Override
    public int delete(Integer id) {
        return employeeMapper.delete(id);
    }

    @Override
    public int update(Employee employee) {
        return employeeMapper.update(employee);
    }

    @Override
    public int updateFID(int Fid) {
        return employeeMapper.updateFID(Fid);
    }

    @Override
    public List<Employee> listAll() {
        return employeeMapper.listAll();
    }

    @Override
    public int selectTotal() {
        return employeeMapper.selectTotal();
    }

    @Override
    public List<Employee> findByCondition(Integer id, String name, Integer departmentId, Integer postId, Integer startIndex) {
        return employeeMapper.findByCondition(id, name, departmentId, postId, startIndex);
    }


    @Override
    public int selectTotalByCondition(Integer id, String name, Integer departmentId, Integer postId) {
        return employeeMapper.selectTotalByCondition(id, name, departmentId, postId);
    }


}
