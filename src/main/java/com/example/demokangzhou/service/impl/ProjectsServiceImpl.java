package com.example.demokangzhou.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demokangzhou.common.Result;
import com.example.demokangzhou.entity.Projects;
import com.example.demokangzhou.mapper.ProjectsMapper;
import com.example.demokangzhou.service.IProjectsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demokangzhou.utils.UserContext;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 项目表 服务实现类
 * </p>
 *
 * @author Sakurapb1105
 * @since 2026-03-24
 */
@Service
public class ProjectsServiceImpl extends ServiceImpl<ProjectsMapper, Projects> implements IProjectsService {
    @Override
    public Result<String> createProject(String name, String description) {
        // 1. 取出当前发送请求的用户的 ID
        Long currentUserId = UserContext.getUserId();

        // 2. 组装项目实体类
        Projects project = new Projects();
        project.setName(name);
        project.setDescription(description);
        // 核心：把当前登录用户设为该项目的创建者！
        project.setCreatorId(currentUserId);

        this.save(project);

        return Result.success("项目创建成功", null);
    }

    @Override
    public Result<List<Projects>> getMyProjects() {
        // 1. 获取当前登录用户 ID
        Long currentUserId = UserContext.getUserId();

        // 2. 去数据库查询所有 creator_id 是当前用户的项目
        LambdaQueryWrapper<Projects> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Projects::getCreatorId, currentUserId)
                // 按照创建时间倒序排列
                .orderByDesc(Projects::getCreatedAt);

        List<Projects> list = this.list(wrapper);

        return Result.success("查询成功", list);
    }
}
