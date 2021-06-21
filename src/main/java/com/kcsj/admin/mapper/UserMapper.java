package com.kcsj.admin.mapper;

import com.kcsj.admin.bean.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lcz
 * @create 2021-06-07-18:30
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    User queryUserByUsernameAndPassword(User user);
    User queryUser(Integer id);
}
