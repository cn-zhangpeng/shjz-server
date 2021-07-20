package com.shjz.zp95sky.shjz.server.software.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 山海紫穹
 * @date 2021年06月24日 13:34
 */
@ApiModel("添加软件参数")
@Data
public class AddSoftwareDto {

    @ApiModelProperty("软件名称")
    private String softwareName;

}
