package com.orientalstorkhub.blog.common.model.vo;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
@AllArgsConstructor
@Builder
public class UserVo {
    private Integer id;
    private String username;
    private String email;
    private String password;
    private Integer role;
    //登录模式：1 用户名登录；2 邮箱登录
    @NotBlank(message = "登录模式不能为空")
    private Integer mode;


}
