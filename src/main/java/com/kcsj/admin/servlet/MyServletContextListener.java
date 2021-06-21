package com.kcsj.admin.servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author lcz
 * @create 2021-06-05-14:28
 */
@Slf4j
//@WebListener
public class MyServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("MyServletContextListener监听项目已初始化");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("MyServletContextListener监听项目已销毁");
    }
}
