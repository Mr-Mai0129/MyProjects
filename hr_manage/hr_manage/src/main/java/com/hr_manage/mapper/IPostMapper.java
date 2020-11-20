package com.hr_manage.mapper;

import com.hr_manage.entity.Post;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 岗位信息Mapper接口
 */
@Repository
public interface IPostMapper {

    /**
     * 根据条件查询岗位信息
     *
     * @param id         岗位ID
     * @param name       岗位名称
     * @param startIndex 开始位置
     * @return
     */
    List<Post> findByCondition(Integer id, String name, Integer startIndex);

    /**
     * 根据条件查询岗位信息的总数
     *
     * @param id         岗位ID
     * @param name       岗位名称
     * @param startIndex 开始位置
     * @return
     */
    int selectTotalByCondition(Integer id, String name, Integer startIndex);


    /**
     * 增加岗位信息
     *
     * @param post
     * @return
     */
    int add(Post post);

    /**
     * 根据ID删除某条岗位信息
     *
     * @param id
     * @return
     */
    int deleteById(Integer id);

    /**
     * 修改某条岗位信息
     *
     * @param post
     * @return
     */
    int update(Post post);


    /**
     * 根据部门名称查询岗位信息总数（判断岗位名是否已经存在）
     *
     * @param name
     * @return
     */
    int selectTotalByName(String name);
}
