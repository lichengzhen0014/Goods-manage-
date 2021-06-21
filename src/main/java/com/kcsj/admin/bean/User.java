package com.kcsj.admin.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;


/**
 * @author lcz
 * @create 2021-06-07-20:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
public class User {

    @Nullable
    private Integer id;
    private String userName;
    private String password;
    private String email;

}
