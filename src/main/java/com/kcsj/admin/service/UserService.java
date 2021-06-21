package com.kcsj.admin.service;

import com.kcsj.admin.bean.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kcsj.admin.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lcz
 * @create 2021-06-07-21:15
 */
public interface UserService extends IService<User> {

    Boolean loginVerification(User user);
    User queryUserByUserNameAndPassword(User user);
    User queryUser(Integer id);
}
