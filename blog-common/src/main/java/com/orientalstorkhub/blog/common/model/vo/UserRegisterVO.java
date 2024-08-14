package com.orientalstorkhub.blog.common.model.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class UserRegisterVO {
    @NotBlank(message = "用户名不能为空")
    @Size(min = 3, max = 20, message = "用户名长度应在3到20个字符之间")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Size(min = 8, max = 32, message = "密码长度应在8到32个字符之间")
    private String password; // 这里假设前端已进行了初步的密码处理

    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;

    public UserRegisterVO(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }


}
