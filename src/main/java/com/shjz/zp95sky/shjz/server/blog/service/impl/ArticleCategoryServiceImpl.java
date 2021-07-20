package com.shjz.zp95sky.shjz.server.blog.service.impl;

import cn.hutool.core.lang.Snowflake;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shjz.zp95sky.shjz.server.blog.domain.ArticleCategoryListDo;
import com.shjz.zp95sky.shjz.server.blog.dto.AddCategoryDto;
import com.shjz.zp95sky.shjz.server.blog.dto.UpdateCategoryDto;
import com.shjz.zp95sky.shjz.server.blog.entity.ArticleCategory;
import com.shjz.zp95sky.shjz.server.blog.mapper.ArticleCategoryMapper;
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

/**
 * 文章类型业务实现
 * @author 华夏紫穹
 */
@Service
@RequiredArgsConstructor(onConstructor = @__({ @Autowired}))
public class ArticleCategoryServiceImpl extends ServiceImpl<ArticleCategoryMapper, ArticleCategory>
        implements ArticleCategoryService {

    private final Snowflake snowflake;

    @Override
    public BaseResult<List<ArticleCategoryListDo>> getCategoryList() {
        List<ArticleCategory> categoryList = list();
        return buildArticleCategoryListDo(categoryList);
    }

    @Override
    public BaseResult<Void> updateCategory(Long categoryId, UpdateCategoryDto categoryDto) {
        ArticleCategory category = ArticleCategory.builder()
                .id(categoryId).categoryName(categoryDto.getCategoryName())
                .createTime(LocalDateTime.now())
                .build();
        return updateById(category) ? ResultUtil.buildResultSuccess() : ResultUtil.buildGeneralResultError();
    }

    @Override
    public BaseResult<Void> deleteCategory(Long categoryId) {
        return removeById(categoryId) ? ResultUtil.buildResultSuccess() : ResultUtil.buildGeneralResultError();
    }

    @Override
    public BaseResult<Void> addCategory(AddCategoryDto categoryDto) {
        ArticleCategory category = ArticleCategory.builder()
                .id(snowflake.nextId()).categoryName(categoryDto.getCategoryName())
                .createTime(LocalDateTime.now())
                .build();
        return save(category) ? ResultUtil.buildResultSuccess() : ResultUtil.buildGeneralResultError();
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
