package com.shjz.zp95sky.shjz.server.blog.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 山海紫穹
 */
@ApiModel(value = "AddCategoryDto 文章类型信息", description = "文章类型信息")
@Data
public class AddCategoryDto {

    @ApiModelProperty("文章类型名称")
    private String categoryName;

}
