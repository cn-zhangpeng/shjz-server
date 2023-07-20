package com.shjz.zp95sky.shjz.server.blog.service;

import com.shjz.zp95sky.shjz.server.annotation.CommonTestAnnotation;
import com.shjz.zp95sky.shjz.server.blog.biz.ArticleCategoryBiz;
import com.shjz.zp95sky.shjz.server.blog.domain.ArticleCategoryListDo;
import com.shjz.zp95sky.shjz.server.common.response.BaseResult;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * 文章类型 Service 测试
 * @author 山海紫穹
 */
@CommonTestAnnotation
public class ArticleCategoryServiceTest {

    @Resource
    private ArticleCategoryBiz categoryBiz;

    @Test
    public void testGetCategoryList() {
        BaseResult<List<ArticleCategoryListDo>> result = categoryBiz.getCategoryList();
        result.getData().forEach(System.out::println);
    }

}
