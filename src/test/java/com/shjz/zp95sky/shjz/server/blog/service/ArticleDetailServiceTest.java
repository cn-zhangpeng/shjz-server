package com.shjz.zp95sky.shjz.server.blog.service;

import com.shjz.zp95sky.shjz.server.annotation.CommonTestAnnotation;
import com.shjz.zp95sky.shjz.server.blog.domain.ArticleDetailDo;
import com.shjz.zp95sky.shjz.server.blog.domain.ArticleListDo;
import com.shjz.zp95sky.shjz.server.common.entity.CustomPage;
import com.shjz.zp95sky.shjz.server.common.response.BasePageResult;
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
        BasePageResult<ArticleListDo> result = articleDetailService.getArticleList(customPage);

        printArticleListInfo(result);
    }

    @Test
    public void testGetArticleListByCategory() {
        CustomPage customPage = CustomPage.builder().page(1).size(10).build();
        BasePageResult<ArticleListDo> result = articleDetailService.getArticleListByCategory(1558686897201L, customPage);

        printArticleListInfo(result);
    }

    @Test
    public void testGetArticleById() {
        ArticleDetailDo result = articleDetailService.getArticleById(1L);
        System.out.println(result);
    }

    private void printArticleListInfo(BasePageResult<ArticleListDo> result) {
        System.out.println("page: " + result.getPage());
        System.out.println("size: " + result.getSize());
        System.out.println("total: " + result.getTotal());

        List<ArticleListDo> articleListDoList = result.getData();
        if (! CollectionUtils.isEmpty(articleListDoList)) {
            articleListDoList.forEach(System.out::println);
        }
    }

}
