package com.example.demokangzhou.controller;

import com.example.demokangzhou.common.Result;
import com.example.demokangzhou.entity.Tasks;
import com.example.demokangzhou.service.ITasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 任务表 前端控制器
 * </p>
 *
 * @author Sakurapb1105
 * @since 2026-03-24
 */
@RestController
@RequestMapping("/tasks")
public class TasksController {

    @Autowired
    private ITasksService tasksService;

    /**
     * 1. 在项目下创建任务
     */
    @CrossOrigin
    @PostMapping("/create")
    public Result<String> createTask(@RequestBody Tasks task) {
        if (task.getProjectId() == null) {
            return Result.error("项目归属不能为空");
        }
        // 这里的 title 根据你数据库的字段可能是 name，如果是 name 请改为 task.getName()
        if (task.getTitle() == null || task.getTitle().trim().isEmpty()) {
            return Result.error("任务标题不能为空");
        }
        return tasksService.createTask(task);
    }

    /**
     * 2. 查询项目的任务列表 (支持筛选)
     */
    @CrossOrigin
    @GetMapping("/list")
    public Result<List<Tasks>> getProjectTasks(
            @RequestParam Long projectId,
            @RequestParam(required = false) Byte status,
            @RequestParam(required = false) Byte priority) {

        if (projectId == null) {
            return Result.error("项目ID不能为空");
        }
        return tasksService.getProjectTasks(projectId, status, priority);
    }

    /**
     * 3. 改变任务状态 (拖拽看板时触发)
     */
    @CrossOrigin
    @PostMapping("/status")
    public Result<String> updateTaskStatus(Long taskId, Byte status) {
        if (taskId == null || status == null) {
            return Result.error("参数不完整");
        }
        return tasksService.updateTaskStatus(taskId, status);
    }

    /**
     * 4. 指派任务给其他用户
     */
    @CrossOrigin
    @PostMapping("/assign")
    public Result<String> assignTask(Long taskId, Long assigneeId) {
        if (taskId == null || assigneeId == null) {
            return Result.error("参数不完整");
        }
        return tasksService.assignTask(taskId, assigneeId);
    }

    /**
     * 5. 修改任务信息
     */
    @PostMapping("/update")
    public Result<String> updateTask(@RequestBody Tasks task) {
        if (task.getId() == null) {
            return Result.error("任务ID不能为空");
        }
        return tasksService.updateTask(task);
    }

    /**
     * 6. 删除任务
     */
    @PostMapping("/delete")
    public Result<String> deleteTask(Long taskId) {
        if (taskId == null) {
            return Result.error("任务ID不能为空");
        }
        return tasksService.deleteTask(taskId);
    }
}
