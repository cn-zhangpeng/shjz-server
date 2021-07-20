package com.shjz.zp95sky.shjz.server.software.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

/**
 * 使用软件
 * @author 山海紫穹
 * @date 2021年06月23日 14:06
 */
@TableName("software")
@Data
@Builder
public class Software {

    @TableId
    private Long id;

    @TableField("software_name")
    private String softwareName;

}
