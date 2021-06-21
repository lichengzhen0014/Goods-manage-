package com.kcsj.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kcsj.admin.bean.Admin;
import com.kcsj.admin.bean.User;

/**
 * @author lcz
 * @create 2021-06-08-13:48
 */
public interface AdminService extends IService<Admin> {

    Boolean loginVerification(Admin admin);

    Admin queryAdmin(Admin admin);

}
