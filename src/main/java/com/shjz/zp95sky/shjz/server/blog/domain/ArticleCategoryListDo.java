package com.shjz.zp95sky.shjz.server.blog.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author 华夏紫穹
 */
@ApiModel(value = "ArticleCategoryListDo 文章类型信息", description = "文章类型信息")
@Data
@Builder
public class ArticleCategoryListDo {

    @ApiModelProperty("类型 ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long categoryId;

    @ApiModelProperty("类型名称")
    private String categoryName;

    @ApiModelProperty("类型创建时间")
    private LocalDateTime createTime;

}
