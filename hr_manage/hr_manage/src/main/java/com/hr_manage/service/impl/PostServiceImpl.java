package com.hr_manage.service.impl;

import com.hr_manage.mapper.IPostMapper;
import com.hr_manage.entity.Post;
import com.hr_manage.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 岗位信息Service实现类
 */
@Service
public class PostServiceImpl implements IPostService {

    @Autowired
    private IPostMapper postMapper;


    @Override
    public List<Post> findByCondition(Integer id, String name, Integer startIndex) {
        return postMapper.findByCondition(id, name, startIndex);
    }

    @Override
    public int selectTotalByCondition(Integer id, String name, Integer startIndex) {
        return postMapper.selectTotalByCondition(id, name, startIndex);
    }

    @Override
    public int add(Post post) {
        return postMapper.add(post);
    }

    @Override
    public int deleteById(Integer id) {
        return postMapper.deleteById(id);
    }

    @Override
    public int update(Post post) {
        return postMapper.update(post);
    }

    @Override
    public int selectTotalByName(String name) {
        return postMapper.selectTotalByName(name);
    }
}
