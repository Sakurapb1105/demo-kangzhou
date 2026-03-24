package com.example.demokangzhou;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class CodeGenerator {
    public static void main(String[] args) {
        // 获取当前项目的根目录路径
        String projectPath = System.getProperty("user.dir");

        // 1. 配置数据库连接
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/team_task_sys?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8",
                        "root", "123456")

                // 2. 全局配置
                .globalConfig(builder -> builder.author("Sakurapb1105")
                        .outputDir(projectPath + "/src/main/java")
                        .disableOpenDir())

                // 3. 包配置
                .packageConfig(builder -> builder.parent("com.example.demokangzhou")
                        .entity("entity")
                        .mapper("mapper")
                        .service("service")
                        .serviceImpl("service.impl")
                        .controller("controller")
                        .pathInfo(Collections.singletonMap(OutputFile.xml, projectPath + "/src/main/resources/mapper")))

                // 4. 策略配置
                .strategyConfig(builder -> builder.addInclude("users", "projects", "tasks")
                        // 实体类配置
                        .entityBuilder()
                        .enableLombok()
                        .enableTableFieldAnnotation()
                        // Controller 配置
                        .controllerBuilder()
                        .enableRestStyle())

                // 5. 模板引擎配置
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}