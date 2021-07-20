package com.shjz.zp95sky.shjz.server.blog.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 山海紫穹
 */
@ApiModel(value = "PublishArticleDto 文章信息", description = "文章信息")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PublishArticleDto {

    @ApiModelProperty("类型ID")
    private Long categoryId;

    @ApiModelProperty("文章标题")
    private String articleTitle;

    @ApiModelProperty("文章内容")
    private String articleContent;

    @ApiModelProperty(value = "文章标签列表", dataType = "array")
    @JsonProperty("articleTags")
    private List<String> articleTagList;

    @ApiModelProperty("是否原创")
    private Boolean isOriginal;

}
