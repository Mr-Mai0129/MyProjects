package com.hr_manage.service.impl;

import com.hr_manage.mapper.IUserMapper;
import com.hr_manage.entity.User;
import com.hr_manage.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户信息service层实现类
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserMapper userMapper;


    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @return
     */
    public User login(String username, String password) {
        return userMapper.login(username, password);
    }

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    public int register(User user) {
        return userMapper.register(user);
    }

    /**
     * 判断用户名是否存在
     *
     * @param username 用户名
     * @return
     */
    @Override
    public int checkUsername(String username) {
        return userMapper.checkUsername(username);
    }


    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    public User findUserById(String id) {
        return userMapper.findUserById(id);
    }

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public int update(User user) {
        return userMapper.update(user);
    }
}
