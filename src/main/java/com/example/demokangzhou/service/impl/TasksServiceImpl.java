package com.example.demokangzhou.service.impl;

import com.example.demokangzhou.entity.Tasks;
import com.example.demokangzhou.mapper.TasksMapper;
import com.example.demokangzhou.service.ITasksService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 任务表 服务实现类
 * </p>
 *
 * @author Sakurapb1105
 * @since 2026-03-24
 */
@Service
public class TasksServiceImpl extends ServiceImpl<TasksMapper, Tasks> implements ITasksService {

}
