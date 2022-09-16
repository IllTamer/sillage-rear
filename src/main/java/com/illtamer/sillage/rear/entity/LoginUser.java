package com.illtamer.sillage.rear.entity;

import com.illtamer.sillage.rear.pojo.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * SpringSecurity 用户实体类
 * */
@Getter
public class LoginUser implements UserDetails {

    private final String username;
    private final String password;
    private final boolean accountNonExpired;
    private final boolean accountNonLocked;
    private final boolean credentialsNonExpired;
    private final boolean enabled;
    private final Collection<? extends GrantedAuthority> authorities;
    private final User user;

    public LoginUser(User user) {
        this(user, AuthorityUtils.createAuthorityList(RoleType.getRoleType(user.getType()).getRole()));
    }

    public LoginUser(User user, Collection<? extends GrantedAuthority> authorities) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
        this.authorities = authorities;
        this.user = user;
    }

}
