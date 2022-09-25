package com.illtamer.sillage.rear.config;

import com.illtamer.sillage.rear.annotation.handler.SecurityUserHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebMVCConfiguration implements WebMvcConfigurer {

    // 跨域配置用 @CrossOrigin 代替

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new SecurityUserHandler());
    }

}
