package com.orientalstorkhub.blog.common.pojo.vo.user;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Data
@NoArgsConstructor 
@AllArgsConstructor
@Builder
@Schema(description = "登录请求vo")
public class UserLoginVo {
    @Schema(description = "用户名/邮箱", example = "fheohueh / cjfijytkuykuykyugE8@qq.com",requiredMode = REQUIRED)
    private String usernameOrEmail;
    @Schema(description = "密码", example = "1245435", requiredMode = REQUIRED )
    private String password;
}
