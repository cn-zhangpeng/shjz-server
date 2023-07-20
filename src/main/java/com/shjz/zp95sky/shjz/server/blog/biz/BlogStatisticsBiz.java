package com.shjz.zp95sky.shjz.server.blog.biz;

import com.shjz.zp95sky.shjz.server.blog.domain.ArticleStatisticsCountDayDo;

import java.util.List;

/**
 * 博客统计业务
 */
public interface BlogStatisticsBiz {

    /**
     * 查询文章天汇总信息
     * @return {@link ArticleStatisticsCountDayDo} 文章汇总信息
     */
    List<ArticleStatisticsCountDayDo> getArticleStatisticsCountDay();

}
