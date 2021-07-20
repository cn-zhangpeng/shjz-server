package com.shjz.zp95sky.shjz.server.software.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

/**
 * @author 山海紫穹
 * @date 2021年07月05日 13:59
 */
@ApiModel("本年每天软件使用统计")
@Data
@Builder
public class YearDateStatisticDo {

    @ApiModelProperty("当前日期")
    private LocalDate curDate;

    @ApiModelProperty("使用时长")
    private Integer useTimeLength;

}
