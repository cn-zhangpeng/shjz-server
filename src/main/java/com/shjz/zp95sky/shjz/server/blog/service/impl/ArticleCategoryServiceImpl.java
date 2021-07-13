package com.shjz.zp95sky.shjz.server.blog.service.impl;

import cn.hutool.core.lang.Snowflake;
import com.shjz.zp95sky.shjz.server.blog.domain.ArticleCategoryListDo;
import com.shjz.zp95sky.shjz.server.blog.dto.AddCategoryDto;
import com.shjz.zp95sky.shjz.server.blog.dto.UpdateCategoryDto;
import com.shjz.zp95sky.shjz.server.blog.entity.ArticleCategory;
import com.shjz.zp95sky.shjz.server.blog.mapper.CategoryMapper;
import com.shjz.zp95sky.shjz.server.blog.service.ArticleCategoryService;
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
public class ArticleCategoryServiceImpl implements ArticleCategoryService {

    private final CategoryMapper categoryMapper;
    private final Snowflake snowflake;

    @Override
    public List<ArticleCategoryListDo> getCategoryList() {
        List<ArticleCategory> categoryList = selectAllArticleCategory();
        return handleCategoryListData(categoryList);
    }

    @Override
    public boolean updateCategory(Long categoryId, UpdateCategoryDto categoryDto) {
        ArticleCategory category = ArticleCategory.builder()
                .id(categoryId).categoryName(categoryDto.getCategoryName())
                .createTime(LocalDateTime.now())
                .build();
        return updateCategoryById(category) == 1;
    }

    @Override
    public boolean deleteCategory(Long categoryId) {
        return deleteCategoryById(categoryId) == 1;
    }

    @Override
    public boolean addCategory(AddCategoryDto categoryDto) {
        ArticleCategory category = ArticleCategory.builder()
                .id(snowflake.nextId()).categoryName(categoryDto.getCategoryName())
                .createTime(LocalDateTime.now())
                .build();
        return insertCategory(category) == 1;
    }

    private int insertCategory(ArticleCategory category) {
        return categoryMapper.insert(category);
    }

    private int deleteCategoryById(Long categoryId) {
        return categoryMapper.deleteById(categoryId);
    }

    private int updateCategoryById(ArticleCategory category) {
        return categoryMapper.updateById(category);
    }

    private List<ArticleCategoryListDo> handleCategoryListData(List<ArticleCategory> categoryList) {
        List<ArticleCategoryListDo> categoryListDoList = new ArrayList<>();

        if (CollectionUtils.isEmpty(categoryList)) { return categoryListDoList; }

        categoryList.forEach(c -> {
            ArticleCategoryListDo categoryListDo = ArticleCategoryListDo.builder()
                    .categoryId(c.getId()).categoryName(c.getCategoryName()).createTime(c.getCreateTime())
                    .build();
            categoryListDoList.add(categoryListDo);
        });

        return categoryListDoList;
    }

    private List<ArticleCategory> selectAllArticleCategory() {
        return categoryMapper.selectList(null);
    }

}
