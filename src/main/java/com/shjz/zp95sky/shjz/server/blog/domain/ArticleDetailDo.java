package com.shjz.zp95sky.shjz.server.blog.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author 山海紫穹
 */
@ApiModel(value = "ArticleDetailDo 文章详情", description = "文章详情")
@Data
@Builder
public class ArticleDetailDo {

    @ApiModelProperty("文章ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long articleId;

    @ApiModelProperty("类型名称")
    private String categoryName;

    @ApiModelProperty("文章标题")
    private String articleTitle;

    @ApiModelProperty("文章内容")
    private String articleContent;

    @ApiModelProperty(value = "文章标签列表", dataType = "array")
    private List<String> articleTagList;

    @ApiModelProperty("是否原创")
    private Boolean isOriginal;

    @ApiModelProperty("文章创建时间（yyyy-MM-dd HH:mm）")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createTime;

}
