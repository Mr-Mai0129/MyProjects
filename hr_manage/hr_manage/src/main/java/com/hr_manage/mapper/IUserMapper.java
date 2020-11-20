package com.hr_manage.mapper;

import com.hr_manage.entity.User;
import org.springframework.stereotype.Repository;


/**
 * 用户信息Mapper接口
 */
@Repository
public interface IUserMapper {

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    User login(String  username,String password);


    /**
     * 用户注册
     * @param user
     * @return
     */
    int register(User user);


    /**
     * 判断用户名是否相同
     *
     * @param username 用户名
     * @return
     */
    int checkUsername(String username);


    /**
     * 根据ID查询
     * @param id
     * @return
     */
    User  findUserById(String id);

    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    User findByUsername(String username);


    /**
     * 更新用户信息
     * @param user
     * @return
     */
    int update(User user);




}
