package com.shjz.zp95sky.shjz.server.blog.service;

import com.shjz.zp95sky.shjz.server.blog.domain.ArticleCategoryListDo;
import com.shjz.zp95sky.shjz.server.blog.dto.AddCategoryDto;
import com.shjz.zp95sky.shjz.server.blog.dto.UpdateCategoryDto;

import java.util.List;

/**
 * 文章类型业务
 * @author 华夏紫穹
 */
public interface ArticleCategoryService {

    /**
     * 查询所有文章类型
     * @return {@link ArticleCategoryListDo} 文章类型列表
     */
    List<ArticleCategoryListDo> getCategoryList();

    /**
     * 更新文章类型
     * @param categoryId 文章类型ID
     * @param categoryDto 文章类型数据
     * @return 更新成功，返回 true，否则返回 false
     */
    boolean updateCategory(Long categoryId, UpdateCategoryDto categoryDto);

    /**
     * 删除文章类型
     * @param categoryId 类型 ID
     * @return 删除成功，返回 true，否则返回 false
     */
    boolean deleteCategory(Long categoryId);

    /**
     * 添加文章类型
     * @param categoryDto 文章类型数据
     * @return 添加成功，返回 true，否则返回 false
     */
    boolean addCategory(AddCategoryDto categoryDto);

}
