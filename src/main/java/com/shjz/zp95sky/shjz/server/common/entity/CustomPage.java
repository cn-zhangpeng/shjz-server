package com.shjz.zp95sky.shjz.server.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author 山海紫穹
 */
@ApiModel(value = "CustomPage 分页信息", description = "分页信息")
@Data
@NoArgsConstructor
@SuperBuilder
public class CustomPage {

    @ApiModelProperty("当前页")
    private Integer page;

    @ApiModelProperty("查询条数")
    private Integer pageSize;

}
