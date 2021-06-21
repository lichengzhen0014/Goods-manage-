package com.kcsj.admin.controller;

import com.kcsj.admin.bean.User;
import com.kcsj.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author lcz
 * @create 2021-06-07-21:03
 */
@Controller
public class RegistController {

    @Autowired
    UserService userService;

    @GetMapping("/register")
    public String registerPage(){
        return "register";
    }

    @PostMapping("/user/register")
    public String userRegister(User user){

        int count = userService.count();
        user.setId(++count);
        userService.save(user);
        return "redirect:/login";
    }

}
