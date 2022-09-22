package com.illtamer.sillage.rear.annotation;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

/**
 * 注解获取 {@link com.illtamer.sillage.rear.pojo.User}
 * Example:
 *      //    @RequestMapping
 *      //    public String charts(@SecurityUser User user) {
 *      //    }
 * */
@Documented
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Order(Ordered.HIGHEST_PRECEDENCE) // 最高优先级
public @interface SecurityUser {

}
