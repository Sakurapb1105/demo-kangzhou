package com.example.demokangzhou.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demokangzhou.common.Result;
import com.example.demokangzhou.entity.Tasks;
import com.example.demokangzhou.mapper.TasksMapper;
import com.example.demokangzhou.service.ITasksService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demokangzhou.utils.UserContext;
import org.springframework.stereotype.Service;

import java.util.List;

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
    @Override
    public Result<String> createTask(Tasks task) {
        // 1. 设置创建人为当前登录用户
        task.setCreatorId(UserContext.getUserId());
        // 2. 新建的任务默认状态为 "0-待处理"
        task.setStatus((byte) 0);
        // 3. 如果创建时没选指派人，可以默认指派给自己，或者留空 (这里以留空为例)

        this.save(task);
        return Result.success("任务创建成功", null);
    }

    @Override
    public Result<List<Tasks>> getProjectTasks(Long projectId, Byte status, Byte priority) {
        // 动态拼接查询条件
        LambdaQueryWrapper<Tasks> wrapper = new LambdaQueryWrapper<>();

        // 必须限定是哪个项目的任务
        wrapper.eq(Tasks::getProjectId, projectId);

        // 如果前端传了状态筛选条件，就加上
        if (status != null) {
            wrapper.eq(Tasks::getStatus, status);
        }

        // 如果前端传了优先级筛选条件，就加上
        if (priority != null) {
            wrapper.eq(Tasks::getPriority, priority);
        }

        // 按照创建时间倒序排列
        wrapper.orderByDesc(Tasks::getId);

        List<Tasks> list = this.list(wrapper);
        return Result.success("查询成功", list);
    }

    @Override
    public Result<String> updateTaskStatus(Long taskId, Byte status) {
        Tasks task = this.getById(taskId);
        if (task == null) {
            return Result.error("任务不存在");
        }
        // 状态流转校验
        task.setStatus(status);
        this.updateById(task);
        return Result.success("任务状态更新成功", null);
    }

    @Override
    public Result<String> assignTask(Long taskId, Long assigneeId) {
        Tasks task = this.getById(taskId);
        if (task == null) {
            return Result.error("任务不存在");
        }
        task.setAssigneeId(assigneeId);
        this.updateById(task);
        return Result.success("任务指派成功", null);
    }

    @Override
    public Result<String> updateTask(Tasks task) {
        // updateById 会根据传入对象里的 ID，自动更新其他非空字段
        boolean success = this.updateById(task);
        return success ? Result.success("任务更新成功", null) : Result.error("更新失败，任务可能不存在");
    }

    @Override
    public Result<String> deleteTask(Long taskId) {
        // removeById 直接根据 ID 物理删除（如果你配置了逻辑删除也会自动变逻辑删除）
        boolean success = this.removeById(taskId);
        return success ? Result.success("任务删除成功", null) : Result.error("删除失败");
    }
}
