package com.shjz.zp95sky.shjz.server.user.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 登录响应结果
 * @author 山海紫穹
 * @date 2021年04月08日 17:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginDo {

    @ApiModelProperty("姓名")
    private String username;

    @ApiModelProperty("token")
    private String accessToken;

}
