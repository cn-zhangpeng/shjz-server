package com.shjz.zp95sky.shjz.server.blog.controller;

import com.shjz.zp95sky.shjz.server.blog.biz.BlogStatisticsBiz;
import com.shjz.zp95sky.shjz.server.blog.domain.ArticleStatisticsCountDayDo;
import com.shjz.zp95sky.shjz.server.common.response.BaseResult;
import com.shjz.zp95sky.shjz.server.common.response.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 山海紫穹
 */
@Api(value = "博客统计", tags = "博客统计接口")
@RestController
@RequestMapping(value = "/blog/statistics")
@RequiredArgsConstructor(onConstructor = @__({ @Autowired}))
public class BlogStatisticsController {

    private final BlogStatisticsBiz blogStatisticsBiz;

    @ApiOperation("文章数量天统计信息")
    @GetMapping(value = "/articleCount/day")
    public BaseResult<List<ArticleStatisticsCountDayDo>> getArticleCountDay() {
        List<ArticleStatisticsCountDayDo> countDayDoList = blogStatisticsBiz.getArticleStatisticsCountDay();
        return ResultUtil.buildResultSuccess(countDayDoList);
    }

}
