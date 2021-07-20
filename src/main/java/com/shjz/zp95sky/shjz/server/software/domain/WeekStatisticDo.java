package com.shjz.zp95sky.shjz.server.software.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * @author 山海紫穹
 * @date 2021年07月02日 14:26
 */
@ApiModel("最近一周软件使用时长统计")
@Data
@Builder
public class WeekStatisticDo {

    @ApiModelProperty("时间列表")
    @JsonFormat(pattern = "MM-dd")
    private List<LocalDate> dateList;

    @ApiModelProperty("使用详情对应的软件名称列表")
    private List<String> nameList;

    @ApiModelProperty("使用详情")
    private List<WeekStatisticUseTimeDo> useTimeDoList;

}
