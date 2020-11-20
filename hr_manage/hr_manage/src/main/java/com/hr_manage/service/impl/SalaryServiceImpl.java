package com.hr_manage.service.impl;

import com.hr_manage.mapper.ISalaryMapper;
import com.hr_manage.entity.Salary;
import com.hr_manage.service.ISalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

/**
 * 薪酬信息service层实现类
 */
@Service
public class SalaryServiceImpl implements ISalaryService {

    @Autowired
    private ISalaryMapper salaryMapper;


    @Override
    public List<Salary> findByCondition(Integer id, List<Integer> eIds, Date date, Integer startIndex) {
        return salaryMapper.findByCondition(id, eIds, date, startIndex);
    }

    @Override
    public int selectTotalByCondition(Integer id, List<Integer> eIds, Date date) {
        return salaryMapper.selectTotalByCondition(id, eIds, date);
    }

    @Override
    public int add(Salary salary) {
        return salaryMapper.add(salary);
    }

    @Override
    public int deleteById(Integer id) {
        return salaryMapper.deleteById(id);
    }

    @Override
    public int update(Salary salary) {
        return salaryMapper.update(salary);
    }




}
