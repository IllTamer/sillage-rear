package com.illtamer.sillage.rear.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
//                .successHandler(authenticationHandler)
//                .failureHandler(authenticationHandler)
                .permitAll()
            .and().logout()
                .logoutUrl("/login/out")
                .logoutSuccessUrl("/")
                .permitAll()
            .and().authorizeRequests()
                .antMatchers("/error", "/favicon.ico",
                        "/", "/index", "/types/**", "/tags/**", "/archive/**", "/about",
                        "/login/*",
                        "/api/**"
                ).permitAll()
                .antMatchers(
                        "/admin/**"
                ).hasRole("ADMIN")
            .anyRequest().permitAll()
            .and().exceptionHandling()
                .accessDeniedPage("/error")
            .and().csrf().disable();
    }

    /**
     * Expose PasswordEncoder as a bean
     * */
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
