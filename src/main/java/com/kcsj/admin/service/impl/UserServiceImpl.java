package com.kcsj.admin.service.impl;

import com.kcsj.admin.bean.User;
import com.kcsj.admin.mapper.UserMapper;
import com.kcsj.admin.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lcz
 * @create 2021-06-07-21:15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public Boolean loginVerification(User user) {
        User login = userMapper.queryUserByUsernameAndPassword(user);
        if (login != null){
            return true;
        }
        return false;
    }

    @Override
    public User queryUserByUserNameAndPassword(User user) {
        return userMapper.queryUserByUsernameAndPassword(user);
    }

    @Override
    public User queryUser(Integer id) {
        User user = userMapper.queryUser(id);
        return user;
    }




}
