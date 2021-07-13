package com.shjz.zp95sky.shjz.server.blog.entity.es;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 文章总数及文章ID列表
 * @author 华夏紫穹
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleTotalAndIdList {

    /** 文章总数 */
    private Long total;

    /** 文章ID列表 */
    private List<Long> articleIdList;

}
