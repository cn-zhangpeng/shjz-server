package com.shjz.zp95sky.shjz.server.blog.biz.impl;

import cn.hutool.core.lang.Snowflake;
import com.shjz.zp95sky.shjz.server.blog.biz.ArticleCategoryBiz;
import com.shjz.zp95sky.shjz.server.blog.domain.ArticleCategoryListDo;
import com.shjz.zp95sky.shjz.server.blog.dto.AddCategoryDto;
import com.shjz.zp95sky.shjz.server.blog.dto.UpdateCategoryDto;
import com.shjz.zp95sky.shjz.server.blog.entity.ArticleCategory;
import com.shjz.zp95sky.shjz.server.blog.service.ArticleCategoryService;
import com.shjz.zp95sky.shjz.server.common.response.BaseResult;
import com.shjz.zp95sky.shjz.server.common.response.ResultUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__({ @Autowired}))
public class ArticleCategoryBizImpl implements ArticleCategoryBiz {

    private final ArticleCategoryService articleCategoryService;

    private final Snowflake snowflake;

    @Override
    public BaseResult<List<ArticleCategoryListDo>> getCategoryList() {
        List<ArticleCategory> categoryList = articleCategoryService.list();
        return buildArticleCategoryListDo(categoryList);
    }

    @Override
    public BaseResult<Void> updateCategory(Long categoryId, UpdateCategoryDto categoryDto) {
        ArticleCategory category = ArticleCategory.builder()
                .id(categoryId).categoryName(categoryDto.getCategoryName())
                .createTime(LocalDateTime.now())
                .build();
        return articleCategoryService.updateById(category) ? ResultUtil.buildResultSuccess() : ResultUtil.buildGeneralResultError();
    }

    @Override
    public BaseResult<Void> deleteCategory(Long categoryId) {
        return articleCategoryService.removeById(categoryId) ? ResultUtil.buildResultSuccess() : ResultUtil.buildGeneralResultError();
    }

    @Override
    public BaseResult<Void> addCategory(AddCategoryDto categoryDto) {
        ArticleCategory category = ArticleCategory.builder()
                .id(snowflake.nextId()).categoryName(categoryDto.getCategoryName())
                .createTime(LocalDateTime.now())
                .build();
        return articleCategoryService.save(category) ? ResultUtil.buildResultSuccess() : ResultUtil.buildGeneralResultError();
    }

    private BaseResult<List<ArticleCategoryListDo>> buildArticleCategoryListDo(List<ArticleCategory> categoryList) {
        List<ArticleCategoryListDo> categoryListDoList = new ArrayList<>();

        if (CollectionUtils.isEmpty(categoryList)) {
            return ResultUtil.buildResultSuccess(categoryListDoList);
        }

        categoryList.forEach(c -> {
            ArticleCategoryListDo categoryListDo = ArticleCategoryListDo.builder()
                    .categoryId(c.getId()).categoryName(c.getCategoryName()).createTime(c.getCreateTime())
                    .build();
            categoryListDoList.add(categoryListDo);
        });

        return ResultUtil.buildResultSuccess(categoryListDoList);
    }

}
