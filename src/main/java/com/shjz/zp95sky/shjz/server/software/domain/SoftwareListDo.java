package com.shjz.zp95sky.shjz.server.software.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @author 山海紫穹
 * @date 2021年07月01日 13:55
 */
@ApiModel("软件")
@Data
@Builder
public class SoftwareListDo {

    @ApiModelProperty("软件ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @ApiModelProperty("软件名称")
    private String softwareName;

}
