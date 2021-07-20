package com.shjz.zp95sky.shjz.server.blog.service;

import com.shjz.zp95sky.shjz.server.annotation.CommonTestAnnotation;
import com.shjz.zp95sky.shjz.server.blog.domain.ArticleStatisticsCountDayDo;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * 博客统计 Service 测试
 * @author 山海紫穹
 */
@CommonTestAnnotation
public class BlogStatisticsServiceTest {

    @Resource
    private BlogStatisticsService statisticsService;

    @Test
    public void testGetArticleStatisticsCountDay() {
        List<ArticleStatisticsCountDayDo> articleStatisticsCountDayDoList = statisticsService.getArticleStatisticsCountDay();
        System.out.println("period: day, size: " + articleStatisticsCountDayDoList.size());
    }

}
