package com.illtamer.sillage.rear.config.security;

import com.illtamer.sillage.rear.config.GlobalCache;
import com.illtamer.sillage.rear.entity.LoginUser;
import com.illtamer.sillage.rear.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * JWT Token 过滤器
 * <p>
 * Headers:
 * <br>
 * token: jwt-string
 * */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private final GlobalCache globalCache;

    public JwtAuthenticationTokenFilter(GlobalCache globalCache) {
        this.globalCache = globalCache;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)) {
            // 跨域 OPTIONS 预请求放行，反馈响应头（不带特殊请求头
            if (request.getMethod().equals("OPTIONS")) {
                response.setStatus(200);
                response.setHeader("Access-Control-Allow-Origin", "*");
                response.setHeader("Access-Control-Allow-Headers", "*");
                response.setHeader("Access-Control-Allow-Methods", "*");
                return;
            }
            // TODO json response
            // 直接放行 让后面原生的 UsernamePasswordAuthenticationFilter 去拦截
            filterChain.doFilter(request, response);
            return;
        }
        // 解析 token
        String userId;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userId = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Token parse error");
        }

        // 获取用户信息
        LoginUser loginUser = (LoginUser) globalCache.get(userId);
        if (Objects.isNull(loginUser)){
            throw new RuntimeException("用户未登录或登录信息已过期");
        }

        // 将用户信息存入 SecurityContextHolder
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                    loginUser,
                    null,
                    null)
        );
        // 放行
        filterChain.doFilter(request, response);
    }

}
