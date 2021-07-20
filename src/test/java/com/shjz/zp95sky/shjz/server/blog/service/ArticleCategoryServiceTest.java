package com.shjz.zp95sky.shjz.server.blog.service;

import com.shjz.zp95sky.shjz.server.annotation.CommonTestAnnotation;
import com.shjz.zp95sky.shjz.server.blog.domain.ArticleCategoryListDo;
import com.shjz.zp95sky.shjz.server.common.response.BaseResult;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * 文章类型 Service 测试
 * @author 华夏紫穹
 */
@CommonTestAnnotation
public class ArticleCategoryServiceTest {

    @Resource
    private ArticleCategoryService categoryService;

    @Test
    public void testGetCategoryList() {
        BaseResult<List<ArticleCategoryListDo>> result = categoryService.getCategoryList();
        result.getData().forEach(System.out::println);
    }

}
