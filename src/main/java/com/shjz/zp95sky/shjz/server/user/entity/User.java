package com.shjz.zp95sky.shjz.server.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 用户实体
 * @author 华夏紫穹
 */
@Data
@Builder
@TableName("user")
public class User {

    /** 用户 ID */
    @TableId(type=IdType.INPUT)
    private Long id;

    /**  */
    @TableField("username")
    private String username;

    /** 密码 */
    @TableField("password")
    @JsonIgnore
    private String password;

    /** 昵称 */
    @TableField("nickname")
    private String nickname;

    /** 性别 */
    @TableField("sex")
    private String sex;

    /** 出生日期 */
    @TableField("birthday")
    private LocalDate birthday;

    /** 头像 */
    @TableField("avatar")
    private String avatar;

    /** 格言 */
    @TableField( "maxim")
    private String maxim;

    /** 电话 */
    @TableField("phone")
    private String phone;

    /** 邮箱 */
    @TableField("email")
    private String email;

    /** QQ */
    @TableField("qq")
    private String qq;

    /** 地区 */
    @TableField("location")
    private String location;

    /** 创建时间 */
    @TableField("create_time")
    private LocalDateTime createTime;

}
