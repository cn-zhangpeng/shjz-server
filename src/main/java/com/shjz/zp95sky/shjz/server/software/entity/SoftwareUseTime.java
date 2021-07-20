package com.shjz.zp95sky.shjz.server.software.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

/**
 * 软件使用时间记录
 * @author 山海紫穹
 * @date 2021年06月23日 13:57
 */
@TableName("software_use_time")
@Data
@Builder
public class SoftwareUseTime {

    @TableId
    private Long id;

    /** 软件ID */
    @TableField("software_id")
    private Long softwareId;

    /** 设备类型，pc：电脑端，mobile：移动端 */
    @TableField("device_type")
    private String deviceType;

    /** 日期 */
    @TableField("cur_date")
    private LocalDate currentDate;

    /** 软件使用时长，单位：分钟 */
    @TableField("use_time_length")
    private Integer useTimeLength;

}
