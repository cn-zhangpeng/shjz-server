package com.shjz.zp95sky.shjz.server.user.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

/**
 * @author 山海紫穹
 */
@ApiModel(value = "UserAllDo 用户详细信息", description = "用户详细信息")
@Data
@Builder
public class UserAllDo {

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("性别")
    private String sex;

    @ApiModelProperty("出生日期")
    private LocalDate birthday;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("格言")
    private String maxim;

    @ApiModelProperty("电话")
    private String phone;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("QQ")
    private String qq;

    @ApiModelProperty("地区")
    private String location;

}
