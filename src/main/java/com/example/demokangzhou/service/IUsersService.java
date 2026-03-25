package com.example.demokangzhou.service;

import com.example.demokangzhou.entity.Users;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demokangzhou.common.Result;

import java.util.Map;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author Sakurapb1105
 * @since 2026-03-24
 */
public interface IUsersService extends IService<Users> {
    // 注册逻辑
    Result<String> register(String username, String password);

    // 登录逻辑
    Result<Map<String, Object>> login(String username, String password);

}
