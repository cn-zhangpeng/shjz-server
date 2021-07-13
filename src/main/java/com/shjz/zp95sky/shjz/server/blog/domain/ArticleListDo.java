package com.shjz.zp95sky.shjz.server.blog.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 华夏紫穹
 */
@ApiModel(value = "ArticleListDo 文章简要信息", description = "文章简要信息")
@Data
@Builder
public class ArticleListDo {

    @ApiModelProperty("文章ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long articleId;

    @ApiModelProperty("类型名称")
    private String categoryName;

    @ApiModelProperty("文章标题")
    private String articleTitle;

    @ApiModelProperty("文章摘要")
    private String articleSummary;

    @ApiModelProperty(value = "文章标签列表", dataType = "array")
    private List<String> articleTagList;

    @ApiModelProperty("是否原创")
    private Boolean isOriginal;

    @ApiModelProperty("文章创建时间")
    private LocalDateTime createTime;

}
