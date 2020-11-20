package com.hr_manage.mapper;

import com.hr_manage.entity.Department;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 部门信息Mapper接口
 */
@Repository
public interface IDepartmentMapper {


    /**
     * 根据条件查询部门信息
     *
     * @param id         部门ID
     * @param name       部门名称
     * @param startIndex 开始位置
     * @return
     */
    List<Department> findByCondition(Integer id, String name, Integer startIndex);

    /**
     * 根据条件查询部门信息的总数
     *
     * @param id         部门ID
     * @param name       部门名称
     * @param startIndex 开始位置
     * @return
     */
    int selectTotalByCondition(Integer id, String name, Integer startIndex);


    /**
     * 增加部门信息
     *
     * @param department
     * @return
     */
    int add(Department department);

    /**
     * 根据ID删除某条部门信息
     *
     * @param id
     * @return
     */
    int deleteById(Integer id);

    /**
     * 修改某条部门信息
     *
     * @param department
     * @return
     */
    int update(Department department);


    /**
     * 根据部门名称查询部门信息总数（判断部门名是否已经存在）
     *
     * @param name
     * @return
     */
    int selectTotalByName(String name);


}
