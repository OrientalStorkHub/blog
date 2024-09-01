package com.orientalstorkhub.blog.common.model.vo.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "登录响应vo")
public class LoginResponseVo {
    @Schema(description = "鉴权密钥",
            example = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIU.zyNTE3MzIy1ns")
    private String  accessToken;
    @Schema(description = "刷新密钥",
            example = "eyJ0eXAiOiJKV1QiLCJhbGciOi.JI6MTcyNTE3AQklY")
    private String refreshToken;
    @Schema(description = "鉴权密钥过期时间", example = "2024-09-01T07:02:05.898+00:00")
    private Timestamp expiresAt;
    @Schema(description = "刷新密钥过期时间", example = "2024-09-01T09:17:05.898+00:00")
    private Timestamp refreshExpiresAt;
    @Schema(description = "用户id", example = "37")
    private Integer userId;
    @Schema(description = "用户名", example = "fewfefedw")
    private String username;
    @Schema(description = "邮箱", example = "cjfijytkuykuykyugE8@qq.com")
    private String email;
    @Schema(description = "昵称", example = "fjfefejytjyiejwi")
    private String nickname;
}
