package com.shjz.zp95sky.shjz.server.software.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @author 山海紫穹
 * @date 2021年07月21日 11:01
 */
@ApiModel("本年度软件使用详情")
@Data
@Builder
public class YearStatisticDo {

    @ApiModelProperty("软件名称")
    private String softwareName;

    @ApiModelProperty("软件使用时长")
    private Integer useTimeLength;

}
