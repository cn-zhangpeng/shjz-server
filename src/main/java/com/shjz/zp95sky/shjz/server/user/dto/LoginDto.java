package com.shjz.zp95sky.shjz.server.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 登录参数
 * @author 山海紫穹
 * @date 2021年04月08日 17:12
 */
@ApiModel(value = "LoginDto 登录参数", description = "登录参数")
@Data
public class LoginDto {

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

}
