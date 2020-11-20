package com.hr_manage.controller;

import com.hr_manage.config.JwtConfigProperties;
import com.hr_manage.dto.ApiJson;
import com.hr_manage.entity.User;
import com.hr_manage.service.IUserService;
import com.hr_manage.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * 用户信息注册登录接口Controller类
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private IUserService userService;


    @Autowired
    private ApiJson apiJson;

    @Autowired
    private JwtConfigProperties jwtConfigProperties;


    //登录操作
    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    public ApiJson login(User user, HttpServletRequest request) {
        String username = user.getUsername();
        String password = user.getPassword();
        User u = userService.login(username, password);
        if (u == null) {
            apiJson.setCode(0);
            apiJson.setMessage("账号或密码错误");
            return apiJson;
        } else {
            HttpSession session1 = request.getSession();
            session1.setAttribute("session_user", user);
            session1.setMaxInactiveInterval(-1);
            apiJson.setCode(1);
            apiJson.setSuccess(true);
            apiJson.setToken(JWTUtil.sign(u.getUsername(), jwtConfigProperties));
            apiJson.setMessage("登录成功");
            return apiJson;
        }
    }


    //检测用户名是否唯一
    @RequestMapping("/check_username.do")
    public ApiJson check_username(String username) {
        if (username == null) {
            apiJson.setCode(0);
            apiJson.setMessage("用户名不能为空");
            return apiJson;
        }
        int row = userService.checkUsername(username);
        if (row > 0) {
            apiJson.setCode(1);
            apiJson.setExist(true);
            apiJson.setMessage("用户名已经存在");
            return apiJson;
        }
        apiJson.setCode(1);
        apiJson.setExist(false);
        apiJson.setMessage("用户名不存在");
        return apiJson;
    }

    //注册操作
    @RequestMapping("/register.do")
    public ApiJson register(User user) {
        int su = userService.register(user);
        if (su == 0) {
            apiJson.setCode(0);
            apiJson.setMessage("注册失败");
            return apiJson;
        }
        apiJson.setCode(1);
        apiJson.setSuccess(true);
        apiJson.setMessage("注册成功");
        return apiJson;
    }


    //退出登录
    @RequestMapping("/logout.do")
    public ApiJson outUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("session_user");
        apiJson.setCode(1);
        return apiJson;
    }

    //未登录
    @RequestMapping("/unlogin.do")
    public ApiJson unlogin(String msg) throws UnsupportedEncodingException {
        apiJson.setCode(2);
        apiJson.setMessage(new String(msg.getBytes(), "UTF-8"));
        return apiJson;

    }

    //更改密码
    @RequestMapping("/reset_password.do")
    public ApiJson update(String username, String password) {
        User user = userService.findByUsername(username);
        user.setPassword(password);
        if (user != null) {
            int i = userService.update(user);
            if (i == 1){
                apiJson.setCode(1);
                apiJson.setMessage("修改密码成功");
                apiJson.setSuccess(true);
            }
        }
        return apiJson;
    }

}
