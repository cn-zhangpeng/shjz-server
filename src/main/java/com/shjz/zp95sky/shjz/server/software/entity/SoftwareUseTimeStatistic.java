package com.shjz.zp95sky.shjz.server.software.entity;

import lombok.Data;

/**
 * 年软件统计
 * @author 山海紫穹
 * @date 2021年07月21日 12:15
 */
@Data
public class SoftwareUseTimeStatistic {

    /** 软件ID */
    private Long softwareId;

    /** 软件使用时长 */
    private Integer useTimeLength;

}
