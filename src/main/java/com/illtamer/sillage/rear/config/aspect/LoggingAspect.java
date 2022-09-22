package com.illtamer.sillage.rear.config.aspect;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 日志处理切面类
 * */
@Slf4j
@Aspect
@Component
public class LoggingAspect {

    /**
     * 切面 - 所有 controller 同级包内类的所有方法
     * */
    @Pointcut("execution(* com.illtamer.sillage.rear.controller.*.*.*(..))")
    private void point() {}

    @Before("point()")
    protected void doBefore(JoinPoint jp) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        Assert.notNull(attributes);
        HttpServletRequest request = attributes.getRequest();
        RequestObject object = RequestObject.builder()
                .uri(request.getRequestURI())
                .ip(request.getRemoteAddr())
                .invokeMethod(jp.getSignature().getDeclaringTypeName() + '.' + jp.getSignature().getName())
                .args(jp.getArgs())
                .build();
        log.info("Request: {}", object);
    }

    @Data
    @Builder
    private static class RequestObject {
        private String uri;
        private String ip;
        private String invokeMethod;
        private Object[] args;
    }

}
