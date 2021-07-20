package com.shjz.zp95sky.shjz.server.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shjz.zp95sky.shjz.server.blog.domain.ArticleStatisticsCountDayDo;
import com.shjz.zp95sky.shjz.server.blog.entity.ArticleDetail;
import com.shjz.zp95sky.shjz.server.blog.service.ArticleDetailService;
import com.shjz.zp95sky.shjz.server.blog.service.BlogStatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 博客统计业务实现
 * @author 华夏紫穹
 */
@Service
@RequiredArgsConstructor(onConstructor = @__({ @Autowired}))
public class BlogStatisticsServiceImpl implements BlogStatisticsService {

    private final ArticleDetailService detailService;

    @Override
    public List<ArticleStatisticsCountDayDo> getArticleStatisticsCountDay() {
        List<ArticleDetail> articleDetailList = getArticleIdAndCreateTime();
        return constructArticleStatisticsCountDayDo(articleDetailList);
    }

    private List<ArticleDetail> getArticleIdAndCreateTime() {
        LambdaQueryWrapper<ArticleDetail> searchParams = new LambdaQueryWrapper<>();
        searchParams.select(ArticleDetail::getId, ArticleDetail::getCreateTime);
        return detailService.list(searchParams);
    }

    private List<ArticleStatisticsCountDayDo> constructArticleStatisticsCountDayDo(List<ArticleDetail> articleDetailList) {
        Map<LocalDate, Integer> statisticMap = new HashMap<>();
        articleDetailList.forEach(ad -> {
            LocalDate statisticDate = ad.getCreateTime().toLocalDate();
            if (statisticMap.containsKey(statisticDate)) {
                statisticMap.put(statisticDate, statisticMap.get(statisticDate) + 1);
            } else {
                statisticMap.put(statisticDate, 1);
            }
        });

        List<ArticleStatisticsCountDayDo> countDayDoList = new ArrayList<>();
        statisticMap.forEach((key, value) -> {
            ArticleStatisticsCountDayDo countDayDo = ArticleStatisticsCountDayDo.builder().statisticDate(key).articleCount(value).build();
            countDayDoList.add(countDayDo);
        });

        return countDayDoList;
    }

}
