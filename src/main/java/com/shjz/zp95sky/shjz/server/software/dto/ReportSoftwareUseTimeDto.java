package com.shjz.zp95sky.shjz.server.software.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 山海紫穹
 * @date 2021年06月24日 13:56
 */
@ApiModel("添加软件参数")
@Data
public class ReportSoftwareUseTimeDto {

    @ApiModelProperty("软件ID")
    private Long softwareId;

    /** {@link com.shjz.zp95sky.shjz.server.software.enums.SoftwareDeviceTypeEnum} */
    @ApiModelProperty("设备类型，pc：电脑端，mobile：移动端")
    private String deviceType;

    @ApiModelProperty("软件使用时间，单位：分钟")
    private Integer useTime;

}
