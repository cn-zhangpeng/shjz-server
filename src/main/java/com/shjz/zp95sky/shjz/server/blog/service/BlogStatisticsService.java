package com.shjz.zp95sky.shjz.server.blog.service;

import com.shjz.zp95sky.shjz.server.blog.domain.ArticleStatisticsCountDayDo;

import java.util.List;

/**
 * 博客统计业务接口
 * @author 华夏紫穹
 */
public interface BlogStatisticsService {

    /**
     * 查询文章天汇总信息
     * @return {@link ArticleStatisticsCountDayDo} 文章汇总信息
     */
    List<ArticleStatisticsCountDayDo> getArticleStatisticsCountDay();

}
