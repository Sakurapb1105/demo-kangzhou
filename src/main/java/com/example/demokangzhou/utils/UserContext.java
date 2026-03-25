package com.example.demokangzhou.utils;

/**
 * 用户上下文工具类：用于在同一个线程内共享当前登录用户的 ID
 */
public class UserContext {
    private static final ThreadLocal<Long> USER_ID_THREAD_LOCAL = new ThreadLocal<>();

    public static void setUserId(Long userId) {
        USER_ID_THREAD_LOCAL.set(userId);
    }

    public static Long getUserId() {
        return USER_ID_THREAD_LOCAL.get();
    }

    // 请求结束后清除，防止内存泄漏和串号
    public static void remove() {
        USER_ID_THREAD_LOCAL.remove();
    }
}