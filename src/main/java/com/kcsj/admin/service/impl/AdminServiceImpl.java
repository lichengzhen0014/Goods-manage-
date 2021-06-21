package com.kcsj.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kcsj.admin.bean.Admin;
import com.kcsj.admin.bean.User;
import com.kcsj.admin.mapper.AdminMapper;
import com.kcsj.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lcz
 * @create 2021-06-08-13:49
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Autowired
    AdminMapper adminMapper;

    @Override
    public Boolean loginVerification(Admin admin) {
        Admin login = adminMapper.queryAdminByUsernameAndPassword(admin);
        if (login != null){
            return true;
        }
        return false;
    }

    @Override
    public Admin queryAdmin(Admin admin) {
        return adminMapper.queryAdminByUsernameAndPassword(admin);
    }
}
