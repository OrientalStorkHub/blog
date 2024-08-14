package com.orientalstorkhub.blog.common.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.FieldFill;

import java.sql.Timestamp;
import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@TableName("users")  // 对应数据库表名
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)  // 主键字段，设置为自增类型
    private Integer id;

    @TableField("username")  // 用户名字段
    private String username;

    @TableField("password")  // 密码字段
    private String password;


    @TableField("email")  // 邮箱字段
    private String email;

    @TableField(value = "created_at", fill = FieldFill.INSERT)  // 插入时填充的创建时间
    private Timestamp createdAt;

    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE)  // 插入和更新时填充的更新时间
    private Timestamp updatedAt;

    @TableField("role")  // 用户角色字段
    private Integer role;

    @TableField("salt") // 盐字段
    private String salt;
    

}
