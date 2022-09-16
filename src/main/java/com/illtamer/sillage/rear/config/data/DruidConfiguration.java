package com.illtamer.sillage.rear.config.data;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Configuration
@PropertySource("classpath:/database/jdbc.properties")
public class DruidConfiguration {

    @Value("${jdbc.druid.servlet.login-username}")
    private String loginUsername;

    @Value("${jdbc.druid.servlet.login-password}")
    private String loginPassword;

    @Value("${jdbc.druid.servlet.allow}")
    private String allow;

    @Bean("druidDataSource")
    @ConfigurationProperties("jdbc.druid")
    public DataSource getDataSource() {
        return new DruidDataSource();
    }

    /**
     * 注册 Druid Servlet
     * Spring 内置 servlet 容器，故仅能用 ServletRegistrationBean<?> 替代配置
     * */
    @Bean("druidStatViewServlet")
    public ServletRegistrationBean<?> getStatViewServlet() {
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(
                new StatViewServlet(), "/druid/*");
        Map<String, String> initParameters = new HashMap<>();
        initParameters.put("loginUsername", loginUsername);
        initParameters.put("loginPassword", loginPassword);
        initParameters.put("allow", allow);
        bean.setInitParameters(initParameters);
        return bean;
    }

    /**
     * 注册 Druid Filter
     * */
    @Bean("druidWebStatFilter")
    public FilterRegistrationBean<?> getWebStatFilter() {
        FilterRegistrationBean<WebStatFilter> bean = new FilterRegistrationBean<>(new WebStatFilter());
        // 排除监控文件路径
        bean.setInitParameters(Collections.singletonMap("exclusions", "*.js,*.css,/druid/*"));
        bean.setUrlPatterns(Collections.singleton("/*"));
        return bean;
    }

}
