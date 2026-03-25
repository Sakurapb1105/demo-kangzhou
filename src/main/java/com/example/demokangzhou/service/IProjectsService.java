package com.example.demokangzhou.service;

import com.example.demokangzhou.common.Result;
import com.example.demokangzhou.entity.Projects;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 项目表 服务类
 * </p>
 *
 * @author Sakurapb1105
 * @since 2026-03-24
 */
public interface IProjectsService extends IService<Projects> {
    // 1. 创建项目
    Result<String> createProject(String name, String description);

    // 2. 获取当前登录用户的项目列表
    Result<List<Projects>> getMyProjects();

}
