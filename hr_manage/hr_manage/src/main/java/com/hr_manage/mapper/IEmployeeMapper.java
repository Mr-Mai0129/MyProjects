package com.hr_manage.mapper;

import com.hr_manage.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 员工信息的Mapper接口
 */
@Repository
public interface IEmployeeMapper {

    /**
     * 查询所有员工信息
     *
     * @return
     */
    List<Employee> listAll();

    /**
     * 查询员工信息的总数
     *
     * @return
     */
    int selectTotal();


    /**
     * 根据条件查询
     *
     * @param name         员工姓名
     * @param id           员工ID
     * @param departmentId 员工部门ID
     * @param postId       员工岗位ID
     * @param startIndex    开始位置
     * @return
     */
    List<Employee> findByCondition(Integer id, String name, Integer departmentId, Integer postId,Integer startIndex);

    /**
     * 根据条件查询员工信息总数
     *
     * @param name         员工姓名
     * @param id           员工ID
     * @param departmentId 员工部门ID
     * @param postId       员工岗位ID
     * @return
     */
    int selectTotalByCondition(Integer id,String name, Integer departmentId, Integer postId);

    /**
     * 增加员工信息
     *
     * @param employee
     * @return
     */
    int add(Employee employee);

    /**
     * 根据ID删除单条员工信息
     *
     * @param id
     * @return
     */
    int delete(Integer id);

    /**
     * 根据ID更新单条员工信息
     *
     * @param employee
     * @return
     */
    int update(Employee employee);

    /**
     * 删除员工简历
     * @param Fid
     * @return
     */
    int updateFID(int Fid);

}
