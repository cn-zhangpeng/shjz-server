package com.shjz.zp95sky.shjz.server.blog.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 华夏紫穹
 * @date 2021年07月15日 17:42
 */
@ApiModel(value = "ChangeOriginalDto 修改文章原创标识", description = "修改文章原创标识参数")
@Data
public class ChangeOriginalDto {

    @ApiModelProperty("是否是原创，true：原创，false：非原创")
    private Boolean isOriginal;

}
