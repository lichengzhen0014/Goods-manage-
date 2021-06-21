package com.kcsj.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lcz
 * @create 2021-06-10-17:42
 */
@Controller
public class TestController {

    @GetMapping("/test")
    public String test01(){
        return "test";
    }
}
