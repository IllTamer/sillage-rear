package com.illtamer.sillage.rear.config.security;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.illtamer.sillage.rear.entity.LoginUser;
import com.illtamer.sillage.rear.mapper.UserMapper;
import com.illtamer.sillage.rear.pojo.User;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * SpringSecurity 用户 Service 类
 * <p>
 * Expose UserDetailsService as a service {@link WebSecurityConfigurerAdapter#configure(AuthenticationManagerBuilder)}
 * */
@Service
public class LoginUserService implements UserDetailsService {

    private final UserMapper userMapper;

    public LoginUserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, username);
        final User user = userMapper.selectOne(wrapper);
        if (user == null)
            throw new UsernameNotFoundException(username);
        return new LoginUser(user);
    }

}
