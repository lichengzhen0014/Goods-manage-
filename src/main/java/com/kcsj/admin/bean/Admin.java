package com.kcsj.admin.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lcz
 * @create 2021-06-08-13:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("admin")
public class Admin {

    private Integer adminId;
    private String account;
    private String password;

}
