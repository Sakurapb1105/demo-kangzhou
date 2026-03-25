package com.example.demokangzhou.controller;

import com.example.demokangzhou.common.Result;
import com.example.demokangzhou.entity.Projects;
import com.example.demokangzhou.service.IProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 项目表 前端控制器
 * </p>
 *
 * @author Sakurapb1105
 * @since 2026-03-24
 */
@RestController
@RequestMapping("/projects")
public class ProjectsController {
    @Autowired
    private IProjectsService projectsService;

    /**
     * 1. 新建项目
     * 接收前端传来的 JSON 数据 (包含 name 和 description)
     */
    @CrossOrigin
    @PostMapping("/create")
    public Result<String> createProject(@RequestBody Projects project) {
        // 简单的参数校验
        if (project.getName() == null || project.getName().trim().isEmpty()) {
            return Result.error("项目名称不能为空");
        }
        return projectsService.createProject(project.getName(), project.getDescription());
    }

    /**
     * 2. 获取我的项目列表
     */
    @CrossOrigin
    @GetMapping("/list")
    public Result<List<Projects>> getMyProjects() {
        return projectsService.getMyProjects();
    }
}
