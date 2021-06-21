package com.kcsj.admin.servlet;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * @author lcz
 * @create 2021-06-05-14:42
 */
@Configuration(proxyBeanMethods = true)
public class Mode2RegistrationBeanConfig {

    @Bean
    public ServletRegistrationBean myServlet(){
        MyServlet myServlet = new MyServlet();
        return new ServletRegistrationBean(myServlet,"/my","/my02");
    }

    @Bean
    public FilterRegistrationBean myFilter(){
        MyFilter myFilter = new MyFilter();
        FilterRegistrationBean filterFilterRegistrationBean = new FilterRegistrationBean(myFilter);
        filterFilterRegistrationBean.setUrlPatterns(Arrays.asList("/my","/my02"));
        return filterFilterRegistrationBean;
    }

    @Bean
    public ServletListenerRegistrationBean myServletListener(){
        MyServletContextListener myServletContextListener = new MyServletContextListener();
        return new ServletListenerRegistrationBean(myServletContextListener);
    }


}
