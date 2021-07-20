package com.shjz.zp95sky.shjz.server.blog.dto;

import com.shjz.zp95sky.shjz.server.common.entity.CustomPage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author 山海紫穹
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "AddCategoryDto 文章类型信息", description = "文章类型信息")
@Data
@NoArgsConstructor
@SuperBuilder
public class GetArticleListByKeywordDto extends CustomPage {

    @ApiModelProperty("搜索关键字")
    private String keyword;

}
