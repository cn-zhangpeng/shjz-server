package com.shjz.zp95sky.shjz.server.blog.service;

import com.shjz.zp95sky.shjz.server.annotation.CommonTestAnnotation;
import com.shjz.zp95sky.shjz.server.blog.entity.es.ArticleTotalAndIdList;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

/**
 * 文章详情 ES Service 测试
 * @author 华夏紫穹
 */
@CommonTestAnnotation
public class ArticleDetailEsServiceTest {

    @Resource
    private ArticleDetailEsService articleDetailEsService;

    @Test
    public void testGetArticleList() {
        ArticleTotalAndIdList result = articleDetailEsService.getArticleIdList(1, 10, "清风");
        System.out.println(result.getTotal());
        System.out.println(result.getArticleIdList());
    }

}
