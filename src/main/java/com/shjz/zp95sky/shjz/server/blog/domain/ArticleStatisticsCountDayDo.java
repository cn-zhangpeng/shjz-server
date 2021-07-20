package com.shjz.zp95sky.shjz.server.blog.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

/**
 * @author 华夏紫穹
 */
@ApiModel(value = "ArticleStatisticsCountDayDo 文章天统计信息", description = "文章天统计信息")
@Data
@Builder
public class ArticleStatisticsCountDayDo {

    @ApiModelProperty("统计日期（yyyy-MM-dd）")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate statisticDate;

    @ApiModelProperty("文章数量")
    private Integer articleCount;

}
