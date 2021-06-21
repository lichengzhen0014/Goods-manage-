package com.kcsj.admin.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lcz
 * @create 2021-06-07-10:20
 */
@Order(value = Ordered.HIGHEST_PRECEDENCE)
@Component
public class CustomerHandlerExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response,
                                         Object handler, Exception ex) {

//        try {
//            response.sendError(511,"我喜欢的错误！");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return new ModelAndView();
        return null;
    }
}
