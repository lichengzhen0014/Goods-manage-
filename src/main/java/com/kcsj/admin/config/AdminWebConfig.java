package com.kcsj.admin.config;

import com.kcsj.admin.interceptor.AdminInterceptor;
import com.kcsj.admin.interceptor.UserInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author lcz
 * @create 2021-06-07-14:17
 */
@Configuration
public class AdminWebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoginInterceptor())
//                .addPathPatterns("/**") //包括静态资源被拦截了
//                .excludePathPatterns("/","/login","/register","/user/register","/index.html",
//                        "/adminLogin","/admin","/admin.html","/adminOut",
//                        "/css/**","/fonts/**","/images/**","/js/**","/myjs/**");

        registry.addInterceptor(new AdminInterceptor())
                .addPathPatterns("/admin.html","/goodsAdopt","/goodsRevoke","receiveAdopt",
                        "/receiveRevoke","/auditUser","/auditUser.html");

        registry.addInterceptor(new UserInterceptor())
                .addPathPatterns("/userAddPage","/userAddGoods","/receive","/user/test");

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry2) {

        //获取文件的真实路径
        String path = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\images\\goods\\";
        //static/upload/**是对应resource下工程目录
        registry2.addResourceHandler("/images/goods/**").addResourceLocations("file:"+path);
    }



}
