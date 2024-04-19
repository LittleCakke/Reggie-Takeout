package com.itheima.reggie.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author GRS
 * @since 2024/4/18 下午2:14
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Employee implements Serializable
{
    @Serial
    private static final long serialVersionUID = 1L;

    @TableId
    private Long id;

    private String username;

    private String name;

    private String password;

    private String phone;

    private String sex;

    // 身份证号码
    private String idNumber;

    private Integer status;

    private String createTime;

    private String updateTime;

    @TableField(fill = FieldFill.INSERT)
    private Long createUser;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;
}