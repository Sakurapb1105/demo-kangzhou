package com.example.demokangzhou.service.impl;

import com.example.demokangzhou.entity.Projects;
import com.example.demokangzhou.mapper.ProjectsMapper;
import com.example.demokangzhou.service.IProjectsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
