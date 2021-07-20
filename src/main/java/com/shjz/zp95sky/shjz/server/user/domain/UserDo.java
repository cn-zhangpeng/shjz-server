package com.shjz.zp95sky.shjz.server.user.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @author 山海紫穹
 */
@ApiModel(value = "UserDo 用户简要信息", description = "用户简要信息")
@Data
@Builder
public class UserDo {

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("格言")
    private String maxim;

}
