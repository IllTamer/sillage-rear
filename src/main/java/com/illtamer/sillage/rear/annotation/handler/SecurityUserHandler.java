package com.illtamer.sillage.rear.annotation.handler;

import com.illtamer.sillage.rear.annotation.SecurityUser;
import com.illtamer.sillage.rear.entity.LoginUser;
import com.illtamer.sillage.rear.pojo.User;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class SecurityUserHandler implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(SecurityUser.class);
    }

    @Override
    public User resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        return ((LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
    }

}
