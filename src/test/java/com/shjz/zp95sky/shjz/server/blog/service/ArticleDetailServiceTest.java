package com.shjz.zp95sky.shjz.server.blog.service;

import com.shjz.zp95sky.shjz.server.annotation.CommonTestAnnotation;
import com.shjz.zp95sky.shjz.server.blog.domain.ArticleDetailDo;
import com.shjz.zp95sky.shjz.server.blog.domain.ArticleListDo;
import com.shjz.zp95sky.shjz.server.common.entity.CustomPage;
import com.shjz.zp95sky.shjz.server.common.response.BasePageData;
import com.shjz.zp95sky.shjz.server.common.response.BaseResult;
import org.junit.jupiter.api.Test;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 文章详情 Service 测试
 * @author 华夏紫穹
 */
@CommonTestAnnotation
public class ArticleDetailServiceTest {

    @Resource
    private ArticleDetailService articleDetailService;

    @Test
    public void testGetArticleList() {
        CustomPage customPage = CustomPage.builder().page(1).size(10).build();
        BaseResult<List<ArticleListDo>> result = articleDetailService.getArticleList(customPage);

        printArticleListInfo(result);
    }

    @Test
    public void testGetArticleListByCategory() {
        CustomPage customPage = CustomPage.builder().page(1).size(10).build();
        BaseResult<List<ArticleListDo>> result = articleDetailService.getArticleListByCategory(1558686897201L, customPage);

        printArticleListInfo(result);
    }

    @Test
    public void testGetArticleById() {
        BaseResult<ArticleDetailDo> result = articleDetailService.getArticleById(1L);
        System.out.println(result);
    }

    private void printArticleListInfo(BaseResult<List<ArticleListDo>> result) {
        System.out.println("page: " + result.getPageData().getPage());
        System.out.println("size: " + result.getPageData().getPageSize());
        System.out.println("total: " + result.getPageData().getTotal());

        List<ArticleListDo> articleListDoList = result.getData();
        if (! CollectionUtils.isEmpty(articleListDoList)) {
            articleListDoList.forEach(System.out::println);
        }
    }

}
