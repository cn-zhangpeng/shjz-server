package com.shjz.zp95sky.shjz.server.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @author 华夏紫穹
 */
@ApiModel(value = "ResetPasswordDto 重置密码参数", description = "重置密码参数")
@Data
@Builder
public class ResetPasswordDto {

    @ApiModelProperty("当前密码")
    private String currentPassword;

    @ApiModelProperty("新密码")
    private String newPassword;

}
