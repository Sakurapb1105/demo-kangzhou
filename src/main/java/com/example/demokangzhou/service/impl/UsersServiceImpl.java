package com.example.demokangzhou.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demokangzhou.common.Result;
import com.example.demokangzhou.entity.Users;
import com.example.demokangzhou.mapper.UsersMapper;
import com.example.demokangzhou.service.IUsersService;
import com.example.demokangzhou.utils.JwtUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Sakurapb1105
 * @since 2026-03-24
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {

    // 实例化
    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public Result<String> register(String username, String password) {
        // 1. 检查防重
        if (this.count(new LambdaQueryWrapper<Users>().eq(Users::getUsername, username)) > 0) {
            return Result.error("用户名已存在");
        }

        // 2. 密码加密入库
        Users user = new Users();
        user.setUsername(username);
        // 使用 passwordEncoder.encode() 自动生成盐值并加密
        user.setPasswordHash(passwordEncoder.encode(password));
        this.save(user);

        return Result.success("注册成功", null);
    }

    @Override
    public Result<Map<String, Object>> login(String username, String password) {
        // 1. 查账号
        Users user = this.getOne(new LambdaQueryWrapper<Users>().eq(Users::getUsername, username));
        if (user == null) {
            return Result.error("账号不存在");
        }

        // 2. 校验密文密码
        if (!passwordEncoder.matches(password, user.getPasswordHash())) {
            return Result.error("密码错误");
        }

        // 3. 校验通过，生成 JWT
        String token = JwtUtils.generateToken(user.getId(), user.getUsername());

        // 4. 封装返回结果
        Map<String, Object> result = new HashMap<>();
        result.put("token", token);
        result.put("userId", user.getId());
        result.put("username", user.getUsername());

        return Result.success("登录成功", result);
    }
}
