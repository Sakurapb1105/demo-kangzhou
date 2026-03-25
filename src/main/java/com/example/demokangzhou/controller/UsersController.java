package com.example.demokangzhou.controller;

import com.example.demokangzhou.common.Result;
import com.example.demokangzhou.entity.Users;
import com.example.demokangzhou.service.IUsersService;
import com.example.demokangzhou.utils.UserContext;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author Sakurapb1105
 * @since 2026-03-24
 */
@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private IUsersService usersService;

    // 接收 JSON 参数的 DTO
    @Data
    public static class LoginDTO {
        private String username;
        private String password;
    }

    @PostMapping("/register")
    public Result<String> register(@RequestBody LoginDTO dto) {
        return usersService.register(dto.getUsername(), dto.getPassword());
    }

    @PostMapping("/login")
    public Result<Map<String, Object>> login(@RequestBody LoginDTO dto) {
        return usersService.login(dto.getUsername(), dto.getPassword());
    }

    /**
     * 获取当前登录用户信息
     */
    @GetMapping("/info")
    public Result<Users> getUserInfo() {

        Long currentUserId = UserContext.getUserId();
        Users user = usersService.getById(currentUserId);
        if (user != null) {
            user.setPasswordHash(null);
        }
        return Result.success("获取成功", user);
    }
}
