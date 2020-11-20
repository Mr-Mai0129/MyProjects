package com.hr_manage.controller;

import com.hr_manage.dto.ApiJson;
import com.hr_manage.entity.Post;
import com.hr_manage.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 岗位信息接口Controller类
 */
@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private IPostService postService;

    @Autowired
    private ApiJson apiJson;


    /**
     * 根据条件显示岗位信息
     *
     * @return
     */
    @RequestMapping("/select.do")
    public ApiJson list(Integer id, String name, Integer index) {
        ApiJson<Post> postApiJson = new ApiJson<>();
        Integer startIndex = null;
        if (index != null) {
            startIndex = (index - 1) * 10;
        }
        List<Post> posts = postService.findByCondition(id, name, startIndex);
        if (posts != null) {
            int total = postService.selectTotalByCondition(id, name, index);
            postApiJson.setCode(1);
            postApiJson.setList(posts);
            postApiJson.setTotal(total);
            postApiJson.setMessage("查询成功");
        }
        return postApiJson;
    }


    /**
     * 检查岗位名称是否存在
     *
     * @param name
     * @return
     */
    @RequestMapping("/check_post_name.do")
    public ApiJson checkDepartmentName(String name) {
        if (name == null) {
            apiJson.setCode(0);
            apiJson.setMessage("岗位名称为空");
            return apiJson;
        }
        int total = postService.selectTotalByName(name);
        if (total > 0) {
            apiJson.setCode(1);
            apiJson.setExist(true);
            apiJson.setMessage("岗位名称已经存在");
            return apiJson;
        }
        apiJson.setCode(1);
        apiJson.setExist(false);
        apiJson.setMessage("岗位名称不存在");
        return apiJson;
    }

    /**
     * 新增岗位信息
     *
     * @param post
     * @return
     */
    @RequestMapping("/add.do")
    public ApiJson add(Post post) {
        if (post == null) {
            apiJson.setCode(0);
            apiJson.setMessage("岗位信息为空");
            return apiJson;
        }
        int row = postService.add(post);
        if (row == 0) {
            apiJson.setCode(0);
            return apiJson;
        }
        apiJson.setCode(1);
        apiJson.setId(post.getId());
        apiJson.setMessage("添加成功");
        return apiJson;
    }

    /**
     * 删除岗位信息
     *
     * @param id
     * @return
     */
    @RequestMapping("/delete.do")
    public ApiJson remove(Integer id) {
        if (id == null) {
            apiJson.setCode(0);
            apiJson.setMessage("id不存在");
        }
        try {
            postService.deleteById(id);
        }catch (DataIntegrityViolationException e){
            e.printStackTrace();
            apiJson.setCode(0);
            apiJson.setMessage("有其他表对其引用,无法删除");
            return apiJson;
        }
        apiJson.setCode(1);
        apiJson.setMessage("删除成功");
        return apiJson;
    }


    /**
     * 修改岗位信息
     *
     * @param post
     * @param id
     * @return
     */
    @RequestMapping("/update.do")
    public ApiJson updateSave(Integer id, Post post) {
        if (id == null) {
            apiJson.setCode(0);
            apiJson.setMessage("id不存在");
            return apiJson;
        }try {
            postService.update(post);
        }catch (DataIntegrityViolationException e){
            e.printStackTrace();
            apiJson.setCode(0);
            apiJson.setMessage("有其他表对其引用,无法修改");
            return apiJson;
        }
        apiJson.setCode(1);
        apiJson.setMessage("修改成功");
        return apiJson;
    }


}
