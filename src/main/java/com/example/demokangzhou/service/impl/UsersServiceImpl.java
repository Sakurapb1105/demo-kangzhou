package com.example.demokangzhou.service.impl;

import com.example.demokangzhou.entity.Users;
import com.example.demokangzhou.mapper.UsersMapper;
import com.example.demokangzhou.service.IUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
