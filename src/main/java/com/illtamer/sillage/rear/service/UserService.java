package com.illtamer.sillage.rear.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.illtamer.sillage.rear.config.GlobalCache;
import com.illtamer.sillage.rear.entity.LoginUser;
import com.illtamer.sillage.rear.mapper.UserMapper;
import com.illtamer.sillage.rear.pojo.User;
import com.illtamer.sillage.rear.util.JwtUtil;
import com.illtamer.sillage.rear.vo.AuthData;
import com.illtamer.sillage.rear.vo.JwtToken;
import com.illtamer.sillage.rear.vo.MinUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService extends ServiceImpl<UserMapper, User> {

    private final GlobalCache globalCache;
    private final AuthenticationManager authenticationManager;

    public UserService(
            GlobalCache globalCache,
            AuthenticationManager authenticationManager
    ) {
        this.globalCache = globalCache;
        this.authenticationManager = authenticationManager;
    }

    /**
     * 登录 + Jwt 认证
     * */
    public JwtToken login(AuthData authData) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        authData.getUsername(),
                        authData.getPassword()
                );
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if (authenticate == null) {
            throw new RuntimeException("用户名或密码错误");
        }
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        // 根据用户 id 生成 token
        String token = JwtUtil.createJWT(userId);
        globalCache.put(userId, loginUser);
        return new JwtToken(token);
    }

    /**
     * 注销
     * */
    public boolean loginOut() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof UsernamePasswordAuthenticationToken)) return false;
        // 获取 SecurityContextHolder 中用户id
        UsernamePasswordAuthenticationToken upAuthenticationToken = (UsernamePasswordAuthenticationToken) authentication;
        LoginUser loginUser = (LoginUser) upAuthenticationToken.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        return globalCache.remove(userId) != null;
    }

    /**
     * 查询最简用户 VO
     * */
    public MinUser getMinUserById(Integer id) {
        final User user = getOne(Wrappers.lambdaQuery(User.class)
                .select(User::getId, User::getNick, User::getAvatar)
                .eq(User::getId, id));
        final MinUser minUser = new MinUser();
        BeanUtils.copyProperties(user, minUser);
        return minUser;
    }

    /**
     * 从已发放的有效 Jwt Token 中获取登录用户实例
     * */
    @Nullable
    public LoginUser getLoginUser(String userId) {
        if (userId == null || userId.length() == 0) return null;
        LoginUser loginUser = (LoginUser) globalCache.get(userId);
        if (loginUser != null) return loginUser;
        try {
            final User user = getById(Integer.parseInt(userId));
            return new LoginUser(user);
        } catch (Exception e) {
            log.error("Unknown error occurred when re-cache login user {}", userId, e);
        }
        return null;
    }

}
