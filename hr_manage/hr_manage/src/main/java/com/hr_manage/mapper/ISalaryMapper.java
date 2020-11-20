package com.hr_manage.mapper;

import com.hr_manage.entity.Salary;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;


/**
 * 薪酬信息Mapper接口
 */
@Repository
public interface ISalaryMapper {

    /**
     * 根据条件查询薪酬信息
     *
     * @param id
     * @param eIds
     * @param date
     * @param startIndex
     * @return
     */
    List<Salary> findByCondition(Integer id,  List<Integer> eIds, Date date, Integer startIndex);


    /**
     * 根据条件查询薪酬信息的总数
     *
     * @param id
     * @param eIds
     * @param date
     * @return
     */
    int selectTotalByCondition(Integer id, List<Integer> eIds, Date date);


    /**
     * 增加部门信息
     *
     * @param salary
     * @return
     */
    int add(Salary salary);

    /**
     * 根据ID删除某条薪酬信息
     *
     * @param id
     * @return
     */
    int deleteById(Integer id);

    /**
     * 修改某条薪酬信息
     *
     * @param salary
     * @return
     */
    int update(Salary salary);


}
