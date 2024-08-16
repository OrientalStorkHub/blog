package com.orientalstorkhub.blog.common.model.vo.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponseVo {
    private String  accessToken;
    private String refreshToken;
    private long expiresAt;
    private long refreshExpiresAt;
    private Integer userId;
    private String username;
    private String email;
}
