package com.example.demokangzhou.service;

import com.example.demokangzhou.common.Result;
import com.example.demokangzhou.entity.Tasks;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 任务表 服务类
 * </p>
 *
 * @author Sakurapb1105
 * @since 2026-03-24
 */
public interface ITasksService extends IService<Tasks> {
    // 1. 创建任务
    Result<String> createTask(Tasks task);

    // 2. 获取项目下的任务列表 (支持按状态和优先级过滤)
    Result<List<Tasks>> getProjectTasks(Long projectId, Byte status, Byte priority);

    // 3. 任务状态流转 (0:待处理, 1:进行中, 2:已完成)
    Result<String> updateTaskStatus(Long taskId, Byte status);

    // 4. 指派任务给其他人
    Result<String> assignTask(Long taskId, Long assigneeId);
    // 5. 修改任务信息
    Result<String> updateTask(Tasks task);

    // 6. 删除任务
    Result<String> deleteTask(Long taskId);

}
