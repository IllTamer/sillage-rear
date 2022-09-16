//package com.illtamer.sillage.rear.annotation.handler;
//
//import com.illtamer.infinite.sillage.annotation.SecurityUser;
//import com.illtamer.infinite.sillage.pojo.SillageUserDetails;
//import com.illtamer.infinite.sillage.pojo.User;
//import org.springframework.core.MethodParameter;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.support.WebDataBinderFactory;
//import org.springframework.web.context.request.NativeWebRequest;
//import org.springframework.web.method.support.HandlerMethodArgumentResolver;
//import org.springframework.web.method.support.ModelAndViewContainer;
//
//@Component
//public class SecurityUserHandler implements HandlerMethodArgumentResolver {
//
//    @Override
//    public boolean supportsParameter(MethodParameter parameter) {
//        return parameter.hasParameterAnnotation(SecurityUser.class);
//    }
//
//    @Override
//    public User resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
//        return ((SillageUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
//    }
//
//}
