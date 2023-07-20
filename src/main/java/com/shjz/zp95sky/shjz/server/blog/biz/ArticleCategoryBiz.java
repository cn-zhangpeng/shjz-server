package com.shjz.zp95sky.shjz.server.blog.biz;

import com.shjz.zp95sky.shjz.server.blog.domain.ArticleCategoryListDo;
import com.shjz.zp95sky.shjz.server.blog.dto.AddCategoryDto;
import com.shjz.zp95sky.shjz.server.blog.dto.UpdateCategoryDto;
import com.shjz.zp95sky.shjz.server.common.response.BaseResult;

import java.util.List;

/**
 * 文章类型业务处理
 */
public interface ArticleCategoryBiz {

    /**
     * 查询所有文章类型
     * @return 文章类型列表
     */
    BaseResult<List<ArticleCategoryListDo>> getCategoryList();

    /**
     * 更新文章类型
     * @param categoryId 文章类型ID
     * @param categoryDto 文章类型数据
     * @return 更新结果
     */
    BaseResult<Void> updateCategory(Long categoryId, UpdateCategoryDto categoryDto);

    /**
     * 删除文章类型
     * @param categoryId 类型 ID
     * @return 删除结果
     */
    BaseResult<Void> deleteCategory(Long categoryId);

    /**
     * 添加文章类型
     * @param categoryDto 文章类型数据
     * @return 添加结果
     */
    BaseResult<Void> addCategory(AddCategoryDto categoryDto);

}
