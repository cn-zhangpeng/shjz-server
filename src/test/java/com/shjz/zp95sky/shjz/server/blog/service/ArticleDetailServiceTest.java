package com.shjz.zp95sky.shjz.server.blog.service;

import com.shjz.zp95sky.shjz.server.annotation.CommonTestAnnotation;
import com.shjz.zp95sky.shjz.server.blog.biz.ArticleDetailBiz;
import com.shjz.zp95sky.shjz.server.blog.domain.ArticleDetailDo;
import com.shjz.zp95sky.shjz.server.blog.domain.ArticleListDo;
import com.shjz.zp95sky.shjz.server.common.entity.CustomPage;
import com.shjz.zp95sky.shjz.server.common.response.BaseResult;
import org.junit.jupiter.api.Test;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 文章详情 Service 测试
 * @author 山海紫穹
 */
@CommonTestAnnotation
public class ArticleDetailServiceTest {

    @Resource
    private ArticleDetailBiz articleDetailBiz;

    @Test
    public void testGetArticleList() {
        CustomPage customPage = CustomPage.builder().page(1).pageSize(10).build();
        BaseResult<List<ArticleListDo>> result = articleDetailBiz.getArticleList(customPage);

        printArticleListInfo(result);
    }

    @Test
    public void testGetArticleListByCategory() {
        CustomPage customPage = CustomPage.builder().page(1).pageSize(10).build();
        BaseResult<List<ArticleListDo>> result = articleDetailBiz.getArticleListByCategory(1558686897201L, customPage);

        printArticleListInfo(result);
    }

    @Test
    public void testGetArticleById() {
        BaseResult<ArticleDetailDo> result = articleDetailBiz.getArticleById(1L);
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
