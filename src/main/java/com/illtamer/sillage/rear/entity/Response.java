package com.illtamer.sillage.rear.entity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 响应结果实体类
 */
@Slf4j
@Data
public class Response implements Serializable {

    private static final long serialVersionUID = 465217954329411031L;

    public static final int STATUS_SUCCESS = 0;
    public static final int STATUS_WARNING = 301;
    public static final int STATUS_ERROR = 500;

    public static final String MSG_SUCCESS = "success";
    public static final String MSG_WARNING = "warning";
    public static final String MSG_ERROR = "error";

    private final Integer status;
    private final String msg;
    private final Object data;

    /**
     * 初始化一个默认成功的响应结果
     */
    public Response() {
        this(STATUS_SUCCESS, MSG_SUCCESS);
    }

    /**
     * 初始化一个新创建的响应结果
     *
     * @param status 状态代码
     * @param msg  返回内容
     */
    public Response(int status, String msg) {
        this(status, msg, null);
    }

    /**
     * 初始化一个新创建的 Response 对象
     *
     * @param status 状态类型
     * @param msg  返回内容
     * @param data 数据对象
     */
    public Response(int status, String msg, @Nullable Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("status", status);
        map.put("msg", msg);
        map.put("data", data);
        return map;
    }

    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static Response success() {
        return Response.success(MSG_SUCCESS);
    }

    /**
     * 返回成功数据
     *
     * @return 成功消息
     */
    public static Response success(Object data) {
        return Response.success(MSG_SUCCESS, data);
    }

    /**
     * 返回成功消息
     *
     * @param msg 返回内容
     * @return 成功消息
     */
    public static Response success(String msg) {
        return Response.success(msg, null);
    }

    /**
     * 返回成功消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static Response success(String msg, Object data) {
        return new Response(STATUS_SUCCESS, msg, data);
    }

    /**
     * 返回警告消息
     *
     * @return 警告消息
     */
    public static Response warn() {
        return Response.warn(MSG_WARNING);
    }

    /**
     * 返回警告消息
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public static Response warn(String msg) {
        return Response.warn(msg, null);
    }

    /**
     * 返回警告消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static Response warn(String msg, HashMap<String, Object> data) {
        return new Response(STATUS_WARNING, msg, data);
    }

    /**
     * 返回错误消息
     *
     * @return 错误消息
     */
    public static Response error() {
        return Response.error(MSG_ERROR);
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @return 错误消息
     */
    public static Response error(String msg) {
        return Response.error(msg, null);
    }

    /**
     * 返回错误消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 错误消息
     */
    public static Response error(String msg, HashMap<String, Object> data) {
        return new Response(STATUS_ERROR, msg, data);
    }

}
