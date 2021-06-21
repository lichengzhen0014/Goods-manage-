package com.kcsj.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kcsj.admin.bean.Admin;
import com.kcsj.admin.bean.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lcz
 * @create 2021-06-08-13:47
 */
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {
    Admin queryAdminByUsernameAndPassword(Admin admin);
}
