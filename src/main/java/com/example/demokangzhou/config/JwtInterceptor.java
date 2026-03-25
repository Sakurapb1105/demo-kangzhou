package com.example.demokangzhou.config;

import com.example.demokangzhou.common.Result;
import com.example.demokangzhou.utils.JwtUtils;
import com.example.demokangzhou.utils.UserContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.micrometer.common.lang.NonNull;
import io.micrometer.common.lang.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {
        // 1. 从请求头中获取名为 "Authorization" 的 token
        String token = request.getHeader("Authorization");

        // 2. 检查 token 是否为空
        if (token == null || token.trim().isEmpty()) {
            return returnErrorResponse(response, "未登录，请先登录");
        }

        try {
            // 3. 解析 Token
            Claims claims = JwtUtils.parseToken(token);

            // 4. 解析成功，提取 userId 并存入UserContext中
            Long userId = claims.get("userId", Long.class);
            UserContext.setUserId(userId);

            // 5. 放行请求
            return true;

        } catch (Exception e) {
            // 如果解析报错，说明 Token 无效或过期，返回错误响应
            return returnErrorResponse(response, "Token无效或已过期，请重新登录");
        }
    }

    @Override
    public void afterCompletion(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @Nullable Object handler, Exception ex) {
        UserContext.remove();
    }

    /**
     * 内部方法：用标准 JSON 格式给前端返回 401 报错
     */
    private boolean returnErrorResponse(HttpServletResponse response, String message) throws Exception {
        response.setContentType("application/json;charset=utf-8");
        // 返回 401 状态码
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        Result<String> errorResult = Result.error(message);
        // 使用 Jackson 把 Result 对象转成 JSON 字符串写回前端
        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(mapper.writeValueAsString(errorResult));
        return false; // false 代表拦截该请求，不让它继续往后走了
    }
}